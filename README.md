# 🎉 Event Management System - React + Spring Boot + Node.js + Docker

This is a full-stack **Event Management System** built with:
- ⚛️ **React** (Frontend)
- ☕ **Spring Boot** (Backend for Events, Venues, Budgets)
- 🔐 **Node.js + Express** (Authentication Module)
- 🐬 **MySQL** (Database)
- 🐳 **Docker** (Containerized using `docker-compose`)

> 🔐 Supports Role-Based Login (Admin/User) with JWT Authentication  
> 📅 Includes Calendar Scheduling, Budget Tracking, Real-Time Alerts, and more

---

## ✨ Features

### 👥 Authentication (Node.js + Express)
- User/Admin registration and login
- JWT-based secure authentication
- Role-based dashboard redirection

### 🗓️ Event Management (Spring Boot)
- CRUD operations for Events and Venues
- Participant limit alerts on overbooking
- Budget creation and category-wise tracking
- Event scheduling and alert system

### 🎨 Frontend (React)
- Modern UI with Glassmorphism design
- Dashboards for Users and Admins
- Pie Charts for budget visualization
- Calendar-style event viewing

---

## 🧰 Tech Stack

| Layer     | Tech Used               |
|-----------|-------------------------|
| Frontend  | React, Material UI     |
| Backend   | Spring Boot (Java 21+), Node.js, Express |
| Database  | MySQL                   |
| Auth      | JWT, Express Middleware |
| DevOps    | Docker, Docker Compose  |

---

## 🚀 Getting Started with Docker

### ✅ Prerequisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop)
- [Git](https://git-scm.com/)

---

### 🐳 Run the App in Docker

#### 1. Clone the Repository
```bash
git clone https://github.com/SuverchaYadav/event-management-system-react-springboot-dockerized.git
cd event-management-system-react-springboot-dockerized

### 2. Start the containers
docker-compose up --build

### 3. Access the app
Frontend: http://localhost:3000

Backend API (Spring Boot): http://localhost:8080

Auth API (Node.js): http://localhost:5000

MySQL: Accessible on port 3306

🌐 Environment Variables
Make sure the following environment variables are configured properly:

🟦 Auth Service (auth/.env or in Dockerfile):
DB_HOST=mysql
DB_USER=root
DB_PASSWORD=root
DB_NAME=event_management

🟨 Spring Boot Application (application.properties or env):
spring.datasource.url=jdbc:mysql://mysql:3306/event_management
spring.datasource.username=root
spring.datasource.password=root

⚙️ (Optional) Build & Run Locally Without Docker
Useful for debugging or running modules independently.

🟩 React Frontend
cd client
npm install
npm run dev

🟦 Auth Service
cd auth
npm install
node server.js

🟨 Spring Boot Backend
⚠️ Requires Java 21+ and Maven installed.
cd backend
./mvnw clean package
java -jar target/*.jar

🧾 Backend Dependencies (pom.xml highlights)

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
</dependencies>

🤝 Contributors
Suvercha Yadav – GitHub

📜 License
This project is open source and available under the MIT License.

📌 Notes
⚠️ If you're using Java 21+, make sure your Dockerfile uses the correct JDK image.

📦 Your Spring Boot .jar file is ~52MB, which exceeds GitHub’s limit — consider using Git LFS or excluding it via .gitignore.