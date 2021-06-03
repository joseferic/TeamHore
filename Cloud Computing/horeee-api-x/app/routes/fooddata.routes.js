module.exports = app => {
    const fooddatas = require("../controllers/fooddata.controller.js");
  
    // Create a new Customer
    app.post("/fooddatas", fooddatas.create);
  
    // Retrieve all Customers
    app.get("/fooddatas", fooddatas.findAll);
  
    // Retrieve a single Customer with id_foodData
    app.get("/fooddatas/:id_foodData", fooddatas.findOne);
  
    // Update a Customer with id_foodData
    app.put("/fooddatas/:id_foodData", fooddatas.update);
  
    // Delete a Customer with id_foodData
    app.delete("/fooddatas/:id_foodData", fooddatas.delete);
  
    // Create a new Customer
    app.delete("/fooddatas", fooddatas.deleteAll);
  };
  