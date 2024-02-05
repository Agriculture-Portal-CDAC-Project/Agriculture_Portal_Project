
const express = require('express');
const config = require('config');
const cors = require("cors")
const PORT = config.get("port");


const app = express()


const productHandlerApp = require('./routes/product');
const categoryHandlerApp = require('./routes/category');
const cartHandlerApp = require('./routes/cart');

app.use(cors("*"))
app.use(express.json())

app.use("/product", productHandlerApp);
app.use("/category",categoryHandlerApp);
app.use("/cart",cartHandlerApp)


app.listen(PORT, ()=>{console.log(`server started listening at port ${PORT}`);});