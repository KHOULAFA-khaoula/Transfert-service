var cookie = require('cookie');
import request from '../utils/request';
import Resource from './resource';
import axios from 'axios';

class ClientResource extends Resource {
  constructor() {
    super('clients');
  }

  async getAll(){
    console.log("rak temma");
  let response = await axios.get('http://localhost:9003/clients/',{
    crossDomain: true
   })
  .catch(err=>{
    console.log(err);
  });
  return response;
}


  async insertClient(client) {

    let response = await axios.post('http://localhost:9003/clients/',client).catch(err=>{
      console.log(err);
    });
    return response;

  }

}

export { ClientResource as default };
