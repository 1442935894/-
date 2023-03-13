<template>
    <div>  
        <span class="index-title">客户信息管理</span>
        <div class="operation">
            <el-button class="opera-btn" type="success" @click="showAddDialog()">添加客户</el-button>
            <el-button  class="opera-btn" type="primary" @click="showEditDialog()">修改客户</el-button>
            <el-button  class="opera-btn"  type="danger" @click="handleDelete()">删除客户</el-button>
      
            <el-input 
                class="search-input"
                v-model="search"
                size="middle"
                placeholder="输入关键字搜索"
                clearable
                @keyup.enter.native="handleIconClick"
                >
                <i
                    class="el-icon-search el-input__icon"
                    slot="suffix"
                    @click="handleIconClick">
                </i>
            </el-input>
        </div>

        <!--添加信息的弹窗 -->
        <el-dialog
        title="添加客户信息"
        :visible.sync="dialogAddFormVisible"
        @close="addDialogClosed">
        <el-form v-model="Addform" style="text-align: left" ref="dataForm">
          <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
            <el-input v-model="Addform.name" autocomplete="off" placeholder="填写客户名"></el-input>
            </el-form-item>
        
        <el-form-item label="性别" :label-width="formLabelWidth" prop="sex">
          <el-select v-model="Addform.sex" clearable placeholder="请选择性别">
            <el-option label="男" :value="true"></el-option>
            <el-option label="女" :value="false"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="地址" :label-width="formLabelWidth" prop="address">
          <el-input v-model="Addform.address" autocomplete="off" placeholder="地址"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" :label-width="formLabelWidth" prop="phone">
          <el-input v-model="Addform.phone" autocomplete="off" placeholder="联系电话"></el-input>
        </el-form-item>
        <el-form-item label="会员等级" :label-width="formLabelWidth" prop="vipLevel">
          <el-input v-model="Addform.vipLevel" autocomplete="off" placeholder="会员等级"></el-input>
        </el-form-item>
        <el-form-item prop="id" style="height: 0">
          <el-input type="hidden" v-model="form.id" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogAddFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="addCategory(Addform)">确 定</el-button>
        </div>
        </el-dialog>
      
        <!--修改信息的弹窗 -->
          <el-dialog
      title="修改客户信息"
      :visible.sync="dialogFormVisible"
      @close="editDialogClosed">
      <el-form v-model="editform" style="text-align: left" ref="dataForm">
        <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="editform.name" autocomplete="off" placeholder="不加《》"></el-input>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth" prop="sex">
          <el-select v-model="editform.sex" clearable placeholder="请选择性别">
            <el-option label="男" :value="true"></el-option>
            <el-option label="女" :value="false"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="地址" :label-width="formLabelWidth" prop="address">
          <el-input v-model="editform.address" autocomplete="off" placeholder="地址"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" :label-width="formLabelWidth" prop="phone">
          <el-input v-model="editform.phone" autocomplete="off" placeholder="联系电话"></el-input>
        </el-form-item>
        <el-form-item label="会员等级" :label-width="formLabelWidth" prop="vipLevel">
          <el-input v-model="editform.vipLevel" autocomplete="off" placeholder="会员等级"></el-input>
        </el-form-item>
        <el-form-item prop="id" style="height: 0">
          <el-input type="hidden" v-model="editform.id" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editArticle(editform)">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="选择商品"
      :visible.sync="placeOrderDialog"
      @close="placeOderClosed">
      <el-table
        :data="productData"
        border
        highlight-current-row
        class="table"
        ref="customTable"
        header-cell-class-name="table-header"
      >
      <!-- @current-change="selectCurrentChange" -->
          

          <el-table-column type="expand">
                <template slot-scope="scope">
                    <el-form inline  class="demo-table-expand"
                    v-for="item of scope.row.suppliers" 
                    :key="item.index"
                    >
                        <el-form-item label="供应商：">
                            <span>{{ item.name }}</span>
                        </el-form-item>
                        <el-form-item label="成本价格：">
                            <span>{{ item.costPrice }}</span>
                        </el-form-item>
                        <el-form-item label="商品单价：">
                            <span>{{ item.price }}</span>
                        </el-form-item>
                        <el-form-item label="商品库存：">
                            <span>{{ item.number }}</span>
                        </el-form-item>
                        <el-form-item>
                          <!-- <el-input
                          class="searchInput"
                          size="mini"
                          v-model="item.count"
                          :disabled="item.count < 1 || item.count > item.number">
                            <i
                                class="el-icon-minus el-input__icon"
                                slot="prefix"
                                @click="item.count--" 
                                >
                            </i>
                            <i
                                class="el-icon-plus el-input__icon"
                                slot="suffix"
                                @click="item.count++">
                            </i>
                          </el-input> -->
                            <el-button
                                size="mini"
                                type="success"  
                                @click="addShopCart(item,scope.row,item.index)">
                                添加购物车
                            </el-button>      
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>

           

          <el-table-column prop="id" label="ID" width="180" align="center"></el-table-column>
          <el-table-column prop="imgUrl" label="图片" width="180" align="center">
            <template slot-scope="scope">
                    <el-popover placement="top-start" title="" trigger="hover">
                    <img :src="scope.row.imageUrl" alt="" style="width: 150px;height: 150px">
                    <img slot="reference" :src="scope.row.imageUrl" style="width: 50px;height: 50px">
                    </el-popover>
                </template>
          </el-table-column>
          <el-table-column prop="name" width="150" label="产品名称" align="center"></el-table-column>
           <el-table-column label="商品种类" fit="true">
                <template slot-scope="scope" >
                    <div v-for="(item,index) in scope.row.categories" 
                        :key="index">
                        <el-tag
                            :disable-transitions="false"
                            :if="index>1"
                            >
                            {{item.categoryName}} 
                        </el-tag>
                    </div>
                </template>
            </el-table-column>
          
            </el-table>
            <!--底部的分页区域-->
            <div class="block">
                <el-pagination
                background
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[10, 20, 30, 50]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="totalSize">
                </el-pagination>
            </div>

            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="memberVisible = false" :disabled="accessCard" >确 定</el-button>
            </span>
    </el-dialog>
        <el-table
            :data="customList"
            stripe
            style="width: 100%"
            :header-cell-style="{'text-align':'center'}"
            :cell-style="{'text-align':'center'}"
            ref="elTable"
            >
            <el-table-column
            type="selection"
            width="55">
            </el-table-column>
            <el-table-column prop="id" label="ID" width="100"></el-table-column>
            <el-table-column prop="name" label="姓名" width="100"></el-table-column>
            <el-table-column prop="sex"  label="性别" width="50">
                 <template slot-scope="scope">{{ scope.row.sex === true ? '男' : '女' }}</template>
            </el-table-column>
            <el-table-column prop="address" label="地址" width="200"></el-table-column>
            <el-table-column prop="phone" label="联系电话" width="150"></el-table-column>
            <el-table-column prop="vipLevel" label="会员等级" width="150"></el-table-column>
            <el-table-column prop="createName" label="销售员" width="100"></el-table-column>
            <el-table-column prop="createDatetime" label="添加时间" width="150"></el-table-column>
            <el-table-column label="操作" fixed="right" width="180">
              <template slot="header" >
               <el-button
                   size="mini"
                   type="success"
                   icon="el-icon-shopping-cart-2"
                   @click="drawer=true">
                   购物车
                   </el-button>
              </template>
                <template slot-scope="scope">
                   
                    <el-button
                    size="mini"
                    type="warning"
                    @click="PlaceOrder(scope.$index, scope.row)">下单</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="block">
            <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[10, 20, 30, 50]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalSize">
            </el-pagination>
        </div>
         <el-drawer
              title="购物车"
              :visible.sync="drawer"
              direction="rtl"
              size="50%"
              :before-close="handleClose">
              <!-- <span>{{this.customer.name}}</span> -->
              <!-- <el-table :data="shopCartData">
                <el-table-column prop="productName" label="产品名称" width="150"></el-table-column>
                <el-table-column prop="supplierName" label="供应商" width="200"></el-table-column>
                <el-table-column prop="productPrice" label="单价"></el-table-column>
                <el-table-column prop="productNumber" label="数量"></el-table-column>
              </el-table>
              <div class="demo-drawer__footer">
                
                <el-button type="warning" @click="addOrder()">结算</el-button>
              </div> -->
             <div class="cart-web">
      <!-- 导航栏 
		左边logo 右边搜索 
		内容 单栏购物车  -->
      <div class="cart-contain">
        <div class="cart-table-container">
          <div class="cart-filter-bar">
            <span class="switch-cart">购物车（全部{{ cartTotalCount }}）</span>
            <!-- <div class="cart-sum" style="display: none">
              <span class="pay-text">已选商品</span>
              <span class="total-symbol">{{ cartTotalPrice }}</span>
              <div class="pay-btn inactive-btn">结算</div>
            </div> -->
          </div>
          <div class="cart-list-content">
            <el-table
              ref="cartTable"
              :data="shopCartData"
              v-loading="loading"
              size="mini"
              style="width: 95%; margin: 0 auto"
              v-on:selection-change="handleSelectionChange"
            >
              <el-table-column
                type="selection"
                align="center"
                width="50"
              ></el-table-column>
              <el-table-column label="商品图片" width="80">
                <template slot-scope="scope">
                  <el-image
                    :src="scope.row.imageUrl"
                    style="width: 50px; height: 50px"
                  >
                    <div slot="placeholder" class="image-slot">
                      Loading<span class="dot">...</span>
                    </div>
                    <div slot="error" class="image-slot">
                      <el-image
                        style="width: 50px; height: 50px"
                        src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"
                      ></el-image>
                    </div>
                  </el-image>
                </template>
              </el-table-column>
              <el-table-column
                label="商品名称"
                prop="productName"
              ></el-table-column>
              <el-table-column
                label="供应商"
                prop="supplierName"
              ></el-table-column>
              <el-table-column label="单价" prop="productPrice"></el-table-column>
              <el-table-column label="数量" width="150">
                <template slot-scope="scope">
                  <el-input-number
                    setp="1"
                    :min="1"
                    v-model="scope.row.productNumber"
                    size="mini"
                    plain
                    v-on:change="calTotalPrice()"
                  ></el-input-number>
                </template>
              </el-table-column>
              <el-table-column label="金额">
                <template slot-scope="scope">
                  <span>{{ (scope.row.productNumber * scope.row.productPrice).toFixed(2) }}</span>
                </template>
              </el-table-column>
              <el-table-column label="操作" fixed="right" align="center">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="danger"
                    v-on:click="delGood(scope.row.productId)"
                    >删除</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div class="cart-filter-bar-bottom">
            <div class="bar-bottom-left">
              <span class="all-check">
                <div>
                  <el-checkbox
                    class="all-check-checkbox"
                    v-model="isAllChecked"
                    v-on:change="checkAll"
                    >全选</el-checkbox
                  >
                </div>
              </span>
              <span class="all-del" v-show="isAllChecked">
                <el-button type="danger" size="small" v-on:click="delAll"
                  >删除</el-button
                >
              </span>
            </div>
            <div class="cart-sum">
              <span class="pay-text">已选商品</span>
              <span class="total-text">合计：</span>
              <span class="total-symbol">{{ cartTotalPrice.toFixed(2) }}</span>
              <!-- <span class="total-text">实际支付：</span>
              <span class="total-symbol">{{(cartTotalPrice * payAmount).toFixed(2)}}</span> -->
              <div v-if="selectionData.length > 0" class="pay-btn-active" @click="handleCustomOrder">
                结算
              </div>
              <div v-else class="pay-btn-inactive" >结算</div>
            </div>
          </div>
        </div>
      </div>
    </div>

    </el-drawer>
    </div>
    
