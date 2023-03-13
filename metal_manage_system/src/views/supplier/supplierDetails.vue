<template>
    <div>  
        <span class="index-title">供应商信息管理</span>
        <div class="operation">
            <el-button class="opera-btn" type="success" @click="showAddDialog()">添加供应商</el-button>
            <el-button class="opera-btn" type="warning" @click="showEditDialog()">编辑供应商</el-button>
            <el-button class="opera-btn" type="danger"  @click="delCategory()"> 删除供应商</el-button>
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

        <!--添加供应商的弹窗 -->
        <el-dialog
        title="添加供应商"
        :visible.sync="dialogAddFormVisible"
        @close="addDialogClosed">
        <el-form v-model="AddSdForm" style="text-align: left" ref="dataForm">
            <el-form-item label="名称" :label-width="formLabelWidth" prop="supplierName">
            <el-input v-model="AddSdForm.name" autocomplete="off" placeholder="填写供应商名称"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" :label-width="formLabelWidth" prop="phone">
                <el-input v-model="AddSdForm.phone" autocomplete="off" placeholder="联系电话"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" :label-width="formLabelWidth" prop="email">
                <el-input v-model="AddSdForm.email" autocomplete="off" placeholder="邮箱"></el-input>
            </el-form-item>
            <el-form-item label="地址" :label-width="formLabelWidth" prop="address">
                <el-input v-model="AddSdForm.address" autocomplete="off" placeholder="地址"></el-input>
            </el-form-item>
            <el-form-item prop="id" style="height: 0">
            <el-input type="hidden" v-model="AddSdForm.id" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogAddFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="addSupplier(AddSdForm)">确 定</el-button>
        </div>
        </el-dialog>
         <!--修改供应商的弹窗 -->
         <el-dialog
      title="修改供应商信息"
      :visible.sync="dialogFormVisible"
      @close="editDialogClosed">
      <el-form v-model="EditSdForm" style="text-align: left" ref="dataForm">
            <el-form-item label="名称" :label-width="formLabelWidth" prop="supplierName">
            <el-input v-model="EditSdForm.name" autocomplete="off" placeholder="填写供应商名称"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" :label-width="formLabelWidth" prop="phone">
                <el-input v-model="EditSdForm.phone" autocomplete="off" placeholder="联系电话"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" :label-width="formLabelWidth" prop="email">
                <el-input v-model="EditSdForm.email" autocomplete="off" placeholder="邮箱"></el-input>
            </el-form-item>
            <el-form-item label="地址" :label-width="formLabelWidth" prop="address">
                <el-input v-model="EditSdForm.address" autocomplete="off" placeholder="地址"></el-input>
            </el-form-item>
            <el-form-item prop="id" style="height: 0">
          <el-input type="hidden" v-model="EditSdForm.id" autocomplete="off"></el-input>
        </el-form-item>
        </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editSupplier(EditSdForm)">确 定</el-button>
      </div>
    </el-dialog>

        <el-table
            :data="SDList"
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
            <el-table-column prop="id" label="供应商ID" width="100"></el-table-column>
            <el-table-column prop="name" label="供应商" width="150"></el-table-column>
            <el-table-column prop="phone" label="联系电话" width="200"></el-table-column>
            <el-table-column prop="email" label="邮箱" width="150"></el-table-column>
            <el-table-column prop="address" label="地址" width="100"></el-table-column>
            <el-table-column prop="createById" label="创建人" width="150"></el-table-column>
            <el-table-column prop="createDatetime" label="添加时间" width="150"></el-table-column>
            <el-table-column prop="updateBy" label="更新人" width="150"></el-table-column>
            <el-table-column prop="updateDatetime" label="更新时间" width="150"></el-table-column>
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
    </div>
</template>

