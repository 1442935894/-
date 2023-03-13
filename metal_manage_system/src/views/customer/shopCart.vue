<template>
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
                    :src="scope.row.image"
                    style="width: 120px; height: 120px"
                  >
                    <div slot="placeholder" class="image-slot">
                      Loading<span class="dot">...</span>
                    </div>
                    <div slot="error" class="image-slot">
                      <el-image
                        style="width: 120px; height: 120px"
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
              <el-table-column label="单价" prop="price"></el-table-column>
              <el-table-column label="数量" width="80">
                <template slot-scope="scope">
                  <el-input-number
                    setp="1"
                    :min="1"
                    v-model="scope.row.num"
                    plain
                    v-on:change="calTotalPrice()"
                  ></el-input-number>
                </template>
              </el-table-column>
              <el-table-column label="金额">
                <template slot-scope="scope">
                  <span>{{ scope.row.num * scope.row.price }}</span>
                </template>
              </el-table-column>
              <el-table-column label="操作" fixed="right" align="center">
                <template slot-scope="scope">
                  <el-button
                    size="medium"
                    type="danger"
                    v-on:click="delGood(scope.row.id)"
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
              <span class="total-symbol">{{ cartTotalPrice }}</span>
              <div v-if="selectionData.length > 0" class="pay-btn-active">
                结算
              </div>
              <div v-else class="pay-btn-inactive">结算</div>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
export default {
    
    data() {
        return {
                //选中数据
                selectionData: [],
                // 购物车列表
                cartList: [],
                // 加载
                loading: false,
                // 购物车总数
                cartTotalCount: 0,
                // 购物车总价格
                cartTotalPrice: 0.0,
                // 是否全选
                isAllChecked: false,
        }
    },
    methods: {
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
        _this.getList();
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
 
    }
}
</script>
<style scoped>

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

