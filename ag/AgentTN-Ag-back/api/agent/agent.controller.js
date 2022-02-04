const {create,
    getAgents,
    getAgentsById,
    updateAgentSolde,
    deletAgent,
    getAgentsByEmail} = require("./agent.service");
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
require("dotenv").config();

module.exports = {
    createAgent: async(req,res)=>{
        const body = req.body;
        let hashedPassword = await bcrypt.hash(body.password,8);
        body.password = hashedPassword;
        create(body,(err,results)=>{
            if(err){
                console.log(err);
                return res.status(500).json({
                    success: 0,
                    message:"Database connection error"
                });
            }
            return res.status(200).json({
                success:1,
                data:results
            })
        });
    },
    GetAgentsById:(req,res)=>{
        const id = req.params.id;
        getAgentsById(id,(err,results)=>{
            if(err){
                console.log(err);
                return;
            }
            if(!results){
                return res.json({
                    success:0,
                    message:"Records not Fount"
                });
            }
            return res.json({
                email:results.email,
                password:results.password,
                company:results.company,
                firstName:results.firstName,
                lastName:results.lastName,
                address:results.address,
                city:results.city,
                country :results.country, 
                about:results.about,
                postal:results.postal,
                solde:results.solde,
            });
        });
    },
    getAgentsByEmail:(req,res)=>{
      const email = req.params.email;
      const password = req.params.password
      console.log(email,password);
      getAgentsByEmail(email,async(err,results)=>{
          if(err){
              console.log(err);
              return;
          }
          if(!results || !(await bcrypt.compare(password,results.password))){
              return res.json({
                  success:0,
                  message:"Records not Fount"
              });
          }
          else{
            const id = results.AgentId;
            const token = jwt.sign({ id } ,process.env.JWT_SECRET, {
              expiresIn: process.env.JWT_EXPIRES_IN
            });
            console.log("The token is : "+token);
            const cookieOptions = {
              expires : new Date(
                Date.now() + process.env.JWT_COOKIE_EXPIRES * 24 * 60 * 60 * 1000
              ),
              httplOnly: true,
              SameSite: 'Lax',
	          secure:false,
            };
          return res
          .cookie('jwt', token, cookieOptions)
          .status(200)
          .json({
            success:1,
            message:"Records Found"
          });
         }

      });
  },
  checkEmail:(req,res)=>{
    const email = req.params.email;
    getAgentsByEmail(email,async(err,results)=>{
        if(err){
            console.log(err);
            return;
        }
        if(!results){
            return res.json({
                success:0,
                message:"Records not Fount"
            });
        }
        else{
        return res.json({
          success:1,
          message:"Records Found"
        });
       }

    });
},
    //controller get all Agents
    getAgents:(req,res)=>{
        getAgents((err,results)=>{
            if(err){
                console.log(err);
                return;
            }
            if(!results){
                return res.json({
                    success:0,
                    message:"Records not Fount"
                });
            }
            return res.json({
                success:1,
                data:results
            });
        });
    },
    //controller update Agents
    updateAgent:(req,res)=>{
        const body = req.body;
        updateAgentSolde(body,(err,results)=>{
            if(err){
                console.log(err);
                return;
            }
            if(!results){
                return res.json({
                    success:0,
                    message:"failed to update Agent"
                });
            }
            return res.json({
                success:1,
                message:"update successfully"
            });
        });
    },
    //controller to delete a Agent
    deletAgent:(req,res) => {
        const data = req.body;
        deletAgent(data,(err,results)=>{
            if(err){
                console.log(err);
                return;
            }
            if(!results){
                return res.json({
                    success:0,
                    message:"Records not Fount"
                });
            }
            return res.json({
                success:1,
                message:"Agent deleted succfully"
            });
        });
    },
}
