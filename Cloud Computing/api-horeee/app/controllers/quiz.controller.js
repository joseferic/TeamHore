const Quiz = require("../models/quiz.model.js");

// Create and Save a new Quiz
exports.create = (req, res) => {
  // Validate request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  // Create a Quiz
  const trxdata = new Quiz({
    id_Fruit: req.body.id_Fruit,
    type_Quiz: req.body.type_Quiz,
    correctanswer_Quiz: req.body.correctanswer_Quiz,
    wronganswer_Quiz: req.body.wronganswer_Quiz
  });

  // Save Quiz in the database
  Quiz.create(trxdata, (err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Quiz."
      });
    else res.send(data);
  });
};

// Retrieve all Customers from the database.
exports.findAll = (req, res) => {
  Quiz.getAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving customers."
      });
    else res.send(data);
  });
};

// Find a single Quiz with a id_Quiz
exports.findOne = (req, res) => {
  Quiz.findById(req.params.id_Quiz, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Quiz with id_Quiz ${req.params.id_Quiz}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Quiz with id_Quiz " + req.params.id_Quiz
        });
      }
    } else res.send(data);
  });
};

// Find a single Quiz with a id_Quiz
exports.findPg = (req, res) => {
  Quiz.findByPg(req.params.type_Quiz, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Quiz with type_Quiz ${req.params.type_Quiz}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Quiz with type_Quiz " + req.params.type_Quiz
        });
      }
    } else res.send(data);
  });
};

// Find a single Quiz with a id_Quiz
exports.findBb = (req, res) => {
  Quiz.findByBb(req.params.type_Quiz, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Quiz with type_Quiz ${req.params.type_Quiz}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Quiz with type_Quiz " + req.params.type_Quiz
        });
      }
    } else res.send(data);
  });
};
// Update a Quiz identified by the id_Quiz in the request
exports.update = (req, res) => {
  // Validate Request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  console.log(req.body);

  Quiz.updateById(
    req.params.id_Quiz,
    new Quiz(req.body),
    (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Quiz with id_Quiz ${req.params.id_Quiz}.`
          });
        } else {
          res.status(500).send({
            message: "Error updating Quiz with id_Quiz " + req.params.id_Quiz
          });
        }
      } else res.send(data);
    }
  );
};

// Delete a Quiz with the specified id_Quiz in the request
exports.delete = (req, res) => {
  Quiz.remove(req.params.id_Quiz, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Quiz with id_Quiz ${req.params.id_Quiz}.`
        });
      } else {
        res.status(500).send({
          message: "Could not delete Quiz with id_Quiz " + req.params.id_Quiz
        });
      }
    } else res.send({ message: `Quiz was deleted successfully!` });
  });
};

// Delete all Customers from the database.
exports.deleteAll = (req, res) => {
  Quiz.removeAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all customers."
      });
    else res.send({ message: `All Customers were deleted successfully!` });
  });
};