</template>

<script>
import { AddCustomerList, DelCustomerList, EditCustomerList,
 getCustomerList, searchCustomerList,addCustomerOrder } from "../../api/customer";
import {getProductList} from '../../api/product'
import { GetDateTime } from '../../utils/datetime';
import shopCart from '../customer/shopCart.vue'
export default {
    components: {shopCart},
    data() {
        return {

                //选中数据
                selectionData: [],
                // 购物车列表
                 shopCartData:[],
                // 加载
                loading: false,
                // 购物车总数
                cartTotalCount: 0,
                // 购物车总价格
                payAmount:0,  //实际支付价格
                cartTotalPrice: 0.0,
                // 是否全选
                isAllChecked: false,

        dialogAddFormVisible: false,
        dialogEditFormVisible: false,
        placeOrderDialog: false,
        accessCard:true,
        drawer: false,
        productData:[],
       
        customer:'',
        Addform:{
          id:'',
          name:'',
          sex:'',
          address:'',
          phone:'',
          vipLevel:''
        },
        editform: {
          id:'',
          name:'',
          sex:'',
          address:'',
          phone:'',
          vipLevel:''
        },
        customList:[],
        form:{
            id:'',
            name:'',
            sex:'',
            address:'',
            phone:'',
            vipLevel:''
        },
        dialogFormVisible: false,
        search:'',
        formLabelWidth: '100px',
        totalSize: 0,
        currentPage:1,
        pageSize: 10,
        }
    },
    methods: {

        //打开下单窗口
        PlaceOrder(val,cal){
          this.placeOrderDialog = true;
          //console.log('placeOrder',val,cal)
          this.customer = {
              buyerId: cal.id,
              name: cal.name,
              phone: cal.phone,
              address: cal.address,
              vipLevel: cal.vipLevel
          }
          console.log(this.customer)
           let currentPage = this.currentPage;
            let pageSize = this.pageSize;
  
            let dataPackage = {
            'pageNum': currentPage,           //页码
            'pageSize': pageSize              //当前页条数
            }
            getProductList(dataPackage).then(response=>{
              if(response.data.code == 200 && response.status == 200) {
                 let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
                this.productData = response.data.data
              }
            }).catch(response=>{
              console.log('getProductList fail',response)
            })
        },
        placeOderClosed(){
          this.placeOrderDialog = false;
        },

        //获取商品列表
        selectCurrentChange(val){
            console.log(val)
            this.accessCard=false;
           
        },

        addShopCart(val,cal) {
          //this.drawer = true;
          //console.log('shopCart',val,cal)
          //console.log(this.customer)
          
          //不同页面写法
          // let info = shopCart
          // if(info) {
          //   this.shopCartData = info
          //   console.log(this.shopCartData)
          // }
          // let isHave = -1
          // for (let i=0; i<this.shopCartData.length; i++) {
          //   if(this.shopCartData[i].productId == shopCart.productId) {
          //     isHave = i
          //   }
          // }
          // if (isHave >-1) {
          //   this.shopCartData[isHave].productNumber+=1
          // } else {
          //   this.shopCartData.push(shopCart)
          // }
         
          //相同页面写法
          let boo = false;
          let index = 0;
          //index是如果存在这个产品 这个产品在baskets中的索引值
        for(let i=0;i<this.shopCartData.length;i++){
          console.log(i,this.shopCartData[i],this.shopCartData[i].productId,cal.id,"sss")
          //i=0;此时this.baskets.length=0;不执行for循环，在购物车内添加一条新数据
          //i=0;此时this.baskets.length=1;执行for循环。执行if判断，this.baskets[0].id == row.id，不等时boo=false，执行for循环外的if-else中的else语句，
          // 之后继续执行for循环，此时i=1；
          //this.baskets[0].id是数组baskets的第一条数组的id，而row.id是所点的数据的id；每次执行都会将点击到的id值和已经在购物车内的数据id值进行比对
          //如果baskets数组中的索引的id数值和传过来的row的id数值一样，则boo=true，执行下边的if判断，商品数量+1
          if(this.shopCartData[i].id == cal.id){
            //则index的数值等于baskets数组中的索引值，判断是那个数据进行数量叠加
            index = i
            boo = true
          }
        }
        //此处boo=true
        if(boo){
          //通过声明的index来改变baskets中index下标的num 当商品存在的时候，对商品的数量进行叠加
          this.shopCartData[index].productNumber=this.shopCartData[index].productNumber+1
        }else{
          //此处boo=false
          //push() 方法可向数组的末尾添加一个或多个元素，并返回新的长度。
          this.shopCartData.push({
              supplierId: val.id,
              supplierName: val.name,
              productId: cal.id,
              productName: cal.name,
              imageUrl: cal.imageUrl,
              productPrice: val.price,
              costPrice: val.costPrice,
              productNumber: val.count
          })
        }
        this.$message({type:'success',message:'添加购物车成功'})
         console.log(this.shopCartData)
        //  localStorage.setItem('shopItem',this.shopCartData)
        },

        handleClose(done) {
              done();
        },
   

      // 选择当前商品
    checkCurrent(item) {
      let _this = this;
      // 如果商品是被勾选的
      if (item.checked) {
        // 从被选择数组中清除
        _this.selectionData.forEach((sItem, sIndex) => {
          if (sItem.id === item.id) {
            _this.selectionData.splice(sIndex, 1);
          }
        });
        // 勾选状态变为false
        _this.shopCartData.forEach((cItem, cIndex) => {
          if (cItem.id === item.id) {
            _this.shopCartData[cIndex]["checked"] = false;
          }
        });
      } else {
        // 如果商品是未被勾选的
        // 勾选状态变为true
        _this.shopCartData.forEach((cItem, cIndex) => {
          if (cItem.id === item.id) {
            _this.shopCartData[cIndex]["checked"] = true;
          }
        });
        // 添加到被选择数组中
        _this.selectionData.push(item);
      }
      // 如果被选择数组没有数据 则全选为false
      if (_this.selectionData.length <= 0) {
        _this.isAllChecked = false;
      }
      // 如果被选择数组数组长度和购物车数组长度相同 则全选为true
      if (_this.selectionData.length === _this.shopCartData.length) {
        _this.isAllChecked = true;
      }
      // 计算商品总价和总数量
      _this.calTotalPrice();
    },

// 计算总价和总数量
    calTotalPrice() {
      let _this = this;
      _this.cartTotalPrice = 0;
      _this.cartTotalCount = 0;
      // 如果有选中项
      if (_this.selectionData.length > 0) {
        _this.selectionData.forEach((item) => {
          _this.cartTotalPrice += item.productNumber * item.productPrice;
          _this.cartTotalCount += item.productNumber;
        });
      }
      
    },

  // 控制选择商品
    handleSelectionChange(data) {
      let _this = this;
      _this.selectionData = data;
      // 这里嵌套循环需要优化
      _this.shopCartData.forEach((good) => {
        if (_this.selectionData.length > 0) {
          // 如果在被选择数组中存在 则checked为true
          _this.selectionData.forEach((selectedItem) => {
            if (selectedItem.productId === good.productId) {
              good.checked = true;
            } else {
              good.checked = false;
            }
          });
        } else {
          // 如果在被选择数组中没有数据则checked全设置为false
          good.checked = false;
        }
      });
      let cartTotalChecked = _this.selectionData.length;
      if (_this.shopCartData.length === cartTotalChecked) {
        _this.isAllChecked = true;
      } else {
        _this.isAllChecked = false;
      }
      _this.calTotalPrice();
    },

// 	全部删除
    delAll() {
      let _this = this;
      _this
        .$confirm("确定要删除全部商品吗?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          // 这里只是前端删除
          _this.shopCartData = [];
          _this.isAllChecked = false;
          // 删除后需要重新获取数据刷新页面
          //   _this.getList();
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // 单个删除
    delGood(id) {
      let _this = this;
      if (!id) {
        _this.$message.error("商品有误，请刷新后重试");
        return;
      }
      _this
        .$confirm("确定要删除此商品吗?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          let goodIndex = 0;
          _this.shopCartData.forEach((item, index) => {
            if (item.productId === id) {
              goodIndex = index;
            }
          });
          _this.shopCartData.splice(goodIndex, 1);
          //   _this.getList();
        })
        .catch((e) => {
          console.log(e);
        });
    },

    // 全选
    checkAll() {
      let _this = this;
      // 如果全选
      if (_this.isAllChecked) {
        _this.shopCartData;
        // 将购物车列表复制给被选择数组
        _this.selectionData = JSON.parse(JSON.stringify(_this.shopCartData));
        _this.shopCartData.forEach((cItem, cIndex) => {
          _this.shopCartData[cIndex]["checked"] = true;
        });
        if (_this.$refs.cartTable) {
          _this.$refs.cartTable.clearSelection();
          // el table全部选中
          _this.$refs.cartTable.toggleAllSelection();
        }
      } else {
        _this.shopCartData.forEach((cItem, cIndex) => {
          _this.shopCartData[cIndex]["checked"] = false;
        });
        _this.selectionData = [];
        if (_this.$refs.cartTable) {
          // el table清空选中
          _this.$refs.cartTable.clearSelection();
        }
      }
      _this.calTotalPrice();
    },


    handleCustomOrder(){
      //console.log('handleCustomOrder')
      let items=[];
      for(let i=0; i< this.shopCartData.length; i++) {
        items.push({
          supplierId: this.shopCartData[i].supplierId,
          productId: this.shopCartData[i].productId,
          productCostPrice: this.shopCartData[i].costPrice,
          productPrice: this.shopCartData[i].productPrice,
          productNumber: this.shopCartData[i].productNumber
        })
      }
      let dataPackage = {
        "buyerId": this.customer.buyerId,
        "phone": this.customer.phone,
        "address": this.customer.address,
        "orderAmount": this.cartTotalPrice,
        items: items
      }
      addCustomerOrder(dataPackage).then(response=>{
         if(response.data.code == 200 && response.status == 200) {
           this.$message({ type: 'success', message: '下单成功！'})
            this.shopCartData = [];
            this.isAllChecked = false;
            this.drawer = false;
            this.loadCustomer()
         }
      }).catch(response=>{
        console.log('addCustomerOrder fail',response)
      })
    },


        //模糊查询
        handleIconClick(ev) {
          console.log(ev,this.search)
            let currentPage = this.currentPage;
            let pageSize = this.pageSize;
            let dataPackage = {
            'pageNum': currentPage,           //页码
            'pageSize': pageSize              //当前页条数
            }
            searchCustomerList(this.search,dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
                this.customList = response.data.data
                console.log(this.customList)
                }
            }).catch(response=>{
                console.log('查找信息失败',response)
            })
        },

        handleSizeChange(val) {
            this.pageSize = val;
            this.loadCustomer()
            console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.loadCustomer()
            console.log(`当前页: ${val}`);
        },
        //获取客户列表
        loadCustomer() {
        // this.listLoading = true
        let currentPage = this.currentPage;
        let pageSize = this.pageSize;

         let dataPackage = {
          'pageNum': currentPage,           //页码
          'pageSize': pageSize              //当前页条数
        }
        getCustomerList(dataPackage).then(response => {
          if(response.data.code == 200 && response.status == 200) {
                 let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
          this.customList = response.data.data
          //console.log(page)
          //this.listLoading = false
        }
        }).catch(response=> {
            console.log('获取客户列表失败',response)
        })
      },

      //弹出添加信息窗口
      showAddDialog() {
            this.dialogAddFormVisible = true;
            console.log('添加窗口')
        },
        //关闭添加信息窗口
        addDialogClosed() {
            this.Addform = {}
        },
        //添加客户信息
        addCategory(form) {
            this.createDatetime = GetDateTime()
            //console.log(this.createDatetime)
            let dataPackage = {
                        id: form.id,
                        name: form.name,
                        sex: form.sex,
                        address: form.address,
                        phone: form.phone,
                        vipLevel:form.vipLevel,
                        createName: this.createName,
                        createDatetime: this.createDatetime
                    }
            AddCustomerList(dataPackage).then(response =>{
                if(response.data.code == 200 && response.status == 200) {
                    console.log(dataPackage);
                    this.$message({
                        type: 'success',
                        message: '添加成功！'
                        }),
                    this.dialogAddFormVisible = false;
                    this.loadCustomer()
                }
            })
        },

        
       
      //弹出编辑窗口
      showEditDialog() {
        //console.log(this.$refs.elTable.selection)
        this.dialogFormVisible = true
        let rowList = JSON.parse(JSON.stringify(this.$refs.elTable.selection)); 
        // this.form = Object.assign({},this.$refs.elTable.selection)
        console.log(rowList[0].sex)
        this.editform = {
            id:rowList[0].id,
            name:rowList[0].name,
            sex:rowList[0].sex,
            address:rowList[0].address,
            phone:rowList[0].phone,
            vipLevel:rowList[0].vipLevel
        }
        
      },

      //删除分类
      handleDelete(){
            let rowList = JSON.parse(JSON.stringify(this.$refs.elTable.selection));
            let dataPackage = []
            for (var i=0; i< rowList.length; i++) {
                dataPackage.push(rowList[i].id)
            }
            //this.qs.stringify({dataPackage}, {arrayFormat: 'repeat'})
            console.log(dataPackage)
            let that = this;
            that.$confirm('此操作将永久删除该客户, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() =>{
                  DelCustomerList(dataPackage).then(response=>{
                    if(response.data.code == 200 && response.status == 200) {
                        this.$message({
                        type: 'success',
                        message: '删除成功！'
                        });
                        this.loadCustomer()
                        dataPackage = [];
                    }
                }).catch(response=>{
                    console.log('删除客户失败',response)
                })
                })
                
              
        },



       //编辑客户信息
       editArticle(editform) {
            let id = editform.id;
            this.updateDatetime = GetDateTime()
            //console.log(this.createDatetime)
            let dataPackage = {
                        id: editform.id,
                        name: editform.name,
                        sex: editform.sex,
                        address: editform.address,
                        phone: editform.phone,
                        vipLevel:editform.vipLevel,
                        updateById: this.updateById,
                        updateDatetime: this.updateDatetime
                    }
            EditCustomerList(id,dataPackage).then(response =>{
                if(response.data.code == 200 && response.status == 200) {
                    this.$message({
                        type: 'success',
                        message: '更新成功！'
                        }),
                    this.dialogFormVisible = false;
                    this.loadCustomer()
                }
            }).catch(response=>{
                 console.log(dataPackage,response)
            })
        },

        //关闭编辑窗口
        editDialogClosed () {
          this.editform = {}
        },


   


    },
    created() {
        this.loadCustomer()
    },
    computed: 
    {
      
      // sumPrice(){
      //   // 计算价格法1：
      //   // let sum = 0;
      //   // for(let book of this.books) {
      //   //     sum += book.price * book.count;
      //   // }
      //   // return  sum;
      //   // 计算价格法2,使用reduce函数。
      //   return this.shopCartData.reduce(((preValue,cart)=>preValue + cart.count * cart.price),0);
    //}
  }
}
</script>

