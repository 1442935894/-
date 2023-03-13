<template>
    <div>  
        <span class="index-title">商品种类管理</span>
        <div class="operation">
            <el-button class="opera-btn" type="success" @click="showAddDialog()">添加种类</el-button>
            <el-button class="opera-btn" type="warning" @click="showEditDialog()">编辑种类</el-button>
            <el-button class="opera-btn" type="danger" @click="delCategory()">删除种类</el-button>
            <el-input 
                class="search-input"
                v-model="search"
                size="middle"
                placeholder="输入关键字搜索"
                clearable
                @keyup.enter.native="handleSearchClick"
                >
                <i
                    class="el-icon-search el-input__icon"
                    slot="suffix"
                    @click="handleSearchClick">
                </i>
            </el-input>
        </div>
        <el-table
            :data="categoryList"
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
            <el-table-column prop="id" label="种类ID" width="100"></el-table-column>
            <el-table-column prop="categoryName" label="种类名称" width="100"></el-table-column>
            <el-table-column prop="createById" label="创建人" width="150"></el-table-column>
            <el-table-column prop="createDatetime" label="添加时间" width="150"></el-table-column>
            <el-table-column prop="updateById" label="更新人" width="150"></el-table-column>
            <el-table-column prop="updateDatetime" label="更新时间" width="150"></el-table-column>
        </el-table>
        <el-dialog
        title="添加类别"
        :visible.sync="dialogAddFormVisible"
        @close="addDialogClosed">
        <el-form v-model="AddCgyForm"  style="text-align: left" ref="dataForm">
            <el-form-item label="名称" :label-width="formLabelWidth" prop="categoryName">
            <el-input v-model="AddCgyForm.categoryName" autocomplete="off" placeholder="填写类别名称"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogAddFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="addCategory(AddCgyForm)">确 定</el-button>
        </div>
        </el-dialog>
        <el-dialog
        title="更改类别"
        :visible.sync="dialogEditFormVisible"
        @close="editDialogClosed">
        <el-form v-model="EditCgyForm" style="text-align: left" ref="dataForm">
            <el-form-item label="ID" :label-width="formLabelWidth" prop="categoryName">
            <el-input v-model="EditCgyForm.id" autocomplete="off" readonly></el-input>
            </el-form-item>
            <el-form-item label="名称" :label-width="formLabelWidth" prop="categoryName">
            <el-input v-model="EditCgyForm.categoryName" autocomplete="off" placeholder="填写类别名称"></el-input>
            </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogEditFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="editCategory(EditCgyForm)">确 定</el-button>
        </div>
        </el-dialog>
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
import {getCategoryList,AddCategoryList,EditCategoryList,DelCategoryList,searchCategoryList} from "../../api/category"
import {GetDateTime} from '../../utils/datetime'
import Bus from '../../main.js'
export default {
    data() {
        return {
            dialogAddFormVisible: false,
            dialogEditFormVisible: false,
            AddCgyForm:{
                categoryName:'',
            },
            EditCgyForm: {
                id: '',
                categoryName:''
            },
            formLabelWidth: '100px',
            categoryList: [],
            totalSize: 0,
            currentPage:1,
            pageSize: 10,
            search:'',
            createById:1,
            updateById:1,
            createDatetime:'',
            updateDatetime:''
        }
    },
    methods: {

        //弹出添加信息窗口
        showAddDialog() {
            this.dialogAddFormVisible = true;
            
        },
        //关闭添加信息窗口
        addDialogClosed() {
            this.AddCgyForm = {}
        },
        //添加分类
        addCategory(AddCgyForm) {
            this.createDatetime = GetDateTime()
            //console.log(this.createDatetime)
            let dataPackage = {
                        categoryName: AddCgyForm.categoryName,
                        createById: this.createById,
                        createDatetime: this.createDatetime
                    }
            AddCategoryList(dataPackage).then(response =>{
                if(response.data.code == 200 && response.status == 200) {
                    console.log(dataPackage);
                    this.$message({
                        type: 'success',
                        message: '添加成功！'
                        }),
                    this.dialogAddFormVisible = false;
                    this.loadCategory()
                }
            })
        },
        //弹出编辑分类窗口
        showEditDialog() {
            let rowList = JSON.parse(JSON.stringify(this.$refs.elTable.selection));
            this.dialogEditFormVisible = true; 
            console.log(rowList[0])
            this.EditCgyForm = {
                id: rowList[0].id,
                categoryName: rowList[0].categoryName
            }
            
           
        },
        //编辑分类
        editCategory(EditCgyForm){
            let id = EditCgyForm.id;
            this.updateDatetime = GetDateTime()
            let dataPackage = {
                        id: EditCgyForm.id,
                        categoryName: EditCgyForm.categoryName,
                        // createById: EditCgyForm.createById,
                        // createDatetime: EditCgyForm.createDatetime,
                        updateById: this.updateById,
                        updateDatetime: this.updateDatetime
                    }
            EditCategoryList(id,dataPackage).then(response =>{
                if(response.data.code == 200 && response.status == 200) {
                    this.$message({
                        type: 'success',
                        message: '更新成功！'
                        }),
                    this.dialogEditFormVisible = false;
                    this.loadCategory()
                }
            })
        },
        //关闭编辑窗口
        editDialogClosed() {
            this.EditCgyForm = {}
        },

        //删除分类
        delCategory(){
            let rowList = JSON.parse(JSON.stringify(this.$refs.elTable.selection));
            let dataPackage = []
            for (var i=0; i< rowList.length; i++) {
                dataPackage.push(rowList[i].id)
            }
            //this.qs.stringify({dataPackage}, {arrayFormat: 'repeat'})
            console.log(dataPackage)
            let that = this;
            that.$confirm('此操作将永久删除该分类, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() =>{
                    DelCategoryList(dataPackage).then(response=>{
                    if(response.data.code == 200 && response.status == 200) {
                        this.$message({
                        type: 'success',
                        message: '删除成功！'
                        });
                        this.loadCategory()
                        dataPackage = [];
                    }
                }).catch(response=>{
                    console.log('删除分类失败',response)
                })
                })
                
              
        },
        //模糊查询
        handleSearchClick(ev) {
            console.log(ev,this.search)
            let currentPage = this.currentPage;
            let pageSize = this.pageSize;
            let dataPackage = {
                'pageNum': currentPage,           //页码
                'pageSize': pageSize              //当前页条数
                }
            searchCategoryList(this.search,dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
                this.categoryList = response.data.data
                console.log(this.categoryList)
                }
            }).catch(response=>{
                console.log('查找信息失败',response)
            })
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.loadCategory()
            //console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.loadCategory()
            console.log(`当前页: ${val}`);
        },
        //获取客户列表
        loadCategory(val,data) {
        // this.listLoading = true
        let currentPage = this.currentPage;
        let pageSize = this.pageSize;
  
         let dataPackage = {
          'pageNum': currentPage,           //页码
          'pageSize': pageSize              //当前页条数
        }
        getCategoryList(dataPackage).then(response => {
            if(response.data.code == 200 && response.status == 200) {
                 let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
                this.categoryList = response.data.data
                console.log(this.categoryList)
                //this.listLoading = false
            }
        }).catch(response=> {
            console.log('获取分类列表失败',response)
        })
      },
    },
    created() {
        this.loadCategory()
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
