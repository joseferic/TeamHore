const Fruit = require("../models/fruit.model.js");

// Create and Save a new Fruit
exports.create = (req, res) => {
  // Validate request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  // Create a Fruit
  const userdata = new Fruit({
    name_Fruit: req.body.name_Fruit,
    fact_Fruit: req.body.fact_Fruit,
    pict_Fruit: req.body.pict_Fruit
  });

  // Save Fruit in the database
  Fruit.create(userdata, (err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Fruit."
      });
    else res.send(data);
  });
};

// Retrieve all Customers from the database.
exports.findAll = (req, res) => {
  Fruit.getAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving customers."
      });
    else res.send(data);
  });
};

// Find a single Fruit with a id_Fruit
exports.findOne = (req, res) => {
  Fruit.findById(req.params.id_Fruit, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Fruit with id_Fruit ${req.params.id_Fruit}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Fruit with id_Fruit " + req.params.id_Fruit
        });
      }
    } else res.send(data);
  });
};

// Update a Fruit identified by the id_Fruit in the request
exports.update = (req, res) => {
  // Validate Request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  console.log(req.body);

  Fruit.updateById(
    req.params.id_Fruit,
    new Fruit(req.body),
    (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Fruit with id_Fruit ${req.params.id_Fruit}.`
          });
        } else {
          res.status(500).send({
            message: "Error updating Fruit with id_Fruit " + req.params.id_Fruit
          });
        }
      } else res.send(data);
    }
  );
};

// Delete a Fruit with the specified id_Fruit in the request
exports.delete = (req, res) => {
  Fruit.remove(req.params.id_Fruit, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Fruit with id_Fruit ${req.params.id_Fruit}.`
        });
      } else {
        res.status(500).send({
          message: "Could not delete Fruit with id_Fruit " + req.params.id_Fruit
        });
      }
    } else res.send({ message: `Fruit was deleted successfully!` });
  });
};

// Delete all Customers from the database.
exports.deleteAll = (req, res) => {
  Fruit.removeAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all customers."
      });
    else res.send({ message: `All Customers were deleted successfully!` });
  });
};

