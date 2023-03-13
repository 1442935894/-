import request from '@/utils/request' //引入封装好的 axios 请求


export function getUserList(dataPackage) {
    return request({
      url: '/user/list',
      method: 'get',
      params: dataPackage,
      
    })
};

export function AddUserList(dataPackage) {
  return request({
    url: '/user',
    method: 'post',
    data: dataPackage,
 
  })
};

export function changeUserStatus(id,dataPackage) {
  return request({
    url: `/user/frozen/${id}`,
    method: 'put',
    data: dataPackage,

  })
};

export function resetUserPwd(id,dataPackage) {
  return request({
    url: `/user/reset/${id}`,
    method: 'put',
    data: dataPackage,

  })
};

export function searchUserList(key,dataPackage) {
  return request({
    url: `/user/list/${key}`,
    method: 'get',
    params: dataPackage,
 
  })
};

export function updateUserList(id,dataPackage) {
  return request({
    url: `/user/${id}`,
    method: 'put',
    data: dataPackage,
  })
};

export function ifDeleteUser(id,dataPackage) {
  return request({
    url: `/user/${id}`,
    method: 'delete',
    data: dataPackage,

  })
};

