const Userdata = require("../models/userdata.model.js");

// Create and Save a new Userdata
exports.create = (req, res) => {
  // Validate request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  // Create a Userdata
  const userdata = new Userdata({
    email_userData: req.body.email_userData,
    password_userData: req.body.password_userData,
    name_userData: req.body.name_userData,
    id_foodData: req.body.id_foodData,
    allergy_userData: req.body.allergy_userData,
    pref_userData: req.body.pref_userData    
  });

  // Save Userdata in the database
  Userdata.create(userdata, (err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Userdata."
      });
    else res.send(data);
  });
};

// Retrieve all Customers from the database.
exports.findAll = (req, res) => {
  Userdata.getAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving customers."
      });
    else res.send(data);
  });
};

// Find a single Userdata with a id_userData
exports.findOne = (req, res) => {
  Userdata.findById(req.params.id_userData, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Userdata with id_userData ${req.params.id_userData}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Userdata with id_userData " + req.params.id_userData
        });
      }
    } else res.send(data);
  });
};

// Update a Userdata identified by the id_userData in the request
exports.update = (req, res) => {
  // Validate Request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  console.log(req.body);

  Userdata.updateById(
    req.params.id_userData,
    new Userdata(req.body),
    (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Userdata with id_userData ${req.params.id_userData}.`
          });
        } else {
          res.status(500).send({
            message: "Error updating Userdata with id_userData " + req.params.id_userData
          });
        }
      } else res.send(data);
    }
  );
};

// Delete a Userdata with the specified id_userData in the request
exports.delete = (req, res) => {
  Userdata.remove(req.params.id_userData, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Userdata with id_userData ${req.params.id_userData}.`
        });
      } else {
        res.status(500).send({
          message: "Could not delete Userdata with id_userData " + req.params.id_userData
        });
      }
    } else res.send({ message: `Userdata was deleted successfully!` });
  });
};

// Delete all Customers from the database.
exports.deleteAll = (req, res) => {
  Userdata.removeAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all customers."
      });
    else res.send({ message: `All Customers were deleted successfully!` });
  });
};

