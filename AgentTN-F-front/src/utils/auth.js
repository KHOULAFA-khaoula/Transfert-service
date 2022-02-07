import Cookies from 'js-cookie';
import jwt_decode from "jwt-decode";

const TokenKey = 'jwt';

export function isLogged() {
  // let res = await user.getByID(decoded.id);
  console.log(Cookies.get(TokenKey));
  if(Cookies.get(TokenKey)){
    console.log("IsLogged");
    var token = Cookies.get(TokenKey);
    var decoded = jwt_decode(token);
    console.log(decoded);
    localStorage.setItem('agentId',decoded.id);
    return true;
  }else{
    console.log("NotLogged");
    return false;
  }

}

export function JwtGetId() {
   var token = Cookies.get(TokenKey);
   var decoded = jwt_decode(token);
   return decoded.id;
}

export function setLogged(isLogged) {
  return Cookies.set(TokenKey, isLogged);
}

export async function removeToken() {
  return Cookies.remove(TokenKey);
}
