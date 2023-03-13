import request from '@/utils/request' //引入封装好的 axios 请求
// import url from 'postcss-url'


export function getCustomerList(dataPackage) {
    return request({
      url: '/customer/list',
      method: 'get',
      params:dataPackage,
   
    })
};



export function searchCustomerList(key,dataPackage) {
  return request({
    url: `/customer/list/${key}`,
    method: 'get',
    params: dataPackage,
 
  })
};

export function AddCustomerList(dataPackage) {
    return request({
      url: '/customer',
      method: 'post',
      data: dataPackage,
  
    })
};

export function EditCustomerList(id,dataPackage) {
  return request({
    url: `/customer/${id}`,
    method: 'put',
    data: dataPackage,
 
  })
};

export function DelCustomerList(dataPackage) {
  return request({
    url: `/customer/${dataPackage}`,
    method: 'delete',

  })
};

export function addCustomerOrder(dataPackage) {
  return request({
    url: '/order',
    method: 'post',
    data: dataPackage,

  })
}