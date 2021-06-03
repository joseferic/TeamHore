module.exports = app => {
    const trxdatas = require("../controllers/trxdata.controller.js");
  
    // Create a new Customer
    app.post("/trxdatas", trxdatas.create);
  
    // Retrieve all Customers
    app.get("/trxdatas", trxdatas.findAll);
  
    // Retrieve a single Customer with id_trxData
    app.get("/trxdatas/:id_trxData", trxdatas.findOne);
  
    // Update a Customer with id_trxData
    app.put("/trxdatas/:id_trxData", trxdatas.update);
  
    // Delete a Customer with id_trxData
    app.delete("/trxdatas/:id_trxData", trxdatas.delete);
  
    // Create a new Customer
    app.delete("/trxdatas", trxdatas.deleteAll);
  };
  
