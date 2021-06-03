const Trxdata = require("../models/trxdata.model.js");

// Create and Save a new Trxdata
exports.create = (req, res) => {
  // Validate request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  // Create a Trxdata
  const trxdata = new Trxdata({
    id_userData: req.body.id_userData,
    id_compsData: req.body.id_compsData,
    path_trxData: req.body.path_trxData,
    lvlalergy_trxData: req.body.lvlalergy_trxData,
  });

  // Save Trxdata in the database
  Trxdata.create(trxdata, (err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Trxdata."
      });
    else res.send(data);
  });
};

// Retrieve all Customers from the database.
exports.findAll = (req, res) => {
  Trxdata.getAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving customers."
      });
    else res.send(data);
  });
};

// Find a single Trxdata with a id_trxData
exports.findOne = (req, res) => {
  Trxdata.findById(req.params.id_trxData, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Trxdata with id_trxData ${req.params.id_trxData}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Trxdata with id_trxData " + req.params.id_trxData
        });
      }
    } else res.send(data);
  });
};

// Update a Trxdata identified by the id_trxData in the request
exports.update = (req, res) => {
  // Validate Request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  console.log(req.body);

  Trxdata.updateById(
    req.params.id_trxData,
    new Trxdata(req.body),
    (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Trxdata with id_trxData ${req.params.id_trxData}.`
          });
        } else {
          res.status(500).send({
            message: "Error updating Trxdata with id_trxData " + req.params.id_trxData
          });
        }
      } else res.send(data);
    }
  );
};

// Delete a Trxdata with the specified id_trxData in the request
exports.delete = (req, res) => {
  Trxdata.remove(req.params.id_trxData, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Trxdata with id_trxData ${req.params.id_trxData}.`
        });
      } else {
        res.status(500).send({
          message: "Could not delete Trxdata with id_trxData " + req.params.id_trxData
        });
      }
    } else res.send({ message: `Trxdata was deleted successfully!` });
  });
};

// Delete all Customers from the database.
exports.deleteAll = (req, res) => {
  Trxdata.removeAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all customers."
      });
    else res.send({ message: `All Customers were deleted successfully!` });
  });
};

