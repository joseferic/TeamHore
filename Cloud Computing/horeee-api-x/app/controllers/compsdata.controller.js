const Compsdata = require("../models/compsdata.model.js");

// Create and Save a new Compsdata
exports.create = (req, res) => {
  // Validate request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  // Create a Compsdata
  const compsdata = new Compsdata({
    id_foodData: req.body.id_foodData,
    name_compsData: req.body.name_compsData,
    desc_compsData: req.body.desc_compsData 
  });

  // Save Compsdata in the database
  Compsdata.create(compsdata, (err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Compsdata."
      });
    else res.send(data);
  });
};

// Retrieve all Customers from the database.
exports.findAll = (req, res) => {
  Compsdata.getAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving customers."
      });
    else res.send(data);
  });
};

// Find a single Compsdata with a id_compsData
exports.findOne = (req, res) => {
  Compsdata.findById(req.params.id_compsData, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Compsdata with id_compsData ${req.params.id_compsData}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Compsdata with id_compsData " + req.params.id_compsData
        });
      }
    } else res.send(data);
  });
};

// Update a Compsdata identified by the id_compsData in the request
exports.update = (req, res) => {
  // Validate Request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  console.log(req.body);

  Compsdata.updateById(
    req.params.id_compsData,
    new Compsdata(req.body),
    (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Compsdata with id_compsData ${req.params.id_compsData}.`
          });
        } else {
          res.status(500).send({
            message: "Error updating Compsdata with id_compsData " + req.params.id_compsData
          });
        }
      } else res.send(data);
    }
  );
};

// Delete a Compsdata with the specified id_compsData in the request
exports.delete = (req, res) => {
  Compsdata.remove(req.params.id_compsData, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Compsdata with id_compsData ${req.params.id_compsData}.`
        });
      } else {
        res.status(500).send({
          message: "Could not delete Compsdata with id_compsData " + req.params.id_compsData
        });
      }
    } else res.send({ message: `Compsdata was deleted successfully!` });
  });
};

// Delete all Customers from the database.
exports.deleteAll = (req, res) => {
  Compsdata.removeAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all customers."
      });
    else res.send({ message: `All Customers were deleted successfully!` });
  });
};
