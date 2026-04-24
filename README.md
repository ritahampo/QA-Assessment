# QA Engineer Assessment

## Overview
This project covers manual testing, UI automation, and API testing for an eCommerce web application.

---

## Tools Used
- Selenium (UI Automation)
- TestNG (Test Execution)
- Postman (API Testing)
- Excel (Test Cases & Bug Reports)

---

## Scope
The following features were tested:
- Login functionality
- Add/remove products from cart
- Product filtering
- View cart
- Checkout process
- Users API endpoint

---

## Test Techniques Used
- Exploratory Testing
- Positive Testing
- Negative Testing
- Boundary Testing 

---

## Automation (Selenium)
Automated test cases:
- Valid user login
- Invalid user login
- Prevent duplicate product addition
- Checkout last name field validation (bug)

---

## API Testing (Postman)
Users endpoint tested:
- POST - Create user
- GET - Get user by username
  

---

## Prerequisites
- Java installed
- Maven installed
- Browser (Chrome)
- Selenium WebDriver configured
- Postman installed
- Internet connection

---

## Observations
- Login works correctly for valid users
- Invalid login is properly handled
- Products can be added and removed from cart
- Filtering functionality works as expected
- API endpoints respond with correct status codes

---

## Bugs / Issues Found
1. Last name field in checkout page is not accepting input
2. Logout does not fully invalidate user session (user can still access data)

---

## Blockers
- No major blockers encountered during testing
- Some minor UI inconsistencies observed but did not stop testing

---

## Improvements Suggested
- Add visibility toggle icon for password field
- Improve validation on checkout fields
- Ensure logout properly invalidates session
- Add more products to make the app more realistic

---

## How to Run Automation
1. Open project in IDE (e.g., Eclipse)
2. Run TestNG test classes
3. Ensure dependencies are installed via Maven

---

## Repository Contents
- Selenium automation scripts
- Postman collection (JSON)
- Test cases and bug report (Excel)
- QA report (PDF)