<style scoped>
    .operation {
        padding: 10px 50px;
        display: flex;
        flex-direction: row;
    }
    .opera-btn {
        margin: 0 20px;
    }
    .search-input {
        width: 250px;
    }
    .searchInput{
      width: 100px;
    }
    .index-title {
        display: flex;
        flex-direction: row;
        font-size: 36px;
        padding: 10px 50px;
    }
    .block {
        display: flex;
        justify-content: center;
    }




/* 购物车web端 */
.cart-web {
  width: 100%;
  height: auto;
  background: #e8e2e0;
}
/* 购物车头部 */
.cart-header {
  width: 100%;
  height: 58px;
  padding: 11px 0;
  background-color: #fff;
  display: flex;
  justify-content: center;
}

.cart-header .cart-header-contain {
  width: 80%;
  display: flex;
  align-items: center;
}

/* 购物车标题 */
.cart-header .cart-header-contain .cart-title {
  font-size: 18px;
  font-weight: 500;
}

.cart-contain {
  width: 100%;
  height: auto;
  display: flex;
  justify-content: center;
  align-items: center;
  padding-top: 24px;
}

.cart-table-container {
  width: 80%;
  border-radius: 25px;
  padding: 10px 30px;
  background: #fff;
  min-height: calc(100vh - 130px);
  position: relative;
}

