const express = require('express');
const AgentRouter = require('./api/agent/agent.router');
var cookieSession = require("cookie-session");
const cors = require("cors");
const cookieParser = require('cookie-parser');
const eurekaHelper = require('./eureka-helper');



const app = express();
app.use(express.json());
app.use(cookieParser());
app.use(express.urlencoded({extended:false}));
app.use((req,res,next)=>{
    res.header('Access-Control-Allow-Origin', req.headers.origin);
    res.header('Access-Control-Allow-Credentials', true);
    res.header(
      "Access-Control-Allow-Headers",
      "Origin,X-Requested-With,Content-Type,Accept,Authorization"
    );
    if(req.method === 'OPTIONS'){
      req.header('Access-Control-Allow-Methods','PUT,POST,Patch,DELETE,GET');
      return res.status(200).json({});
    }
    next();
  });
  app.set('trust proxy', 1)
  app.use(
      cookieSession({
        name: "__session",
        keys: ["key1"],
          maxAge: 24 * 60 * 60 * 100,
          secure: false,
          httpOnly: true,
          sameSite: 'Lax'
      })
  );
app.use(cors({credentials: true,origin: ["*"],}));
app.use('/api/agents',AgentRouter);

let port = process.env.PORT || 4000;

app.listen(port,()=>{
    console.log(`Listening on ${port}`);
});

//eurekaHelper.registerWithEureka('AGENT-SERVICE', 4000);
