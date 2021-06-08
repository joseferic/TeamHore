module.exports = app => {
    const quizzes = require("../controllers/quiz.controller.js");
  
    // Create a new Customer
    app.post("/quizzes", quizzes.create);
  
    // Retrieve all Customers
    app.get("/quizzes", quizzes.findAll);
  
    // Retrieve a single Customer with id_Quiz
    app.get("/quizzes/:id_Quiz", quizzes.findOne);
    
    // Retrieve a single Customer with id_Quiz
    app.get("/quizzes/type/:type_Quiz", quizzes.findPg);

    // Retrieve a single Customer with id_Quiz
    // app.get("/quizzes/bb/:type_Quiz", quizzes.findBb);     

    // Update a Customer with id_Quiz
    // app.put("/quizzes/:id_Quiz", quizzes.update);j
  
    // Delete a Customer with id_Quiz
    // app.delete("/quizzes/:id_Quiz", quizzes.delete);
  
    // Create a new Customer
    // app.delete("/quizzes", quizzes.deleteAll);s
  };
  
