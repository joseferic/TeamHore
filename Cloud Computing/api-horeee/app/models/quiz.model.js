const sql = require("./db.js");

// constructor
const Quiz = function(quiz) {
  this.id_Fruit = quiz.id_Fruit;
  this.type_Quiz = quiz.type_Quiz;
  this.correctanswer_Quiz = quiz.correctanswer_Quiz;
  this.wronganswer_Quiz = quiz.wronganswer_Quiz;
};

Quiz.create = (newContentdata, result) => {
  sql.query("INSERT INTO quiz SET ?", newContentdata, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("created quiz: ", { id_Quiz: res.insertId, ...newContentdata });
    result(null, { id_Quiz: res.insertId, ...newContentdata });
  });
};

Quiz.findById = (id_Quiz, result) => {
  sql.query(`SELECT * FROM quiz WHERE id_Quiz = ${id_Quiz}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found quiz: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Quiz with the id_Quiz
    result({ kind: "not_found" }, null);
  });
};

Quiz.findByPg = (type_Quiz, result) => {
  sql.query(`SELECT * FROM quiz WHERE type_Quiz = ${type_Quiz}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("quiz: ", res);
      result(null, res);
      return;
    }

    // not found Quizhistory with the type_Quiz
    result({ kind: "not_found" }, null);
  });
};

Quiz.findByBb = (type_Quiz, result) => {
  sql.query(`SELECT * FROM quiz WHERE type_Quiz = ${type_Quiz}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found quiz: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Quiz with the id_Quiz
    result({ kind: "not_found" }, null);
  });
};
Quiz.getAll = result => {
  sql.query("SELECT * FROM quiz", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("quiz: ", res);
    result(null, res);
  });
};

Quiz.updateById = (id_Quiz, quiz, result) => {
  sql.query(
    "UPDATE quiz SET id_Fruit = ?, type_Quiz = ?, correctanswer_Quiz = ?, wronganswer_Quiz = ? WHERE id_Quiz = ?",
    [quiz.id_Fruit, quiz.type_Quiz, quiz.correctanswer_Quiz, quiz.wronganswer_Quiz, id_Quiz],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found Quiz with the id_Quiz
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated quiz: ", { id_Quiz: id_Quiz, ...quiz });
      result(null, { id_Quiz: id_Quiz, ...quiz });
    }
  );
};

Quiz.remove = (id_Quiz, result) => {
  sql.query("DELETE FROM quiz WHERE id_Quiz = ?", id_Quiz, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      // not found Quiz with the id_Quiz
      result({ kind: "not_found" }, null);
      return;
    }

    console.log("deleted quiz with id_Quiz: ", id_Quiz);
    result(null, res);
  });
};

Quiz.removeAll = result => {
  sql.query("DELETE FROM quiz", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log(`deleted ${res.affectedRows} quiz`);
    result(null, res);
  });
};

module.exports = Quiz;

