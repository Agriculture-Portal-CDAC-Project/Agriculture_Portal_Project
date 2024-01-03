
const mysql = require('mysql');
const config = require('config');

const connectionDetails = {
    server : config.get("server"),
    database : config.get("database"),
    password : config.get("password"),
    user : config.get("user")
}
var connection = mysql.createConnection(connectionDetails);

connection.connect;

module.exports = connection;