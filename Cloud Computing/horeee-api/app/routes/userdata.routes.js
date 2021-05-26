module.exports = app => {
  const userdatas = require("../controllers/userdata.controller.js");

  // Create a new Customer
  app.post("/userdatas", userdatas.create);

  // Retrieve all Customers
  app.get("/userdatas", userdatas.findAll);

  // Retrieve a single Customer with id_userData
  app.get("/userdatas/:id_userData", userdatas.findOne);

  // Update a Customer with id_userData
  app.put("/userdatas/:id_userData", userdatas.update);

  // Delete a Customer with id_userData
  app.delete("/userdatas/:id_userData", userdatas.delete);

  // Create a new Customer
  app.delete("/userdatas", userdatas.deleteAll);
};
