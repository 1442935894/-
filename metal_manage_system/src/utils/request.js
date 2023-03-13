import axios from 'axios' //引入 axios
//import baseUrl from '../api/baseUrl' //使用环境变量 + 模式的方式定义基础URL
 
// 创建 axios 实例
// axios.defaults.headers.post['Content-Type'] = 'multipart/form-data;';
const token = localStorage.getItem('token')
const service = axios.create({
  baseURL: 'http://10.23.43.22:8081/', // api 的 base_url

  timeout: 15000, // 请求超时时间
//   transformRequest: [
//     function (data) {
//       let dataPackage = data.dataPackage;
//       return JSON.stringify(dataPackage);
//     }  
//   ],
})

// 请求拦截器
service.interceptors.request.use(
	config => {
    if (localStorage.getItem('token')) {
      config.headers.token = localStorage.getItem('token');
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  });





export default service
