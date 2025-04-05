const db = require("../config/db");

const createUserTable = () => {
  const sql = `CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    role ENUM('admin', 'customer') DEFAULT 'customer'
  )`;
  db.query(sql, (err) => {
    if (err) throw err;
    console.log("Users table created");
  });
};

createUserTable();
