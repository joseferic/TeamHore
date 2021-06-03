const Contentdata = require("../models/contentdata.model.js");

// Create and Save a new Contentdata
exports.create = (req, res) => {
  // Validate request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  // Create a Contentdata
  const contentdata = new Contentdata({
    cat_contentData: req.body.cat_contentData,
    image_contentData: req.body.image_contentData,
    title_contentData: req.body.title_contentData,
    content_contentData: req.body.content_contentData,
  });

  // Save Contentdata in the database
  Contentdata.create(contentdata, (err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Contentdata."
      });
    else res.send(data);
  });
};

// Retrieve all Customers from the database.
exports.findAll = (req, res) => {
  Contentdata.getAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving customers."
      });
    else res.send(data);
  });
};

// Find a single Contentdata with a id_contentData
exports.findOne = (req, res) => {
  Contentdata.findById(req.params.id_contentData, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Contentdata with id_contentData ${req.params.id_contentData}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Contentdata with id_contentData " + req.params.id_contentData
        });
      }
    } else res.send(data);
  });
};

// Update a Contentdata identified by the id_contentData in the request
exports.update = (req, res) => {
  // Validate Request
  if (!req.body) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
  }

  console.log(req.body);

  Contentdata.updateById(
    req.params.id_contentData,
    new Contentdata(req.body),
    (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Contentdata with id_contentData ${req.params.id_contentData}.`
          });
        } else {
          res.status(500).send({
            message: "Error updating Contentdata with id_contentData " + req.params.id_contentData
          });
        }
      } else res.send(data);
    }
  );
};

// Delete a Contentdata with the specified id_contentData in the request
exports.delete = (req, res) => {
  Contentdata.remove(req.params.id_contentData, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Contentdata with id_contentData ${req.params.id_contentData}.`
        });
      } else {
        res.status(500).send({
          message: "Could not delete Contentdata with id_contentData " + req.params.id_contentData
        });
      }
    } else res.send({ message: `Contentdata was deleted successfully!` });
  });
};

// Delete all Customers from the database.
exports.deleteAll = (req, res) => {
  Contentdata.removeAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all customers."
      });
    else res.send({ message: `All Customers were deleted successfully!` });
  });
};

