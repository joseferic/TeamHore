const sql = require("./db.js");

// constructor
const Fruit = function(fruit) {
  this.name_Fruit = fruit.name_Fruit;
  this.fact_Fruit = fruit.fact_Fruit;
  this.pict_Fruit = fruit.pict_Fruit;
};

Fruit.create = (newFruit, result) => {
  sql.query("INSERT INTO fruit SET ?", newFruit, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("created fruit: ", { id_Fruit: res.insertId, ...newFruit });
    result(null, { id_Fruit: res.insertId, ...newFruit });
  });
};

Fruit.findById = (id_Fruit, result) => {
  sql.query(`SELECT * FROM fruit WHERE id_Fruit = ${id_Fruit}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found fruit: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Fruit with the id_Fruit
    result({ kind: "not_found" }, null);
  });
};

Fruit.getAll = result => {
  sql.query("SELECT * FROM fruit", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("fruit: ", res);
    result(null, res);
  });
};

Fruit.updateById = (id_Fruit, fruit, result) => {
  sql.query(
    "UPDATE fruit SET name_Fruit = ?, fact_Fruit = ?, pict_Fruit = ? WHERE id_Fruit = ?",
    [fruit.name_Fruit, fruit.fact_Fruit, fruit.pict_Fruit, id_Fruit],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found Fruit with the id_Fruit
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated fruit: ", { id_Fruit: id_Fruit, ...fruit });
      result(null, { id_Fruit: id_Fruit, ...fruit });
    }
  );
};

Fruit.remove = (id_Fruit, result) => {
  sql.query("DELETE FROM fruit WHERE id_Fruit = ?", id_Fruit, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      // not found Fruit with the id_Fruit
      result({ kind: "not_found" }, null);
      return;
    }

    console.log("deleted fruit with id_Fruit: ", id_Fruit);
    result(null, res);
  });
};

Fruit.removeAll = result => {
  sql.query("DELETE FROM fruit", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log(`deleted ${res.affectedRows} fruit`);
    result(null, res);
  });
};

module.exports = Fruit;

