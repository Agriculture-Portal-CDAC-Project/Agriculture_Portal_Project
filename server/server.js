const express= require('express');
const cors = require('cors');
const config = require('config');

const PORT = config.get("port");

const usersRouteHandler = require('./routes/users')
const productsRouteHandler = require('./routes/products')

const app = express();

app.use(cors());
app.use(express.json());


app.use("/users",usersRouteHandler);
app.use("/products",productsRouteHandler);

app.listen(PORT,()=>{console.log(`server started listening at ${PORT}`)});


