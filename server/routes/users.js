const mysql= require('mysql');
const express = require('express');
const config = require('config');
const utils =require('./utils');
// const jwt = require('jsonwebtoken'); // jsonWebToken for login validation 

const app = express.Router();

const connectionDetails = {
    server : config.get("server"),
    database : config.get("database"),
    password : config.get("password"),
    user : config.get("user")
}

// user registration 
app.post("/",(request,response)=>
{ 
    var first_name = request.body.first_name;
    var last_name = request.body.last_name;
    var email = request.body.email;
    var password = request.body.password;
    var mobile = request.body.mobile;
    var dob = request.body.dob;
    var address = request.body.address;
    var roll = request.body.roll;

    var connection = mysql.createConnection(connectionDetails);

     const statement =`insert into users(first_name, last_name, email, password, mobile,dob, address, roll) values('${first_name}','${last_name}','${email}','${password}','${mobile}','${dob}','${address}','${roll}')`;
     
     connection.query(statement,(error , result)=>{
        if(error==null)
        {
             connection.end();
            response.send(utils.createResult(error,result));

        }
        else
        {
            connection.end();
            response.send(utils.createResult(error,result));
        }
     })

});




app.get("/",(request, response)=>{
    var email = request.body.email;
    var password = request.body.password;

    var connection = mysql.createConnection(connectionDetails);

    const statement =`select * from users where email ='${email}' && password='${password}'`;
    
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
//user login
// app.get("/",(request, response)=>{
//     var email = request.body.email;
//     var password = request.body.password;

//     var connection = mysql.createConnection(connectionDetails);

//     const statement =`select count(*) as count from users where email ='${email}' && password='${password}'`;
    
//     connection.query(statement,(error , result)=>{
//        if(error==null)
//        {
//             console.log(result);
//             console.log(result.count[0])
            
//             if(result[0].count>0)
//             {
//                 var connection2 = mysql.createConnection(connectionDetails);
//                 const statement2 =`select * from users where email ='${email}' && password='${password}'`;
//                 connection2.query(statement2,(error2,result2)=>{
                   
//                     var payload ={
//                         "user_credentials" :result2
//                     }

//                     var token = jwt.sign(payload,key);
//                     console.log(token);

//                     var responseMessage ={
//                         loginToken :token,
//                         message : "success"
//                     }

//                     response.write(JSON.stringify(responseMessage));
                    
//                     response.end();
//                     connection2.end();


//                 });
            
//             }
//             else
//             {
//                 var responseMessage ={
//                     message : "failure"
//                 }

//                 response.write(JSON.stringify(responseMessage));
              
//                 response.end();
//                 connection2.end();
//             }
            
//             // connection.end();
//             // response.send(utils.createResult(error,result));

//        }
//        else
//        {
//            connection.end();
//            response.send(utils.createResult(error,result));
//        }
//     })
// })

// get all users
// app.get("/",(request, response)=>{

//     var connection = mysql.createConnection(connectionDetails);

//     const statement =`select * from users `;
    
//     connection.query(statement,(error , result)=>{
//        if(error==null)
//        {
//             connection.end();
//             response.send(utils.createResult(error,result));

//        }
//        else
//        {
//            connection.end();
//            response.send(utils.createResult(error,result));
//        }
//     })
// })

module.exports = app;
