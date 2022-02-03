
const { createAgent,
    getAgentsById,
    getAgents,
    updateAgent,
    deletAgent,
  getAgentsByEmail,checkEmail } = require("./Agent.controller");
const router = require("express").Router();

router.post("/insert",createAgent);
router.get("/getAll",getAgents);
router.get("/get/:id",getAgentsById);
router.get("/getE/:email/:password",getAgentsByEmail);
router.get("/checkE/:email",checkEmail);
router.patch("/update",updateAgent);
router.delete("/delete",deletAgent);


module.exports = router;
