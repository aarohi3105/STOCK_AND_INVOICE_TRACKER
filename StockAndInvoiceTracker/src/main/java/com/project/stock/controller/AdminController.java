package com.project.stock.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.stock.dto.InvoiceRequest;
import com.project.stock.entity.Invoice;
import com.project.stock.entity.User;
import com.project.stock.repository.ProductRepository;
import com.project.stock.repository.InvoiceRepository;
import org.springframework.web.bind.annotation.PathVariable;
import com.project.stock.service.InvoiceService;
import com.project.stock.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {

	private final InvoiceService invoiceService;
	private final ProductRepository productRepository;
	private final InvoiceRepository invoiceRepository;
	private final UserService userService;

	@GetMapping("/invoice-page")
	public String invoicePage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			redirectAttributes.addFlashAttribute("error", "Your session has expired. Please log in first.");
			return "redirect:/login";
		}
		model.addAttribute("products", productRepository.findAll());
		// 🔥 Always regenerate invoice number fresh from DB so it's always unique
		model.addAttribute("invoiceNumber", invoiceService.generateInvoiceNumber());
		model.addAttribute("todayDate", LocalDate.now());
		return "invoice/generateInvoice";
	}

	@PostMapping("/invoice")
	public String createInvoice(@ModelAttribute InvoiceRequest request,
								HttpSession session,
								RedirectAttributes redirectAttributes) {
		try {
			User user = (User) session.getAttribute("loggedInUser");
			if (user == null) {
				redirectAttributes.addFlashAttribute("error", "Your session has expired. Please log in first.");
				return "redirect:/login";
			}
			System.out.println("Generating invoice: " + request.getInvoiceNumber() + " for " + request.getCustomerName());
			System.out.println("Product IDs: " + request.getProductIds() + ", Qty: " + request.getQuantities());
			invoiceService.generateInvoice(request, user.getEmail());
			redirectAttributes.addFlashAttribute("success", "Invoice " + request.getInvoiceNumber() + " generated successfully!");
			return "redirect:/invoice-page";
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", e.getMessage() != null ? e.getMessage() : "Error: " + e.toString());
			return "redirect:/invoice-page";
		}
	}

	@GetMapping("/manage-users")
	public String manageUsers(@RequestParam(required = false) String keyword, Model model) {
		if (keyword != null && !keyword.isBlank()) {
			model.addAttribute("users", userService.searchUsers(keyword));
		} else {
			model.addAttribute("users", userService.getAllUsers());
		}
		return "User/manageUser";
	}

	// 🔥 Supports optional keyword search — filtered by cashier role (limited view)
	@GetMapping("/invoice-history")
	public String invoiceHistory(@RequestParam(required = false, defaultValue = "") String keyword,
								 HttpSession session,
								 Model model) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/login";
		}

		List<Invoice> invoices;
		if ("ADMIN".equals(user.getRole())) {
			invoices = invoiceService.getAllInvoices();
		} else {
			invoices = invoiceRepository.findByCreatedBy(user);
		}

		if (!keyword.isBlank()) {
			String kw = keyword.toLowerCase();
			invoices = invoices.stream()
					.filter(inv ->
							(inv.getCustomerName() != null && inv.getCustomerName().toLowerCase().contains(kw)) ||
									(inv.getInvoiceNumber() != null && inv.getInvoiceNumber().toLowerCase().contains(kw))
					)
					.collect(Collectors.toList());
		}

		model.addAttribute("invoices", invoices);
		model.addAttribute("keyword", keyword);
		return "invoice/invoiceHistory";
	}

	@GetMapping("/invoice/view/{id}")
	public String viewInvoice(@PathVariable Long id, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/login";
		}
		Invoice invoice = invoiceRepository.findById(id).orElse(null);
		if (invoice == null) {
			redirectAttributes.addFlashAttribute("error", "Invoice not found!");
			return "redirect:/invoice-history";
		}
		// Cashier can only view their own invoices
		if (!"ADMIN".equals(user.getRole()) && !invoice.getCreatedBy().getId().equals(user.getId())) {
			redirectAttributes.addFlashAttribute("error", "Unauthorized access!");
			return "redirect:/invoice-history";
		}
		model.addAttribute("invoice", invoice);
		return "invoice/viewInvoice";
	}

	@GetMapping("/invoice/delete/{id}")
	@org.springframework.transaction.annotation.Transactional
	public String deleteInvoice(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/login";
		}
		if (!"ADMIN".equals(user.getRole())) {
			redirectAttributes.addFlashAttribute("error", "Only admins are authorized to delete invoices.");
			return "redirect:/invoice-history";
		}
		Invoice invoice = invoiceRepository.findById(id).orElse(null);
		if (invoice == null) {
			redirectAttributes.addFlashAttribute("error", "Invoice not found!");
			return "redirect:/invoice-history";
		}
		invoiceRepository.delete(invoice);
		redirectAttributes.addFlashAttribute("success", "Invoice " + invoice.getInvoiceNumber() + " deleted successfully!");
		return "redirect:/invoice-history";
	}

	@GetMapping("/manage-inventory")
	public String inventoryDashboard(HttpSession session, RedirectAttributes redirectAttributes) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/login";
		}
		// Restrict inventory dashboard to admin only
		if (!"ADMIN".equals(user.getRole())) {
			redirectAttributes.addFlashAttribute("error", "Access denied: Admins only.");
			return "redirect:/dashboard";
		}
		return "Inventory/ManageInventory";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
