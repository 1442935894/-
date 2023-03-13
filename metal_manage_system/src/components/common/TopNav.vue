<template>
  <el-menu class="el-menu-demo" mode="horizontal" background-color="#334157" text-color="#fff" active-text-color="#fff">
    <a class="buttonimg">
      
      <i class="el-icon-s-fold" style="color:white;"  @click="doToggle()"></i>
    </a>
    
    <el-submenu index="2" class="submenu">
      <template slot="title"><img class="avatar" :src="baseUrl+user.avatarUrl">{{user.username}}</template>
      <el-menu-item index="2-2">个人中心</el-menu-item>
      <el-menu-item @click="logout" index="2-3">退出</el-menu-item>
    </el-submenu>
  </el-menu>
</template>
 
<script>
import {userLogout} from '../../api/login'
import {mapGetters} from 'vuex'
  export default {
    name: "TopNav",
    data() {
      return {
        collapsed: false,
        baseUrl:'http://10.23.43.22:8081',
        user:{}
      }
    },
    created(){
      
    },
    mounted(){
     this.loadUser()
      
    },
    computed: {
      // user() {
      //   console.log(this.$store.state.userInfo.username)
      //   return this.$store.state.userInfo
      // }
      //...mapGetters(['username'])
      
    },
 
    methods: {
      loadUser() {
            
        this.user = Object.assign({},this.$store.getters.username.userInfo)
        console.log('top',this.user)
      },
    
      doToggle() { //主要控制collapsed为true和false
        this.collapsed = !this.collapsed;
        this.$root.Bus.$emit("Handle", this.collapsed);
      },
      logout(){
        userLogout().then(response=>{
          if (response.data.code == 200 && response.status == 200) {
            this.$message.success('退出登录成功！');
            localStorage.removeItem('user');
            localStorage.removeItem('userItem');
            localStorage.removeItem('token');
            this.$store.dispatch('removeAccount',response.data)
            this.$router.push('/login');
          }
        }).catch(response=>{
          this.$message.error('退出登录失败！',response);
        })
        
      }
    }
 
  }
</script>
 
<style scoped>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    border: none;
  }
 
  .submenu {
    float: right;
  }
 .avatar {
    margin: 6px;
    width: 45px;
    height: 45px;
    border-radius: 50%;
  }
  .buttonimg {
    height: 50px;
    font-size:50px;
    background-color: transparent;
    border: none;
  }
 
  .showimg {
    width: 26px;
    height: 26px;
    position: absolute;
    top: 17px;
    left: 17px;
  }
 
  .showimg:active {
    border: none;
  }
</style>