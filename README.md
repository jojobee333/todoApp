Documentation for TodoAppController in Spring Application

The TodoAppController class is a part of the com.revature.toDoApp.controller package in a Spring application. This controller provides RESTful endpoints for managing Todo and Account entities. Below is the detailed documentation of its API endpoints.
Endpoints
Todo Management

    Create a New Todo
        URL: /todo
        Method: POST
        Description: Creates a new todo item.
        Request Body: Expects a Todo object.
        Response: Returns the created Todo object with a 201 Created status.

    Get a Specific Todo
        URL: /todo/{todo_id}
        Method: GET
        Description: Retrieves a specific todo item by its ID.
        URL Parameters: todo_id (required) - The ID of the todo item.
        Response: Returns the requested Todo object with a 200 OK status.

    Get All Todos
        URL: /todo
        Method: GET
        Description: Retrieves all todo items.
        Response: Returns a list of Todo objects with a 200 OK status.

    Update a Todo
        URL: /todo/{todo_id}
        Method: PATCH
        Description: Updates an existing todo item.
        URL Parameters: todo_id (required) - The ID of the todo to update.
        Request Body: Expects a Todo object with updated fields.
        Response: Returns the updated Todo object with a 200 OK status.

    Delete a Todo
        URL: /todo/{todo_id}
        Method: DELETE
        Description: Deletes a specific todo item.
        URL Parameters: todo_id (required) - The ID of the todo to delete.
        Response: Returns a success message with a 200 OK status if deletion is successful.

Account Management

    Create a New Account
        URL: /account
        Method: POST
        Description: Creates a new account.
        Request Body: Expects an Account object.
        Response: Returns the created Account object with a 200 OK status.

    Get All Accounts
        URL: /account
        Method: GET
        Description: Retrieves all accounts.
        Response: Returns a list of Account objects with a 200 OK status.

    Get All Todos by Account
        URL: /account/{account_name}/todo
        Method: GET
        Description: Retrieves all todos associated with a specific account.
        URL Parameters: account_name (required) - The name of the account.
        Response: Returns a list of Todo objects with a 200 OK status.

    Delete an Account
        URL: /account/{account_id}
        Method: DELETE
        Description: Deletes a specific account.
        URL Parameters: account_id (required) - The ID of the account to delete.
        Response: Returns a success message with a 200 OK status if deletion is successful.

Exception Handling

    AccountNotFoundException: Thrown when an operation is attempted on a non-existing account.
    TodoNotFoundException: Thrown when an operation is attempted on a non-existing todo item.

Notes

    All endpoints return appropriate HTTP status codes indicating the success or failure of the request.
    The application uses JSON format for request and response bodies.
    It's recommended to handle exceptions gracefully and return meaningful error messages to the client.



