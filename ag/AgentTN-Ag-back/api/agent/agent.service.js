const pool = require("../../config/database");

module.exports = {
    //method to create new Agent
    create: (data,callBack)=> {
        pool.query(
            `insert into agents(email,password,company,firstName,lastName,address,city,country, about,postal,solde)
             values(?,?,?,?,?,?,?,?,?,?,?)`,

            [
                data.email,
                data.password,
                data.company,
                data.firstName,
                data.lastName,
                data.address,
                data.city,
                data.country, 
                data.about,
                data.postal,
                data.solde

            ],
            (error , results,fields)=>{
                if(error){
                   return callBack(error);
                }
                return callBack(null,results);
            }
        );
    },

    //Method to get all agents
    getAgents: callBack => {
        pool.query(
            `select * from agents`,
            [],
            (err,results,fields) => {
                if (err) {
                   return callBack(err);
                }
                return callBack(null,results);
            }
        )
    },
    //method to get Agent by id
    getAgentsById: (id, callBack) => {
        pool.query(
            `select * from agents where agentId=?`,
            [id],
            (err,results,fields) => {
                if (err) {
                   return callBack(err);
                }
                return callBack(null,results[0]);
            }
        )
    },
    //method to get Agent by email
    getAgentsByEmail: (email, callBack) => {
      pool.query(
          `select * from agents where email=?`,
          [email],
          (err,results,fields) => {
              if (err) {
                 return callBack(err);
              }
              return callBack(null,results[0]);
          }
      )
  },
    //update Agent
    updateAgentSolde: (data,callBack)=> {
        pool.query(
            `update agents set solde=? where agentId=?`,

            [
                //update solde
                data.solde,
                data.agentId,

            ],
            (error , results,fields)=>{
                if(error){
                   return callBack(error);
                }
                return callBack(null,results);
            }
        );
    },
    //delete Agent
    deletAgent: (data,callBack)=> {
        pool.query(
            `delete from agents where agentId = ?`,

            [
                data.agentId
            ],
            (error , results,fields)=>{
                if(error){
                   return callBack(error);
                }
                return callBack(null,results);
            }
        );
    }

};
