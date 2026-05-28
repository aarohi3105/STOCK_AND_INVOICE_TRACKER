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
<img width="1906" height="937" alt="image" src="https://github.com/user-attachments/assets/55770330-b00e-4eca-a6fb-fad0a98f6c6d" />

---

## 🔹 Admin Dashboard
- Analytics cards
- Monthly sales chart
- Low stock overview
- Inventory summary
<img width="1917" height="963" alt="image" src="https://github.com/user-attachments/assets/419d3ad7-041c-4071-855c-87f4922e46ac" />

---

## 🔹 Manage Users
- Add/Edit/Delete/Search users
<img width="1919" height="912" alt="image" src="https://github.com/user-attachments/assets/b706c431-837b-461e-a3ef-6e0c4ecd3892" />

---

## 🔹 Inventory Management
- Product cards
- Low stock products
- Inventory reports
<img width="1919" height="908" alt="image" src="https://github.com/user-attachments/assets/f5ae3cba-72de-4872-9075-f9a14636f144" />
<img width="799" height="600" alt="image" src="https://github.com/user-attachments/assets/502d1828-e26c-462c-8b12-dd3eab0c4637" />
<img width="1917" height="906" alt="image" src="https://github.com/user-attachments/assets/c839a79e-58d5-45be-b63a-13f73701a499" />
<img width="1919" height="902" alt="image" src="https://github.com/user-attachments/assets/1746b804-9079-446e-807f-e446cc0312c8" />
<img width="1916" height="908" alt="image" src="https://github.com/user-attachments/assets/e78ba679-48fd-4dd2-bb25-5e99e76c48ba" />
<img width="1919" height="901" alt="image" src="https://github.com/user-attachments/assets/a50d6f48-304a-41fe-8096-702ec07cb5d0" />
<img width="1919" height="907" alt="image" src="https://github.com/user-attachments/assets/93210980-cb50-48ca-8542-163b7c766eaa" />







---

## 🔹 Invoice Generation
- Live invoice preview
- Professional invoice UI
- Print invoice functionality
<img width="1912" height="850" alt="image" src="https://github.com/user-attachments/assets/5d835150-e895-45e0-838d-06caa295eb6e" />
<img width="1913" height="731" alt="image" src="https://github.com/user-attachments/assets/6933b50a-47dd-4b16-8e6b-1f4b3f8a41ae" />


---

# 👨‍💻 Team Members

| Name | Role |
|------|------|
| Aarohi Jain |  Frontend Development |
| Aashi jain |  Backend Development |

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
  
# Project Report
[StockAndInvoiceTracker_Report MiniProject.pdf](https://github.com/user-attachments/files/28349539/StockAndInvoiceTracker_Report.MiniProject.pdf)

