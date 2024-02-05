const express = require("express")
const mysql = require("mysql")
const cors = require("cors")
const multer = require("multer")
const app = express()
const path = require('path');
const fs = require('fs');

app.use(cors("*"))
app.use(express.json())

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

app.get('/gets',(req,res)=>{

    const connection = mysql.createConnection(
        {
            host : "localhost",
            user : "root",
            password : "manager",
            database : "project"
        }
    )
    
    connection.query("select * from product",(error,result)=>{
        if(error == null){
            res.send("data : "+JSON.stringify(result));
            res.end()
            connection.end()
        }
        else{
            res.send("eroor : "+error);
            res.end()
        }
        
    })

   

})

// app.post('/upload',upload.single('file'),(req,res)=>{
//     res.send('File uploaded successfully');
//     res.end;
// });


app.post('/upload', upload.single('image'), (req, res) => {
    // req.file contains the details of the uploaded file
    if (!req.file) {
      return res.status(400).send('No file uploaded.');
    }
  
    const fileName = req.file.filename;
    res.status(200).send(`File ${fileName} uploaded successfully.`);
  });

  app.get('/images', (req, res) => {
    fs.readdir('uploads', (err, files) => {
      if (err) {
        return res.status(500).send('Error reading the directory.');
      }
  
      const images = files.filter(file => path.extname(file).toLowerCase() === '.jpg' || path.extname(file).toLowerCase() === '.png');
      res.status(200).json({ images });
    });
  });



app.get('/images/:imageName', (req, res) => {
    const imageName = req.params.imageName;
    const imagePath = path.join(__dirname, 'uploads', imageName);
    console.log('Image Path:', imagePath);
    res.sendFile(imagePath, (err) => {
      if (err) {
        res.status(500).send('Error sending the file.');
      }
    });
  });
  


app.listen(3000,()=>{console.log("server started at port : 3000")});