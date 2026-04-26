# Inventory Management System 📦

A robust and modern Inventory Management System built with **Spring Boot**, **Thymeleaf**, and **Bootstrap 5**. This application allows users to manage their products and categories efficiently with a premium dashboard and secure authentication.

## 🚀 Features

### 📊 Premium Dashboard
- **Real-time Statistics**: Total products, inventory value, and active/inactive status.
- **Visual Analytics**: Interactive bar charts for product distribution per category using **Chart.js**.
- **Low Stock Alerts**: Instant notifications for products with stock levels below 5 units.
- **Glassmorphism Design**: Modern and clean UI with subtle blur effects and gradients.

### 📦 Product Management
- **Full CRUD**: Create, Read, Update, and Delete products.
- **Advanced Filtering**: Search by name and filter by category.
- **Pagination**: Efficiently handle large datasets with 10 items per page.
- **Rupiah Formatting**: Automatic currency formatting for prices.

### 🏷️ Category Management
- Organize products into customizable categories with full CRUD support.
- Paginated category lists for better organization.

### 👤 Profile & Security
- **Authentication**: Secure Login and Registration system.
- **Profile Customization**: Update personal info, bio, and profile picture URL.
- **Account Security**: Secure password change feature with BCrypt encryption.
- **Spring Security**: Robust role-based access control and session management.

## 🛠️ Tech Stack
- **Backend**: Java 21, Spring Boot 3.2.5
- **Frontend**: Thymeleaf, HTML5, CSS3 (Vanilla + Bootstrap 5)
- **Database**: H2 (In-Memory for Dev), MySQL (Ready for Production)
- **Security**: Spring Security (BCrypt Password Encoding)
- **Icons & Visuals**: Bootstrap Icons, Chart.js, Google Fonts (Inter)

## ⚙️ Installation & Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/edwardmarcelhutagaol/web2-ti-sec-a-project-final.git
   cd web2-ti-sec-a-project-final
   ```

2. **Configuration**
   - Copy `.env.example` to `.env` (if using environment variables) or update `src/main/resources/application.properties`.
   - By default, the app uses an **H2 In-Memory Database** (no setup required).
   - To use MySQL, uncomment the MySQL section in `application.properties` and provide your credentials.

3. **Run the Application**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the App**
   - Open your browser and navigate to: `http://localhost:8080`
   - **H2 Console**: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`)

## 🤝 Collaborators
- **Edward Marcel Hutagaol**
- **raakkkaaaa**

## 📄 License
This project is for educational purposes as part of the Web 2 - TI SEC A Project.
