import request from '@/utils/request' //引入封装好的 axios 请求
const token = localStorage.getItem('token')

export function getCustomerOrderList(dataPackage) {
    return request({
      url: `/order/list`,
      method: 'get',
      params: dataPackage,
 
    })
};

export function reMarkCustomOrder(id,dataPackage) {
  return request({
    url: `/order/finish/${id}`,
    method: 'put',
    data: dataPackage,

  })
};

export function updateCustomOrder(id,dataPackage) {
  return request({
    url: `/order/${id}`,
    method: 'put',
    data: dataPackage,

  })
};

export function selectCustomerOrder(id,params) {
  return request({
    url: `/order/list/customer/user/${id}`,
    method: 'get',
    params: params,

  })
};

export function searchCustomOrder(key,params) {
  return request({
    url: `/order/list/${key}`,
    method: 'get',
    params: params,

  })
};

export function updateOrderPayStatus(id,params){
  return request({
    url: `/order/pay/${id}`,
    method: 'put',
    data: params,

  })
};

export function updateOrderStatusSend(id,params) {
  return request({
    url: `/order/send/${id}`,
    method: 'put',
    data: params,

  })
};

export function cancelCustomOrder(id,params) {
  return request({
    url: `/order/${id}`,
    method: 'delete',
    data: params,

  })
}

export function updateOrderStatusReceive(id) {
  return request({
    url: `/order/receive/${id}`,
    method: 'put',

  })
};

export function addTips(id) {
  return request({
    url: `/order/warn/${id}`,
    method: 'put',

  })
};

