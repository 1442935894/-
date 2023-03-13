import request from '@/utils/request' //引入封装好的 axios 请求


export function getProductList(dataPackage) {
    return request({
      url: '/product/list',
      method: 'get',
      params: dataPackage,

    })
};

export function updateSupplierPrice(id,dataPackage) {
  return request({
    url:`/product/supplier/${id}`,
    method: 'put',
    data: dataPackage,

  })
};

export function addProductList(dataPackage) {
  return request({
    url: '/product',
    method: 'post',
    data: dataPackage,

  })
};

export function updateProductList(id,dataPackage) {
  return request({
    url: `/product/${id}`,
    method: 'put',
    data: dataPackage,

  })
};

// export function updateProductCategory(dataPackage) {
//   return request({
//     url: `/product/category`,
//     method: 'post',
//     data: dataPackage
//   })
// };

export function ifDeleteProduct(id,dataPackage) {
  return request({
    url: `/product/${id}`,
    method: 'delete',
    data: dataPackage,

  })
};

export function addProductCategory(dataPackage) {
  return request({
    url: `/product/category`,
    method:'post',
    data: dataPackage,

  })
};

export function delProductCategory(id,dataPackage) {
  return request({
    url: `/product/category/${id}`,
    method: 'delete',
    data: dataPackage,

  })
};

export function addProductSupplier(dataPackage) {
  return request({
    url: `/product/supplier`,
    method:'post',
    data: dataPackage,

  })
};

export function delProductSuppiler(id,dataPackage) {
  return request({
    url: `/product/supplier/${id}`,
    method: 'delete',
    data: dataPackage,

  })
};

export function searchProductDetail(key,dataPackage) {
  return request({
    url: `/product/list/${key}`,
    method: 'get',
    params: dataPackage,

  })
}