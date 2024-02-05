const express = require('express');
const mysql = require('mysql');
const app = express.Router();
const config = require('config')
const multer = require("multer")
const path = require('path');
const fs = require('fs');
const utils = require("../utils");
const { error } = require('console');

const db = mysql.createConnection({
    host : config.get("host"),
    user : config.get("user"),
    password : config.get("password"),
    database : config.get("database")
})

// Connect to MySQL
// db.connect(err => {
//     if (err) {
//       console.error('Error connecting to MySQL:', err);
//       return;
//     }
//     console.log('Connected to MySQL');
//   });
  

// app.get('/',(req,res)=>{
//     res.write("Get working");
//     res.end();
// })

//app.use('/uploads', express.static(path.join(__dirname, 'uploads')));

const storage = multer.diskStorage({
    destination :(req,file, cb)=>{
        cb(null,'uploads/');
    },
    filename: (req,file,cb)=>{
        cb(null,Date.now()+ '-'+file.originalname);
    },
});

const fileFilter = function (req, file, cb) {
    const allowedFileTypes = /png|jpg/;
    const extname = allowedFileTypes.test(path.extname(file.originalname).toLowerCase());
    const mimetype = allowedFileTypes.test(file.mimetype);
  
    if (extname && mimetype) {
      return cb(null, true);
    } else {
      return cb(new Error('Only PNG and JPG files are allowed!'), false);
    }
  };

const upload = multer({ storage: storage, fileFilter: fileFilter });


//get all products
app.get("/getAll",(req,res)=>{
    const query = "select * from product";
    db.query(query,(err,result)=>{

        res.send(utils.createResult(err,result));
    })
})


//get products by name
app.get("/findByName/:name",(req,res)=>{
    const {name} = req.params;

    const query = "select * from product where name=?";
    const values = [name];
  
    db.query(query,values,(err,result)=>{

        res.send(utils.createResult(err,result));
    })
});

//getting products within range
app.get("/findByPrice/:price",(req,res)=>{
  const {price} = req.params;

  const query = "select * from product where price between 1 and ?";
  
  const values = [price];

  db.query(query,values,(err,result)=>{

      res.send(utils.createResult(err,result));
  })
});

//getting products by category name
app.get("/findByCategory/:name",(req,res)=>{
  
    const { name } = req.params;
    
    const query = "select * from product where cat_id=(select id from category where name=?)";
    const values = [ name ]
    
    db.query(query,values,(error,result)=>{
        res.send(utils.createResult(error,result));
    });

});

  //get names all images
  app.get('/images', (req, res) => {
    fs.readdir('uploads', (err, files) => {
      if (err) {
        return res.status(500).send('Error reading the directory.');
      }
  
      const images = files.filter(file => path.extname(file).toLowerCase() === '.jpg' || path.extname(file).toLowerCase() === '.png');
      res.status(200).json({ images });
    });
  });




  //get all image by image name
  app.get('/images/:imageName', (req, res) => {
    const imageName = req.params.imageName;
    // const imagePath = path.join(__dirname, 'uploads', imageName);
    const imagePath = path.join(__dirname, '..', 'uploads', imageName);
    console.log('Image Path:', imagePath);
    res.sendFile(imagePath, (err) => {
      if (err) {
        res.status(500).send('Error sending the file.?');
      }
    });
  });
  

//insert all data in product table including the image through form-data
app.post('/products', upload.single('image'), (req, res) => {
    const { name, price, description, cat_id, qty } = req.body;
    
   
    const image = req.file ? req.file.filename : null;
  
    const query = 'INSERT INTO product (name, price, description, cat_id, qty, image,created_at) VALUES (?, ?, ?, ?, ?, ?,now())';
    const values = [name, price, description, cat_id, qty, image];
  
    db.query(query, values, (err, result) => {
      if (err) {
        console.error('Error inserting product:', err);
        return res.status(500).json({ success: false, error: 'Internal Server Error' });
      }
  
      const productId = result.insertId;
      res.status(201).json({ success: true, message: 'Product created successfully', productId });
    });
  });


//updating image by id 
  app.put('/update/image/:id', upload.single('image'), (req, res) => {
    const { id } = req.params;
    
   
    const image = req.file ? req.file.filename : null;
  
    const query = 'update product set image = ?,modified_at=now() where id =?';
    const values = [image,id];
  
    db.query(query, values, (err, result) => {
      if (err) {
        console.error('Error inserting product:', err);
        return res.status(500).json({ success: false, error: 'Internal Server Error' });
      }
      const productId = result.insertId;
      res.status(201).json({ success: true, message: 'Product created successfully', productId });
    });
  });


  // we can write update api to update product info by id as well ???????????????



  //for delete product by id

  app.delete("/delete/:id",(req,res)=>{
    const {id} = req.params;

    const query = "delete from product where id = ?";
    const values = [id];
  
    db.query(query,values,(err,result)=>{
         res.send(utils.createResult(err,result));
    })
});

module.exports =app;