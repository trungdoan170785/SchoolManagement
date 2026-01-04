# 🎓 School Management System

A web-based School Management System built with **Spring Boot**, **Thymeleaf**, **Spring Security**, and **MySQL**.  
The system supports managing students, teachers, enrollments, attendance, payments, notifications, and user authentication.

---

## 🚀 Features

### 👨‍🏫 Academic Management
- Manage **Students**
- Manage **Teachers**
- Manage **Courses & Class Groups**
- Student **Enrollment**

### 📅 Attendance
- Mark attendance by class
- Status types: `PRESENT`, `ABSENT`, `LATE`, `EXCUSED`
- View attendance history by date
- Auto-load previous attendance when reopening the page

### 💰 Payment
- Record student tuition fee payments
- Track amount, date, and method
- Link payments to students

### 🔔 Notification
- Send internal notifications
- Support long content
- Linked to sender (User)

### 📊 Reports & Dashboard
- Student statistics
- Payment summaries
- Attendance summary
- Dynamic dashboard view

### 🔐 Security (Spring Security)
- User authentication
- Role-based authorization
- Users have roles such as:
  - `ROLE_ADMIN`
  - `ROLE_TEACHER`
  - `ROLE_STAFF`
  - `ROLE_ACCOUNTANT`
- Login / Logout
- Show logged-in user in navbar
- Admin can create accounts for existing:
  - Students
  - Teachers
  - Staff

---

## 🛠️ Tech Stack

| Layer | Technology |
|------|-----------|
| Backend | Spring Boot 4 / Java 17 |
| View | Thymeleaf + AdminLTE |
| Security | Spring Security 6 |
| Database | MySQL |
| ORM | Spring Data JPA |
| Build Tool | Gradle |
| Template Security | thymeleaf-extras-springsecurity6 |

---
