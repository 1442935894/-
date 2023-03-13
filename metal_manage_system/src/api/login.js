import request from '@/utils/request' //引入封装好的 axios 请求

export function userLogin(data) {
    return request({
        url: '/user/login',
        method:'post',
        data: data,
    })
};

export function userLogout() {
    return request({
        url: '/user/logout',
        method: 'post',
   
    })
};

export function getUserItem() {
    return request({
        url: '/user/person',
        method: 'get',

    })
}