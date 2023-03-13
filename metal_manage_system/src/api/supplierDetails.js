import request from '@/utils/request'; //引入封装好的 axios 请求


export function getSDList(dataPackage) {
    return request({
      url: '/supplier/list',
      method: 'get',
      params:dataPackage,
 
    })
  };
  export function searchSDList(key,dataPackage) {
    return request({
      url: `/supplier/list/${key}`,
      method: 'get',
      params: dataPackage,
  
    })
  };
  
  export function AddSDList(dataPackage) {
      return request({
        url: '/supplier',
        method: 'post',
        data: dataPackage,
    
      })
  };
  
  export function EditSDList(id,dataPackage) {
    return request({
      url: `/supplier/${id}`,
      method: 'put',
      data: dataPackage,
    
    })
  };
  
  export function DelSDList(dataPackage) {
    return request({
      url: `/supplier/${dataPackage}`,
      method: 'delete',
 
    })
  };