const sql = require("./db.js");

// constructor
const Quizhistory = function(quizhistory) {
  this.timestamp_quizHistory = quizhistory.timestamp_quizHistory;
  this.id_Quiz = quizhistory.id_Quiz;
  this.id_User = quizhistory.id_User;
  this.type_Quiz = quizhistory.type_Quiz;
  this.result_quizHistory = quizhistory.result_quizHistory;
};

Quizhistory.create = (newQuizhistory, result) => {
  sql.query("INSERT INTO quizhistory SET ?", newQuizhistory, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("created quizhistory: ", { id_quizHistory: res.insertId, ...newQuizhistory });
    result(null, { id_quizHistory: res.insertId, ...newQuizhistory });
  });
};

Quizhistory.findById = (id_quizHistory, result) => {
  sql.query(`SELECT * FROM quizhistory WHERE id_quizHistory = ${id_quizHistory}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found quizhistory: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Quizhistory with the id_quizHistory
    result({ kind: "not_found" }, null);
  });
};

Quizhistory.findByUser = (id_User, result) => {
  sql.query(`SELECT * FROM quizhistory WHERE id_User = ${id_User}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("quizhistory: ", res);
      result(null, res);
      return;
    }

    // not found Quizhistory with the id_User
    result({ kind: "not_found" }, null);
  });
};


Quizhistory.getAll = result => {
  sql.query("SELECT * FROM quizhistory", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("quizhistory: ", res);
    result(null, res);
  });
};

Quizhistory.updateById = (id_quizHistory, quizhistory, result) => {
  sql.query(
    "UPDATE quizhistory SET timestamp_quizHistory = ?, id_Quiz = ?, id_User = ?, type_Quiz = ?, score_quizHistory = ? WHERE id_quizHistory = ?",
    [quizhistory.timestamp_quizHistory, quizhistory.id_Quiz, quizhistory.id_User, uizhistory.type_Quiz,  quizhistory.score_quizHistory, id_quizHistory],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found Quizhistory with the id_quizHistory
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated quizhistory: ", { id_quizHistory: id_quizHistory, ...quizhistory });
      result(null, { id_quizHistory: id_quizHistory, ...quizhistory });
    }
  );
};

Quizhistory.remove = (id_quizHistory, result) => {
  sql.query("DELETE FROM quizhistory WHERE id_quizHistory = ?", id_quizHistory, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      // not found Quizhistory with the id_quizHistory
      result({ kind: "not_found" }, null);
      return;
    }

    console.log("deleted quizhistory with id_quizHistory: ", id_quizHistory);
    result(null, res);
  });
};

Quizhistory.removeAll = result => {
  sql.query("DELETE FROM quizhistory", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log(`deleted ${res.affectedRows} quizhistory`);
    result(null, res);
  });
};

module.exports = Quizhistory;

