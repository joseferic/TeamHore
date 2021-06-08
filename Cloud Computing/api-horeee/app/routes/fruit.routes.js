module.exports = app => {
    const fruits = require("../controllers/fruit.controller.js");
  
    // Create a new Fruit
    app.post("/fruits", fruits.create);
  
    // Retrieve all Fruits
    app.get("/fruits", fruits.findAll);
  
    // Retrieve a single Fruit with id_Fruit
    app.get("/fruits/:id_Fruit", fruits.findOne);
  
    // Update a Fruit with id_Fruit
 //   app.put("/fruits/:id_Fruit", fruits.update);
  
    // Delete a Fruit with id_Fruit
 //   app.delete("/fruits/:id_Fruit", fruits.delete);
  
    // Create a new Fruit
 //   app.delete("/fruits", fruits.deleteAll);
  };
  
