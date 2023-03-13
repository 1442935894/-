<template>
    <div>
        <span class="index-title">财务统计管理</span>
        <div class="operation">
            <el-button type="success" @click="selectSalesDialog()">选择销售员</el-button>
            <el-date-picker
                v-model="datePick"
                type="daterange"
                :picker-options="pickerOptions"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd"
                align="center">
            </el-date-picker>
            <el-button @click="datePicker()" type="primary" icon="el-icon-search">搜索</el-button>
        </div>
        
        <el-dialog title="销售员选择" :visible.sync="memberVisible">
            <el-table
                    :data="userData"
                    border
                    highlight-current-row
                    class="table"
                    ref="multipleTable"
                    header-cell-class-name="table-header"
                    @current-change="selectCurrentChange"
                    :header-cell-style="{'text-align':'center'}"
                    :cell-style="{'text-align':'center'}"
            >
               <el-table-column prop="avatarUrl" label="用户头像" >
                 <template slot-scope="scope">
                    <el-popover placement="top-start" title="" trigger="hover">
                    <img :src="baseUrl+scope.row.avatarUrl" alt="" style="width: 150px;height: 150px">
                    <img slot="reference" :src="baseUrl+scope.row.avatarUrl" style="width: 50px;height: 50px">
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column prop="username" label="姓名" ></el-table-column>
            <el-table-column prop="sex" label="性别" >
                 <template slot-scope="scope">{{ scope.row.sex === true ? '男' : '女' }}</template>
            </el-table-column>
            
            </el-table>
            <!--底部的分页区域-->
            <div class="block">
                <el-pagination
                background
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[5, 10]"
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
            :data="OrderList"
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
            
            <el-table-column prop="remark" label="回访" width="150"></el-table-column>
            <el-table-column prop="createName" label="销售员" width="80"></el-table-column>
            <el-table-column prop="createDatetime" label="创建时间" width="150"></el-table-column>
            
            <el-table-column prop="buyerFinishTime" label="创建时间" width="150"></el-table-column>
            <el-table-column label="操作" width="250" fixed="right">
                <template slot-scope="scope">
                    <el-button type="primary" @click="downloadExcel(scope.row)">
                        导出
                        <i class="el-icon-download el-icon--right"></i>
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import {downloadFile,billOrderList,userList} from '../api/finance'
export default {
    data(){
        return {
          pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        datePick:'',
        userData:[],
        memberVisible: false,
        accessCard:true,
        OrderList:[],
        userId:'',
        totalSize: 0,
        currentPage:1,
        pageSize: 5,
        baseUrl:'http://10.23.43.22:8081'
        }
    },
    created(){
    },
    methods: {

        selectSalesDialog(){
            
            this.memberVisible = true;
            this.loadUserList()
        },
        selectCurrentChange(val){
            console.log(val)
            this.accessCard=false;
            this.userId = val.id
        },

        datePicker(){
            console.log(this.datePick)
            let data = {
                startDate: this.datePick[0],
                endDate: this.datePick[1],
                id: this.userId,
                'pageNum': this.currentPage,           //页码
                 'pageSize': this.pageSize  
            }
            billOrderList(data).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    this.OrderList=response.data.data
                    this.$message({type:'success',message:'加载订单列表成功！'})
                }
            }).catch(response=>{
                console.log('loadOrderList fail',response)
                this.$message.error('加载订单列表失败！')
            })
        },

        //改变每页显示条数
      handleSizeChange(val) {
            this.pageSize = val;
            this.loadUserList()
            //console.log(`每页 ${val} 条`);
        },

        //改变当前页码
        handleCurrentChange(val) {
            this.currentPage = val;
            this.loadUserList()
            console.log(`当前页: ${val}`);
        },

        downloadExcel() {
            let data = {
                startDate: this.datePick[0],
                endDate: this.datePick[1],
                id: this.userId,
                'pageNum': this.currentPage,           //页码
                 'pageSize': this.pageSize  
            }
            this.$message.info("导出中，请稍后~");
            downloadFile(data).then(response=>{
                
                    let blob = new Blob([response.data], {
                    type: "application/vnd.ms-excel",
                    });
    let filename = "客户订单" + this.datePick[0] + '至' + this.datePick[1] + '.xls'
    // 将blob对象转为一个URL
    var blobURL = window.URL.createObjectURL(blob);
    // 创建一个a标签
    var tempLink = document.createElement("a");
    // 隐藏a标签
    tempLink.style.display = "none";
    // 设置a标签的href属性为blob对象转化的URL
    tempLink.href = blobURL;
    // 给a标签添加下载属性
    tempLink.setAttribute("download", filename);
    if (typeof tempLink.download === "undefined") {
      tempLink.setAttribute("target", "_blank");
    }
    // 将a标签添加到body当中
    document.body.appendChild(tempLink);
    // 启动下载
    tempLink.click();
    // 下载完毕删除a标签
    document.body.removeChild(tempLink);
    window.URL.revokeObjectURL(blobURL);
    this.$message({
      message: "导出成功~",
      type: "success",
    });
    

            }).catch(response=>{
                console.log('download fail',response)
                this.$message.error('下载失败，请重试或者联系管理员');
            })
        },

     

        loadUserList(){
            let data = {
                'pageNum': this.currentPage,           //页码
                 'pageSize': this.pageSize  
            } 
            userList(data).then(response=>{
                if (response.data.code == 200 && response.status == 200) {
                    let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
                    this.userData = response.data.data
                }
            }).catch(response=>{
                console.log('load userList fail',response)
            })
        }
    }
}
</script>

<style scoped>

     .index-title {
        display: flex;
        flex-direction: row;
        font-size: 36px;
        padding: 10px 50px;
    }
    .operation {
        padding: 10px 50px;
        display: flex;
        flex-direction: row;
    }
</style>