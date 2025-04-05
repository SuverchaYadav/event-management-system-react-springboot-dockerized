const express = require("express");
const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
const db = require("../config/db"); // Ensure you have a DB connection file
const { verifyToken, isAdmin } = require("../middleware/authMiddleware");

const router = express.Router();

// Secret key for JWT
const JWT_SECRET = process.env.JWT_SECRET || "your_secret_key";

// ✅ User Registration
router.post("/register", async (req, res) => {
  const { name, email, password, role } = req.body;

  try {
    // Check if user already exists
    const [existingUser] = await db.promise().query("SELECT * FROM users WHERE email = ?", [email]);
    if (existingUser.length > 0) {
      return res.status(400).json({ message: "User already exists" });
    }

    // Hash the password
    const hashedPassword = await bcrypt.hash(password, 10);

    // Insert user into DB
    await db.promise().query("INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)", 
      [name, email, hashedPassword, role || 'user']
    );

    res.status(201).json({ message: "User registered successfully" });

  } catch (error) {
    console.error(error);
    res.status(500).json({ message: "Server error" });
  }
});

// ✅ User Login
router.post("/login", async (req, res) => {
  const { email, password } = req.body;

  try {
    // Find user
    const [user] = await db.promise().query("SELECT * FROM users WHERE email = ?", [email]);
    if (user.length === 0) {
      return res.status(400).json({ message: "Invalid credentials" });
    }

    const userData = user[0];  // ✅ Fix the variable name

    // Compare password
    const isMatch = await bcrypt.compare(password, userData.password);
    if (!isMatch) {
      return res.status(400).json({ message: "Invalid credentials" });
    }

    // Generate JWT
    const token = jwt.sign({ id: userData.id, role: userData.role }, JWT_SECRET, { expiresIn: "1h" });

    res.json({ 
      token, 
      user: { id: userData.id, name: userData.name, email: userData.email, role: userData.role } 
    });

  } catch (error) {
    console.error(error);
    res.status(500).json({ message: "Server error" });
  }
});


// ✅ Protected Route: Verify User Authentication
router.get("/verify", verifyToken, (req, res) => {
    res.json({ user: req.user });
  });
  
  // ✅ Protected Admin Route
  router.get("/admin-dashboard", verifyToken, isAdmin, (req, res) => {
    res.json({ message: "Welcome to the Admin Dashboard" });
  });
  
  // ✅ Protected User Dashboard Route
  router.get("/user-dashboard", verifyToken, (req, res) => {
    res.json({ message: "Welcome to the User Dashboard" });
  });
  
  module.exports = router;


