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

//getting All Cart products of current user
//http://localhost:9898/cart/getAll/1
app.get("/getAll/:u_id",(req,res)=>{
   
    const { u_id } = req.params
    const query = "select * from product inner join cart on product.id = cart.p_id where u_id=?";
    const values = [u_id]

    db.query(query,values,(err,result)=>{
        res.send(utils.createResult(err,result));
    });
});


//inserting in cart for particular user
//1st check same entry exists or not if yes update previous???????????? remaining
app.post("/addCart",(req,res)=>{
    const {u_id,p_id,qty} = req.body;

    const query="insert into cart(u_id,p_id,qty) values(?,?,?)";
    const values = [u_id,p_id,qty];
    db.query(query,values,(err,result)=>{
        res.send(utils.createResult(err,result));
        
    });
});




//updating qty of product
//patch is used when we want to update an small part not entire part//if we wish entire then use post
app.patch("/updateName/:u_id/:qty",(req,res)=>{
    const {u_id,qty} = req.params;

    const query="update cart set qty=? where u_id=?";
    const values = [qty,u_id];

    db.query(query,values,(err,result)=>{
        res.send(utils.createResult(err,result));
    });
});


// delete cart by id for particular user(by its id)
 
app.delete("/delete/:p_id/:u_id",(req,res)=>{
    const { u_id , p_id } = req.params;

    const query = "delete from cart where u_id=? and p_id=?"
    const values = [u_id,p_id]

    db.query(query,values,(err,result)=>{
        res.send(utils.createResult(err,result));
    });

});


module.exports =app;