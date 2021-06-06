module.exports = app => {
    const fruits = require("../controllers/fruit.controller.js");
  
    // Create a new Customer
    app.post("/fruits", fruits.create);
  
    // Retrieve all Customers
    app.get("/fruits", fruits.findAll);
  
    // Retrieve a single Customer with id_Fruit
    app.get("/fruits/:id_Fruit", fruits.findOne);
  
    // Update a Customer with id_Fruit
    app.put("/fruits/:id_Fruit", fruits.update);
  
    // Delete a Customer with id_Fruit
    app.delete("/fruits/:id_Fruit", fruits.delete);
  
    // Create a new Customer
    app.delete("/fruits", fruits.deleteAll);
  };
  
