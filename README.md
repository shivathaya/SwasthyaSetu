# SwasthyaSetu – Community Healthcare Management Platform

## Overview

SwasthyaSetu is a full-stack healthcare management platform designed to digitize and streamline record management for ASHA (Accredited Social Health Activist) workers. The platform eliminates paper-based processes by providing a centralized system to manage child birth records and beneficiary information securely and efficiently.

Built using React and Spring Boot, the application enables healthcare workers to maintain records digitally, improving accessibility, data accuracy, and operational efficiency.


## Features

### Authentication & User Management

* User Registration
* Secure Login
* Password Encryption
* Spring Security Integration
* Session-Based Authentication

### Child Birth Record Management

* Create Child Birth Records
* Store Child Information
* Track Parent Details
* Maintain Village Information
* Record Hospital Details
* View User-Specific Records

### Security

* Spring Security Configuration
* Protected API Endpoints
* User Session Management
* Password Hashing

### Monitoring & Logging

* Log4j2 Integration
* Request and Application Logging

---

## Technology Stack

### Backend

* Java 17
* Spring Boot 3.4
* Spring Security
* Spring Data JPA
* Maven
* REST APIs
* Log4j2

### Frontend

* React 18
* React Router DOM
* Axios
* HTML5
* CSS3
* JavaScript

### Database

* MySQL

### Tools

* Git
* GitHub
* Postman

---

## System Architecture

React Frontend
       |
       v
REST APIs
       |
       v
Spring Boot Backend
       |
       +-------------------+
       | Spring Security   |
       +-------------------+
       |
       v
JPA Repository Layer
       |
       v
MySQL Database

## Database Design

### User Entity

Stores application user information.

Fields:

* id
* username
* password
* roles

### ChildBirth Entity

Stores child birth records.

Fields:

* id
* childName
* birthDate
* birthTime
* parentsName
* villageName
* hospitalName
* userId

---

## API Endpoints

### Authentication

```http
POST /ashaUserAuth/register
POST /ashaUserAuth/login
```

### Child Birth Records

```http
POST /api/childbirths
GET  /api/childbirths
```

---

## Project Structure

SwasthyaSetu
│
├── backend_asha
│   ├── controller
│   ├── model
│   ├── repository
│   ├── security
│   ├── service
│   └── resources
│
├── frontend_asha
│   ├── public
│   ├── src
│   │   ├── components
│   │   ├── services
│   │   └── pages
│   └── package.json
│
└── README.md
```



## Local Setup

### Backend

git clone https://github.com/shivathaya/SwasthyaSetu.git

cd backend_asha

mvn clean install

mvn spring-boot:run
```

Backend runs at:


http://localhost:8080


### Frontend

```bash
cd frontend_asha

npm install

npm start
```

Frontend runs at:

http://localhost:3000



## Future Enhancements

* Vaccination Schedule Tracking
* Maternal Healthcare Management
* Beneficiary Dashboard
* SMS & Email Notifications
* Report Generation
* Mobile Application
* JWT Authentication
* Docker Deployment
* AWS Cloud Deployment

---

## Learning Outcomes

This project demonstrates hands-on experience with:

* Spring Boot REST API Development
* React Frontend Development
* Spring Security
* Session-Based Authentication
* JPA & Hibernate
* MySQL Integration
* Full-Stack Application Development
* Client-Server Architecture

---

## Author

**Abhishek Shivathaya**

Full-Stack Developer | Java | Spring Boot | React | SQL

GitHub: https://github.com/shivathaya
