<template>
    <div>  
        <span class="index-title">商品基本信息管理</span>
        <el-table
            :data="productList"
            stripe
            style="width: 100%" 
            :header-cell-style="{'text-align':'center'}"
            :cell-style="{'text-align':'center'}"
            >
            <!--row-key="name" 
                :tree-props="{children: 'suppliers'}" -->
            <el-table-column    
            width="55">
            </el-table-column>
            <!-- type="selection" -->
            <el-table-column type="expand">
                <template slot-scope="scope">
                    <el-form inline  class="demo-table-expand"
                    v-for="item of scope.row.suppliers" 
                    :key="item.index"
                    @click="operate(item, $event)">
                        <el-form-item label="供应商：">
                            <span>{{ item.name }}</span>
                        </el-form-item>
                        <el-form-item label="商品 ID：">
                            <span>{{ item.id }}</span>
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
                        <el-form-item label="地址：">
                            <span>{{ item.address }}</span>
                        </el-form-item>
                        <el-form-item label="电话：">
                            <span>{{ item.phone }}</span>
                        </el-form-item>
                        <el-form-item label="邮箱：">
                            <span>{{ item.email }}</span>
                        </el-form-item>
                        <el-form-item >
                             <el-button
                                size="mini"
                                type="success"  
                                @click="showEditDialog(item,$event,scope.row)">修改价格</el-button> 
                                <el-button
                                size="mini"
                                type="danger"  
                                @click="delSupplier(item,scope.row)">删除</el-button> 
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <!-- <el-table-column prop="id" label="ID" width="100"></el-table-column> -->
            <el-table-column prop="imageUrl" label="供应商ID" width="100">
                 <template slot-scope="scope">
                    <el-popover placement="top-start" title="" trigger="hover">
                    <img :src="baseUrl+scope.row.imageUrl" alt="" style="width: 150px;height: 150px">
                    <img slot="reference" :src="baseUrl+scope.row.imageUrl" style="width: 50px;height: 50px">
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column prop="name" label="产品名称" width="150"></el-table-column>
            <el-table-column prop="description" label="产品描述" width="200" show-overflow-tooltip></el-table-column>
            <el-table-column label="商品种类" fit="true">
                <template slot-scope="scope" >
                    <div v-for="(item,index) in scope.row.categories" 
                        :key="index">
                        <el-tag
                            closable
                            :disable-transitions="false"
                            @close="handleClose(scope.row,item)"
                            :if="index>1"
                            >
                            {{item.categoryName}} 
                        </el-tag>
                    </div>
                </template>
            </el-table-column>
            <!-- <el-table-column prop="number" label="库存数量" width="100"></el-table-column>
            <el-table-column prop="costPrice" label="成本价" width="80"></el-table-column>
            <el-table-column prop="price" label="零售价" width="80"></el-table-column>
            <el-table-column prop="phone" label="联系电话" width="120"></el-table-column>
            <el-table-column prop="email" label="邮箱" width="120"></el-table-column>
            <el-table-column prop="address" label="地址" width="150"></el-table-column> -->
            <el-table-column label="操作">
                <template slot="header">
                    <el-button
                    size="mini"
                    type="primary"
                    @click="OpenAddProductDialog()">添加商品</el-button>
                    <el-input 
                    class="search-input"
                    v-model="search"
                    size="mini"
                    placeholder="输入关键字搜索"
                    @keyup.enter.native="handleProductSearch"
                    clearable
                    >
                    <i
                        class="el-icon-search el-input__icon"
                        slot="suffix"
                        @click="handleProductSearch">
                    </i>
            </el-input>
            </template>
                <template slot-scope="scope">
                    <el-autocomplete
                        class="input-new-tag"
                        v-if="scope.row.inputSupplierVisible"
                        :fetch-suggestions="querySearchSupplierAsync"
                        placeholder="请输入供应商"
                        @select="handleSupplierSelect"
                        v-model="scope.row.suppliers.name"
                        ref="saveTagInput"
                        size="mini"
                        autofocus
                        @blur="handleSupplierInputConfirm(scope.row,scope.row.categories)">
                    </el-autocomplete>
                    <el-button
                    v-else  class="button-new-tag"
                    size="mini"
                    type="info"
                    @click="addProductSupplierList(scope.row)">新增供应商</el-button>
                    <el-autocomplete
                            class="input-new-tag"
                            v-if="scope.row.inputVisible"
                            :fetch-suggestions="querySearchAsync"
                            placeholder="请输入分类"
                            @select="handleSelect"
                            v-model="scope.row.categories.categoryName"
                            ref="saveTagInput"
                            size="mini"
                            autofocus
                            @blur="handleInputConfirm(scope.row,scope.row.categories)"
                        ></el-autocomplete>
                        <el-button v-else  class="button-new-tag" type="success" size="mini" @click="showInput(scope.row)">新增分类</el-button>
                    <el-button
                    size="mini"
                    type="warning"
                    @click="updateEditProduct(scope.row)">编辑</el-button>
                    <el-button
                    size="mini"
                    type="danger"
                    @click="delProduct(scope.row)">删除</el-button> 
                </template>
            </el-table-column>
            <!-- <el-table-column prop="createBy" label="创建人" width="150"></el-table-column>
            <el-table-column prop="createDatetime" label="添加时间" width="150"></el-table-column>
            <el-table-column prop="updateBy" label="更新人" width="150"></el-table-column>
            <el-table-column prop="updateDatetime" label="更新时间" width="150"></el-table-column> -->
        </el-table>

        <el-dialog
        title="添加/修改商品基本信息"
        :visible.sync="AddProductFormVisible"
        @close="AddProductDialogClosed">
        <el-form :model="addProductForm" :rules="rules" style="text-align: left" ref="dataForm">
            <el-form-item label="名称" :label-width="formLabelWidth" prop="name">
                <el-input v-model="addProductForm.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="图片URL" :label-width="formLabelWidth" prop="imageUrl">
                <el-input  v-model="addProductForm.imageUrl" type="text" autocomplete="off" >
                </el-input>
                <product-img-upload @onUpload="uploadProductImg" ref="proImgUpload"></product-img-upload> 
            </el-form-item>
            <el-form-item label="商品描述" :label-width="formLabelWidth" prop="description">
                <el-input v-model="addProductForm.description" type="textarea" autocomplete="off" ></el-input>
            </el-form-item>
            <!-- <el-form-item label="商品类别" :label-width="formLabelWidth" prop="description">
                <el-select v-model="categoryName" clearable placeholder="请选择">
                    <el-option
                    v-for="item in categories"
                    :key="item.id"
                    :label="item.categoryName"
                    :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item> -->
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="AddProductFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleEditProduct(addProductForm)">确 定</el-button>
        </div>
        </el-dialog>

        <el-dialog
        title="修改价格"
        :visible.sync="dialogEditFormVisible"
        @close="editDialogClosed">
        <el-form :model="editForm" :rules="rules" style="text-align: left" ref="dataForm">
            <el-form-item label="ID" :label-width="formLabelWidth" prop="supplierId">
                <el-input v-model="editForm.supplierId" autocomplete="off" readonly></el-input>
            </el-form-item>
            <el-form-item label="名称" :label-width="formLabelWidth" prop="name">
                <el-input v-model="editForm.name" autocomplete="off" readonly></el-input>
            </el-form-item>
            <el-form-item label="单价" :label-width="formLabelWidth" prop="price">
                <el-input v-model="editForm.price" autocomplete="off" ></el-input>
            </el-form-item>
            <el-form-item label="成本价格" :label-width="formLabelWidth" prop="costPrice">
                <el-input v-model="editForm.costPrice" autocomplete="off" ></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogEditFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="editSupplier(editForm)">确 定</el-button>
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
import {
    getProductList,updateSupplierPrice,addProductList,updateProductList,
    updateProductCategory,ifDeleteProduct,addProductCategory,addProductSupplier,
    delProductCategory,searchProductDetail,delProductSuppiler
    } from "../../api/product"
