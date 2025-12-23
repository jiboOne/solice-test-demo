const asyncHandler = require("express-async-handler");
const {
    getAllStudents,
    addNewStudent,
    getStudentDetail,
    setStudentStatus,
    updateStudent,
} = require("./students-service");

/**
 * GET /students?name=&className=&section=&roll=
 */
const handleGetAllStudents = asyncHandler(async (req, res) => {
    const {name, className, section, roll} = req.query;

    const students = await getAllStudents({
        name,
        className,
        section,
        roll,
    });

    res.status(200).json({data: students});
});

/**
 * POST /students
 */
const handleAddStudent = asyncHandler(async (req, res) => {
    // body should contain whatever your stored proc expects
    const payload = req.body;

    // If you want to store who created student:
    // payload.reporterId = req.user?.id;

    const result = await addNewStudent(payload);
    res.status(201).json(result);
});

/**
 * PUT /students/:id
 */
const handleUpdateStudent = asyncHandler(async (req, res) => {
    const id = Number(req.params.id);
    const payload = {...req.body, id};

    const result = await updateStudent(payload);
    res.status(200).json(result);
});

/**
 * GET /students/:id
 */
const handleGetStudentDetail = asyncHandler(async (req, res) => {
    const id = Number(req.params.id);

    const student = await getStudentDetail(id);
    res.status(200).json({data: student});
});

/**
 * POST /students/:id/status
 * body: { status: true/false }
 */
const handleStudentStatus = asyncHandler(async (req, res) => {
    const userId = Number(req.params.id);
    const {status} = req.body;

    // reviewerId should come from auth middleware (req.user)
    const reviewerId = req.user?.id;

    const result = await setStudentStatus({
        userId,
        reviewerId,
        status,
    });

    res.status(200).json(result);
});

module.exports = {
    handleGetAllStudents,
    handleGetStudentDetail,
    handleAddStudent,
    handleStudentStatus,
    handleUpdateStudent,
};
