package com.project.stock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.stock.entity.Product;
import com.project.stock.service.InventoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // 🔍 Search Product
    @GetMapping("/search")
    public String searchProduct(@RequestParam(required = false, defaultValue = "") String keyword, Model model) {
        List<Product> results = inventoryService.searchByName(keyword);
        model.addAttribute("products", results);
        model.addAttribute("keyword", keyword);
        return "Inventory/Searchproduct";
    }

    // 📦 All Products
    @GetMapping({"", "/"})
    public String allProducts(Model model) {
        List<Product> products = inventoryService.getAllProducts();
        model.addAttribute("products", products);
        return "Inventory/Searchproduct";
    }

    // 📊 Inventory Report
    @GetMapping("/report")
    public String inventoryReport(Model model) {
        List<Product> products = inventoryService.getAllProducts();
        long totalProducts = products.size();
        long outOfStock = products.stream().filter(p -> p.getQuantity() == 0).count();
        long lowStock = products.stream().filter(p -> p.getQuantity() > 0 && p.getQuantity() <= p.getLowStockThreshold()).count();
        double totalValue = products.stream().mapToDouble(p -> p.getPrice() * p.getQuantity()).sum();

        model.addAttribute("products", products);
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("outOfStock", outOfStock);
        model.addAttribute("lowStock", lowStock);
        model.addAttribute("totalValue", String.format("%.2f", totalValue));
        return "Inventory/Inventoryreport";
    }

    // ❌ Out of Stock
    @GetMapping("/out-of-stock")
    public String outOfStock(Model model) {
        List<Product> products = inventoryService.getOutOfStockProducts();
        model.addAttribute("products", products);
        return "Inventory/Outofstock";
    }

    // ⚠ Low Stock
    @GetMapping("/low-stock")
    public String lowStock(Model model) {
        List<Product> products = inventoryService.getLowStockProducts();
        model.addAttribute("products", products);
        return "Inventory/Lowstock";
    }

    // ➕ Add Product Form
    @GetMapping({"/addproduct", "/add-product"})
    public String showAddProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "Inventory/addProduct";
    }

    // 🔹 Save product
    @PostMapping({"/addproduct", "/add-product"})
    public String addProduct(@ModelAttribute Product product,
                             RedirectAttributes redirectAttributes) {

        try {
            inventoryService.saveProduct(product);
            redirectAttributes.addFlashAttribute("success", "Product added successfully!");
            return "redirect:/manage-inventory";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/inventory/addproduct";
        }
    }

    // ✏️ Edit Product Form
    @GetMapping("/edit/{id}")
    public String showEditProductPage(@PathVariable Long id, Model model,
                                      RedirectAttributes redirectAttributes) {
        return inventoryService.getProductById(id).map(product -> {
            model.addAttribute("product", product);
            return "Inventory/editProduct";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("error", "Product not found!");
            return "redirect:/inventory/search";
        });
    }

    // 🔹 Update product
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute Product product,
                                RedirectAttributes redirectAttributes) {
        try {
            product.setId(id);
            inventoryService.saveProduct(product);
            redirectAttributes.addFlashAttribute("success", "Product \"" + product.getName() + "\" updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/inventory/search";
    }

    // 🗑️ Delete product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        try {
            inventoryService.getProductById(id).ifPresentOrElse(
                product -> {
                    inventoryService.deleteProduct(id);
                    redirectAttributes.addFlashAttribute("success", "Product \"" + product.getName() + "\" deleted successfully!");
                },
                () -> redirectAttributes.addFlashAttribute("error", "Product not found!")
            );
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Could not delete product: " + e.getMessage());
        }
        return "redirect:/inventory/search";
    }
}