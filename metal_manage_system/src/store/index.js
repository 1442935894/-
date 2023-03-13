import Vuex from 'vuex'
import Vue from 'vue'
import {getUserItem,userLogout} from '../api/login'
Vue.use(Vuex)

const state = {
   token: localStorage.getItem('token') ? localStorage.getItem('token') : ''
};
const getters = {
  
};
const mutations = {
    changeLogin (state, user) {
        state.token = user.token;
        
        localStorage.setItem('token', user.token);
      },

};
const actions = {


};

export default new Vuex.Store({
    state,
    mutations,
    actions,
    getters
})