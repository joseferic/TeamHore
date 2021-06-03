const sql = require("./db.js");

// constructor
const Trxdata = function(trxdata) {
  this.id_userData = trxdata.id_userData;
  this.id_compsData = trxdata.id_compsData;
  this.path_trxData = trxdata.path_trxData;
  this.lvlalergy_trxData = trxdata.lvlalergy_trxData;
};

Trxdata.create = (newTrxdata, result) => {
  sql.query("INSERT INTO trxdata SET ?", newTrxdata, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("created trxdata: ", { id_trxData: res.insertId, ...newTrxdata });
    result(null, { id_trxData: res.insertId, ...newTrxdata });
  });
};

Trxdata.findById = (id_trxData, result) => {
  sql.query(`SELECT * FROM trxdata WHERE id_trxData = ${id_trxData}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found trxdata: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Trxdata with the id_trxData
    result({ kind: "not_found" }, null);
  });
};

Trxdata.getAll = result => {
  sql.query("SELECT * FROM trxdata", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("trxdata: ", res);
    result(null, res);
  });
};

Trxdata.updateById = (id_trxData, trxdata, result) => {
  sql.query(
    "UPDATE trxdata SET id_userData = ?, id_compsData = ?, path_trxData = ?, lvlalergy_trxData = ? WHERE id_trxData = ?",
    [trxdata.id_userData, trxdata.id_compsData, trxdata.path_trxData, trxdata.lvlalergy_trxData, id_trxData],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found Trxdata with the id_trxData
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated trxdata: ", { id_trxData: id_trxData, ...trxdata });
      result(null, { id_trxData: id_trxData, ...trxdata });
    }
  );
};

Trxdata.remove = (id_trxData, result) => {
  sql.query("DELETE FROM trxdata WHERE id_trxData = ?", id_trxData, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      // not found Trxdata with the id_trxData
      result({ kind: "not_found" }, null);
      return;
    }

    console.log("deleted trxdata with id_trxData: ", id_trxData);
    result(null, res);
  });
};

Trxdata.removeAll = result => {
  sql.query("DELETE FROM trxdata", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log(`deleted ${res.affectedRows} trxdata`);
    result(null, res);
  });
};

module.exports = Trxdata;

