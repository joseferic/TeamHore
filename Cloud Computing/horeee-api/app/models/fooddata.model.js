const sql = require("./db.js");

// constructor
const Fooddata = function(fooddata) {
  this.class_foodData = fooddata.class_foodData;
  this.type_foodData = fooddata.type_foodData;
  this.group_foodData = fooddata.group_foodData;
  this.food_foodData = fooddata.food_foodData;
  this.allergy_foodData = fooddata.allergy_foodData;
};

Fooddata.create = (newFooddata, result) => {
  sql.query("INSERT INTO fooddata SET ?", newFooddata, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("created fooddata: ", { id_foodData: res.insertId, ...newFooddata });
    result(null, { id_foodData: res.insertId, ...newFooddata });
  });
};

Fooddata.findById = (id_foodData, result) => {
  sql.query(`SELECT * FROM fooddata WHERE id_foodData = ${id_foodData}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found fooddata: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Fooddata with the id_foodData
    result({ kind: "not_found" }, null);
  });
};

Fooddata.getAll = result => {
  sql.query("SELECT * FROM fooddata", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("fooddata: ", res);
    result(null, res);
  });
};

Fooddata.updateById = (id_foodData, fooddata, result) => {
  sql.query(
    "UPDATE fooddata SET class_foodData = ?, type_foodData = ?, group_foodData = ?, food_foodData = ?, allergy_foodData = ? WHERE id_foodData = ?",
    [fooddata.class_foodData, fooddata.type_foodData, fooddata.group_foodData, fooddata.food_foodData, fooddata.allergy_foodData, id_foodData],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found Fooddata with the id_foodData
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated fooddata: ", { id_foodData: id_foodData, ...fooddata });
      result(null, { id_foodData: id_foodData, ...fooddata });
    }
  );
};

Fooddata.remove = (id_foodData, result) => {
  sql.query("DELETE FROM fooddata WHERE id_foodData = ?", id_foodData, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      // not found Fooddata with the id_foodData
      result({ kind: "not_found" }, null);
      return;
    }

    console.log("deleted fooddata with id_foodData: ", id_foodData);
    result(null, res);
  });
};

Fooddata.removeAll = result => {
  sql.query("DELETE FROM fooddata", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log(`deleted ${res.affectedRows} fooddata`);
    result(null, res);
  });
};

module.exports = Fooddata;
