package person.johnson.hardware.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.johnson.hardware.model.SysOrderItem;
import person.johnson.hardware.model.vo.OrderItemVo;

@Mapper
public interface SysOrderItemMapper {
    @Delete({
        "delete from sys_order_item",
        "where item_id = #{itemId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long itemId);

    @Insert({
        "insert into sys_order_item (item_id, order_id, ",
        "supplier_id, product_id, product_cost_price ,",
        "product_price, product_number, ",
        "remark, create_datetime, ",
        "create_by_id)",
        "values (#{itemId,jdbcType=BIGINT}, #{orderId,jdbcType=BIGINT}, ",
        "#{supplierId,jdbcType=BIGINT}, #{productId,jdbcType=BIGINT}, #{productCostPrice,jdbcType=DECIMAL},",
        "#{productPrice,jdbcType=DECIMAL}, #{productNumber,jdbcType=DECIMAL}, ",
        "#{remark,jdbcType=VARCHAR}, #{createDatetime,jdbcType=TIMESTAMP}, ",
        "#{createById,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="itemId", before=false, resultType=Long.class)
    int insert(SysOrderItem record);

    @Select({
        "select",
        "item_id, order_id, supplier_id, product_id, product_cost_price, product_price, ",
        "product_number, remark, create_datetime, create_by_id",
        "from sys_order_item",
        "where item_id = #{itemId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="item_id", property="itemId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
        @Result(column="supplier_id", property="supplierId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_cost_price", property="productCostPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="product_price", property="productPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="product_number", property="productNumber", jdbcType=JdbcType.DECIMAL),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysOrderItem selectByPrimaryKey(Long itemId);

    @Select({
        "select",
        "item_id, order_id, supplier_id, product_id, product_cost_price, product_price, ",
        "product_number, remark, create_datetime, create_by_id",
        "from sys_order_item"
    })
    @Results({
        @Result(column="item_id", property="itemId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
        @Result(column="supplier_id", property="supplierId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_cost_price", property="productCostPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="product_price", property="productPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="product_number", property="productNumber", jdbcType=JdbcType.DECIMAL),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.VARCHAR)
    })
    List<SysOrderItem> selectAll();

    @Update({
        "update sys_order_item",
        "set order_id = #{orderId,jdbcType=BIGINT},",
          "supplier_id = #{supplierId,jdbcType=BIGINT},",
          "product_id = #{productId,jdbcType=BIGINT},",
          "product_cost_price = #{productCostPrice,jdbcType=DECIMAL},",
          "product_price = #{productPrice,jdbcType=DECIMAL},",
          "product_number = #{productNumber,jdbcType=DECIMAL},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "create_datetime = #{createDatetime,jdbcType=TIMESTAMP},",
          "create_by_id = #{createById,jdbcType=VARCHAR}",
        "where item_id = #{itemId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysOrderItem record);

    @Select({
            "select",
            "oi.item_id, oi.order_id, oi.supplier_id, oi.product_id, oi.product_cost_price, oi.product_price,",
            "oi.product_number, oi.remark, oi.create_datetime, oi.create_by_id,",
            "s.`name` as supplier_name,p.`name` as product_name",
            "from sys_order_item as oi",
            "left join sys_supplier as s",
            "on s.id = oi.supplier_id",
            "left join sys_product as p",
            "on p.id = oi.product_id ",
            "where order_id=#{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="item_id", property="itemId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
            @Result(column="supplier_id", property="supplierId", jdbcType=JdbcType.BIGINT),
            @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
            @Result(column="product_cost_price", property="productCostPrice", jdbcType=JdbcType.DECIMAL),
            @Result(column="product_price", property="productPrice", jdbcType=JdbcType.DECIMAL),
            @Result(column="product_number", property="productNumber", jdbcType=JdbcType.DECIMAL),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.VARCHAR),
            @Result(column="supplier_name", property="supplierName", jdbcType=JdbcType.VARCHAR),
            @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR)

    })
    List<OrderItemVo> getItemsByOrderId(Long id);

    @Select({
            "select",
            "oi.item_id, oi.order_id, oi.supplier_id, oi.product_id, oi.product_cost_price, oi.product_price,",
            "oi.product_number, oi.remark, oi.create_datetime, oi.create_by_id,",
            "s.`name` as supplier_name,p.`name` as product_name",
            "from sys_order_item as oi",
            "left join sys_supplier as s",
            "on s.id = oi.supplier_id",
            "left join sys_product as p",
            "on p.id = oi.product_id ",
            "where oi.item_id=#{itemId,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="item_id", property="itemId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
            @Result(column="supplier_id", property="supplierId", jdbcType=JdbcType.BIGINT),
            @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
            @Result(column="product_cost_price", property="productCostPrice", jdbcType=JdbcType.DECIMAL),
            @Result(column="product_price", property="productPrice", jdbcType=JdbcType.DECIMAL),
            @Result(column="product_number", property="productNumber", jdbcType=JdbcType.DECIMAL),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.VARCHAR),
            @Result(column="supplier_name", property="supplierName", jdbcType=JdbcType.VARCHAR),
            @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR)
    })
    OrderItemVo getItemsById(Long itemId);
}