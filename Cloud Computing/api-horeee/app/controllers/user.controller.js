const User = require("../models/user.model.js");

// Create and Save a new User
exports.create = (req, res) => {
  // Validate request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  // Create a User
  const user = new User({
    name_User: req.body.name_User,
    email_User: req.body.email_User,
    pass_User: req.body.pass_User 
  });

  // Save User in the database
  User.create(user, (err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the User."
      });
    else res.send(data);
  });
};

// Retrieve all Customers from the database.
exports.findAll = (req, res) => {
  User.getAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving customers."
      });
    else res.send(data);
  });
};

// Find a single User with a id_User
exports.findOne = (req, res) => {
  User.findById(req.params.id_User, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found User with id_User ${req.params.id_User}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving User with id_User " + req.params.id_User
        });
      }
    } else res.send(data);
  });
};

// Update a User identified by the id_User in the request
exports.update = (req, res) => {
  // Validate Request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  console.log(req.body);

  User.updateById(
    req.params.id_User,
    new User(req.body),
    (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found User with id_User ${req.params.id_User}.`
          });
        } else {
          res.status(500).send({
            message: "Error updating User with id_User " + req.params.id_User
          });
        }
      } else res.send(data);
    }
  );
};

// Delete a User with the specified id_User in the request
exports.delete = (req, res) => {
  User.remove(req.params.id_User, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found User with id_User ${req.params.id_User}.`
        });
      } else {
        res.status(500).send({
          message: "Could not delete User with id_User " + req.params.id_User
        });
      }
    } else res.send({ message: `User was deleted successfully!` });
  });
};

// Delete all Customers from the database.
exports.deleteAll = (req, res) => {
  User.removeAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all customers."
      });
    else res.send({ message: `All Customers were deleted successfully!` });
  });
};

