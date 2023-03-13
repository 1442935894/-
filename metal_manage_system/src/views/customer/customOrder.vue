<template>
    <div>  
        <span class="index-title">客户订单管理</span>
        <div class="operation">
            <!-- <div class="crumbs">
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item>
                        <i class="el-icon-lx-cascades"></i> {{BuyerName}}的订单列表
                    </el-breadcrumb-item>
                </el-breadcrumb>
            </div> -->
            <el-button class="opera-btn" type="success" @click="openSelectCustomDialog()">选择客户</el-button>
            <!-- <el-button class="opera-btn" type="primary">添加订单</el-button> -->
            <!-- <el-button class="opera-btn" type="danger">取消订单</el-button> -->
            <el-input 
                class="search-input"
                v-model="search"
                size="middle"
                placeholder="输入销售员或顾客名称搜索"
                @keyup.enter.native="handleIconClick"
                clearable
                >
                <i
                    class="el-icon-search el-input__icon"
                    slot="suffix"
                    @click="handleIconClick">
                </i>
            </el-input>
        </div>
        <el-table
            :data="CustomOrderList"
            stripe
            style="width: 100%"
            :header-cell-style="{'text-align':'center'}"
            :cell-style="{'text-align':'center'}"
            >
            <!-- <el-table-column
            type="selection"
            width="55">
            </el-table-column> -->
            <el-table-column type="expand">
                <template slot-scope="scope"> 
                    <el-form inline  class="demo-table-expand"
                    v-for="item of scope.row.items" 
                    :key="item.id"
                    @click="operate(item, $event)">
                        <el-form-item label="供应商：">
                            <span>{{ item.supplierName }}</span>
                        </el-form-item>
                        <el-form-item label="商品名称：">
                            <span>{{ item.productName }}</span>
                        </el-form-item>
                        <!-- <el-form-item label="成本价格：">
                            <span>{{ item.productCostPrice }}</span>
                        </el-form-item> -->
                        <el-form-item label="商品单价：">
                            <span>{{ item.productPrice }}</span>
                        </el-form-item>
                        <el-form-item label="商品数量：">
                            <span>{{ item.productNumber }}</span>
                        </el-form-item>
                        <el-form-item label="备注：">
                            <span>{{ item.remark }}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column prop="orderNo" label="订单号" width="100"></el-table-column>
            <el-table-column prop="buyerName" label="买家姓名" width="80"></el-table-column>
            <el-table-column prop="phone" label="联系电话" width="150"></el-table-column>
            <el-table-column prop="address" label="地址" width="200"></el-table-column>
            <el-table-column prop="orderAmount" label="订单金额" width="80"></el-table-column>
            <el-table-column prop="payAmount" label="支付金额" width="80"></el-table-column>
            <el-table-column
                prop="tag"
                label="支付状态"
                width="100">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.payStatus == 1"  type="warning">待付款</el-tag>
                    <el-tag v-else-if="scope.row.payStatus == 2"  type="success">已付款</el-tag> 
                </template>
            </el-table-column>
            <el-table-column
                prop="tag"
                label="订单状态"
                width="100">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.orderStatus == 0"  type="warning">进行中</el-tag>
                    <el-tag v-else-if="scope.row.orderStatus == 1"  type="primary">已结算</el-tag>
                    <el-tag v-else-if="scope.row.orderStatus == 2" type="success">已完成</el-tag>
                    <el-tag v-else-if="scope.row.orderStatus == 3" type="info">订单关闭</el-tag>   
                    <!--订单状态 
                        0进行中，
                        1已结算，
                        2已完成，
                        3取消交易 -->
                </template>
            </el-table-column>
            <el-table-column
                prop="tag"
                label="配送状态"
                width="100">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.deliverStatus == 0"  type="info">采购中</el-tag>
                    <el-tag v-else-if="scope.row.deliverStatus == 1"  type="warning">送货中</el-tag>
                    <el-tag v-else-if="scope.row.deliverStatus == 2"  type="success">已收货</el-tag>  
                </template>
            </el-table-column>
            <el-table-column
                prop="tag"
                label="采购状态"
                width="100">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.deliverStatus == 0"  type="success">已采购</el-tag>
                    <el-tag v-else-if="scope.row.deliverStatus == 1"  type="danger">需采购</el-tag>
                      
                </template>
            </el-table-column>
            <el-table-column prop="remark" label="回访" width="150"></el-table-column>
            <el-table-column prop="createName" label="销售员" width="80"></el-table-column>
            <el-table-column prop="createDatetime" label="创建时间" width="150"></el-table-column>
            
            <el-table-column label="操作" width="250" fixed="right">
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    type="success"
                    @click="openOrderStatusDialog(scope.$index, scope.row)">更新状态</el-button>
                    <el-button
                    size="mini"
                    type="warning"
                    @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                    size="mini"
                    type="primary"
                    v-if="(scope.row.orderStatus == 1) || (scope.row.orderStatus == 2)"
                    @click="openRemark(scope.row)">回访</el-button>
                    <el-button
                    size="mini"
                    type="danger"
                    v-else
                    @click="handleDelete(scope.$index, scope.row)">取消</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog title="客户选择" :visible.sync="memberVisible">
            <el-table
                    :data="customerData"
                    border
                    highlight-current-row
                    class="table"
                    ref="multipleTable"
                    header-cell-class-name="table-header"
                    @current-change="selectCurrentChange"
            >
                <el-table-column prop="id" label="ID" width="180" align="center"></el-table-column>
                <el-table-column prop="name" width="150" label="会员姓名" align="center"></el-table-column>
                <el-table-column prop="sex" label="性别" align="center">
                    <template slot-scope="scope">{{ scope.row.sex === true ? '男' : '女' }}</template>
                </el-table-column>
                <el-table-column prop="vipLevel" label="会员等级" align="center"></el-table-column>
            </el-table>
            <!--底部的分页区域-->
            <div class="block">
                <el-pagination
                background
                @size-change="handleCustomSizeChange"
                @current-change="handleCustomCurrentChange"
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

        <el-dialog
            title="更新订单状态"
            :visible.sync="statusDialog"
            :close="statusDialogClosed">
            <el-form :model="statusForm" >
                <el-form-item  prop="payStatus" v-if="statusForm.payStatus==1 && statusForm.deliverStatus==null" :key="101">
                    <!-- <el-select v-model="statusForm.payStatus" placeholder="请选择" >
                        <el-option label="待付款" :value="1" disabled></el-option>
                        <el-option label="已付款" :value="2"></el-option>
                        </el-select> -->
                     <el-form-item label="支付金额:">
                        <el-input disabled v-model="statusForm.payAmount"></el-input>
                    </el-form-item>
                        <el-button type="primary" @click="changePayStatus()">确认付款</el-button>
                </el-form-item>
                <el-form-item  prop="orderStatus" v-else :key="102">
                    <!-- 订单状态：<el-select v-model="statusForm.orderStatus" placeholder="请选择">
                        <el-option label="进行中" :value="0"></el-option>
                        <el-option label="已结算" :value="1"></el-option>
                        <el-option label="已完成" :value="2"></el-option>
                        <el-option label="取消交易" :value="3"></el-option>
                    </el-select> -->
                     <el-button  type="warning" @click="changeOrderStatus(3)">提醒库存</el-button>
                    <el-button :key="201" v-if="statusForm.orderStatus==0 && statusForm.deliverStatus==0" type="primary" @click="changeOrderStatus(1)">确认发货</el-button>
                    <el-button :key="202" v-else-if="statusForm.deliverStatus==1" type="primary" @click="changeOrderStatus(2)">确认收货</el-button>
                 </el-form-item>
                
                
                 
            </el-form>
            
        </el-dialog>

        <el-dialog
         title="修改订单信息"
         :visible.sync="OrderDialog"
         @close="OrderDialogClosed">
            <el-form v-model="EditCustomOrder" style="text-align: left" ref="dataForm">
                <el-form-item label="订单ID" :label-width="formLabelWidth" prop="orderId">
                    <el-input v-model="EditCustomOrder.orderId" autocomplete="off" placeholder="" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="买家姓名" :label-width="formLabelWidth" prop="buyerName">
                    <el-input v-model="EditCustomOrder.buyerName" autocomplete="off" placeholder="" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="电话" :label-width="formLabelWidth" prop="phone">
                    <el-input v-model="EditCustomOrder.phone" autocomplete="off" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="地址" :label-width="formLabelWidth" prop="address">
                    <el-input v-model="EditCustomOrder.address" autocomplete="off" placeholder=""></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="OrderDialog = false">取 消</el-button>
                <el-button type="primary" @click="editCustomOrder(EditCustomOrder)">确 定</el-button>
            </div>
        </el-dialog>
        <!-- 页码 -->
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
    </div>
