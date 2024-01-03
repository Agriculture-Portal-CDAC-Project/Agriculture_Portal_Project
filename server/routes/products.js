const mysql= require('mysql');
const express = require('express');
const config = require('config');
const utils =require('./utils');

const app = express.Router();

const connectionDetails = {
    server : config.get("server"),
    database : config.get("database"),
    password : config.get("password"),
    user : config.get("user")
}

app.get("/",(request,response)=>
{
    var connection = mysql.createConnection(connectionDetails);

    const statement =`select * from products`;
    
    connection.query(statement,(error , result)=>{
        if(error==null)
       {
            console.log(result);
            connection.end();
            response.send(JSON.stringify(utils.createResult(error,result)));

       }
       else
       {
          
            connection.end();
            response.send(JSON.stringify(utils.createResult(error,result)));
        
       }
    })
})

app.post("/",(request,response)=>
{

    product_name = request.body.product_name;
    product_price = request.body.product_price;
    product_quantity = request.body.product_quantity;
    description = request.body.description;
    image = request.body.image;
    category= request.body.category;


    var connection = mysql.createConnection(connectionDetails);

    const statement =`insert into products(product_name,product_price,product_quantity,description,image,category)
    values('${product_name}', ${product_price}, ${product_quantity}, '${description}', '${image}','${category}');`;
    
    connection.query(statement,(error , result)=>{
       if(error==null)
       {
            console.log(result);
            response.write(JSON.stringify(utils.createResult(error,result)));
            response.end();
            connection.end();

       }
       else
       {
           
           response.write(JSON.stringify(utils.createResult(error,result)));
           response.end();
           connection.end();
       }
    })
})

app.put("/:id",(request,response)=>
{
     pid = request.params.id;

    product_name = request.body.product_name;
    product_price = request.body.product_price;
    product_quantity = request.body.product_quantity;
    description = request.body.description;
    image = request.body.image;
    category= request.body.category;


    var connection = mysql.createConnection(connectionDetails);

    const statement =`update products set product_name='${product_name}',product_price = ${product_price},product_quantity = ${product_quantity}, description ='${description}', image ='${image}',category ='${category}' where id = ${pid};`
    
    connection.query(statement,(error , result)=>{
       if(error==null)
       {
            console.log(result);
            connection.end();
            response.send(JSON.stringify(utils.createResult(error,result)));

       }
       else
       {
          
            connection.end();
            response.send(JSON.stringify(utils.createResult(error,result)));
        
       }
    })
})

app.delete("/:id",(request,response)=>
{
     pid = request.params.id;
    var connection = mysql.createConnection(connectionDetails);

    const statement =`delete from products where id = ${pid};`
    
    connection.query(statement,(error , result)=>{
       if(error==null)
       {
            console.log(result);
            connection.end();
            response.send(JSON.stringify(utils.createResult(error,result)));

       }
       else
       {
          
            connection.end();
            response.send(JSON.stringify(utils.createResult(error,result)));
        
       }
    })
})

module.exports =app;
