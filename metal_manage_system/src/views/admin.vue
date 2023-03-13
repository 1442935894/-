<template>
    <div>  
        <span class="index-title">用户管理</span>
        <div class="operation">
            <el-button class="opera-btn" type="success" @click="showAddDialog()">添加用户</el-button>
            <el-button class="opera-btn" type="warning" @click="showEditDialog()">编辑用户</el-button>
            <el-button class="opera-btn" type="danger" @click="delUser()">删除用户</el-button>
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
                    @click="handleSearchClick">
                </i>
            </el-input>
        </div>
        <el-table
            :data="userList"
            stripe
            style="width: 100%"
            :header-cell-style="{'text-align':'center'}"
            :cell-style="{'text-align':'center'}"
            ref="elTable"
             v-loading="loading"
            >
            <el-table-column
            type="selection"
            width="55">
            </el-table-column>
            <el-table-column prop="avatarUrl" label="用户头像" width="100">
                 <template slot-scope="scope">
                    <el-popover placement="top-start" title="" trigger="hover">
                    <img :src="baseUrl+scope.row.avatarUrl" alt="" style="width: 150px;height: 150px">
                    <img slot="reference" :src="baseUrl+scope.row.avatarUrl" style="width: 50px;height: 50px">
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column prop="username" label="姓名" width="100"></el-table-column>
            <el-table-column prop="password" label="密码" width="100" show-overflow-tooltip></el-table-column>
            <el-table-column prop="sex" label="性别" width="50">
                 <template slot-scope="scope">{{ scope.row.sex === true ? '男' : '女' }}</template>
            </el-table-column>
            <el-table-column prop="birthtime" label="出生年月" width="100"></el-table-column>
            <el-table-column prop="address" label="地址" width="100"></el-table-column>
            <el-table-column prop="email" label="邮箱" width="100"></el-table-column>
            <el-table-column prop="roleName" label="角色" width="100" ></el-table-column>
            <el-table-column prop="lastLoginDatetime" label="上次登录时间" width="150"></el-table-column>
            <el-table-column prop="createById" label="创建人" width="100"></el-table-column>
            <el-table-column prop="createDatetime" label="创建时间" width="150"></el-table-column>
            <el-table-column prop="updateById" label="更新人" width="100"></el-table-column>
            <el-table-column prop="updateDatetime" label="更新时间" width="150"></el-table-column>
            
            <el-table-column label="操作" width="100" fixed="right">
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    type="info"
                    @click="resetPwd(scope.row)">重置密码</el-button> 
                </template>
            </el-table-column>
            <el-table-column prop="status" label="状态" width="100" fixed="right" >
                 <template slot-scope="scope">
                    <el-switch
                    @change="stateChanged(scope.row)"
                    v-model="scope.row.status"
                    >
                    </el-switch>
                </template>
            </el-table-column>
        </el-table>
        <!-- 添加用户弹框 -->
        <el-dialog
        title="添加用户"
        :visible.sync="dialogAddFormVisible"
        @close="addDialogClosed">
        <el-form v-model="AddUserForm" style="text-align: left" ref="dataForm">
            <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
                <el-input v-model="AddUserForm.username" autocomplete="off" placeholder=""> </el-input>
            </el-form-item>
            <el-form-item label="图片URL" :label-width="formLabelWidth" prop="avatarUrl">
                <el-input  v-model="AddUserForm.avatarUrl" type="text" autocomplete="off" >
                    <!-- <template slot="prepend">http://10.23.43.22:8080</template> -->
                </el-input>
                <img-upload @onUpload="uploadImg" ref="imgUpload"></img-upload> 
            </el-form-item>
            <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
                <el-input v-model="AddUserForm.password" autocomplete="off" type="password" placeholder=""></el-input>
            </el-form-item>
            <el-form-item label="性别" :label-width="formLabelWidth" prop="sex">
                <el-select v-model="AddUserForm.sex" clearable placeholder="请选择性别">
                    <el-option label="男" :value="true"></el-option>
                    <el-option label="女" :value="false"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="出生年月" :label-width="formLabelWidth" prop="birthtime">
                <el-date-picker
                    v-model="AddUserForm.birthtime"
                    type="date"
                    placeholder="选择日期"
                    format="yyyy 年 MM 月 dd 日"
                    value-format="yyyy-MM-dd"
                    align="center">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="邮箱" :label-width="formLabelWidth" prop="email">
                <el-input v-model="AddUserForm.email" autocomplete="off" placeholder=""></el-input>
            </el-form-item>
            <el-form-item label="地址" :label-width="formLabelWidth" prop="address">
                <el-input v-model="AddUserForm.address" autocomplete="off" placeholder=""></el-input>
            </el-form-item>
            <el-form-item label="角色" :label-width="formLabelWidth" prop="roleId">
                <el-select v-model="AddUserForm.roleId" clearable placeholder="请选择角色">
                    <el-option label="管理员" :value="1"></el-option>
                    <el-option label="销售员" :value="2"></el-option>
                    <el-option label="跟单员" :value="3"></el-option>
                    <el-option label="财务" :value="4"></el-option>
                </el-select>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogAddFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="addUser(AddUserForm)">确 定</el-button>
        </div>
        </el-dialog>
        <!-- 编辑用户信息弹框 -->
        <el-dialog
        title="编辑用户"
        :visible.sync="dialogEditFormVisible"
        @close="editDialogClosed">
        <el-form v-model="EditUserForm" style="text-align: left" ref="dataForm">
            <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
                <el-input v-model="EditUserForm.username" autocomplete="off" placeholder=""></el-input>
            </el-form-item>
            <el-form-item label="图片URL" :label-width="formLabelWidth" prop="avatarUrl">
                <el-input  v-model="EditUserForm.avatarUrl" type="text" autocomplete="off" >
                </el-input>
                <img-upload @onUpload="uploadImg" ref="imgUpload"></img-upload> 
            </el-form-item>
            <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
                <el-input v-model="EditUserForm.password" autocomplete="off" type="password" placeholder=""></el-input>
            </el-form-item>
            <el-form-item label="性别" :label-width="formLabelWidth" prop="sex">
                <el-select v-model="EditUserForm.sex" clearable placeholder="请选择性别">
                    <el-option label="男" :value="true"></el-option>
                    <el-option label="女" :value="false"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="出生年月" :label-width="formLabelWidth" prop="birthtime">
                <el-date-picker
                    v-model="EditUserForm.birthtime"
                    type="date"
                    placeholder="选择日期"
                    format="yyyy 年 MM 月 dd 日"
                    value-format="yyyy-MM-dd">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="邮箱" :label-width="formLabelWidth" prop="email">
                <el-input v-model="EditUserForm.email" autocomplete="off" placeholder=""></el-input>
            </el-form-item>
            <el-form-item label="地址" :label-width="formLabelWidth" prop="address">
                <el-input v-model="EditUserForm.address" autocomplete="off" placeholder=""></el-input>
            </el-form-item>
            <el-form-item label="角色" :label-width="formLabelWidth" prop="roleId">
                <el-select v-model="EditUserForm.roleId" clearable placeholder="请选择角色">
                    <el-option label="管理员" :value="1"></el-option>
                    <el-option label="销售员" :value="2"></el-option>
                    <el-option label="跟单员" :value="3"></el-option>
                    <el-option label="财务" :value="4"></el-option>
                </el-select>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogEditFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="editUser(EditUserForm)">确 定</el-button>
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
import {getUserList,AddUserList,changeUserStatus,resetUserPwd,searchUserList,updateUserList,ifDeleteUser} from "../api/user"
import {GetDateTime} from '../utils/datetime'
import ImgUpload from '../components/ImgUpload'
export default {
    components: {ImgUpload},
    data() {
        return {
            dialogAddFormVisible: false,
            dialogEditFormVisible: false,
            loading: true,
            AddUserForm:{
                "avatarUrl":"",
                "username":"",
                "password":"",
                "sex":"",
                "birthtime":'',
                "email":"",
                "address":"",
                "roleId":"",
            },
            EditUserForm: {
                "avatarUrl":"",
                "username":"",
                "password":"",
                "sex":"",
                "birthtime":'',
                "email":"",
                "address":"",
                "roleId":"",
            },
            formLabelWidth: '100px',
            userList:[],
            totalSize: 0,
            currentPage:1,
            pageSize: 10,
            search:'',
            createById:1,
            updateById:1,
            createDatetime:'',
            updateDatetime:'',
            baseUrl:'http://10.23.43.22:8081'
        }
    },
    created() {
        this.loadUserList()
    },
    methods: {

        //上传头像
         uploadImg (){
            this.AddUserForm.avatarUrl = this.$refs.imgUpload.url
            this.EditUserForm.avatarUrl = this.$refs.imgUpload.url
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },



        //模糊查询
        handleSearchClick(ev) {
            console.log(ev,this.search);
            let currentPage = this.currentPage;
            let pageSize = this.pageSize;
            let dataPackage = {
                'pageNum': currentPage,           //页码
                'pageSize': pageSize              //当前页条数
            }
            searchUserList(this.search,dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
                this.userList = response.data.data
                console.log(this.userList)
                }
            }).catch(response=>{
                console.log('查找信息失败',response)
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
        //获取用户列表
        loadUserList(val) {
        // this.listLoading = true
        let currentPage = this.currentPage;
        let pageSize = this.pageSize;
  
         let dataPackage = {
          'pageNum': currentPage,           //页码
          'pageSize': pageSize              //当前页条数
        }
        getUserList(dataPackage).then(response => {
            if(response.data.code == 200 && response.status == 200) {
                 let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
                this.userList = response.data.data
                console.log(this.userList)
                this.loading = true;
                setTimeout(() => {
                    this.loading = false;
                }, 1000);
            }
        }).catch(response=> {
            console.log('获取用户列表失败',response)
        })
      },

       //弹出添加信息窗口
        showAddDialog() {
            this.dialogAddFormVisible = true;
            
        },
        //关闭添加信息窗口
        addDialogClosed() {
            this.AddUserForm = {}
        },

        //添加用户
        addUser(AddUserForm){
            this.createDatetime = GetDateTime()
            //console.log(this.createDatetime)
            let dataPackage = {
                        avatarUrl: AddUserForm.avatarUrl,
                        username: AddUserForm.username,
                        password: AddUserForm.password,
                        sex: AddUserForm.sex,
                        birthtime: AddUserForm.birthtime,
                        email: AddUserForm.email,
                        address: AddUserForm.address,
                        roleId: AddUserForm.roleId,
                        createById: this.createById,
                        createDatetime: this.createDatetime
                    }
            AddUserList(dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    console.log(dataPackage);
                    this.$message({
                        type: 'success',
                        message: '添加成功！'
                        }),
                    this.dialogAddFormVisible = false;
                    this.loadUserList()
                }
            }).catch(response=>{
                console.log('addUser fail',response,dataPackage)
            })
        },

        //弹出编辑信息窗口
        showEditDialog() {
            let rowList = JSON.parse(JSON.stringify(this.$refs.elTable.selection));
            this.dialogEditFormVisible = true; 
            console.log(rowList[0])
            this.EditUserForm = {
                id: rowList[0].id,
                avatarUrl: rowList[0].avatarUrl,
                username: rowList[0].username,
                password: rowList[0].password,
                sex: rowList[0].sex,
                address: rowList[0].address,
                email: rowList[0].email,
                birthtime: rowList[0].birthtime,
                roleId: rowList[0].roleId
            }        
        },
        //关闭编辑窗口
        editDialogClosed() {
            this.EditUserForm = {}
        },
        

        //编辑用户信息
        editUser(EditUserForm) {
            let id = EditUserForm.id;
            this.updateDatetime = GetDateTime()
            let dataPackage = {
                        id: EditUserForm.id,
                        avatarUrl: EditUserForm.avatarUrl,
                        username: EditUserForm.username,
                        password: EditUserForm.password,
                        sex: EditUserForm.sex,
                        birthtime: EditUserForm.birthtime,
                        email: EditUserForm.email,
                        address: EditUserForm.address,
                        roleId: EditUserForm.roleId,
                        updateById: this.updateById,
                        updateDatetime: this.updateDatetime
            }
            updateUserList(id,dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    this.$message({
                        type: 'success',
                        message: '更新成功！'
                        }),
                    this.dialogEditFormVisible = false;
                    this.loadUserList()
                }
            }).catch(response=>{
                console.log('updateUserList fail',response)
            })
        },

        //假删除用户
        delUser(){
            let rowList = JSON.parse(JSON.stringify(this.$refs.elTable.selection));
            console.log(rowList[0].id)
            let id = rowList[0].id;
            this.updateDatetime = GetDateTime()
            let dataPackage = {
                id: id,
                ifDelete : true,
                updateById: this.updateById,
                updateDatetime: this.updateDatetime
            }
            ifDeleteUser(id,dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    this.$message({
                        type: 'success',
                        message: '删除成功！'
                        }),
                    this.loadUserList()
                }
            }).catch(response=>{
                console.log('ifdeleteUser fail',response)
            })
        },

        //启动或冻结账户
        stateChanged(ev){
            this.updateDatetime = GetDateTime()
            let dataPackage = {
                status: ev.status,
                updateById: this.updateById,
                updateDatetime: this.updateDatetime
            }
            changeUserStatus(ev.id,dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    console.log(dataPackage)
                }
            }).catch(response=>{
                console.log('changeUserStatus fail',response,dataPackage)
            })
        },

        //重置用户密码
        resetPwd(ev) {
            console.log(ev)
            this.updateDatetime = GetDateTime()
            let dataPackage = {
                id: ev.id,
                updateById: this.updateById,
                updateDatetime: this.updateDatetime
            }
            resetUserPwd(ev.id,dataPackage).then(response=>{
                 if(response.data.code == 200 && response.status == 200) {
                    console.log('resetUserPwd success',dataPackage)
                    this.loadUserList()
                }
            }).catch(response=>{
                console.log('resetPwd fail',response)
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
</style>