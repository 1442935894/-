import request from '@/utils/request' //引入封装好的 axios 请求


export function uploadUserImg(dataPackage) {
    return request({
      url: '/picture/user/upload',
      method: 'post',
      data: dataPackage,
 
    })
  };

export function uploadProductImg(dataPackage) {
    return request({
        url: '/picture/product/upload',
        method: 'post',
        data: dataPackage,

    })
}