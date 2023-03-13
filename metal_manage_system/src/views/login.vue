<template>
        <div class="container">
            <div class="title">
                <img src="../assets/logo.png" width="40" height="40"><span class="title-text">五金门店销售系统</span>
            </div>
            <div class="card">
                <div class="login-form">
                    <div class="form-item">
                        <el-input placeholder="请输入邮箱或用户名" v-model="loginForm.username"></el-input>
                    </div>
                    <div class="form-item">
                        <el-input placeholder="请输入密码" v-model="loginForm.password" show-password></el-input>
                    </div>
                    
                    <el-button class="login-btn" type="success" @click="login()">登录</el-button>
                </div>
            </div>
        </div>
</template>

<script>
import {userLogin,getUserItem} from '../api/login'
import { mapMutations } from 'vuex';
  export default {
    inject: ['reload'],
    data() {
      return {
        userToken:'',
        loginForm: {
          username: '',
          password: ''
        },
      };
    },
    methods: {
        ...mapMutations(['changeLogin']),
        login() {
            let data = {
                username: this.loginForm.username,
                password: this.loginForm.password
            }
           userLogin(data).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    this.$message.success('登录成功！');
                    //localStorage.setItem('user', this.loginForm.username);
                    this.userToken = response.data.data.token;
                    this.changeLogin({token: this.userToken})
                    this.$router.push('/');
                }
            }).catch(response=>{
                this.$message.error(response);
                console.log('login fail',response)
            })
       
        }
    }
  };
</script>

<style scoped>
    .container {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        background-color: #4570b5;
        height: 100vh;
    }
    .title {
        display: inline-flex;
    }
    .title-text{
        font-size: 36px;
        color: white;
        line-height: 40px;
    }
    .card {
        height: 300px;
        width: 400px;
        margin: 20px;
        border: 1px white solid;
        border-radius: 2%;
        background-color: aliceblue;
    }
    .form-item {
        margin: 20px;
        width: 350px;
    }
    .remember {
        display: flex;
        flex-direction: row;
        margin: 10px 30px;
    }
    .login-form {
        display: flex;
        flex-direction: column;
        margin-top: 20px;
    }
    .login-btn {
        width: 350px;
        margin: 0 20px;
        
    }
</style>