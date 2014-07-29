SingleServlet
=============

Web application with no servlets using only filters and spring beans


following are the core classes


1. com.single.controllers.BaseController
   base controller for application user need to handle the request and response.
 
2. com.single.controllers.DispatchController
   It extends the base controller user needs to handle the request and response.
   it may contains different controller methods based on request parameter

3. com.single.controllers.DynaResponseController
   It extends the basecontroller.
   it may contains different controller methods.
   It handles the response (sending json, redirect to another jsp, forwarding to another jsp)
   It can call the validation methods dynamically) and
   sends the validation messages in json format or object format


following are the examples for above core classes and usecase.

1. com.single.controllers.StudentDynaResponse (Example for DynaResponseController)

2. com.single.controllers.StudentController(Example for DispatchController)

3. com.single.controllers.SignUpController(Example for BaseController)
