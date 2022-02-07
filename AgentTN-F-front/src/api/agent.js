var cookie = require('cookie');
import request from '../utils/request';
import Resource from './resource';
import axios from 'axios';

class AgentResource extends Resource {
  constructor() {
    super('agents');
  }

  getByID(id) {
    return request({
      url: '/agents/get/' + id,
      method: 'get',
    });
  }

  async getByEmail(agent) {

    let response = await axios.get('http://localhost:4000/api/agents/getE/'+ agent.email+'/'+agent.password,
    {
      withCredentials: true,
      crossDomain: true
     }).catch(err=>{
      console.log(err);
    });
    return response;

  }

}

export { AgentResource as default };
