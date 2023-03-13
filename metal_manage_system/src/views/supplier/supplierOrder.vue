<template>
    <div>  
        <span class="index-title">供应商订单管理</span>
        <div class="operation">
            <el-button class="opera-btn" type="primary" @click="handleIconClick()">新增采购单</el-button>
            <el-button class="opera-btn" type="success" @click="openAddPurchaseDialog()">选择客户订单</el-button>
            
            
            <el-input 
                class="search-input"
                v-model="search"
                size="middle"
                placeholder="输入关键字搜索"
                clearable
                >
                <i
                    class="el-icon-search el-input__icon"
                    slot="suffix"
                    @click="handleIconClick">
                </i>
            </el-input>
        </div>

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
                        <el-form-item label="商品数量：">
                            <span>{{ item.number }}</span>
                        </el-form-item>
                        <el-form-item>
                          <el-input
                          class="searchInput"
                          size="mini"
                          v-model="item.count"
                          :disabled="item.count < 1 ">
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
                          </el-input>
                            <el-button
                                size="mini"
                                type="success"  
                                @click="addOrder(item,scope.row)">
                                下单
                            </el-button>      
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
          <el-table-column prop="id" label="ID" width="180" align="center"></el-table-column>
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
                @size-change="handleSizeProChange"
                @current-change="handleProCurrentChange"
                :current-page="currentPage"
                :page-sizes="[10, 20, 30, 50]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="totalSize">
                </el-pagination>
            </div>

            <!-- <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="memberVisible = false" :disabled="accessCard" >确 定</el-button>
            </span> -->
        </el-dialog>


        <el-dialog
            title="选择客单"
            :visible.sync="purchaseOrderDialog"
            @close="purchaseOderClosed">
            <el-table
                :data="customerOrderList"
                border
                highlight-current-row
                class="table"
                ref="customTable"
                header-cell-class-name="table-header"
                @current-change="selectCurrentChange"
            >
                <el-table-column prop="orderNo" label="订单号" width="180" align="center"></el-table-column>
                <el-table-column prop="buyerName" width="150" label="买家姓名" align="center"></el-table-column>
                <el-table-column prop="orderAmount" label="订单金额" width="80"></el-table-column>
                <el-table-column prop="payAmount" label="支付金额" width="80"></el-table-column>
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
                <el-button type="primary" @click="handlePurchaseOrder()" :disabled="accessCard" >添加采购单</el-button>
            </span>
    </el-dialog>


        <el-table
            :data="purchaseOrderList"
            stripe
            style="width: 100%"
            :header-cell-style="{'text-align':'center'}"
            :cell-style="{'text-align':'center'}"
            >
            <el-table-column
            type="selection"
            width="55">
            </el-table-column>
            <el-table-column prop="purchaseNo" label="订单号" width="100"></el-table-column>
            <el-table-column prop="productName" label="产品名称" width="150"></el-table-column>
            <el-table-column prop="supplierName" label="供应商" width="150"></el-table-column>
            <el-table-column prop="productNumber" label="数量" width="100"></el-table-column>
            <el-table-column prop="createName" label="跟单员" width="150"></el-table-column>
            <el-table-column prop="productTotalCost" label="订单金额" width="100"></el-table-column>
            <el-table-column prop="orderStatus" label="订单状态" width="100">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.orderStatus == 0"  type="primary">已下单</el-tag>
                    <el-tag v-else-if="scope.row.orderStatus == 1"  type="warning">采购中</el-tag>
                    <el-tag v-else-if="scope.row.orderStatus == 2" type="success">已收货</el-tag>
                    <el-tag v-else-if="scope.row.orderStatus == 3" type="info">订单关闭</el-tag>   
                    <!--订单状态 
                        0已下单，
                        1采购中，
                        2已收货，
                        3取消交易 -->
                </template>
            </el-table-column>
            <el-table-column prop="orderItemVo.remark" label="备注" width="100"></el-table-column>
            <el-table-column prop="createDatetime" label="添加时间" width="150"></el-table-column>
            <el-table-column prop="orderFinishTime" label="完成时间" width="150"></el-table-column>
            <el-table-column label="操作" width="300" fixed="right">
                <template slot-scope="scope">
                    <el-button
                        v-if="scope.row.orderStatus == 0" 
                        size="mini"
                        type="success"
                        @click="confirmPurchase(scope.$index, scope.row)">
                        确认采购
                    </el-button>
                    <el-button
                        v-else-if="scope.row.orderStatus == 1" 
                        size="mini"
                        type="success"
                        @click="handleConfirmReceive(scope.$index, scope.row)">
                        确认收货
                    </el-button>
                    <!-- <el-button
                        v-else-if="scope.row.orderStatus == 3"
                        size="mini"
                        type="success"
                        disabled
                       >
                        取消订单
                    </el-button> -->
                    
                    <el-button 
                        v-if="scope.row.orderStatus == 1 || scope.row.orderStatus == 0"
                        size="mini"
                        type="success"
                        @click="cancelPurchaseOrder(scope.$index, scope.row)">
                        取消订单
                    </el-button>
                   <el-button
                        v-if="scope.row.orderId == null && scope.row.orderStatus == 0"
                        size="mini"
                        type="warning"
                        @click="changeNumber(scope.$index, scope.row)">
                        修改数量
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        
        <el-dialog
            title="修改订单数量"
            :visible.sync="changePurchaseOrderDialog"
            @close="changePurchaseOderClosed">
            <el-form
            :data="updateForm">
                <el-form-item label="商品名称" :label-width="formLabelWidth" prop="categoryName">
                    <el-input v-model="updateForm.productName" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="供应商" :label-width="formLabelWidth" prop="categoryName">
                    <el-input v-model="updateForm.supplierName" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="订单数量" :label-width="formLabelWidth" prop="categoryName">
                   <el-input-number v-model="updateForm.productNumber"></el-input-number>
                </el-form-item>
                <el-button type="primary" @click="confirmUpdateNumber(updateForm)">确认修改</el-button>
            </el-form>

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
import {getPurchaseOrderList,addPurchaseOrder,getWarnPurchaseOrder,
confirmPurchaseOrder,confirmReceive,cancelPurchaseOrder,updatePurchaseNumber
} from '../../api/supplier'
import {getProductList} from '../../api/product'
export default {
    data() {
        return {
            updateForm:{},
            changePurchaseOrderDialog:false,
            productData:[],
            placeOrderDialog: false,
             accessCard:true,
            purchaseItem:'',
            customerOrderList:[],
            purchaseOrderDialog: false,
            totalSize: 0,
            currentPage:1,
            pageSize: 10,
           purchaseOrderList: [{
            orderId: '202209270001',
            supplier: '鼎鑫',
            phone: '137154682145',
            merchandiser:'跟单员1号',
            orderPrice:'85000',
            orderState:'进行中',
            createDatetime: '2016-05-02',
            address: '上海市普陀区金沙江路 1518 弄',
        }, {
            orderId: '202209270001',
            supplier: '鼎鑫',
            phone: '137154682145',
            merchandiser:'跟单员1号',
            orderPrice:'85000',
            orderState:'进行中',
            createDatetime: '2016-05-02',
            address: '上海市普陀区金沙江路 1518 弄',
        }, {
            orderId: '202209270001',
            supplier: '鼎鑫',
            phone: '137154682145',
            merchandiser:'跟单员1号',
            orderPrice:'85000',
            orderState:'进行中',
            createDatetime: '2016-05-02',
            address: '上海市普陀区金沙江路 1518 弄',
        }, {
            orderId: '202209270001',
            supplier: '鼎鑫',
            phone: '137154682145',
            merchandiser:'跟单员1号',
            orderPrice:'85000',
            orderState:'进行中',
            createDatetime: '2016-05-02',
            address: '上海市普陀区金沙江路 1518 弄',
        }],
        search:''
        }
    },
    created() {
        this.loadPurchaseOrder()
    },
    methods: {

        changeNumber(val,cal){
            this.changePurchaseOrderDialog = true
            console.log(val,cal)
            this.updateForm = cal
            
        },

        confirmUpdateNumber(updateForm){
            let data = {
                purchaseId: updateForm.purchaseId,
                productNumber: updateForm.productNumber
            }
            updatePurchaseNumber(updateForm.purchaseId,data).then(response=>{
                if(response.data.code == 200 && response.status == 200){
                    this.$message({type:'success',message:'修改订单数量成功！'})
                    this.changePurchaseOrderDialog = false
                    this.loadPurchaseOrder()
                }
            }).catch(response=>{
                console.log('update number fail',response)
            })
        },

       changePurchaseOderClosed(){
        this.changePurchaseOrderDialog = false
       },

        handleIconClick(ev) {
            console.log(ev)
            this.placeOrderDialog = true

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

        placeOderClosed() {
            this.placeOrderDialog = false
        },

         //新增采购单
        addOrder(val,cal) {
            console.log(val,cal)
            let item= []
           
                item.push({
                    
                    "supplierId": val.id,
                    "productId": cal.id,
                    "productCostPrice": val.price,
                    "productTotalCost": (val.costPrice * val.count).toFixed(2),
                    "productNumber": val.count
                })
        

            addPurchaseOrder(item).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                     this.placeOrderDialog = false
                    this.loadPurchaseOrder()
                    this.$message({
                        type:'success',message:'添加采购单成功！'
                    })
                    
                }
            }).catch(response=>{
                console.log('addPurchaseOrder fail', response)
            })
        },

        //确认收货
        handleConfirmReceive(val,cal) {
            console.log(val,cal)
            let dataPackage = {
                purchaseId: cal.purchaseId
            }
            confirmReceive(cal.purchaseId,dataPackage).then(response=>{
                if (response.data.code == 200 && response.status == 200) {
                    this.$message({type:'success',message:'采购单确认收货成功！'})
                    this.loadPurchaseOrder()
                }
            }).catch(response=>{
                console.log('confirmPurchase fail', response)
            })
        },

        //取消订单
        cancelPurchaseOrder(val,cal){
            console.log(val,cal)
            let dataPackage = {
                purchaseId: cal.purchaseId
            }
            cancelPurchaseOrder(cal.purchaseId,dataPackage).then(response=>{
                if (response.data.code == 200 && response.status == 200) {
                    this.$message({type:'success',message:'取消采购单成功！'})
                    this.loadPurchaseOrder()
                }
            }).catch(response=>{
                console.log('cancelPurchase fail', response)
            })
        },

        //确认采购
        confirmPurchase(val,cal) {
            console.log(val,cal)
            let dataPackage = {
                purchaseId: cal.purchaseId
            }
            confirmPurchaseOrder(cal.purchaseId,dataPackage).then(response=>{
                if (response.data.code == 200 && response.status == 200) {
                    this.$message({type:'success',message:'确认采购订单成功！'})
                    this.loadPurchaseOrder()
                }
            }).catch(response=>{
                console.log('confirmPurchase fail', response)
            })
        },


        //选择客单信息
        selectCurrentChange(val){
            console.log(val)
            this.accessCard=false;
            let item= []
            for (let i=0; i< val.items.length; i++) {
                item.push({
                    "orderId": val.items[i].orderId,
                    "orderItemId": val.items[i].itemId,
                    "supplierId": val.items[i].supplierId,
                    "productId": val.items[i].productId,
                    "productCostPrice": val.items[i].productPrice,
                    "productTotalCost": (val.items[i].productCostPrice * val.items[i].productNumber).toFixed(2),
                    "productNumber": val.items[i].productNumber
                })
            }
            console.log(item)
            this.purchaseItem = item
        },
        //添加采购单
        handlePurchaseOrder(){
            // let totalPrice = (index.number*index.price).toFixed(2)
            // let dataPackage = {
            //         "orderId": this.purchaseItem.orderId,
            //         "orderItemId": this.purchaseItem.orderItemId,
            //         "supplierId": index.spId,
            //         "productId": val.id,
            //         "productCostPrice": index.price,
            //         "productTotalCost": totalPrice,
            //         "productNumber": index.number,
                   
            // }
            addPurchaseOrder(this.purchaseItem).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    this.purchaseOrderDialog = false
                    this.loadPurchaseOrder()
                    this.$message({
                        type:'success',message:'添加采购单成功！'
                    })
                }
            }).catch(response=>{
                console.log('addPurchaseOrder fail', response)
            })
        },

        //打开选择客单窗口
        openAddPurchaseDialog(val,cal){
            console.log(val,cal)
            
            this.purchaseOrderDialog = true

            let currentPage = this.currentPage;
            let pageSize = this.pageSize;
  
            let dataPackage = {
            'pageNum': currentPage,           //页码
            'pageSize': pageSize              //当前页条数
            }
            getWarnPurchaseOrder(dataPackage).then(response=>{
              if(response.data.code == 200 && response.status == 200) {
                 let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
                this.customerOrderList = response.data.data
              }
            }).catch(response=>{
              console.log('getProductList fail',response)
            })
        },
        purchaseOderClosed() {
            this.purchaseOrderDialog = false
        },

       

        //显示所有采购单
        loadPurchaseOrder(){
             let currentPage = this.currentPage;
                let pageSize = this.pageSize;
                let dataPackage = {
                'pageNum': currentPage,           //页码
                'pageSize': pageSize              //当前页条数
                }
            getPurchaseOrderList(dataPackage).then(response=>{
                if (response.data.code == 200 && response.status == 200) {
                    let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
                     this.purchaseOrderList = response.data.data
                }
            }).catch(response=>{
                console.log('getPurchaseList fail',response)
            })
        },
          //改变每页显示条数
      handleSizeChange(val) {
            this.pageSize = val;
            this.loadPurchaseOrder()
            //console.log(`每页 ${val} 条`);
        },

        //改变当前页码
        handleCurrentChange(val) {
            this.currentPage = val;
            this.loadPurchaseOrder()
            console.log(`当前页: ${val}`);
        },

        handleSizeProChange(val){
            this.pageSize = val;
            this.handleIconClick()
        },
        handleProCurrentChange(val) {
            this.currentPage = val;
            this.handleIconClick()
            //console.log(`当前页: ${val}`);
        },

        handleSizeCustomChange(val){
            this.pageSize = val;
            this.openAddPurchaseDialog()
        },
        handleCustomCurrentChange(val) {
            this.currentPage = val;
            this.openAddPurchaseDialog()
            //console.log(`当前页: ${val}`);
        },
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
    .searchInput{
      width: 100px;
    }
</style>