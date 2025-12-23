const { ApiError } = require("../utils");
const { env } = require("../config");

const serviceAuth = (req, res, next) => {
    const key = req.headers["x-api-key"];

    console.log("SERVICE_API_KEY loaded:", !!env.SERVICE_API_KEY);

    if (!key || key !== env.SERVICE_API_KEY) {
        return next(new ApiError(401, "Unauthorized service"));
    }
    return next();
};

module.exports = { serviceAuth };