.cart-filter-bar {
  width: 100%;
  display: flex;
  justify-content: space-between;
}

.cart-filter-bar .switch-cart {
  overflow: hidden;
  color: #000;
  font-size: 18px;
  font-weight: 600;
  height: 72px;
  line-height: 72px;
}

.cart-filter-bar .cart-sum {
  font-size: 14px;
  display: flex;
  align-items: center;
}

.cart-sum .total-symbol {
  font-weight: 400;
  margin-right: 12px;
  font-size: 24px;
  color: #ff5000;
}

/* 激活状态的支付按钮 */
.cart-sum .pay-btn-active {
  width: 74px;
  height: 42px;
  line-height: 42px;
  color: #fff;
  background: #ff5000;
  border-radius: 21px;
  text-align: center;
  cursor: pointer;
  text-decoration: none;
  font-size: 16px;
}

/* 未激活状态的支付按钮 */
.cart-sum .pay-btn-inactive {
  width: 74px;
  height: 42px;
  line-height: 42px;
  color: #fff;
  background: #a3a3a3;
  border-radius: 21px;
  text-align: center;
  cursor: not-allowed;
  text-decoration: none;
  font-size: 16px;
}

.inactive-btn {
  background: #aaa !important;
  color: #fff;
  cursor: not-allowed !important;
}