import {getCategoryList,searchCategoryList} from '../../api/category'
import {searchProductSupplier} from '../../api/supplier'
import {GetDateTime} from '../../utils/datetime'
import {validateNumber} from '../../utils/validator'
import ProductImgUpload from './ProductImgUpload.vue'
export default {
    components: {ProductImgUpload},
    data() {
        return {
            timeout:  null,
            isAdd:false,
            dialogAddFormVisible: false,
            dialogEditFormVisible: false,
            AddProductFormVisible: false,
            dialogVisible: false,
            formLabelWidth: '100px',
            rules:{
                price: [{ required: true,message:'请输入数字', trigger: "blur", validator: validateNumber }],
                costPrice: [{ required: true,message:'请输入数字', trigger: "blur", validator: validateNumber }],
            },
            categoryName:'',
            productId:'',
            categories:{
                createById:'',
                createDatetime:'',
                id: '',
                updateById:'',
                updateDatetime:'',
                value:''
            },
            productList: [{
                id:'',
                name:'',
                imageUrl:'',
                description:'',
                suppliers:[{
                    id:1
                },
                {id:2}],
                categories:{
                    id:'',
                    categoryName:''
                }
            }],
            editForm:{
                price:'',
                
            },
            addProductForm:{
                imageUrl:'',
            },
            totalSize: 0,
            currentPage:1,
            pageSize: 10,
            search:'',
            createById:1,
            updateById:1,
            createDatetime:'',
            updateDatetime:'',
            //分类tag标签
            inputVisible: false,
            inputSupplierVisible: false,
            inputValue: '',
            baseUrl:'http://10.23.43.22:8081'
        }
    },
    created() {
        this.loadProductList()
    },
    methods: {

        //上传商品图片
         uploadProductImg (){
            console.log('onUpload',this.$refs.proImgUpload.url)
            this.addProductForm.imageUrl = this.$refs.proImgUpload.url
        },

        querySearchAsync(queryString, cd) {
      // 发请求获取搜索结果
       let currentPage = this.currentPage;
            let pageSize = this.pageSize;
            let dataPackage = {
            'pageNum': currentPage,           //页码
            'pageSize': pageSize              //当前页条数
            }
      searchCategoryList(queryString,dataPackage).then(res => {
        // 定义一个空数组用来临时存放处理好的结果
        var category = [];
        // 循环接口返回值，将接口返回对象的SName属性名改为ui组件要的value
        res.data.data.forEach(item => {
            // console.log(queryString,cd)
          const value = JSON.parse(
            JSON.stringify(item).replace(/categoryName/g, "value")
          );
          // push到空数组
          category.push(value);
        });
        // 清空定时器
        clearTimeout(this.timeout);
        // 定一个定时器，有结果之前展示loading
        this.timeout = setTimeout(() => {
          // 调用组件自定方法展示搜索结果
          cd(category);
        }, 2000 * Math.random());
      });
    },
        //删除商品分类标签
         handleClose(row,tag) {
            //console.log(row.id,tag)
            let id = tag.id;
            let dataPackage = {
                "categoryId": id,
                "productId": row.id
            }
            delProductCategory(id,dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    this.$message({
                        type: 'success',
                        message: '删除商品类别成功！'
                        }),
                    this.loadProductList()
                }
            }).catch(response=>{
                console.log('delProductCate fail',response)
            })
      },
        //添加商品分类标签
      showInput(btn) {
        //console.log(btn,'btn')
        this.productId = btn.id
        btn.inputVisible = true 
        this.$forceUpdate()
      },
      handleSelect(select) {
        //console.log(select,'select');
        this.createDatetime = GetDateTime()
        let dataPackage = {
            categoryId: select.id,
            productId: this.productId,
            createById: this.createById,
            createDatetime: this.createDatetime
        }
        addProductCategory(dataPackage).then(response=>{
            if(response.data.code == 200 && response.status == 200) {
                    console.log(dataPackage);
                    this.$message({
                        type: 'success',
                        message: '添加商品分类成功！'
                        }),
                    this.loadProductList()
                }
        }).catch(response=>{
            console.log('addProductCategory fail',response)
        })
      },

      handleInputConfirm(ip) {
        ip.inputVisible = false;
        //console.log(ip.inputVisible,ip,'input')
        //this.$forceUpdate()
      },


        //添加商品供应商来源
        addProductSupplierList(ev) {
            console.log(ev)
            this.productId = ev.id
            ev.inputSupplierVisible = true 
            this.$forceUpdate()
        },

        delSupplier(ev,av){
            console.log(ev,av)
            let id = ev.id
            let dataPackage = {
                "supplierId": ev.id,
                "productId": av.id
            }
            delProductSuppiler(id,dataPackage).then(response=>{
                if (response.data.code == 200 && response.status == 200) {
                    this.$message({ type: 'success', message: '删除供应商来源成功！'})
                    this.loadProductList()
                }
            }).catch(response=>{
                console.log('delProductSupplier fail',response)
            })
        },

        //发请求获取供应商搜索结果
        querySearchSupplierAsync(queryString, cd){
            let currentPage = this.currentPage;
            let pageSize = this.pageSize;
            let dataPackage = {
                'pageNum': currentPage,           //页码
                'pageSize': pageSize              //当前页条数
            }
            searchProductSupplier(queryString,dataPackage).then(response=>{
                var supplier = [];
                response.data.data.forEach(item => {
            // console.log(queryString,cd)
            const value = JSON.parse(
                JSON.stringify(item).replace(/name/g, "value")
            );
            supplier.push(value);
            });
        clearTimeout(this.timeout);
        this.timeout = setTimeout(() => {
          cd(supplier);
        }, 2000 * Math.random());
            }).catch(response=>{
                console.log('searchProductSupplier fail',response)
            })
        },


        //选择供应商来源
        handleSupplierSelect(select){
             console.log(select,'selectSupplier');
            this.createDatetime = GetDateTime()
            let dataPackage = {
                supplierId: select.id,
                productId: this.productId,
                // createById: this.createById,
                // createDatetime: this.createDatetime
            }
            addProductSupplier(dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    console.log(dataPackage);
                    this.$message({
                        type: 'success',
                        message: '添加商品供应商成功！'
                        }),
                    this.loadProductList()
                }
            }).catch(response=>{
                console.log('addProductSupplier fail',response)
            })
        },

        handleSupplierInputConfirm(ip) {
            ip.inputSupplierVisible = false
        },

       
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },

        //商品详情模糊查询
        handleProductSearch(ev) {
            console.log(ev,this.search)
            let currentPage = this.currentPage;
            let pageSize = this.pageSize;
            let dataPackage = {
                'pageNum': currentPage,           //页码
                'pageSize': pageSize              //当前页条数
                }
            searchProductDetail(this.search,dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    let page = response.data;
                    this.totalSize = page.count;
                    this.currentPage = page.currentPage;
                    this.pageSize = page.pageSize;
                this.productList = response.data.data
                console.log(this.productList)
                }
            }).catch(response=>{
                console.log('查找信息失败',response)
            })
        },
        
        //假删除商品信息
        delProduct(ev){
            console.log(ev.id)
            let id = ev.id;
            this.updateDatetime = GetDateTime()
            let dataPackage = {
                id: id,
                ifDelete : true,
                updateById: this.updateById,
                updateDatetime: this.updateDatetime
            }
            ifDeleteProduct(id,dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    this.$message({
                        type: 'success',
                        message: '删除成功！'
                        }),
                    this.loadProductList()
                }
            }).catch(response=>{
                console.log('ifDelProduct fail',response)
            })
        },
        
        //打开编辑窗口
        showEditDialog(item, event,sp) {
            this.dialogEditFormVisible = true;
            //console.log(item,event,sp)
            this.editForm = {
                productId: sp.id,
                supplierId: item.id,
                name: item.name,
                price: item.price,
                costPrice: item.costPrice,
            }
            //console.log(this.editForm)
        },
        //关闭编辑窗口
        editDialogClosed() {
            this.$refs["dataForm"].clearValidate();
            this.editForm = {}
        },
        editSupplier(editForm){
            this.$refs["dataForm"].validate((valid) => {
            if(valid){
                this.updateDatetime = GetDateTime()
                let id = editForm.productId;
                let dataPackage = {
                    productId: editForm.productId,
                    supplierId: editForm.supplierId,
                    price: editForm.price,
                    costPrice: editForm.costPrice,
                    updateById: this.updateById,
                    updateDatetime: this.updateDatetime
                }
                updateSupplierPrice(id,dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    this.$message({
                        type: 'success',
                        message: '更新成功！'
                        }),
                        console.log(id,dataPackage)
                    this.dialogEditFormVisible = false;
                    this.loadProductList()
                }
            }).catch(response=>{
                console.log('updateUserList fail',response,id)
            })      
            } else {
                console.log('error')
            }
        })

            this.editForm = {
                supplierId:'',
                name:'',
                price:'',
                costPrice:'',
                updateById: this.updateById,
                updateDatetime: this.updateDatetime
            }
        },
        

        //改变每页显示条数
      handleSizeChange(val) {
            this.pageSize = val;
            this.loadProductList()
            //console.log(`每页 ${val} 条`);
        },

        //改变当前页码
        handleCurrentChange(val) {
            this.currentPage = val;
            this.loadProductList()
            console.log(`当前页: ${val}`);
        },

        //获取商品信息列表
        loadProductList(){
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
                this.productList = response.data.data
                console.log(response.data.data)
                for (let item of this.productList)
                    item.inputValue = ''
            }
            }).catch(response=>{
                console.log('getProductList fail',response)
            })
            
        },

        //打开添加商品信息窗口
        OpenAddProductDialog(){
            this.isAdd = true
            this.AddProductFormVisible=true
        },

        //关闭添加商品信息窗口
        AddProductDialogClosed() {
            this.addProductForm = {}
            this.AddProductFormVisible=false
        },
        
        //添加商品详情信息
        handleEditProduct(addProductForm) {
            this.createDatetime = GetDateTime()
            this.updateDatetime =  GetDateTime()
             
             //添加商品基本信息
            if(this.isAdd){
                let dataPackage = {
                    name: addProductForm.name,
                    imageUrl: addProductForm.imageUrl,
                    description: addProductForm.description,
                    createById: this.createById,
                    createDatetime: this.createDatetime
                }
                addProductList(dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    console.log(dataPackage,'isadd');
                    this.$message({
                        type: 'success',
                        message: '添加成功！'
                        }),
                    this.AddProductFormVisible = false;
                    this.loadProductList()
                }
            }).catch(response=>{
                console.log('addProductList fail',response,dataPackage)
            })
            }   //修改商品基本信息
            else{
                let id = addProductForm.id;
                let dataPackage = {
                    id: addProductForm.id,
                    name: addProductForm.name,
                    imageUrl: addProductForm.imageUrl,
                    description: addProductForm.description,
                    updateId: this.updateById,
                    updateDatetime: this.updateDatetime
                }
                updateProductList(id,dataPackage).then(response=>{
                    if(response.data.code == 200 && response.status == 200) {
                    console.log(dataPackage,'isupdate');
                    this.$message({
                        type: 'success',
                        message: '修改成功！'
                        }),
                    this.AddProductFormVisible = false;
                    this.loadProductList()
                }
                }).catch(response=>{
                    console.log('updateproductList fail',response)
                })
            }
             
             
        },

        //弹出修改商品信息窗口
        updateEditProduct(ev,av){
            this.isAdd = false
            this.AddProductFormVisible=true
            console.log(ev)
            this.addProductForm = ev 
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
    .demo-table-expand {
        display: flex;
        flex-direction: row;
        font-size: 0;
        padding: 0px 50px;
        margin: 0 auto;
    }
   
    .demo-table-expand label {
        width: 90px;
        color: #4f5a65;
        padding: 0;
    }
    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }

    /* 分类tag标签 */
    .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
</style>