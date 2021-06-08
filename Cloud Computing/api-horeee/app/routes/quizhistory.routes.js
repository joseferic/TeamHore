module.exports = app => {
    const quizhistorys = require("../controllers/quizhistory.controller.js");
  
    // Create a new Customer
    app.post("/quizhistorys", quizhistorys.create);
  
    // Retrieve all Customers
    app.get("/quizhistorys", quizhistorys.findAll);
  
    // Retrieve a single Customer with id_quizHistory
    app.get("/quizhistorys/:id_quizHistory", quizhistorys.findOne);
  
    // Retrieve a single Customer with id_quizHistory
    app.get("/quizhistorys/user/:id_User", quizhistorys.findUser);
    
    // Update a Customer with id_quizHistory
    // app.put("/quizhistorys/:id_quizHistory", quizhistorys.update);
  
    // Delete a Customer with id_quizHistory
    // app.delete("/quizhistorys/:id_quizHistory", quizhistorys.delete);
  
    // Create a new Customer
    // app.delete("/quizhistorys", quizhistorys.deleteAll);
  };
  
