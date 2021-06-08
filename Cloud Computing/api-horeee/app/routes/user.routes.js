module.exports = app => {
  const users = require("../controllers/user.controller.js");

  // Create a new Customer
  app.post("/users", users.create);

  // Retrieve all Customers
  app.get("/users", users.findAll);

  // Retrieve a single Customer with id_User
  app.get("/users/:id_User", users.findOne);

  // Update a Customer with id_User
  app.put("/users/:id_User", users.update);

  // Delete a Customer with id_User
  // app.delete("/users/:id_User", users.delete);

  // Create a new Customer
  // app.delete("/users", users.deleteAll);
};

