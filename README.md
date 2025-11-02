💰 Money Manager Backend

The Money Manager Backend is a Spring Boot–based REST API designed to help users efficiently manage their personal finances.
It provides secure endpoints for tracking income, expenses, categories, and profile information — with support for analytics and notifications.

🚀 Overview

This backend forms the foundation of the Money Manager application, allowing users to record transactions, categorize spending, and view financial summaries.
It is built with a modular service structure to keep business logic clean, testable, and scalable.

⚙️ Core Services
Service	Description
AppUserDetailsService	Handles user authentication, authorization, and integration with Spring Security.
CategoryService	Manages CRUD operations for income and expense categories.
DashboardService	Aggregates user financial data, providing overviews such as total income, total expenses, and savings summaries.
EmailService	Sends transactional or reminder emails (e.g., expense alerts or monthly summaries).
ExpenseService	Handles expense-related CRUD operations and business logic.
IncomeService	Manages income entries, categories, and statistics.
NotificationService	Sends and manages user notifications for reminders, limits, or insights.
ProfileService	Handles user profile information (name, email, preferences, etc.).
🧩 Key Features

🔐 Secure Authentication – Integrated with Spring Security for login and access control.

💸 Expense & Income Tracking – Log and categorize transactions effortlessly.

📊 Dashboard Analytics – View total savings, expense patterns, and spending ratios.

📬 Email & Notifications – Automated reminders and summaries.

🗂️ Category Management – Organize income and expenses into flexible categories.

👤 Profile Management – Update personal details and preferences.

🏗️ Tech Stack

Java 21

Spring Boot 3.x

Spring Security

Spring Data JPA

PostgreSQL / MySQL

Lombok

Maven

🗄️ Database Structure (Simplified)
Table	Description
users	Stores user credentials and profile info.
income	Logs all income records linked to users.
expenses	Logs all expense transactions with category and timestamp.
categories	Stores income and expense categories.
notifications	Stores reminders and notification messages.
🔌 API Overview
Endpoint	Method	Description
/api/income	POST / GET / PUT / DELETE	Manage income entries
/api/expenses	POST / GET / PUT / DELETE	Manage expenses
/api/categories	GET / POST	Retrieve or add categories
/api/dashboard	GET	Fetch summary of income vs expenses
/api/profile	GET / PUT	Get or update user profile
/api/notifications	GET	Retrieve notifications
/api/email	POST	Send email notifications