.cart-list-content {
  max-height: 100vh;
  padding-bottom: 72px;
  overflow-y: auto;
}

/* 购物车底部栏 */
.cart-filter-bar-bottom {
  width: 95%;
  display: flex;
  justify-content: space-between;
  position: absolute;
  bottom: 0;
  background: #fff;
  z-index: 999;
}

.cart-filter-bar-bottom .bar-bottom-left {
  display: flex;
  justify-content: center;
  align-items: center;
}

/* 全选 */
.cart-filter-bar-bottom .all-check {
  overflow: hidden;
  color: #000;
  height: 72px;
  line-height: 72px;
  font-size: 12px;
}

.cart-filter-bar-bottom .all-check .all-check-checkbox {
  margin-right: 10px;
}

/* 全部删除 */
.cart-filter-bar-bottom .all-del {
  margin-left: 20px;
}

.cart-filter-bar-bottom .cart-sum {
  font-size: 14px;
  display: flex;
  align-items: center;
}

.cart-sum .pay-text {
  margin: 0 12px;
}

.cart-sum .total-text {
  margin: 0 0 0 12px;
}

.cart-box {
  padding: 10px 0;
}

.cart-list-item {
  position: relative;
  border-radius: 8px;
  overflow: hidden;
  border: 0;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin: 20px;
  background-color: #fff;
  padding: 8px 0;
}

