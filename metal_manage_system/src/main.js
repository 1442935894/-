// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import qs from 'qs'

import store from './store/index'

Vue.prototype.$store = store
Vue.prototype.qs = qs


import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import * as echarts from 'echarts'
//局部或全局定义
Vue.prototype.$echarts = echarts;

Vue.use(ElementUI);
Vue.config.productionTip = false

//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
  document.title = `metal_manage_system`;
  //localStorage主要是用来作为本地存储来使用的，其中的setItem可以设置一个map值（在Login.vue中设置的值）
  //getItem可以从浏览器拿到数组，这里拿到用户名
  //逻辑：当用户名没取到，且当前要跳转的组件不是login，则跳转到login
  // const role = localStorage.getItem('user');
  if (to.path === '/login') {
    next();
  } else {
    let token = localStorage.getItem('token');
  if (token == null  || token == '' || token == undefined) {
      next('/login');
      console.log('router')
  } else  {
    if (to.matched.length === 0) {
      next('/404')
    } else {
      next();
    }

      }
  }
  
});


/* eslint-disable no-new */
export default new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>',
  data: {//Bus总线
    //自定义的事件总线对象，用于父子组件的通信
    Bus: new Vue()
  }
})