//package com.project.stock.controller;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import com.project.stock.entity.User;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.project.stock.dto.InvoiceRequest;
//import com.project.stock.entity.Invoice;
//import com.project.stock.repository.ProductRepository;
//import com.project.stock.service.InvoiceService;
//import com.project.stock.service.UserService;
//
//import lombok.RequiredArgsConstructor;
//
//@Controller
//@RequiredArgsConstructor
//public class AdminController {
//
//	private final InvoiceService invoiceService;
//	private final ProductRepository productRepository;
//	private final UserService userService;
//
//	@GetMapping("/invoice-page")
//	public String invoicePage(Model model) {
//		model.addAttribute("products", productRepository.findAll());
//		model.addAttribute("invoiceNumber", invoiceService.generateInvoiceNumber());
//		model.addAttribute("todayDate", LocalDate.now());
//		model.addAttribute("dashboardUrl", "/admin/dashboard"); // simplified
//		return "invoice/generateInvoice";
//	}
//
//	@PostMapping("/invoice")
//	public String createInvoice(@ModelAttribute InvoiceRequest request,
//								HttpSession session)
//								 {
//		try {
//			User user = (User) session.getAttribute("loggedInUser");
//			invoiceService.generateInvoice(request ,user.getEmail()); // ✅ NO EMAIL
//
//			//redirectAttributes.addFlashAttribute("success",
//					//"Invoice " + request.getInvoiceNumber() + " generated successfully!");
//			return "redirect:/invoice-page";
//		} catch (Exception e) {
//			//redirectAttributes.addFlashAttribute("error", e.getMessage());
//			return "redirect:/invoice-page";
//		}
//	}
//
//	@GetMapping("/manage-users")
//	public String manageUsers(Model model) {
//		model.addAttribute("users", userService.getAllUsers());
//		return "User/manageUser";
//	}
//
//	@GetMapping("/invoice-history")
//	public String invoiceHistory(@RequestParam(required = false, defaultValue = "") String keyword,
//								 Model model) {
//
//		List<Invoice> all = invoiceService.getAllInvoices();
//
//		if (!keyword.isBlank()) {
//			String kw = keyword.toLowerCase();
//			all = all.stream()
//					.filter(inv ->
//							(inv.getCustomerName() != null && inv.getCustomerName().toLowerCase().contains(kw)) ||
//									(inv.getInvoiceNumber() != null && inv.getInvoiceNumber().toLowerCase().contains(kw))
//					)
//					.collect(Collectors.toList());
//		}
//
//		model.addAttribute("invoices", all);
//		model.addAttribute("keyword", keyword);
//		return "invoice/invoiceHistory";
//	}
//
//	@GetMapping("/manage-inventory")
//	public String inventoryDashboard() {
//		return "Inventory/ManageInventory";
//	}
//
//	@GetMapping("/logout")
//	public String logout(jakarta.servlet.http.HttpSession session) {
//		session.invalidate();
//		return "redirect:/login";
//	}
//}