module.exports = app => {
    const compsdatas = require("../controllers/compsdata.controller.js");
  
    // Create a new Customer
    app.post("/compsdatas", compsdatas.create);
  
    // Retrieve all Customers
    app.get("/compsdatas", compsdatas.findAll);
  
    // Retrieve a single Customer with id_compsData
    app.get("/compsdatas/:id_compsData", compsdatas.findOne);
  
    // Update a Customer with id_compsData
    app.put("/compsdatas/:id_compsData", compsdatas.update);
  
    // Delete a Customer with id_compsData
    app.delete("/compsdatas/:id_compsData", compsdatas.delete);
  
    // Create a new Customer
    app.delete("/compsdatas", compsdatas.deleteAll);
  };
  
