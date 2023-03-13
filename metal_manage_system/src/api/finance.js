import request from '@/utils/request' //引入封装好的 axios 请求


export function downloadFile(params) {
    return request({
        url: '/bill/down',
        method: 'get',
        responseType: 'blob',
        params: params,
   
    })
}

export function billOrderList(params) {
    return request({
        url: '/bill/order/list',
        method: 'get',
        params: params,

    })
};

export function userList(data){
    return request({
        url: '/bill/user/sales',
        method: 'get',
        params: data,

    })
}