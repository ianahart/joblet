# Job Board with Spring Boot, React, Postgresql, JPA and Hibernate

### Features

- Authentication
- Refresh Token
- User signup
- Employer signup
- Uploading pdf and creating profiles
- Job board
- Forgot password functionality
- And much more

# Steps to Setup

1. ### Clone the application

```
git clone https://github.com/ianahart/joblet.git
```

2. ### Create database called **auth** inside **Postgres**

```
CREATE DATABASE auth;
```

3. ### Change username and password as per your installation inside **application.properties**
   > - Open src/main/resources/application.properties

> - Change spring.datasource.username and spring.datasource.password as per your postgresql installation
> - Change spring.datasource.url to jdbc:postgresql://localhost:5432/auth

1. ### Build and run the app using maven

```
mvn clean install spring-boot:run
```

Alternatively you can run the app without packaging it using -

```
mvn spring-boot:run
```

5. ### From the home directory **cd** into **frontend** and run

```
npm install
```

6. ### To run the frontend

```
npm start
```

# Important

In order for the email portion of the website to work you will need to make an account with **sendgrid**. You will also need to create an AWS S3 bucket called **arrow-date**.

### Test Credentials

- user smith@aol.com
- pass Test12345%
- https://prod-joblet.netlify.app

