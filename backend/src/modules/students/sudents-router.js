const express = require("express");
const router = express.Router();
const studentController = require("./students-controller");

const { authenticateToken } = require("../../middlewares/authenticate-token");
const { csrfProtection } = require("../../middlewares/csrf-protection");
const { serviceAuth } = require("../../middlewares/authenticate-service");

router.get("/service/:id", serviceAuth, studentController.handleGetStudentDetail);
router.get("", authenticateToken, studentController.handleGetAllStudents);
router.post("", authenticateToken, csrfProtection, studentController.handleAddStudent);
router.get("/:id", authenticateToken, studentController.handleGetStudentDetail);
router.post("/:id/status", authenticateToken, csrfProtection, studentController.handleStudentStatus);
router.put("/:id", authenticateToken, csrfProtection, studentController.handleUpdateStudent);

module.exports = { studentsRoutes: router };
