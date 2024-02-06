# Project 1: To-Do Application

* Assigned Date: 2/6/2024
* Due Date: 2/9/2024

# Description

Using Spring Boot, create a simple API for creating To-Do lists via HTTP Requests.

## Requirements
- Build the application using at least Java 11 and Spring Boot 2.7 (Spring Boot 3 is newer and while it is viable, may not provide the same support Spring Boot 2.7 has)

- All interactions between a User and the API should happen via HTTP Requests. Using a tool like Postman will allow you to set up these requests.

- All data should be stored in a SQL Database, using an in-memory H2 database will be the easiest was to tackle this problem. Your To-Do class should have fields for at least the following: ID, To-Do text, and a boolean marking if it's completed or not. Make sure the class is properly encapsulated by marking instance fields as private and creating getters and setters.

- You'll be expected to complete 4 of the following User Stories as a **MINIMUM**. The more stories implemented the better, but make sure you have at least 4 of the following:

    - As a user, I can create a new To-Do
    - As a user, I can view all To-Dos
    - As a user, I can view a singular To-Do by its ID (HINT: Use Path Params to select a To-Do by its ID)
    - As a user, I can update a To-Do (Change the title, mark it complete/incomplete)
    - As a user, I can delete a To-Do by its ID (HINT: Use Path Params to select a To-Do by its ID)
    - As a user, I can create an account to hold my To-Dos
    - As a user, I can login to my account (which is stored in the database)
    - As a user, I can view the To-Dos associated with my account
    - As a user, I can filter To-Dos to only show completed or incompleted ones. (HINT: This should use Query Params on a previously created handler for viewing To-Dos)
    - As a user, I can filter my To-Dos by topics (HINT: This should use Query Params on a previously created handler for viewing To-Dos and would require an additional field on the ToDo Class)

- Other Optional Requirements include the following:
    - Giving accounts roles (USER and ADMIN) so Admins can view everyone's To-Dos while a User can only view their own
    - Using JUnit to test Service Layer methods (70% coverage at least would be optimal)
    - Switching the H2 Database for the SQL Server database we created on 2/5/2024

- **NOTE** Responses from the API must include proper response bodies (in JSON) and **status codes** (i.e. If I try to go to http://localhost:8080/To-Do/5 and there is no resource there, I should receive a 404 status code (NOT FOUND) in the response, but if the resource is there I should receive a 200 status code (OK) instead)

## Presentation
- Use Postman to showcase how your application handles HTTP requests and responses (A Collection on Postman is useful for presenting prewritten HTTP requests)
- Clear, concise, and professional communication during the project presentation
- Ability to communicate clear answers to fully address questions asked about the project
- Logical flow to the project presentation
- Approx. 5 Minutes in length

# Frequently Asked Questions
1. When is the project due? 
    >A: February 9th, 2024 
2. Is there a code freeze? 
    >A: It is recommended that you institute your own code freeze at least a day before the project presentations. However, this is a recommendation only; it will not be enforced. NOTE: The code that will be evaluated by your trainer will be the code you last pushed to your repository BEFORE the time set for project presentations. Code submitted while presentations are on-going will not be evaluated. 
3. What happens if I break my project that was mostly working right before the due date? 
    >A: **As you should have been regularly pushing code to your repository** you should be able to roll back to previously working version. If you have not regularly pushed your code and do not have a working commit to return to you will need to present the state of your application in its current form. 
4. Who will be evaluating the project? 
    >A: Your trainer will be the one providing the full evaluation of your projects. However, the QC team will also be present at presentations to ask questions about your project and consult with your trainer. 
5. Are we allowed to collaborate with others on our projects? 
    >A: Collaboration is encouraged. By helping others, and in turn posing good questions, you will become a stronger developer. Hopefully you will work together to solve the problems presented in this project.  However, you should still be ultimately designing the project yourself. Straight copy/pasting of another person's code is considered plagiarism and NOT allowed. NOTE: Code provided in demos by your trainer is not subject to plagiarism concerns. *Feel free to copy/paste and edit your trainer's code as necessary to suit your projects.*
