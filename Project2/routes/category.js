const express = require('express');
const mysql = require('mysql');
const app = express.Router();
const config = require('config')


const utils = require("../utils")

const db = mysql.createConnection({
    host : config.get("host"),
    user : config.get("user"),
    password : config.get("password"),
    database : config.get("database")
})

//get All Category
app.get("/getAll",(req,res)=>{
   
    const query="select * from category";
  
    db.query(query,(err,result)=>{
        res.send(utils.createResult(err,result));
    });
});


//inserting category//we can 
app.post("/addCategory",(req,res)=>{
    const {name} = req.body;

    const query="insert into category(name,created_at) values(?,now())";
    const values = [name];
    db.query(query,values,(err,result)=>{
        res.send(utils.createResult(err,result));
        
    });
});




//updating category
app.put("/updateName/:name/:id",(req,res)=>{
    const {name,id} = req.params;

    const query="update category set name=?,modified_at=now() where id=?";
    const values = [name,id];

    db.query(query,values,(err,result)=>{
        res.send(utils.createResult(err,result));
    });
});


//delete product of user 
app.delete("/delete/:id",(req,res)=>{
    const { id } = req.params;

    const query = "delete from category where id = ?"
    const values = [id]

    db.query(query,values,(err,result)=>{
        res.send(utils.createResult(err,result));
    });

});


module.exports =app;