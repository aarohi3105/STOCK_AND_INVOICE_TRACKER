# 📦 Stock And Invoice Tracker

## 📌 Project Overview

The Stock and Invoice Tracker is a full-stack web application developed to simplify inventory tracking, billing, invoice generation, and sales management for businesses and shops.

The system provides an attractive admin dashboard where users can manage products, monitor stock levels, generate invoices, track sales analytics, and manage system users efficiently.

This project helps reduce manual work, improves billing accuracy, and provides real-time inventory insights.

---

# 🚀 Features & Functionalities

## 🔐 Authentication & Authorization
- User Login System
- Session Management
- Role-Based Access
  - Admin
  - Cashier

---

# 👨‍💼 Admin Features

## 📊 Dashboard
- Total Sales Overview
- Total Invoices
- Total Products
- Low Stock Count
- Monthly Sales Chart
- Recent Activities
- Inventory Summary

---

## 👥 User Management
- Add User
- Edit User
- Delete User
- Search User
- Role Management

---

## 📦 Inventory Management

### ✅ Product Features
- Add Product
- Edit Product
- Soft Delete Product
- View All Products
- Search Products
- Product Categories
- Supplier Management

---

## ⚠ Stock Monitoring
- Low Stock Products
- Out of Stock Products
- Inventory Reports
- Top Expensive Products
- Top Selling Products

---

## 🧾 Invoice Management
- Generate Professional Invoice
- Live Invoice Preview
- Automatic Total Calculation
- Invoice History
- Printable Invoice
- Payment Status

---

## 📈 Sales Analytics
- Monthly Sales Chart
- Revenue Tracking
- Product Sales Insights

---

# 🛠 Technology Stack & Tools Used

## 💻 Frontend
- HTML5
- CSS3
- JavaScript
- Thymeleaf
- Chart.js

---

## ⚙ Backend
- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate

---

## 🗄 Database
- MySQL

---

## 🧰 Tools & Platforms
- Spring Tool Suite (STS)
- Maven
- Git & GitHub
- Postman
- MySQL Workbench

---

# 🏗 Project Architecture

The project follows the MVC Architecture:

- Controller Layer → Handles requests
- Service Layer → Business logic
- Repository Layer → Database operations
- Entity Layer → Database models
- View Layer → Thymeleaf frontend pages

---

# 📂 Modules Included

| Module | Description |
|--------|-------------|
| Authentication | Login & Session Handling |
| Dashboard | Sales & Inventory Analytics |
| User Management | CRUD Operations for Users |
| Inventory Management | Product & Stock Management |
| Invoice Management | Invoice Generation & History |
| Reports | Inventory & Sales Reports |

---

# ⚡ Installation & Execution Steps

## 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/inventory-invoice-system.git
```

---

## 2️⃣ Open Project

Open the project in:
- Spring Tool Suite (STS)
- IntelliJ IDEA
- Eclipse

---

## 3️⃣ Configure Database

Create a MySQL database:

```sql
CREATE DATABASE inventory_system;
```

---

## 4️⃣ Update application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_system
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 5️⃣ Install Maven Dependencies

```bash
mvn clean install
```

---

## 6️⃣ Run Application

```bash
mvn spring-boot:run
```

OR run:

```text
StockAndInvoiceTrackerApplication.java
```

---

## 7️⃣ Open Browser

```text
http://localhost:8080
```

---

# 📸 Project Screenshots / Output

## 🔹 Login Page
- Secure login interface with role-based authentication.

---

## 🔹 Admin Dashboard
- Analytics cards
- Monthly sales chart
- Low stock overview
- Inventory summary

---

## 🔹 Manage Users
- Add/Edit/Delete/Search users

---

## 🔹 Inventory Management
- Product cards
- Low stock products
- Inventory reports

---

## 🔹 Invoice Generation
- Live invoice preview
- Professional invoice UI
- Print invoice functionality

---

# 👨‍💻 Team Members

| Name | Role |
|------|------|
| Aarohi Jain |  Frontend Development |
| Aarohi jain |  Backend Development |

---

# 🎯 Future Enhancements

- PDF Invoice Download
- Email Invoice Feature
- GST Calculation
- Barcode/QR Integration
- Notification System
- Dark/Light Theme
- Cloud Deployment
- REST API Integration
- Payment Integration
---

# 📌 Conclusion

The Inventory & Invoice Management System successfully automates inventory tracking and invoice generation processes while providing a modern dashboard and professional user experience.

The project demonstrates:
- Full Stack Development
- MVC Architecture
- Database Management
- Session Handling
- CRUD Operations
- Dynamic UI Development

---

# ⭐ Developed Using
- Spring Boot
- Thymeleaf
- MySQL
- HTML/CSS/JavaScript
- 
- <img width="1906" height="937" alt="image" src="https://github.com/user-attachments/assets/05da3193-4111-4130-aa08-e75a4f3901d6" />

