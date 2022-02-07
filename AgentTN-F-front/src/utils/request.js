import axios from 'axios';


// Create axios instance
const service = axios.create({
  baseURL: "http://localhost:4000/api",
  timeout: 50000, // Request timeout
});

// Request intercepter
service.interceptors.request.use(
  config => {
    return config;
  },
  error => {
    // Do something with request error
    console.log(error); // for debug
    Promise.reject(error);
  }
);

// response pre-processing
service.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    console.log(error); // for debug
    return Promise.reject(error);
  }
);

export default service;
