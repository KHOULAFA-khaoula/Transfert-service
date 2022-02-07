var cookie = require('cookie');
import request from '../utils/request';
import Resource from './resource';
import axios from 'axios';

class  TransactionResource extends Resource {
  constructor() {
    super('transactions');
  }



  async insertTransfert(tx) {

    let response = await axios.post('http://localhost:9020/transferts/emettre',tx).catch(err=>{
      console.log(err);
    });
    return response;

  }

  async getTransferts() {
    let response = await axios.get('http://localhost:9020/transferts/agent/'+localStorage.agentId).catch(err=>{
      console.log(err);
    });
    return response;

  }

  async servirTransferts(ref) {
    let response = await axios.post('http://localhost:9020/transferts/servir/PVW/'+ref).catch(err=>{
      console.log(err);
    });
    return response;

  }
}

export { TransactionResource as default };
