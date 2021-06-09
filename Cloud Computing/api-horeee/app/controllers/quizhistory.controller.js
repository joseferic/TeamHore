const Quizhistory = require("../models/quizhistory.model.js");

// Create and Save a new Quizhistory
exports.create = (req, res) => {
  // Validate request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  // Create a Quizhistory
  const quizhistory = new Quizhistory({
    timestamp_quizHistory: req.body.timestamp_quizHistory,
    id_Quiz: req.body.id_Quiz,
    id_User: req.body.id_User,
    type_Quiz: req.body.type_Quiz,
    score_quizHistory: req.body.score_quizHistory
  });

  // Save Quizhistory in the database
  Quizhistory.create(quizhistory, (err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Quizhistory."
      });
    else res.send(data);
  });
};

// Retrieve all Customers from the database.
exports.findAll = (req, res) => {
  Quizhistory.getAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving customers."
      });
    else res.send(data);
  });
};

// Find a single Quizhistory with a id_quizHistory
exports.findOne = (req, res) => {
  Quizhistory.findById(req.params.id_quizHistory, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Quizhistory with id_quizHistory ${req.params.id_quizHistory}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Quizhistory with id_quizHistory " + req.params.id_quizHistory
        });
      }
    } else res.send(data);
  });
};

// Find a single Quizhistory with a id_quizHistory
exports.findUser = (req, res) => {
  Quizhistory.findByUser(req.params.id_User, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Quizhistory with id_User ${req.params.id_User}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Quizhistory with id_User " + req.params.id_User
        });
      }
    } else res.send(data);
  });
};

// Update a Quizhistory identified by the id_quizHistory in the request
exports.update = (req, res) => {
  // Validate Request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  console.log(req.body);

  Quizhistory.updateById(
    req.params.id_quizHistory,
    new Quizhistory(req.body),
    (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Quizhistory with id_quizHistory ${req.params.id_quizHistory}.`
          });
        } else {
          res.status(500).send({
            message: "Error updating Quizhistory with id_quizHistory " + req.params.id_quizHistory
          });
        }
      } else res.send(data);
    }
  );
};

// Delete a Quizhistory with the specified id_quizHistory in the request
exports.delete = (req, res) => {
  Quizhistory.remove(req.params.id_quizHistory, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Quizhistory with id_quizHistory ${req.params.id_quizHistory}.`
        });
      } else {
        res.status(500).send({
          message: "Could not delete Quizhistory with id_quizHistory " + req.params.id_quizHistory
        });
      }
    } else res.send({ message: `Quizhistory was deleted successfully!` });
  });
};

// Delete all Customers from the database.
exports.deleteAll = (req, res) => {
  Quizhistory.removeAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all customers."
      });
    else res.send({ message: `All Customers were deleted successfully!` });
  });
};

