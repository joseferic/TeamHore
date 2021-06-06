const sql = require("./db.js");

// constructor
const Userdata = function(userdata) {
  this.email_userData = userdata.email_userData;
  this.password_userData = userdata.password_userData;
  this.name_userData = userdata.name_userData;
  this.allergy_userData = userdata.allergy_userData;
  this.pref_userData = userdata.pref_userData;
};

Userdata.create = (newUserdata, result) => {
  sql.query("INSERT INTO userdata SET ?", newUserdata, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("created userdata: ", { id_userData: res.insertId, ...newUserdata });
    result(null, { id_userData: res.insertId, ...newUserdata });
  });
};

Userdata.findById = (id_userData, result) => {
  sql.query(`SELECT * FROM userdata WHERE id_userData = ${id_userData}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found userdata: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Userdata with the id_userData
    result({ kind: "not_found" }, null);
  });
};

Userdata.getAll = result => {
  sql.query("SELECT * FROM userdata", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("userdata: ", res);
    result(null, res);
  });
};

Userdata.updateById = (id_userData, userdata, result) => {
  sql.query(
    "UPDATE userdata SET email_userData = ?, password_userData = ?, name_userData = ?, allergy_userData = ?, pref_userData = ? WHERE id_userData = ?",
    [userdata.email_userData, userdata.password_userData, userdata.name_userData, userdata.allergy_userData, userdata.pref_userData, id_userData],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found Userdata with the id_userData
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated userdata: ", { id_userData: id_userData, ...userdata });
      result(null, { id_userData: id_userData, ...userdata });
    }
  );
};

Userdata.remove = (id_userData, result) => {
  sql.query("DELETE FROM userdata WHERE id_userData = ?", id_userData, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      // not found Userdata with the id_userData
      result({ kind: "not_found" }, null);
      return;
    }

    console.log("deleted userdata with id_userData: ", id_userData);
    result(null, res);
  });
};

Userdata.removeAll = result => {
  sql.query("DELETE FROM userdata", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log(`deleted ${res.affectedRows} userdata`);
    result(null, res);
  });
};

module.exports = Userdata;

