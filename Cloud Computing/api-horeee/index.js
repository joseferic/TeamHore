const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const mysql = require('mysql');
const multer = require('multer')
const multerMid = multer({
    storage: multer.memoryStorage(),
    limits: {
        fileSize: 5 * 1024 * 1024,
    },
})
const uploadImage = require('./helpers/helpers')


// parse application/json
app.use(bodyParser.json());

//create database connection
const conn = mysql.createConnection({
  host: '34.136.79.167',
  user: 'root',
  password: 'db-check',
  database: 'check_product'
});

//connect to database
conn.connect((err) =>{
  if(err) throw err;
  console.log('Mysql Connected...');
});

    

    
app.disable('x-powered-by')
app.use(multerMid.single('file'))
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended: false}))

app.post('/uploads', async (req, res, next) => {
  try {
    const myFile = req.file
    const imageUrl = await uploadImage(myFile)
    res
      .status(200)
      .json({
        message: "Upload was successful",
        data: imageUrl
      })
  } catch (error) {
    next(error)
  }
})

app.use((err, req, res, next) => {
  res.status(500).json({
    error: err,
    message: 'Internal server error!',
  })
  next()
})
//tampilkan semua data compsdata
app.get('/compsdata',(req, res) => {
  let sql = "SELECT * FROM compsdata";
  let query = conn.query(sql, (err, results) => {
    if(err) throw err;
    res.send(JSON.stringify({"status": 200, "error": null, "response": results}));
  });
});

//tampilkan semua data fooddata
app.get('/fooddata',(req, res) => {
  let sql = "SELECT * FROM fooddata";
  let query = conn.query(sql, (err, results) => {
    if(err) throw err;
    res.send(JSON.stringify({"status": 200, "error": null, "response": results}));
  });
});

//tampilkan semua data userdata
app.get('/userdata',(req, res) => {
  let sql = "SELECT * FROM userdata";
  let query = conn.query(sql, (err, results) => {
    if(err) throw err;
    res.send(JSON.stringify({"status": 200, "error": null, "response": results}));
  });
});

//tampilkan data fooddata berdasarkan id
app.get('/fooddata/:id',(req, res) => {
  let sql = "SELECT * FROM fooddata WHERE id_foodData="+req.params.id;
  let query = conn.query(sql, (err, results) => {
    if(err) throw err;
    res.send(JSON.stringify({"status": 200, "error": null, "response": results}));
  });
});

//tampilkan data userdata berdasarkan id
app.get('/userdata/:id',(req, res) => {
  let sql = "SELECT * FROM userdata WHERE id_userData="+req.params.id;
  let query = conn.query(sql, (err, results) => {
    if(err) throw err;
    res.send(JSON.stringify({"status": 200, "error": null, "response": results}));
  });
});

//Tambahkan data userdata baru
app.post('/userdata',(req, res) => {
  let data = {email_userData: req.body.email_userData, password_userData: req.body.password_userData, name_userData: req.body.name_userData, id_foodData: req.body.id_foodData, allergy_userData: req.body.allergy_userData, pref_userData: req.body.pref_userData};
  let sql = "INSERT INTO userdata SET ?";
  let query = conn.query(sql, data,(err, results) => {
    if(err) throw err;
    res.send(JSON.stringify({"status": 200, "error": null, "response": results}));
  });
});

//Edit data userdata berdasarkan id
app.put('/userdata/:id',(req, res) => {
  let sql = "UPDATE userdata SET email_userData='"+req.body.email_userData+"', password_userData='"+req.body.password_userData+"' WHERE id_userData="+req.params.id;
  let query = conn.query(sql, (err, results) => {
    if(err) throw err;
    res.send(JSON.stringify({"status": 200, "error": null, "response": results}));
  });
});

//Delete data userdata berdasarkan id
app.delete('/userdata/:id',(req, res) => {
  let sql = "DELETE FROM userdata WHERE id_userData="+req.params.id+"";
  let query = conn.query(sql, (err, results) => {
    if(err) throw err;
      res.send(JSON.stringify({"status": 200, "error": null, "response": results}));
  });
});

//Server listening
app.listen(8080,() =>{
  console.log('Server started on port 8080...');
});


