const Fooddata = require("../models/fooddata.model.js");

// Create and Save a new Fooddata
exports.create = (req, res) => {
  // Validate request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  // Create a Fooddata
  const fooddata = new Fooddata({
    class_foodData: req.body.class_foodData,
    type_foodData: req.body.type_foodData,
    group_foodData: req.body.group_foodData,
    food_foodData: req.body.food_foodData,
    allergy_foodData: req.body.allergy_foodData
  });

  // Save Fooddata in the database
  Fooddata.create(fooddata, (err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Fooddata."
      });
    else res.send(data);
  });
};

// Retrieve all Customers from the database.
exports.findAll = (req, res) => {
  Fooddata.getAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving customers."
      });
    else res.send(data);
  });
};

// Find a single Fooddata with a id_foodData
exports.findOne = (req, res) => {
  Fooddata.findById(req.params.id_foodData, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Fooddata with id_foodData ${req.params.id_foodData}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Fooddata with id_foodData " + req.params.id_foodData
        });
      }
    } else res.send(data);
  });
};

// Update a Fooddata identified by the id_foodData in the request
exports.update = (req, res) => {
  // Validate Request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  console.log(req.body);

  Fooddata.updateById(
    req.params.id_foodData,
    new Fooddata(req.body),
    (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Fooddata with id_foodData ${req.params.id_foodData}.`
          });
        } else {
          res.status(500).send({
            message: "Error updating Fooddata with id_foodData " + req.params.id_foodData
          });
        }
      } else res.send(data);
    }
  );
};

// Delete a Fooddata with the specified id_foodData in the request
exports.delete = (req, res) => {
  Fooddata.remove(req.params.id_foodData, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Fooddata with id_foodData ${req.params.id_foodData}.`
        });
      } else {
        res.status(500).send({
          message: "Could not delete Fooddata with id_foodData " + req.params.id_foodData
        });
      }
    } else res.send({ message: `Fooddata was deleted successfully!` });
  });
};

// Delete all Customers from the database.
exports.deleteAll = (req, res) => {
  Fooddata.removeAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all customers."
      });
    else res.send({ message: `All Customers were deleted successfully!` });
  });
};

