module.exports = app => {
    const contentdatas = require("../controllers/contentdata.controller.js");
  
    // Create a new Customer
    app.post("/contentdatas", contentdatas.create);
  
    // Retrieve all Customers
    app.get("/contentdatas", contentdatas.findAll);
  
    // Retrieve a single Customer with id_contentData
    app.get("/contentdatas/:id_contentData", contentdatas.findOne);
  
    // Update a Customer with id_contentData
    app.put("/contentdatas/:id_contentData", contentdatas.update);
  
    // Delete a Customer with id_contentData
    app.delete("/contentdatas/:id_contentData", contentdatas.delete);
  
    // Create a new Customer
    app.delete("/contentdatas", contentdatas.deleteAll);
  };
  
