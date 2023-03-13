import request from '@/utils/request' //引入封装好的 axios 请求


export function searchProductSupplier(key,dataPackage) {
    return request({
        url: `/supplier/list/${key}`,
        method: 'get',
        params: dataPackage,
     
    })
};

export function getPurchaseOrderList(dataPackage) {
    return request({
        url: '/purchase/list',
        method: 'get',
        params: dataPackage,
     
    })
};

export function getWarnPurchaseOrder(dataPackage) {
    return request({
        url: '/purchase/order/list',
        method: 'get',
        params: dataPackage,
    
    })
}

export function addPurchaseOrder(dataPackage) {
    return request({
        url: '/purchase',
        method: 'post',
        data: dataPackage,
     
    })
};

export function confirmPurchaseOrder(id,data) {
    return request({
        url: `/purchase/buy/${id}`,
        method: 'put',
        data: data,
     
    })
};

export function confirmReceive(id,data) {
    return request({
        url: `/purchase/finish/${id}`,
        method: 'put',
        data: data,
     
    })
};

export function cancelPurchaseOrder(id,params) {
    return request({
      url: `/purchase/${id}`,
      method: 'delete',
      data: params,
 
    })
  };

export function updatePurchaseNumber(id,data) {
    return request({
        url: `/purchase/${id}`,
        method: 'put',
        data: data,
    
    })
}

