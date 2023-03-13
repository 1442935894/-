package person.johnson.hardware.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.johnson.hardware.model.SysPurchaseOrder;
import person.johnson.hardware.model.vo.OrderItemVo;
import person.johnson.hardware.model.vo.PurchaseVo;

@Mapper
public interface SysPurchaseOrderMapper {
    @Delete({
        "delete from sys_purchase_order",
        "where purchase_id = #{purchaseId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long purchaseId);

    @Insert({
        "insert into sys_purchase_order (purchase_id,purchase_no, order_id, order_item_id, ",
        "product_id, supplier_id, product_cost_price, product_total_cost, ",
        "product_number, order_finish_time, ",
        "order_status, create_by_id, " ,
        "create_datetime, update_by_id, update_datetime)",
        "values (#{purchaseId,jdbcType=BIGINT},#{purchaseNo,jdbcType=BIGINT},",
        "#{orderId,jdbcType=BIGINT}, #{orderItemId,jdbcType=BIGINT},",
        "#{productId,jdbcType=BIGINT}, #{supplierId,jdbcType=BIGINT},",
        "#{productCostPrice,jdbcType=DECIMAL},#{productTotalCost,jdbcType=DECIMAL}, ",
        "#{productNumber,jdbcType=DECIMAL}, ",
        "#{orderFinishTime,jdbcType=TIMESTAMP},#{orderStatus,jdbcType=TINYINT}, ",
        "#{createById,jdbcType=BIGINT}, #{createDatetime,jdbcType=TIMESTAMP},",
        "#{updateById,jdbcType=BIGINT}, #{updateDatetime,jdbcType=TIMESTAMP})"
    })
    int insert(SysPurchaseOrder record);

    @Select({
        "select",
        " purchase_id,purchase_no, order_id, order_item_id,  " ,
        " product_id, supplier_id, product_cost_price, product_total_cost,  " ,
        " product_number, order_finish_time,  " ,
        " order_status, create_by_id,  " ,
        " create_datetime, update_by_id, update_datetime ",
        "from sys_purchase_order",
        "where purchase_id = #{purchaseId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="purchase_id", property="purchaseId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="purchase_no", property="purchaseNo", jdbcType=JdbcType.BIGINT),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
        @Result(column="order_item_id", property="orderItemId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="supplier_id", property="supplierId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_cost_price", property="productCostPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="product_total_cost", property="productTotalCost", jdbcType=JdbcType.DECIMAL),
        @Result(column="product_number", property="productNumber", jdbcType=JdbcType.DECIMAL),
        @Result(column="order_finish_time", property="orderFinishTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysPurchaseOrder selectByPrimaryKey(Long purchaseId);

    @Select({
            "select",
            " purchase_id,purchase_no, order_id, order_item_id,  " ,
            " product_id, supplier_id, product_cost_price, product_total_cost,  " ,
            " product_number, order_finish_time,  " ,
            " order_status, create_by_id,  " ,
            " create_datetime, update_by_id, update_datetime ",
            "from sys_purchase_order"
    })
    @Results({
        @Result(column="purchase_id", property="purchaseId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="purchase_no", property="purchaseNo", jdbcType=JdbcType.BIGINT),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
        @Result(column="order_item_id", property="orderItemId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="supplier_id", property="supplierId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_cost_price", property="productCostPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="product_total_cost", property="productTotalCost", jdbcType=JdbcType.DECIMAL),
        @Result(column="product_number", property="productNumber", jdbcType=JdbcType.DECIMAL),
        @Result(column="order_finish_time", property="orderFinishTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysPurchaseOrder> selectAll();

    @Update({
        "update sys_purchase_order",
        "set purchase_no = #{purchaseNo,jdbcType=BIGINT},",
          "order_id = #{orderId,jdbcType=BIGINT},",
          "order_item_id = #{orderItemId,jdbcType=BIGINT}",
          "product_id = #{productId,jdbcType=BIGINT},",
          "supplier_id = #{supplierId,jdbcType=BIGINT},",
          "product_cost_price = #{productCostPrice,jdbcType=DECIMAL},",
          "product_total_cost = #{productTotalCost,jdbcType=DECIMAL},",
          "product_number = #{productNumber,jdbcType=DECIMAL},",
          "create_by_id = #{createById,jdbcType=BIGINT},",
          "create_datetime = #{createDatetime,jdbcType=TIMESTAMP},",
          "order_finish_time = #{orderFinishTime,jdbcType=TIMESTAMP},",
          "order_status = #{orderStatus,jdbcType=TINYINT},",
          "update_by_id = #{updateById,jdbcType=BIGINT},",
          "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP}",
        "where purchase_id = #{purchaseId,jdbcType=BIGINT}",
    })
    int updateByPrimaryKey(SysPurchaseOrder record);

    @Update({
            "update sys_purchase_order",
            "set ",
            "product_number = #{productNumber,jdbcType=DECIMAL},",
            "product_total_cost = #{productTotalCost,jdbcType=DECIMAL},",
            "update_by_id = #{updateById,jdbcType=BIGINT},",
            "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP} ",
            "where purchase_id = #{purchaseId,jdbcType=BIGINT}",
    })
    int updateNumberById(SysPurchaseOrder record);

    @Update({
            "update sys_purchase_order",
            "set ",
            "order_status = #{orderStatus,jdbcType=TINYINT},",
            "update_by_id = #{updateById,jdbcType=BIGINT},",
            "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP} ",
            "where purchase_id = #{purchaseId,jdbcType=BIGINT}",
    })
    int updateStatusById(SysPurchaseOrder purchaseOrder);

    @Update({
            "update sys_purchase_order",
            "set ",
            "order_status = #{orderStatus,jdbcType=TINYINT},",
            "update_by_id = #{updateById,jdbcType=BIGINT},",
            "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP} ",
            "where order_id = #{orderId,jdbcType=BIGINT}",
    })
    int updateStatusByOrderId(SysPurchaseOrder purchaseOrder);

    @Select({
            "select",
            "po.purchase_id,po.purchase_no, po.order_id, po.order_item_id,",
            "po.product_id, po.supplier_id, po.product_cost_price, po.product_total_cost,",
            "po.product_number, po.order_finish_time,",
            "po.order_status, po.create_by_id,",
            "po.create_datetime, po.update_by_id, po.update_datetime,",
            "u.username as create_name,",
            "ut.username as update_name,",
            "p.name as product_name,",
            "s.name as supplier_name ",
            "from sys_purchase_order as po",
            "LEFT JOIN sys_user as u",
            "on u.id  = po.create_by_id",
            "LEFT JOIN sys_user as ut",
            "on ut.id = po.update_by_id",
            "left join sys_product as p ",
            "on p.id = po.product_id",
            "left join sys_supplier as s ",
            "on s.id = po.supplier_id"

            })
    @Results({
            @Result(column="purchase_id", property="purchaseId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="purchase_no", property="purchaseNo", jdbcType=JdbcType.BIGINT),
            @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
            @Result(column="order_item_id", property="orderItemId", jdbcType=JdbcType.BIGINT),
            @Result(
                    property = "orderItemVo",//要封装的属性名称
                    column = "order_item_id",//根据那个字段去查询user表的数据
                    javaType = OrderItemVo.class,//要封装的实体类型
                    one = @One(select = "person.johnson.hardware.dao.SysOrderItemMapper.getItemsById")
            ),
            @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
            @Result(column="product_name", property = "productName",jdbcType = JdbcType.VARCHAR),
            @Result(column="supplier_id", property="supplierId", jdbcType=JdbcType.BIGINT),
            @Result(column="supplier_name", property = "supplierName",jdbcType = JdbcType.VARCHAR),
            @Result(column="product_cost_price", property="productCostPrice", jdbcType=JdbcType.DECIMAL),
            @Result(column="product_total_cost", property="productTotalCost", jdbcType=JdbcType.DECIMAL),
            @Result(column="product_number", property="productNumber", jdbcType=JdbcType.DECIMAL),
            @Result(column="order_finish_time", property="orderFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_name", property = "createName",jdbcType = JdbcType.VARCHAR),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_name", property = "updateName",jdbcType = JdbcType.VARCHAR),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<PurchaseVo> getAll();

    @Select({
            "select",
            "po.purchase_id,po.purchase_no, po.order_id, po.order_item_id,",
            "po.product_id, po.supplier_id, po.product_cost_price, po.product_total_cost,",
            "po.product_number, po.order_finish_time,",
            "po.order_status, po.create_by_id,",
            "po.create_datetime, po.update_by_id, po.update_datetime,",
            "u.username as create_name,",
            "ut.username as update_name,",
            "p.name as product_name,",
            "s.name as supplier_name ",
            "from sys_purchase_order as po",
            "LEFT JOIN sys_user as u",
            "on u.id  = po.create_by_id",
            "LEFT JOIN sys_user as ut",
            "on ut.id = po.update_by_id ",
            "left join sys_product as p ",
            "on p.id = po.product_id",
            "left join sys_supplier as s ",
            "on s.id = po.supplier_id ",
            "where po.create_by_id = #{id}"
    })
    @Results({
            @Result(column="purchase_id", property="purchaseId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="purchase_no", property="purchaseNo", jdbcType=JdbcType.BIGINT),
            @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
            @Result(column="order_item_id", property="orderItemId", jdbcType=JdbcType.BIGINT),
            @Result(
                    property = "orderItemVo",//要封装的属性名称
                    column = "order_item_id",//根据那个字段去查询user表的数据
                    javaType = OrderItemVo.class,//要封装的实体类型
                    one = @One(select = "person.johnson.hardware.dao.SysOrderItemMapper.getItemsById")
            ),
            @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
            @Result(column="product_name", property = "productName",jdbcType = JdbcType.VARCHAR),
            @Result(column="supplier_id", property="supplierId", jdbcType=JdbcType.BIGINT),
            @Result(column="supplier_name", property = "supplierName",jdbcType = JdbcType.VARCHAR),
            @Result(column="product_cost_price", property="productCostPrice", jdbcType=JdbcType.DECIMAL),
            @Result(column="product_total_cost", property="productTotalCost", jdbcType=JdbcType.DECIMAL),
            @Result(column="product_number", property="productNumber", jdbcType=JdbcType.DECIMAL),
            @Result(column="order_finish_time", property="orderFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_name", property = "createName",jdbcType = JdbcType.VARCHAR),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_name", property = "updateName",jdbcType = JdbcType.VARCHAR),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<PurchaseVo> getBodyByUid(Long id);
}