<script>
import { AddSDList, DelSDList, EditSDList, getSDList, searchSDList } from "../../api/supplierDetails";
import { GetDateTime } from '../../utils/datetime';
export default {
    data() {
        return {
            dialogAddFormVisible: false,
            dialogEditFormVisible: false,
            AddSdForm:{
                name:'',
                phone:'',
                email:'',
                address:''
            },
            EditSdForm:{
                id:'',
                name:'',
                phone:'',
                email:'',
                address:''
            },
            form:{
            id:'',
            name:'',
            phone:'',
            email:'',
            address:'',
        },
          SDList: [{
            id: '12432531',
          name: '鼎鑫',
          phone: '1354687458',
          email: 'dingxin@fifox.com',
          createDatetime: '2016-05-02',
          createById: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          num: 200333,
        }, {
            id: '12432531',
          name: '鼎鑫',
          phone: '1354687458',
          email: 'dingxin@fifox.com',
          createDatetime: '2016-05-02',
          createById: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          num: 200333,
        }, {
            id: '12432531',
          name: '鼎鑫',
          phone: '1354687458',
          email: 'dingxin@fifox.com',
          createDatetime: '2016-05-02',
          createById: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          num: 200333,
        }, {
            id: '12432531',
          name: '鼎鑫',
          phone: '1354687458',
          email: 'dingxin@fifox.com',
          createDatetime: '2016-05-02',
          createById: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          num: 200333,
        }],
        dialogFormVisible: false,
        formLabelWidth: '100px',
        totalSize: 0,
        currentPage:1,
        pageSize: 10,
        search:''
        }
    },
    methods: {

        //弹出添加信息窗口
        showAddDialog() {
            this.dialogAddFormVisible = true;
            
        },
        //关闭添加信息窗口
        addDialogClosed() {
            this.AddSdForm = {}
        },
        //添加供应商
        addSupplier(AddSdForm) {
            this.createDatetime = GetDateTime()
            //console.log(this.createDatetime)
            let dataPackage = {
                        id:AddSdForm.id,
                        name: AddSdForm.name,
                        phone:AddSdForm.phone,
                        email:AddSdForm.email,
                        address:AddSdForm.address,
                        createById: this.createById,
                        createDatetime: this.createDatetime
                    }
                    AddSDList(dataPackage).then(response =>{
                if(response.data.code == 200 && response.status == 200) {
                    console.log(dataPackage);
                    this.$message({
                        type: 'success',
                        message: '添加成功！'
                        }),
                    this.dialogAddFormVisible = false;
                    this.loadSupplierDetails()
                }
            })
        },


        //弹出编辑窗口
        showEditDialog() {
        //console.log(this.$refs.elTable.selection)
        this.dialogFormVisible = true
        let rowList = JSON.parse(JSON.stringify(this.$refs.elTable.selection)); 
        // this.form = Object.assign({},this.$refs.elTable.selection)
        console.log(rowList[0].id)
        this.EditSdForm = {
            id:rowList[0].id,
            name:rowList[0].name,
            phone:rowList[0].phone,
            email:rowList[0].email,
            address:rowList[0].address,
        }
        
      },

        //编辑客户信息
        editSupplier(EditSdForm) {
            let id = EditSdForm.id;
            this.updateDatetime = GetDateTime()
            //console.log(this.createDatetime)
            let dataPackage = {
                        id:EditSdForm.id,
                        name: EditSdForm.name,
                        phone:EditSdForm.phone,
                        email:EditSdForm.email,
                        address:EditSdForm.address,
                        updateById: this.updateById,
                        updateDatetime: this.updateDatetime
                    }
                EditSDList(id,dataPackage).then(response =>{
                if(response.data.code == 200 && response.status == 200) {
                    this.$message({
                        type: 'success',
                        message: '更新成功！'
                        }),
                        console.log(id,dataPackage)
                    this.dialogFormVisible = false;
                    this.loadSupplierDetails()
                }
            }).catch(response=>{
                 console.log(dataPackage,response)
            })
        },

        //关闭编辑窗口
        editDialogClosed () {
          this.EditSdForm = {}
        },



        //删除供应商
        delCategory(){
            let rowList = JSON.parse(JSON.stringify(this.$refs.elTable.selection));
            let dataPackage = []
            for (var i=0; i< rowList.length; i++) {
                dataPackage.push(rowList[i].id)
            }
            //this.qs.stringify({dataPackage}, {arrayFormat: 'repeat'})
            console.log(dataPackage)
            let that = this;
            that.$confirm('此操作将永久删除该供应商, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() =>{
                    DelSDList(dataPackage).then(response=>{
                    if(response.data.code == 200 && response.status == 200) {
                        this.$message({
                        type: 'success',
                        message: '删除成功！'
                        });
                        this.loadSupplierDetails()
                        dataPackage = [];
                    }
                }).catch(response=>{
                    console.log('删除客户失败',response)
                })
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
            searchSDList(this.search,dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
                this.SDList = response.data.data
                console.log(this.SDList)
                }
            }).catch(response=>{
                console.log('查找信息失败',response)
            })
        },

        handleSizeChange(val) {
            this.pageSize = val;
            this.loadSupplierDetails()
            //console.log(`每页 ${val} 条`);

        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.loadSupplierDetails()
            console.log(`当前页: ${val}`);
        },

        //获取客户列表
        loadSupplierDetails(val) {
        // this.listLoading = true
        let currentPage = this.currentPage;
        let pageSize = this.pageSize;
  
         let dataPackage = {
          'pageNum': currentPage,           //页码
          'pageSize': pageSize              //当前页条数
        }
        getSDList(dataPackage).then(response => {
            if(response.data.code == 200 && response.status == 200) {
                 let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
                this.SDList = response.data.data
                console.log(this.SDList)
                //this.listLoading = false
            }
        }).catch(response=> {
            console.log('获取供应商列表失败',response)
        })
      },
    },
    created() {
        this.loadSupplierDetails()
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
</style>