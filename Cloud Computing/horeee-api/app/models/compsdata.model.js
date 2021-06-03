const sql = require("./db.js");

// constructor
const Compsdata = function(compsdata) {
  this.id_foodData = compsdata.id_foodData;
  this.name_compsData = compsdata.name_compsData;
  this.desc_compsData = compsdata.desc_compsData;
};

Compsdata.create = (newCompsdata, result) => {
  sql.query("INSERT INTO compsdata SET ?", newCompsdata, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("created compsdata: ", { id_compsData: res.insertId, ...newCompsdata });
    result(null, { id_compsData: res.insertId, ...newCompsdata });
  });
};

Compsdata.findById = (id_compsData, result) => {
  sql.query(`SELECT * FROM compsdata WHERE id_compsData = ${id_compsData}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found compsdata: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Compsdata with the id_compsData
    result({ kind: "not_found" }, null);
  });
};

Compsdata.getAll = result => {
  sql.query("SELECT * FROM compsdata", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("compsdata: ", res);
    result(null, res);
  });
};

Compsdata.updateById = (id_compsData, compsdata, result) => {
  sql.query(
    "UPDATE compsdata SET id_foodData = ?, name_compsData = ?, desc_compsData = ? WHERE id_compsData = ?",
    [compsdata.id_foodData, compsdata.name_compsData, compsdata.desc_compsData, id_compsData],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found Compsdata with the id_compsData
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated compsdata: ", { id_compsData: id_compsData, ...compsdata });
      result(null, { id_compsData: id_compsData, ...compsdata });
    }
  );
};

Compsdata.remove = (id_compsData, result) => {
  sql.query("DELETE FROM compsdata WHERE id_compsData = ?", id_compsData, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      // not found Compsdata with the id_compsData
      result({ kind: "not_found" }, null);
      return;
    }

    console.log("deleted compsdata with id_compsData: ", id_compsData);
    result(null, res);
  });
};

Compsdata.removeAll = result => {
  sql.query("DELETE FROM compsdata", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log(`deleted ${res.affectedRows} compsdata`);
    result(null, res);
  });
};

module.exports = Compsdata;