.cart-list-item .cart-item-info {
  display: flex;
  align-items: center;
  padding: 0 0 0 15px;
}

.cart-list-item .cart-item-left {
  width: 100px;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.cart-list-item .left-check {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 8%;
}

/* 单个选择按钮 */
.cart-list-item .left-check .checkbox {
  width: 14px;
  height: 14px;
  border-radius: 50%;
  background: #fff;
  border: 1px solid #b9b9b9;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 单个选择按钮选中 */
.cart-list-item .left-check .checkbox .selected-check {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background: red;
}

.cart-item-right {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  padding: 10px;
}

.cart-item-opera {
  display: flex;
  align-items: center;
}

/* 删除按钮 */
.del-btn {
  position: absolute;
  right: 10px;
  bottom: 10px;
  font-size: 17px;
  color: red;
}
.cart-item-right .cart-item-info-content {
  margin-right: 20px;
  width: 70px;
}

.cart-item-right .goods-name {
  width: 100%;
  font-size: 15px;
  color: #333333;
  margin-top: 3px;
  text-align: left;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.cart-item-right .numbers {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-top: 10px;
}

.cart-item-right .numbers .price {
  font-size: 14px;
  line-height: 25px;
  color: #ff0000;
}

.bottom-all {
  height: 50px;
  width: 100%;
  padding: 0 2%;
  align-items: center;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  position: fixed;
  bottom: 0;
  left: 0;
  background-color: #fdfdfd;
  z-index: 11;
  border-top: 1px solid #f8f8f8;
}

.bottom-all .left {
  float: left;
  width: 28vw;
  font-size: 14px;
  position: relative;
}

.bottom-all .left .text {
  position: absolute;
  left: 35px;
  top: 50%;
  transform: translateY(-52%);
  color: #333333;
}

.bottom-all .del-text {
  color: red;
  position: absolute;
  left: 80px;
  top: 47%;
  transform: translateY(-52%);
}

.bottom-all .left-check {
  padding-left: 8px;
  flex-shrink: 0;
  height: 50px;
  margin-right: 8px;
  align-items: center;
  position: relative;
  display: flex;
}

.bottom-all .rights {
  width: 40vw;
  line-height: 50px;
  font-size: 16px;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}

.bottom-all .settlement-inactive {
  padding: 0 4px;
  border-radius: 16px;
  width: 18vw;
  height: 35px;
  line-height: 35px;
  color: #ffffff;
  font-size: 17px;
  text-align: center;
  margin-right: 20px;
  cursor: not-allowed;
  background-color: #a3a3a3;
}

.bottom-all .settlement-active {
  padding: 0 4px;
  border-radius: 16px;
  width: 18vw;
  height: 35px;
  line-height: 35px;
  color: #ffffff;
  font-size: 17px;
  text-align: center;
  margin-right: 20px;
  background-color: #ff5000;
}

/* 购物车为空的样式 */
.cart-no-data {
  text-align: center;
}

.total-pay-count {
  font-size: 24px;
  color: #ff5000;
}

@media (max-width: 700px) {
  /* 弹窗 */
  .el-message-box {
    width: 300px !important;
  }

  /* el-input-number样式 */
  .el-input-number {
    width: 100px !important;
    line-height: 28px !important;
  }
  .el-input-number .el-input__inner {
    padding-left: 0 !important;
    padding-right: 0 !important;
  }
  .el-input__inner {
    height: 30px !important;
  }
  .el-input-number__decrease,
  .el-input-number__increase {
    width: 20px !important;
  }
}
</style>

