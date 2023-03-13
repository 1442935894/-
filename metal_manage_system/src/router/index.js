import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

// export default new Router({
//   routes: [
//     {
//       path: '/',
//       name: 'Home',
//       component: () =>import('../views/Home')
//     }
//   ]
// })
const routes = [
  {
    path: '/login',
    name: "Login",
    component: () => import('@/views/login')
  },
  {
    path: '/',
    name: "Home",
    component: () => import('@/components/Home'),
    redirect: '/index',
    children: [
      {
        path: '/index',
        name: 'App',
        component: () => import('@/components/index'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/admin',
        name: 'Admin',
        component: () => import('../views/admin'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/customer',
        name: 'Customer',
        component: () => import('../views/customer/customer'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/customOrder',
        name: 'CustomOrder',
        component: () => import('../views/customer/customOrder'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/product/productDetails',
        name: 'ProductDetails',
        component: () => import('../views/product/productDetails'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/product/productCgy',
        name: 'ProductCgy',
        component: () => import('../views/product/productCgy'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/supplier/supplierDetails',
        name: 'supplierDetails',
        component: () => import('../views/supplier/supplierDetails'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/supplier/supplierOrder',
        name: 'supplierOrder',
        component: () => import('../views/supplier/supplierOrder'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/finance',
        name: 'finance',
        component: () => import('../views/finance'),
        meta: {
          requireAuth: true
        }
      }
    ]
  },
  
]



const router = new Router({
  routes,
  mode: 'history'
})

export default router
