# Easy Shop Application

## Overview
This project is a simple shopping cart application using Spring Boot and Postman. It allows users to register, login, search, filter, add products to their shopping cart, update product quantities, and clear the cart. The application includes API endpoints for managing shopping cart operations.

## Features
- Register: Register with a username and password.
- Login: Login using the correct username and password.
- Add Products to Cart: Users can add products to their shopping cart by providing the product ID.
- Update Product Quantities: Users can update the quantity of products in their cart.
- Clear Cart: Users can clear all products from their shopping cart.
- Get Cart: Users can view the contents of their shopping cart.

## Technologies Used
- Spring Boot: Backend framework
- Postman: API testing, Authorizstion, Tokens
- Java: Programming language
- Azure: Database

## Setup and Installation
1. Clone the repository:
   https://github.com/bricastanon/EasyshopApplication

2. Access the application:
    - The application will be running at `http://localhost:8080`.

## Testing
Using Postman
1. Register:
   - Method: Post
   - URL: `http://localhost:8080/register`
   - Headers: `Authorization: Bearer <your-token>`

2. Login:
   - Method: Post
   - URL: `http://localhost:8080/login`
   - Headers: `Authorization: Bearer <your-token>`
  
3. Get Cart:
   - Method: GET
   - URL: `http://localhost:8080/cart`
   - Headers: `Authorization: Bearer <your-token>`

4. Add Product to Cart:
   - Method: POST
   - URL: `http://localhost:8080/cart/products/{productId}`
   - Headers: `Authorization: Bearer <your-token>`

5. Update Product in Cart:
   - Method: PUT
   - URL: `http://localhost:8080/cart/products/{productId}`
   - Headers: `Authorization: Bearer <your-token>`
 
6. Clear Cart:
   - Method: DELETE
   - URL: `http://localhost:8080/cart/{Id}`
   - Headers: `Authorization: Bearer <your-token>`

