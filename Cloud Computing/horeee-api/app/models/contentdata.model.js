const sql = require("./db.js");

// constructor
const Contentdata = function(contentdata) {
  this.cat_contentData = contentdata.cat_contentData;
  this.image_contentData = contentdata.image_contentData;
  this.title_contentData = contentdata.title_contentData;
  this.content_contentData = contentdata.content_contentData;
};

Contentdata.create = (newContentdata, result) => {
  sql.query("INSERT INTO contentdata SET ?", newContentdata, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("created contentdata: ", { id_foodData: res.insertId, ...newContentdata });
    result(null, { id_foodData: res.insertId, ...newContentdata });
  });
};

Contentdata.findById = (id_foodData, result) => {
  sql.query(`SELECT * FROM contentdata WHERE id_foodData = ${id_foodData}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found contentdata: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Contentdata with the id_foodData
    result({ kind: "not_found" }, null);
  });
};

Contentdata.getAll = result => {
  sql.query("SELECT * FROM contentdata", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("contentdata: ", res);
    result(null, res);
  });
};

Contentdata.updateById = (id_foodData, contentdata, result) => {
  sql.query(
    "UPDATE contentdata SET cat_contentData = ?, image_contentData = ?, title_contentData = ?, content_contentData = ? WHERE id_foodData = ?",
    [contentdata.cat_contentData, contentdata.image_contentData, contentdata.title_contentData, contentdata.content_contentData, id_foodData],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found Contentdata with the id_foodData
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated contentdata: ", { id_foodData: id_foodData, ...contentdata });
      result(null, { id_foodData: id_foodData, ...contentdata });
    }
  );
};

Contentdata.remove = (id_foodData, result) => {
  sql.query("DELETE FROM contentdata WHERE id_foodData = ?", id_foodData, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      // not found Contentdata with the id_foodData
      result({ kind: "not_found" }, null);
      return;
    }

    console.log("deleted contentdata with id_foodData: ", id_foodData);
    result(null, res);
  });
};

Contentdata.removeAll = result => {
  sql.query("DELETE FROM contentdata", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log(`deleted ${res.affectedRows} contentdata`);
    result(null, res);
  });
};

module.exports = Contentdata;