</template>

<script>
import {
    getCustomerOrderList,selectCustomerOrder,
    reMarkCustomOrder,updateCustomOrder,searchCustomOrder,
    updateOrderPayStatus,updateOrderStatusSend,updateOrderStatusReceive,
    addTips,cancelCustomOrder
} from '../../api/customOrder'
import {getCustomerList} from '../../api/customer'
export default {
    data() {
        return {
            OrderDialog: false,
            memberVisible: false,
            accessCard:true,
            statusDialog: false,
            customerData:[],
            BuyerName:'',
            statusForm:[],
            
            formLabelWidth:'100px',
            EditCustomOrder:{
                orderId:'',
                buyerName:'',
                phone:'',
                address:''
            },
            CustomOrderList: [{
            orderNo:1665212415929,
            createDatetime: '2016-05-02',
            buyerName: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄',
            phone: 200333,
            "orderAmount": 460.00,
            "remark": "remark一下",
            "createName": "Johnson",
            "payStatus": 1,
            "orderStatus": 0,
            deliverStatus: 0,
            "items": [
                {
                    "itemId": 1,
                    "orderId": 1,
                    "supplierId": 1,
                    "productId": 1,
                    "productCostPrice": 1.90,
                    "productPrice": 2.30,
                    "productNumber": 100,
                    "remark": null,
                    "createDatetime": "2022-10-08 15:25:05",
                    "createById": 1
                },
                {
                    "itemId": 2,
                    "orderId": 1,
                    "supplierId": 2,
                    "productId": 1,
                    "productCostPrice": 1.60,
                    "productPrice": 2.30,
                    "productNumber": 100,
                    "remark": null,
                    "createDatetime": "2022-10-08 15:25:21",
                    "createById": 1
                }
            ]
        }, {
            orderNo:1665212415929,
            createDatetime: '2016-05-02',
            buyerName: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄',
            phone: 200333,
            "orderAmount": 460.00,
            "remark": "remark一下",
            "createName": "Johnson",
            "payStatus": 2,
            "orderStatus": 1,
        }, {
            orderNo:1665212415929,
            createDatetime: '2016-05-02',
            buyerName: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄',
            phone: 200333,
            "orderAmount": 460.00,
            "remark": "remark一下",
            "createName": "Johnson",
            "payStatus": 2,
            "orderStatus": 0,
            deliverStatus: 1,
        }, {
            orderNo:1665212415929,
            createDatetime: '2016-05-02',
            buyerName: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄',
            phone: 200333,
            "orderAmount": 460.00,
            "remark": "remark一下",
            "createName": "Johnson",
            "payStatus": 1,
            "orderStatus": 3,
        }],
        search:'',
        totalSize: 0,
        currentPage:1,
        pageSize: 10,
        }
    },
    created(){
       this.loadCustomOrderList()
    },
    methods: {

        openSelectCustomDialog(){
             this.memberVisible = true;
            this.loadCustomer()
        },

        //更新支付状态
        changePayStatus() {
            let id = this.statusForm.orderId
            let data = {
                orderId: this.statusForm.orderId,
                payAmount: this.statusForm.payAmount
            }
            updateOrderPayStatus(id,data).then(response=>{
                if (response.data.code == 200 && response.status == 200) {
                    this.$message({
                        type: 'success',
                        message: '更新支付状态成功！'
                    });
                    this.statusDialog = false;
                    this.loadCustomOrderList()
                }
            }).catch(response=>{
                console.log('update payStatus fail',response)
            })
        },

        //更新订单状态
        changeOrderStatus(val){
            console.log(val)
            let id = this.statusForm.orderId
            
            if (val == 1){
                updateOrderStatusSend(id).then(response=>{
                    if (response.data.code == 200 && response.status == 200) {
                         this.$message({
                        type: 'success',
                        message: '更新订单状态成功！'
                    });
                    this.statusDialog = false;
                    this.loadCustomOrderList()
                    }
                }).catch(response=>{
                    console.log('发货失败',response)
                })
            }
            else if (val == 2) {
                let id = this.statusForm.orderId
                let data = {
                orderId: this.statusForm.orderId
            }
                updateOrderStatusReceive(id,data).then(response=>{
                     if (response.data.code == 200 && response.status == 200) {
                         this.$message({
                        type: 'success',
                        message: '更新订单状态成功！'
                    });
                    this.statusDialog = false;
                    this.loadCustomOrderList()
                    }
                }).catch(response=>{
                    console.log('收货失败',response)
                })
            }
            else if (val==3) {
                let id = this.statusForm.orderId
                addTips(id).then(response=>{
                    if (response.data.code == 200 && response.status == 200) {
                         this.$message({
                        type: 'success',
                        message: '提醒库存紧急成功！'
                    });
                    this.statusDialog = false;
                    this.loadCustomOrderList()
                    }
                }).catch(response=>{
                    console.log('提醒失败',response)
                })
            
            }
            
        },

        //回访
        openRemark(val){
            console.log(val)
            this.$prompt('客户电话：'+val.phone,'请输入回访内容', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                // inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
                // inputErrorMessage: '邮箱格式不正确'
        }).then(({ value }) => {
            console.log('remark:',value)
            let dataPackage = {
                orderId: val.orderId,
                remark: value
            }
            reMarkCustomOrder(val.orderId,dataPackage).then(response=>{
                if (response.status == 200 && response.data.code == 200) {
                    this.$message({
                        type: 'success',
                        message: '回访完成'
                    });
                    this.loadCustomOrderList()
                }
            }).catch(response=>{
                console.log('remark fail',response)
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });       
        });
        },


        //open更改地址电话
        handleEdit(val,cal){
            console.log('handleEdit',val,cal)
            if (cal.orderStatus == 0 && cal.payStatus == 1) {
                this.OrderDialog = true;
                console.log('更改地址和电话');
                this.EditCustomOrder = {
                    orderId: cal.orderId,
                    buyerName: cal.buyerName,
                    phone: cal.phone,
                    address: cal.address
                }
                
            }
            else {
                 this.$alert('该订单状态下无法更改', '更改错误', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$message({
                        type: 'info',
                        message: `取消更改`
                        });
                    }
                    });
            }
        },

        //更改订单地址和电话
        editCustomOrder(EditCustomOrder) {
                let dataPackage = {
                    orderId: EditCustomOrder.orderId,
                    phone: EditCustomOrder.phone,
                    address: EditCustomOrder.address
                }
                updateCustomOrder(EditCustomOrder.orderId,dataPackage).then(response=>{
                    if (response.status == 200 && response.data.code == 200) {
                        this.$message({
                            type: 'success',
                            message: '修改地址和电话成功！'
                        });
                        this.EditCustomOrder=[]
                        this.OrderDialog = false;
                        this.loadCustomOrderList()
                    }
                }).catch(response=>{
                    console.log('update address&phone fail',response)
                })
        },

        OrderDialogClosed() {
            this.OrderDialog = false;
            this.EditCustomOrder = {};
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
            searchCustomOrder(this.search,dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
                this.CustomOrderList = response.data.data
                console.log(this.CustomOrderList)
                }
            }).catch(response=>{
                console.log('查找信息失败',response)
            })
        },

         //改变每页显示条数
      handleSizeChange(val) {
            this.pageSize = val;
            this.loadCustomOrderList()
            //console.log(`每页 ${val} 条`);
        },

        //改变当前页码
        handleCurrentChange(val) {
            this.currentPage = val;
            this.loadCustomOrderList()
            console.log(`当前页: ${val}`);
        },

           //改变每页显示条数
      handleCustomSizeChange(val) {
            this.pageSize = val;
            this.loadCustomer()
            //console.log(`每页 ${val} 条`);
        },

        //改变当前页码
        handleCustomCurrentChange(val) {
            this.currentPage = val;
            this.loadCustomer()
            console.log(`当前页: ${val}`);
        },

        
        //打开更新订单状态弹框
        openOrderStatusDialog(val,cal) {
            if (cal.orderStatus == 3 || cal.orderStatus == 2) {
                this.$alert('该订单状态下无法更改', '更改错误', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$message({
                        type: 'info',
                        message: `取消更改`
                        });
                    }
                    });
            }
             else {
                this.statusDialog = true;
            console.log(val,cal)
            this.statusForm = cal
            switch(cal.vipLevel) {
                case 1:
                this.statusForm.payAmount=1*cal.orderAmount
                break;
                case 2:
                 this.statusForm.payAmount=0.95*cal.orderAmount
                break;
                case 3:
                 this.statusForm.payAmount=0.9*cal.orderAmount
                break;
                case 4:
                 this.statusForm.payAmount=0.85*cal.orderAmount
                break;
                case 5:
                 this.statusForm.payAmount=0.8*cal.orderAmount
                break;
                case 6:
                 this.statusForm.payAmount=0.75*cal.orderAmount
                break;
            }
            }
            
            
        },

        //关闭更新订单状态弹框
        statusDialogClosed(){
            this.statusDialog = false;
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
          this.customerData = response.data.data
          console.log(page)
          //this.listLoading = false
        }
        }).catch(response=> {
            console.log('获取客户列表失败',response)
        })
      },

        //根据客户查询订单
        selectCurrentChange(val){
            console.log(val)
            this.accessCard=false;
            let currentPage = this.currentPage;
            let pageSize = this.pageSize;
  
            let dataPackage = {
            'pageNum': currentPage,           //页码
            'pageSize': pageSize              //当前页条数
            }
            selectCustomerOrder(val.id,dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                 let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
                this.CustomOrderList = response.data.data
                this.BuyerName = this.CustomOrderList[0].buyerName
                console.log(this.CustomOrderList)
                }
            }).catch(response=>{
                console.log('select customer fail',response)
            })
        },

        //显示客户订单列表
        loadCustomOrderList(){
            let currentPage = this.currentPage;
            let pageSize = this.pageSize;
  
            let dataPackage = {
            'pageNum': currentPage,           //页码
            'pageSize': pageSize              //当前页条数
            }

            getCustomerOrderList(dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                 let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
                this.CustomOrderList = response.data.data
                console.log(this.CustomOrderList)
                //this.listLoading = false
            }
            }).catch(response=>{
                console.log('getCustomOrderList fail',dataPackage,response)
            })

        },

        //取消订单
        handleDelete(val,cal) {
            console.log(val,cal)
            let id = cal.orderId
            let data = {
                id: cal.orderId,
                phone: cal.phone,
                address: cal.address
            }
            cancelCustomOrder(id,data).then(response=>{
                 if(response.data.code == 200 && response.status == 200) {
                    this.$message({
                        type: 'success',
                        message: '取消订单成功！'
                    });
                    this.loadCustomOrderList()
                 }
            }).catch(response=>{
                console.log('cancelOrder fail',response)
            })
        }
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
    .demo-table-expand {
        display: flex;
        flex-direction: row;
        font-size: 0;
        padding: 0px 50px;
        margin: 0 auto;
    }.demo-table-expand label {
        width: 90px;
        color: #4f5a65;
        padding: 0;
    }
    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }
    .crumbs {
    margin: 10px 0;
}
</style>