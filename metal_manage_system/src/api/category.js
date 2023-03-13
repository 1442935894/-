import request from '@/utils/request' //引入封装好的 axios 请求


export function getCategoryList(dataPackage) {
    return request({
      url: '/category/list',
      method: 'get',
      params: dataPackage,
  
    })
};

export function searchCategoryList(key,dataPackage) {
  return request({
    url: `/category/list/${key}`,
    method: 'get',
    params: dataPackage,

  })
};

export function AddCategoryList(dataPackage) {
    return request({
      url: '/category',
      method: 'post',
      data: dataPackage,
 
    })
};

export function EditCategoryList(id,dataPackage) {
  return request({
    url: `/category/${id}`,
    method: 'put',
    data: dataPackage,

  })
};

export function DelCategoryList(dataPackage) {
  return request({
    url: `/category/${dataPackage}`,
    method: 'delete',

  })
};

