
Please Implement below API,
# 1. Registration service with /users endpoint for taking a JSON body with details such as,
## FIELDS & VALIDATIONS
* Username - alphanumeric, no spaces
* Password – min length 8, at least one upper case letter & number
* Email – email id with email format
* DoB (Date of Birth) - ISO 8601 format
* Credit Card Number – This field is optional. If given should have 16 digits.
## EXPECTED RESPONSES:
* If the request body fails to satisfy any of the basic validation checks return HTTP Status code: 400
* Reject requests if the user is under the age of 18 and return HTTP Status code: 403
* If the username has already been used reject the request and return HTTP Status code: 409
* A successful action should return HTTP Status code: 201

GET /users endpoint:
Consumer should be able to provide a filter (CreditCard=Yes/No) in his request. If “Yes” then should return Users
registered with Credit Card Number.
If “No” then return Users registered without a Credit Card Number.
Without any filter should return all the Registered Users.

# 2. Payment service with /payments endpoint for taking a JSON body with details such as,
## FIELDS & VALIDATIONS
* Credit Card Number – 16 digits
* Amount – 3 digits
## EXPECTED RESPONSES:
* If the request body fails to satisfy any of the basic validation checks return HTTP Status code: 400
* If credit card number is not registered against any Registered User return HTTP Status code: 404
* A successful payment should return HTTP Status code: 201
## REQUIREMENTS:
* Free to use any Programming Language or Framework you are comfortable with.
* Include instructions for running the application
* Don’t need you to implement any Frontend code
* No need to use any Databases/in-memory Databases. You can use language provided data strcutures in place of
that.
* provide Unit Tests for your solution.