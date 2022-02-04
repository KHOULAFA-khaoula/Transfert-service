const pool = require("../../config/database");

module.exports = {
    //method to create new Agent
    create: (data,callBack)=> {
        pool.query(
            `insert into agents(email,password)
                        values(?,?)`,

            [
                data.email,
                data.password,
            ],
            (error , results,fields)=>{
                if(error){
                   return callBack(error);
                }
                return callBack(null,results);
            }
        );
    },
    createU: (data)=> {
        pool.query(
            `insert into agents(email,password)
                        values(?,?)`,

            [
                data.email,
                data.password,
            ],
            (error , results,fields)=>{
                if(error){
                   return console.log(error);
                }
                return console.log(null,results);
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
            `select * from agents where AgentId=?`,
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
    updateAgent: (data,callBack)=> {
        pool.query(
            `update agents set email=?,password=? where AgentId=?`,

            [
                data.email,
                data.password,
                data.AgentId
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
            `delete from agents where AgentId = ?`,

            [
                data.AgentId
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
