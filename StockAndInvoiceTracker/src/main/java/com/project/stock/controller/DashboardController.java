package com.project.stock.controller;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpSession;
import com.project.stock.entity.User;
import com.project.stock.entity.Invoice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.stock.repository.InvoiceRepository;
import com.project.stock.repository.ProductRepository;
import com.project.stock.service.DashboardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DashboardController {
	
	private final DashboardService dashboardService;
	
	private final InvoiceRepository invoiceRepository;
	
	private final ProductRepository productRepository;
	@GetMapping("/admin/dashboard")
	public String adminDashboard(Model model) {
		Map<String, Object> chartData = dashboardService.getMonthlyChartData();

        model.addAttribute("months", chartData.get("months"));
        System.out.println("Months: " + chartData.get("months")); // 🔥 debug log
        model.addAttribute("sales", chartData.get("sales"));
        System.out.println("Sales: " + chartData.get("sales")); // 🔥 debug log

        // 🔥 Stats
        model.addAttribute("totalInvoices", invoiceRepository.count());
        model.addAttribute("totalProducts", productRepository.count());

        // 🔥 Real DB Data
        model.addAttribute("totalSales", invoiceRepository.getTotalSales());
        model.addAttribute("lowStock", productRepository.findLowStock().size());

        // 🔥 Tables
        model.addAttribute("recentInvoices",
                invoiceRepository.findTop5ByOrderByIdDesc());

        model.addAttribute("lowStockProducts",
                productRepository.findLowStock());

        model.addAttribute("userName", "Admin");

        return "admin-dashboard";
	}

	@GetMapping("/cashier/dashboard")
	public String cashierDashboard(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/login";
		}
		if ("ADMIN".equals(user.getRole())) {
			return "redirect:/admin/dashboard";
		}
		
		double totalSales = invoiceRepository.getTotalSalesByUser(user);
		long totalInvoices = invoiceRepository.countByCreatedBy(user);
		List<Invoice> recentInvoices = invoiceRepository.findTop5ByCreatedByOrderByIdDesc(user);

		model.addAttribute("totalSales", totalSales);
		model.addAttribute("totalInvoices", totalInvoices);
		model.addAttribute("recentInvoices", recentInvoices);
		model.addAttribute("userName", user.getFirstname());

		return "cashier-dashboard";
	}
	
	
}
