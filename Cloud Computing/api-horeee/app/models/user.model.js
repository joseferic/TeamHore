const sql = require("./db.js");

// constructor
const User = function(user) {
  this.name_User = user.name_User;
  this.email_User = user.email_User;
  this.pass_User = user.pass_User;
};

User.create = (newUser, result) => {
  sql.query("INSERT INTO user SET ?", newUser, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("created user: ", { id_User: res.insertId, ...newUser });
    result(null, { id_User: res.insertId, ...newUser });
  });
};

User.findById = (id_User, result) => {
  sql.query(`SELECT * FROM user WHERE id_User = ${id_User}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found user: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found User with the id_User
    result({ kind: "not_found" }, null);
  });
};

User.getAll = result => {
  sql.query("SELECT * FROM user", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("user: ", res);
    result(null, res);
  });
};

User.updateById = (id_User, user, result) => {
  sql.query(
    "UPDATE user SET name_User = ?, email_User = ?, pass_User = ? WHERE id_User = ?",
    [user.name_User, user.email_User, user.pass_User, id_User],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found User with the id_User
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated user: ", { id_User: id_User, ...user });
      result(null, { id_User: id_User, ...user });
    }
  );
};

User.remove = (id_User, result) => {
  sql.query("DELETE FROM user WHERE id_User = ?", id_User, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      // not found User with the id_User
      result({ kind: "not_found" }, null);
      return;
    }

    console.log("deleted user with id_User: ", id_User);
    result(null, res);
  });
};

User.removeAll = result => {
  sql.query("DELETE FROM user", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log(`deleted ${res.affectedRows} user`);
    result(null, res);
  });
};

module.exports = User;

