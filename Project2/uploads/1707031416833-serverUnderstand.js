//we need to process request of users and give associative response to them


const express=require('express');
const mysql=require('mysql');

const app = express();
app.use(express.json());
app.use((request, response,next)=>{
    //Below line allows calls from any domain / site b'coz of *
    response.setHeader("Access-Control-Allow-Origin", "*");
    
    //Below line allows calls with any method GET,PUT,POST, DELETE *
    response.setHeader("Access-Control-Allow-Methods", "*");

    //Below line allows calls with any Headers - even custom so *
    response.setHeader("Access-Control-Allow-Headers", "*");
    next();
})

app.get("/shravani",(request,response)=>{

    const connection = mysql.createConnection({
        host :"localhost", user:"root", password:"manager",database:"dmc"
    })

    var statement=`select * from users`;
    connection.query(statement,(error,result)=>{
        if(error==null)
        {
            response.setHeader("Content-Type","application/json");
            response.write(JSON.stringify(result));
            connection.end();
            response.end();
        }
        else
        {
            response.setHeader("Content-Type","application/json");
            response.write(JSON.stringify(error));
            connection.end();
            response.end();

        }
    })
});


//post...sends data from client 
//insert query

app.post("/shravani",(request,response)=>{

    const connection=mysql.createConnection({
        host:"localhost",user:"root",password:"manager",database:"dmc"
    });

    var No=request.body.No;
    var Name=request.body.Name;
    var Address=request.body.Address;


    var statement=`insert into users values(${No},'${Name}','${Address}')`
    connection.query(statement,(error,result)=>{
        if(error==null)
        {
            response.setHeader("Content-Type","application/json");
            response.write(JSON.stringify(result));
            connection.end();
            response.end();
        }
        else
        {
            response.setHeader("Content-Type","application/json");
            response.write(JSON.stringify(error));
            connection.end();
            response.end();

        }
    });

});


//put ...send data in header and body
//update query
//check for error in db connection & 
//in sql syntax

app.put("/shravani/:No",(request,response)=>{

    const connection=mysql.createConnection({
        host:"localhost",user:"root",password:"manager",database:"dmc"
    });

    var No=request.params.No;
    var Name=request.body.Name;
    var Address=request.body.Address;


    var statement=`update users set Name='${Name}',Address='${Address}' where No=${No}`
    connection.query(statement,(error,result)=>{
        if(error==null)
        {
            response.setHeader("Content-Type","application/json");
            response.write(JSON.stringify(result));
            connection.end();
            response.end();
        }
        else
        {
            response.setHeader("Content-Type","application/json");
            response.write(JSON.stringify(error));
            connection.end();
            response.end();

        }
    });

});


app.delete("/shravani/:No",(request,response)=>{

    const connection=mysql.createConnection({
        host:"localhost",user:"root",password:"manager",database:"dmc"
    });

    var No=request.params.No;
    var Name=request.body.Name;
    var Address=request.body.Address;


    var statement=`delete from users  where No=${No}`
    connection.query(statement,(error,result)=>{
        if(error==null)
        {
            response.setHeader("Content-Type","application/json");
            response.write(JSON.stringify(result));
            connection.end();
            response.end();
        }
        else
        {
            response.setHeader("Content-Type","application/json");
            response.write(JSON.stringify(error));
            connection.end();
            response.end();

        }
    });

});

app.listen(9898,()=>{console.log("Server Started at port 9898")});