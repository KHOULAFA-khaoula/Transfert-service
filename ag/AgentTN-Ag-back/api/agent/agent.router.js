
const {getAgentsById,updateAgentSolde} = require("./agent.service");
const { createAgent,
    GetAgentsById,
    getAgents,
    updateAgent,
    deletAgent,
  getAgentsByEmail,checkEmail } = require("./Agent.controller");
const router = require("express").Router();

router.post("/insert",createAgent);
router.get("/getAll",getAgents);
router.get("/get/:id",GetAgentsById);
router.get("/getE/:email/:password",getAgentsByEmail);
router.get("/checkE/:email",checkEmail);
router.patch("/update",updateAgent);
router.patch("/updateBalance/:id/:amount/:operation",(req,res)=>{
        const id = req.params.id;
        const amount = req.params.amount;
        const operation = req.params.operation;

        console.log(id,amount,operation);
        getAgentsById(id,(err,results)=>{
          if(err){
            console.log(err);
          }
          if(results)
            var newSolde = results.solde;
            if(operation == "Ajouter"){
              newSolde +=amount;
            }else{
              newSolde -=amount;
            }
            console.log(newSolde);
            updateAgentSolde({agentId:id,solde:newSolde},(err,results)=>{
              if(err){
                console.log(err);
              }
              if(results){
                return res.json({
                  message:"success",
                })
              }
            });
          })
        })

router.delete("/delete",deletAgent);


module.exports = router;
