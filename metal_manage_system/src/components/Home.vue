<template>
  <el-container class="main-container">
    <el-aside v-bind:class="asideClass">
      <!-- <LeftAside></LeftAside> -->
      <el-menu default-active="2" class="el-menu-vertical-demo" background-color="#334157" text-color="#fff"
    active-text-color="#ffd04b" :collapse="collapsed" :collapse-transition="bb" router>
    <!-- <el-menu default-active="2" :collapse="collapsed" collapse-transition router :default-active="$route.path" unique-opened class="el-menu-vertical-demo" background-color="#334157" text-color="#fff" active-text-color="#ffd04b"> -->
    <div class="logobox">
      <img class="logoimg" src="../assets/logo.png" alt="">
      <span>五金门店销售系统</span>
    </div>
    <el-menu-item index="/index">
        <i class="el-icon-s-home"></i>
        <span slot="title">首页</span>
      </el-menu-item>
      <el-menu-item index="/admin"
      v-if="user.roleId == 1"
      >
        <i class="el-icon-menu"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
      <!-- 客户管理 -->
      <el-submenu index="1"
      v-if="user.roleId == 1 || user.roleId == 2"
       >
        <template slot="title">
          <i class="el-icon-s-custom"></i>
          <span slot="title">客户管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/customer">
            <i class="el-icon-document"></i>
            <span slot="title">客户信息列表</span>
          </el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group>
          <el-menu-item index="/customOrder">
            <i class="el-icon-tickets"></i>
            <span slot="title">客户订单列表</span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <!-- 商品管理 -->
      <el-submenu index="2"
      v-if="user.roleId == 1"
       >
        <template slot="title"
        >
          <i class="el-icon-goods"></i>
          <span slot="title">商品管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/product/productCgy">
            <i class="el-icon-present"></i>
            <span slot="title">商品种类</span>
          </el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group>
          <el-menu-item index="/product/productDetails">
            <i class="el-icon-tickets"></i>
            <span slot="title">商品信息</span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <!-- 供应商管理 -->
      <el-submenu index="3"
      v-if="user.roleId == 1 || user.roleId == 3"
       >
        <template slot="title">
          <i class="el-icon-s-shop"></i>
          <span slot="title">供应商管理</span>
        </template>
        <el-menu-item-group
        >
      
          <el-menu-item index="/supplier/supplierOrder">
            <i class="el-icon-tickets"></i>
            <span slot="title">供应商订单</span>
          </el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group>
          <el-menu-item index="/supplier/supplierDetails">
            <i class="el-icon-tickets"></i>
            <span slot="title">供应商信息</span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="/finance"
      v-if="user.roleId == 1 || user.roleId == 4"
      >
        <i class="el-icon-data-analysis"></i>
        <span slot="title">财务统计</span>
      </el-menu-item>
  </el-menu>
    </el-aside>
    <el-container>
      <el-header class="main-header">
        <!-- <TopNav></TopNav> -->
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
      </el-header>
      <el-main class="main-center"><router-view /></el-main>
    </el-container>
  </el-container>
</template>

<script>

import {userLogout,getUserItem} from '../api/login'
  export default {
    name: 'Home',
    
    data() {
      return {
        collapsed: false,
        bb: true,
        baseUrl:'http://10.23.43.22:8081',
        user:{}
      }
    },

    computed: { //计算属性
      asideClass() { //如果collapsed属性为true就展开不样式 反之就展开样式
        return this.collapsed ? "main-aside-collapsed" : "main-aside";
      }
    },
    created() { //钩子函数
      // this.$root.Bus.$on("Handle", value => {
      //   this.collapsed = value;
      // }); 
       this.getUser(()=>{this.loadUserItem()})
       
    },
    mounted(){
     
      
    },
    methods: {
    
      getUser(callback){
        console.log('home')
        //this.$store.dispatch('setAccount')
            //获取用户信息
            getUserItem().then(response=>{
                        if (response.data.code == 200 && response.status == 200) {
                            //console.log(response.data.data)
                            //console.log(window.localStorage.getItem('token'))
                            window.localStorage.setItem('userItem',JSON.stringify(response.data.data))
                            if(callback)
                                callback()
                        }
                    }).catch(response=>{    
                        console.log('getUserItem fail',response)
                    })    
                    
                    
        },
       loadUserItem(){
                var val = window.localStorage.getItem('userItem');//获取存储的元素
                this.user = JSON.parse(val);//解析出json对象
                // console.log(this.user)  
                this.$root.Bus.$emit('user',this.user)
            },
      
      doToggle() { //主要控制collapsed为true和false
        this.collapsed = !this.collapsed;
        this.$root.Bus.$emit("Handle", this.collapsed);
      },
      logout(){
        userLogout().then(response=>{
          if (response.data.code == 200 && response.status == 200) {
            this.$message.success('退出登录成功！');
            window.localStorage.removeItem('user');
            window.localStorage.removeItem('userItem');
            window.localStorage.removeItem('token');
            window.localStorage.clear()
            //this.$store.dispatch('removeAccount',response.data)
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
  .main-container {
    height: 100%;
    width: 100%;
    box-sizing: border-box;
  }
 
  /* 不展开样式*/
  .main-aside-collapsed {
    /* 在CSS中，通过对某一样式声明! important ，可以更改默认的CSS样式优先级规则，使该条样式属性声明具有最高优先级 */
    width: 64px !important;
    height: 100%;
    background-color: #334157;
    margin: 0px;
    overflow: hidden;
  }
 
  /* 展开样式*/
  .main-aside {
    width: 240px !important;
    height: 100%;
    background-color: #334157;
    margin: 0px;
  }
 
  .main-header,
  .main-center {
    padding: 0px;
    border-left: 2px solid #333;
  }


  /* header */
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

  /* aside */
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 240px;
    min-height: 400px;
  }
 
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    border: none;
    text-align: left;
  }
 
  .el-menu-item-group__title {
    padding: 0px;
  }
 
  .el-menu-bg {
    background-color: #1f2d3d !important;
  }
 
  .el-menu {
    border: none;
  }
 
  .logobox {
    height: 40px;
    line-height: 40px;
    color: #9d9d9d;
    font-size: 20px;
    text-align: center;
    padding: 20px 10px;
    display: inline-flex;
  }
 
  .logoimg {
    height: 40px;
  }
  .logobox span {
    font-size: 18px;
    color: #fff;
    margin: 0 15px;
  }
</style>