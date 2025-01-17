# SeleniumJavaAmazonAutomation
This project demonstrates an automation workflow for the Amazon e-commerce platform using Selenium and Java. It includes user login functionality, adding a product to the cart, and navigating to the checkout process.
## Description
This project automates basic tasks on the Amazon website. It includes:
- Searching for products
- Adding products to the cart
- Proceeding to checkout
- Logging in with a username and password
## Requirements
- Java 17 or higher
- Selenium 4.x
- Maven
- Google Chrome (latest version)
- ChromeDriver (compatible with your Chrome version)
## Setting Up Environment Variables
Before running the project, ensure you have set the following environment variables:
- **EMAIL**: Your Amazon login email.
- **PASSWORD**: Your Amazon login password.
## Setup
 Clone the repository:
   ```bash
   git clone https://github.com/OguzCanYildizbas/SeleniumJavaAmazonAutomation.git
   cd SeleniumJavaAmazonAutomation
export EMAIL="your-email@example.com"
export PASSWORD="your-password"
mvn clean install
mvn test

