<!--挡泥板组件，首页-->
<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="8">
                <el-card shadow="hover" class="mgb20" style="height:252px;">
                    <div class="user-info">
                        <img :src="baseUrl+user.avatarUrl" class="user-avator" alt/>
                        <div class="user-info-cont">
                            <div class="user-info-name">{{user.username}}</div>
                            <div>{{user.roleName}}</div>
                        </div>
                    </div>
                    <!--登录时间地点使用axios从服务器请求到-->
                    <div class="user-info-list">
                        管理员ID
                        <span>{{user.id}}</span>
                    </div>
                    <div class="user-info-list">
                        上次登录时间
                        <span>{{user.lastLoginDatetime}}</span>
                    </div>
                </el-card>

                <!--percontage需要必须要保证数据存在再获取，不然刚获取时，数据还未计算完成，会在后台报错-->
                <el-card shadow="hover" style="height:350px;">
                    <template #header>
						<div class="clearfix">
							<span>待办事项</span>
							<el-button style="float: right; padding: 3px 0" text>添加</el-button>
						</div>
					</template>

					<el-table :show-header="false" :data="todoList" style="width: 100%">
						<el-table-column width="40">
							<template #default="scope">
								<el-checkbox v-model="scope.row.status"></el-checkbox>
							</template>
						</el-table-column>
						<el-table-column>
							<template #default="scope">
								<div
									class="todo-item"
									:class="{
										'todo-item-del': scope.row.status
									}"
								>
									{{ scope.row.title }}
								</div>
							</template>
						</el-table-column>
					</el-table>
                </el-card>
            </el-col>
            <!--用户访问量，系统消息，数量等也从服务端拿-->
            <el-col :span="16">
                <el-card shadow="hover" style="height:650px;">
                    <div slot="header" class="clearfix">
                        <el-calendar v-model="value" >
                        </el-calendar>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
 import {getUserItem} from '../api/login'
  import {getProductList} from '../api/product'



    export default {
        name: 'dashboard',
        data() {
            return {
                todoList:[
                    {
		title: '今天要修复100个bug',
		status: false
	},
	{
		title: '今天要修复100个bug',
		status: false
	},
	{
		title: '今天要写100行代码加几个bug吧',
		status: false
	}
                ],
                value: new Date(),
                query: {
                    pageIndex: 1,
                    pageSize: 4,
                    movieId: '',
                    managerId:''
                },
                movieAllData:[],
                user: {},
                dealData: {},
                statisticData: {},
                pageTotal: 0,
                form: {},
                insertForm:{},
                editVisible: false,
                insertVisible:false,
                total: 0,
                multipleSelection: [],
                defaultManagerId:'admin',
                timer: "",//定义一个定时器的变量
                workTime: "", // 获取当前时间
                baseUrl:'http://10.23.43.22:8081'
            };
        },
    
        created() {
            this.loadProduct()
            this.loadUserItem()
        },
    
        mounted () {
           
            
            this.$root.Bus.$on('user', (mes) => {
                this.user = mes
            })
        },
        methods: {
    
            loadUserItem(){
                var val = window.localStorage.getItem('userItem');//获取存储的元素
                this.user = JSON.parse(val);//解析出json对象
           
            },

        
    loadProduct(){
                let dataPackage = {
                'pageNum': 1,           //页码
                'pageSize': 100              //当前页条数
            }
            getProductList(dataPackage).then(response=>{
                if(response.data.code == 200 && response.status == 200) {
                    //console.log(response.data.data)
                    let arr = response.data.data
                
            }
            }).catch(response=>{
                console.log('getProductList fail',response)
            })
            }

            
   	},


           
            


        

        
    };
</script>


<style scoped>

    .grid-content {
        display: flex;
        align-items: center;
        height: 100px;
    }

    .grid-cont-right {
        flex: 1;
        text-align: center;
        font-size: 14px;
        color: #999;
    }

    .grid-num {
        font-size: 30px;
        font-weight: bold;
    }

    .grid-con-icon {
        font-size: 50px;
        width: 100px;
        height: 100px;
        text-align: center;
        line-height: 100px;
        color: #fff;
    }

    .grid-con-1 .grid-con-icon {
        background: rgb(45, 140, 240);
    }

    .grid-con-1 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-2 .grid-con-icon {
        background: rgb(100, 213, 114);
    }

    .grid-con-2 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-3 .grid-con-icon {
        background: rgb(242, 94, 67);
    }

    .grid-con-3 .grid-num {
        color: rgb(242, 94, 67);
    }

    .user-info {
        display: flex;
        align-items: center;
        padding-bottom: 20px;
        border-bottom: 2px solid #ccc;
        margin-bottom: 20px;
    }

    .user-avator {
        width: 120px;
        height: 120px;
        border-radius: 50%;
    }

    .user-info-cont {
        padding-left: 50px;
        flex: 1;
        font-size: 14px;
        color: #999;
    }

    .user-info-cont div:first-child {
        font-size: 30px;
        color: #222;
    }

    .user-info-list {
        font-size: 14px;
        color: #999;
        line-height: 25px;
    }

    .user-info-list span {
        margin-left: 70px;
    }

    .mgb20 {
        margin: 10px 5px;
    }
.todo-item {
	font-size: 14px;
}

.todo-item-del {
	text-decoration: line-through;
	color: #999;
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
