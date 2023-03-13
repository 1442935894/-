<template>
  <el-menu default-active="2" class="el-menu-vertical-demo" background-color="#334157" text-color="#fff"
    active-text-color="#ffd04b" :collapse="collapsed" :collapse-transition="bb" router>
    <!-- <el-menu default-active="2" :collapse="collapsed" collapse-transition router :default-active="$route.path" unique-opened class="el-menu-vertical-demo" background-color="#334157" text-color="#fff" active-text-color="#ffd04b"> -->
    <div class="logobox">
      <img class="logoimg" src="../../assets/logo.png" alt="">
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
      v-if="user.roleId == 1 || user.roleId == 3"
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
      v-if="user.roleId == 1"
       >
        <template slot="title">
          <i class="el-icon-s-shop"></i>
          <span slot="title">供应商管理</span>
        </template>
        <el-menu-item-group
        
        >
        <!-- v-if="user.roleId == 1 || user.roleId == 3 " -->
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
</template>
<script>
  export default {
    name: "LeftAside",
    data() {
      return {
        collapsed: false,
        bb: true,
        user:{},
       
      }
    },
    mounted(){
      this.loadUser()
    },
    methods: {
      loadUser() {
        this.user = Object.assign({},this.$store.getters.username.userInfo)
        console.log('left')
        
      }
    },
    created() { //钩子函数
      this.$root.Bus.$on("Handle", value => {
        this.collapsed = value;
      });
      
    },
 
  }
</script>
<style>
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