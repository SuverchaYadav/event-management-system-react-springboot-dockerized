const express = require("express");
const cors = require("cors");
require("dotenv").config();
const authRoutes = require("./routes/authRoutes");
const bodyParser = require("body-parser");
const db = require("./config/db");
const { verifyToken, isAdmin } = require("./middleware/authMiddleware");


const app = express();
//app.use(express.json());
app.use(cors());
app.use(bodyParser.json());
app.use("/api/auth", authRoutes);

// ✅ Protected Route Example: Admin Only
app.get("/api/admin-dashboard", verifyToken, isAdmin, (req, res) => {
    res.json({ message: "Welcome to the Admin Dashboard" });
  });
  
  // ✅ Protected Route Example: User Only
app.get("/api/user-dashboard", verifyToken, (req, res) => {
    res.json({ message: "Welcome to the User Dashboard" });
  });
  

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
