package person.johnson.hardware.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import person.johnson.hardware.model.SysProductOrder;
import person.johnson.hardware.model.vo.OrderVo;

@Mapper
public interface SysProductOrderMapper {
    @Delete({
        "delete from sys_product_order",
        "where order_id = #{orderId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long orderId);

    @Insert({
        "insert into sys_product_order (order_id, order_no, ",
        "buyer_id, phone, address, ",
        "order_status, pay_status, ",
        "deliver_status, create_datetime, ",
        "pay_time, buyer_finish_time, ",
        "order_finish_time, create_by_id, ",
        "order_amount, pay_amount, ",
        "remark,warn_status)",
        "values (#{orderId,jdbcType=BIGINT}, #{orderNo,jdbcType=BIGINT}, ",
        "#{buyerId,jdbcType=BIGINT}, #{phone,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{orderStatus,jdbcType=TINYINT}, #{payStatus,jdbcType=TINYINT}, ",
        "#{deliverStatus,jdbcType=TINYINT}, #{createDatetime,jdbcType=TIMESTAMP}, ",
        "#{payTime,jdbcType=TIMESTAMP}, #{buyerFinishTime,jdbcType=TIMESTAMP}, ",
        "#{orderFinishTime,jdbcType=TIMESTAMP}, #{createById,jdbcType=BIGINT}, ",
        "#{orderAmount,jdbcType=DECIMAL}, #{payAmount,jdbcType=DECIMAL}, ",
        "#{remark,jdbcType=VARCHAR},#{warnStatus,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="orderId", before=false, resultType=Long.class)
    int insert(SysProductOrder record);

    @Select({
        "select",
        "order_id, order_no, buyer_id, phone, address, order_status, pay_status, deliver_status, ",
        "create_datetime, pay_time, buyer_finish_time, order_finish_time, create_by_id, ",
        "order_amount, pay_amount, remark, warn_status ",
        "from sys_product_order",
        "where order_id = #{orderId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="order_no", property="orderNo", jdbcType=JdbcType.BIGINT),
        @Result(column="buyer_id", property="buyerId", jdbcType=JdbcType.BIGINT),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="pay_status", property="payStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="deliver_status", property="deliverStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="buyer_finish_time", property="buyerFinishTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="order_finish_time", property="orderFinishTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="order_amount", property="orderAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="pay_amount", property="payAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="warn_status", property="warnStatus", jdbcType=JdbcType.TINYINT)
    })
    SysProductOrder selectByPrimaryKey(Long orderId);

    @Select({
            "select",
            "order_id, order_no, buyer_id, phone, address, order_status, pay_status, deliver_status, ",
            "create_datetime, pay_time, buyer_finish_time, order_finish_time, create_by_id, ",
            "order_amount, pay_amount, remark, warn_status ",
            "from sys_product_order",
            "where buyer_id = #{cid,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="order_no", property="orderNo", jdbcType=JdbcType.BIGINT),
            @Result(column="buyer_id", property="buyerId", jdbcType=JdbcType.BIGINT),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="pay_status", property="payStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="deliver_status", property="deliverStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="buyer_finish_time", property="buyerFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="order_finish_time", property="orderFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="order_amount", property="orderAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="pay_amount", property="payAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="warn_status", property="warnStatus", jdbcType=JdbcType.TINYINT)
    })
    List<SysProductOrder> selectByCustomerId(Long cid);

    @Select({
        "select",
        "order_id, order_no, buyer_id, phone, address, order_status, pay_status, deliver_status, ",
        "create_datetime, pay_time, buyer_finish_time, order_finish_time, create_by_id, ",
        "order_amount, pay_amount, remark , warn_status",
        "from sys_product_order"

    })
    @Results({
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="order_no", property="orderNo", jdbcType=JdbcType.BIGINT),
        @Result(column="buyer_id", property="buyerId", jdbcType=JdbcType.BIGINT),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="pay_status", property="payStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="deliver_status", property="deliverStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="buyer_finish_time", property="buyerFinishTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="order_finish_time", property="orderFinishTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="order_amount", property="orderAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="pay_amount", property="payAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="warn_status", property="warnStatus", jdbcType=JdbcType.TINYINT)
    })
    List<SysProductOrder> selectAll();

    @Update({
        "update sys_product_order",
        "set order_no = #{orderNo,jdbcType=BIGINT},",
          "buyer_id = #{buyerId,jdbcType=BIGINT},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "order_status = #{orderStatus,jdbcType=TINYINT},",
          "pay_status = #{payStatus,jdbcType=TINYINT},",
          "deliver_status = #{deliverStatus,jdbcType=TINYINT},",
          "create_datetime = #{createDatetime,jdbcType=TIMESTAMP},",
          "pay_time = #{payTime,jdbcType=TIMESTAMP},",
          "buyer_finish_time = #{buyerFinishTime,jdbcType=TIMESTAMP},",
          "order_finish_time = #{orderFinishTime,jdbcType=TIMESTAMP},",
          "create_by_id = #{createById,jdbcType=BIGINT},",
          "order_amount = #{orderAmount,jdbcType=DECIMAL},",
          "pay_amount = #{payAmount,jdbcType=DECIMAL},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "warn_status = #{warnStatus,jdbcType=TINYINT}",
        "where order_id = #{orderId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysProductOrder record);

    @Update({
            "update sys_product_order",
            "set ",
            "phone = #{phone,jdbcType=VARCHAR},",
            "address = #{address,jdbcType=VARCHAR} ",
            "where order_id = #{orderId,jdbcType=BIGINT}"
    })
    int updatePhoneAddressById(SysProductOrder record);

    @Update({
            "update sys_product_order",
            "set ",
            "order_status =  #{status,jdbcType=TINYINT} ",
            "where order_id = #{id,jdbcType=BIGINT}"
    })
    int changeOrderStatusById(Long id,Byte status);

    @Update({
            "update sys_product_order",
            "set buyer_finish_time = #{finishTime,jdbcType=TIMESTAMP},",
            "order_status =  #{status,jdbcType=TINYINT} ",
            "where order_id = #{id,jdbcType=BIGINT}"
    })
    int changeBuyerFinishTimeById(Long id,Byte status,Date finishTime);

    @Update({
            "update sys_product_order",
            "set pay_amount = #{payAmount,jdbcType=DECIMAL},",
            "pay_time = #{payTime,jdbcType=TIMESTAMP}, ",
            "pay_status =  #{status,jdbcType=TINYINT} ",
            "where order_id = #{id,jdbcType=BIGINT}"
    })
    int changePayStatusById(Long id, Byte status, BigDecimal payAmount,Date payTime);

    @Update({
            "update sys_product_order",
            "set ",
            "deliver_status =  #{status,jdbcType=TINYINT} ",
            "where order_id = #{id,jdbcType=BIGINT}"
    })
    int changeDeliverStatusById(Long id,Byte status);

    @Update({
            "update sys_product_order",
            "set ",
            "warn_status =  #{status} ",
            "where order_id = #{id}"
    })
    int updateWarnStatus(Long id, Byte status);

    @Update({
            "update sys_product_order",
            "set ",
            "remark =  #{remark} ,",
            "order_finish_time = #{finishTime,jdbcType=TIMESTAMP} ",
            "where order_id = #{id}"
    })
    int updateRemarkById(Long id, String remark, Date finishTime);

    @Select({
            "select",
            "po.order_id, po.order_no, po.buyer_id, po.phone, po.address, po.order_status, po.pay_status, po.deliver_status, ",
            "po.create_datetime, po.pay_time, po.buyer_finish_time, po.order_finish_time, po.create_by_id, ",
            "po.order_amount, po.pay_amount, po.remark, po.warn_status,",
            "c.`name` as buyer_name, c.vip_level,",
            "u.username as create_name ",
            "from sys_product_order as po ",
            "LEFT JOIN sys_customer as c " ,
            "on po.buyer_id = c.id " ,
            "LEFT JOIN sys_user as u " ,
            "on po.create_by_id = u.id"

    })
    @Results({
            @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="order_no", property="orderNo", jdbcType=JdbcType.BIGINT),
            @Result(column="buyer_id", property="buyerId", jdbcType=JdbcType.BIGINT),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="pay_status", property="payStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="deliver_status", property="deliverStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="buyer_finish_time", property="buyerFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="order_finish_time", property="orderFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="order_amount", property="orderAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="pay_amount", property="payAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="buyer_name", property="buyerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_name", property="createName", jdbcType=JdbcType.VARCHAR),
            @Result(column="vip_level", property="vipLevel", jdbcType=JdbcType.TINYINT),
            @Result(column="warn_status", property="warnStatus", jdbcType=JdbcType.TINYINT),
            @Result(property = "items",column = "order_id",
                    many = @Many(select = "person.johnson.hardware.dao.SysOrderItemMapper.getItemsByOrderId",fetchType = FetchType.LAZY))
    })
    List<OrderVo> getAll();

    @Select({
            "select",
            "po.order_id, po.order_no, po.buyer_id, po.phone, po.address, po.order_status, po.pay_status, po.deliver_status, ",
            "po.create_datetime, po.pay_time, po.buyer_finish_time, po.order_finish_time, po.create_by_id, ",
            "po.order_amount, po.pay_amount, po.remark,po.warn_status,",
            "c.`name` as buyer_name, c.vip_level,",
            "u.username as create_name ",
            "from sys_product_order as po ",
            "LEFT JOIN sys_customer as c " ,
            "on po.buyer_id = c.id " ,
            "LEFT JOIN sys_user as u " ,
            "on po.create_by_id = u.id ",
//            "where CONCAT_WS('', c.`name`, u.username) like CONCAT('%',#{name,jdbcType=VARCHAR},'%')"
            "where c.`name` like CONCAT('%',#{name},'%') and po.create_by_id = #{userId}"

    })
    @Results({
            @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="order_no", property="orderNo", jdbcType=JdbcType.BIGINT),
            @Result(column="buyer_id", property="buyerId", jdbcType=JdbcType.BIGINT),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="pay_status", property="payStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="deliver_status", property="deliverStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="buyer_finish_time", property="buyerFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="order_finish_time", property="orderFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="order_amount", property="orderAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="pay_amount", property="payAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="buyer_name", property="buyerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_name", property="createName", jdbcType=JdbcType.VARCHAR),
            @Result(column="vip_level", property="vipLevel", jdbcType=JdbcType.TINYINT),
            @Result(column="warn_status", property="warnStatus", jdbcType=JdbcType.TINYINT),
            @Result(property = "items",column = "order_id",
                    many = @Many(select = "person.johnson.hardware.dao.SysOrderItemMapper.getItemsByOrderId",fetchType = FetchType.LAZY))
    })
    List<OrderVo> getBodyByNameAndUid(String name,Long userId);


    @Select({
            "select",
            "po.order_id, po.order_no, po.buyer_id, po.phone, po.address, po.order_status, po.pay_status, po.deliver_status, ",
            "po.create_datetime, po.pay_time, po.buyer_finish_time, po.order_finish_time, po.create_by_id, ",
            "po.order_amount, po.pay_amount, po.remark,po.warn_status,",
            "c.`name` as buyer_name, c.vip_level,",
            "u.username as create_name ",
            "from sys_product_order as po ",
            "LEFT JOIN sys_customer as c " ,
            "on po.buyer_id = c.id " ,
            "LEFT JOIN sys_user as u " ,
            "on po.create_by_id = u.id ",
            "where po.create_by_id = #{id}"

    })
    @Results({
            @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="order_no", property="orderNo", jdbcType=JdbcType.BIGINT),
            @Result(column="buyer_id", property="buyerId", jdbcType=JdbcType.BIGINT),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="pay_status", property="payStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="deliver_status", property="deliverStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="buyer_finish_time", property="buyerFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="order_finish_time", property="orderFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="order_amount", property="orderAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="pay_amount", property="payAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="buyer_name", property="buyerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_name", property="createName", jdbcType=JdbcType.VARCHAR),
            @Result(column="vip_level", property="vipLevel", jdbcType=JdbcType.TINYINT),
            @Result(column="warn_status", property="warnStatus", jdbcType=JdbcType.TINYINT),
            @Result(property = "items",column = "order_id",
                    many = @Many(select = "person.johnson.hardware.dao.SysOrderItemMapper.getItemsByOrderId",fetchType = FetchType.LAZY))
    })
    List<OrderVo> getBodyByUid(Long id);


    @Select({
            "select",
            "po.order_id, po.order_no, po.buyer_id, po.phone, po.address, po.order_status, po.pay_status, po.deliver_status, ",
            "po.create_datetime, po.pay_time, po.buyer_finish_time, po.order_finish_time, po.create_by_id, ",
            "po.order_amount, po.pay_amount, po.remark,po.warn_status,",
            "c.`name` as buyer_name, c.vip_level,",
            "u.username as create_name ",
            "from sys_product_order as po ",
            "LEFT JOIN sys_customer as c " ,
            "on po.buyer_id = c.id " ,
            "LEFT JOIN sys_user as u " ,
            "on po.create_by_id = u.id ",
            "where po.create_by_id = #{uid} and po.buyer_id = #{cid}"

    })
    @Results({
            @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="order_no", property="orderNo", jdbcType=JdbcType.BIGINT),
            @Result(column="buyer_id", property="buyerId", jdbcType=JdbcType.BIGINT),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="pay_status", property="payStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="deliver_status", property="deliverStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="buyer_finish_time", property="buyerFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="order_finish_time", property="orderFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="order_amount", property="orderAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="pay_amount", property="payAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="buyer_name", property="buyerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_name", property="createName", jdbcType=JdbcType.VARCHAR),
            @Result(column="vip_level", property="vipLevel", jdbcType=JdbcType.TINYINT),
            @Result(column="warn_status", property="warnStatus", jdbcType=JdbcType.TINYINT),
            @Result(property = "items",column = "order_id",
                    many = @Many(select = "person.johnson.hardware.dao.SysOrderItemMapper.getItemsByOrderId",fetchType = FetchType.LAZY))
    })
    List<OrderVo> getOrdersByCidAndUid(Long cid,Long uid);

    @Select({
            "select",
            "po.order_id, po.order_no, po.buyer_id, po.phone, po.address, po.order_status, po.pay_status, po.deliver_status, ",
            "po.create_datetime, po.pay_time, po.buyer_finish_time, po.order_finish_time, po.create_by_id, ",
            "po.order_amount, po.pay_amount, po.remark,po.warn_status,",
            "c.`name` as buyer_name, c.vip_level,",
            "u.username as create_name ",
            "from sys_product_order as po ",
            "LEFT JOIN sys_customer as c " ,
            "on po.buyer_id = c.id " ,
            "LEFT JOIN sys_user as u " ,
            "on po.create_by_id = u.id ",
            "where po.order_id = #{oid}"

    })
    @Results({
            @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="order_no", property="orderNo", jdbcType=JdbcType.BIGINT),
            @Result(column="buyer_id", property="buyerId", jdbcType=JdbcType.BIGINT),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="pay_status", property="payStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="deliver_status", property="deliverStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="buyer_finish_time", property="buyerFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="order_finish_time", property="orderFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="order_amount", property="orderAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="pay_amount", property="payAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="buyer_name", property="buyerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="vip_level", property="vipLevel", jdbcType=JdbcType.TINYINT),
            @Result(column="warn_status", property="warnStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="create_name", property="createName", jdbcType=JdbcType.VARCHAR),
            @Result(property = "items",column = "order_id",
                    many = @Many(select = "person.johnson.hardware.dao.SysOrderItemMapper.getItemsByOrderId",fetchType = FetchType.LAZY))
    })
    OrderVo getBodyByOid(Long oid);

    @Select({
            "select",
            "po.order_id, po.order_no, po.buyer_id, po.phone, po.address, po.order_status, po.pay_status, po.deliver_status, ",
            "po.create_datetime, po.pay_time, po.buyer_finish_time, po.order_finish_time, po.create_by_id, ",
            "po.order_amount, po.pay_amount, po.remark,po.warn_status,",
            "c.`name` as buyer_name, c.vip_level,",
            "u.username as create_name ",
            "from sys_product_order as po ",
            "LEFT JOIN sys_customer as c " ,
            "on po.buyer_id = c.id " ,
            "LEFT JOIN sys_user as u " ,
            "on po.create_by_id = u.id ",
            "where po.order_finish_time >= #{startDate} and po.order_finish_time <= #{endDate} and po.order_status = 2",
            "and po.create_by_id = #{id}"

    })
    @Results({
            @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="order_no", property="orderNo", jdbcType=JdbcType.BIGINT),
            @Result(column="buyer_id", property="buyerId", jdbcType=JdbcType.BIGINT),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="pay_status", property="payStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="deliver_status", property="deliverStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="buyer_finish_time", property="buyerFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="order_finish_time", property="orderFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="order_amount", property="orderAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="pay_amount", property="payAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="buyer_name", property="buyerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_name", property="createName", jdbcType=JdbcType.VARCHAR),
            @Result(column="vip_level", property="vipLevel", jdbcType=JdbcType.TINYINT),
            @Result(column="warn_status", property="warnStatus", jdbcType=JdbcType.TINYINT),
            @Result(property = "items",column = "order_id",
                    many = @Many(select = "person.johnson.hardware.dao.SysOrderItemMapper.getItemsByOrderId",fetchType = FetchType.LAZY))
    })
    List<OrderVo> getBodyByDate(String startDate,String endDate,Long id);

    @Select({
            "select",
            "po.order_id, po.order_no, po.buyer_id, po.phone, po.address, po.order_status, po.pay_status, po.deliver_status, ",
            "po.create_datetime, po.pay_time, po.buyer_finish_time, po.order_finish_time, po.create_by_id, ",
            "po.order_amount, po.pay_amount, po.remark,po.warn_status,",
            "c.`name` as buyer_name, c.vip_level,",
            "u.username as create_name ",
            "from sys_product_order as po ",
            "LEFT JOIN sys_customer as c " ,
            "on po.buyer_id = c.id " ,
            "LEFT JOIN sys_user as u " ,
            "on po.create_by_id = u.id ",
            "where po.warn_status = 1"

    })
    @Results({
            @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="order_no", property="orderNo", jdbcType=JdbcType.BIGINT),
            @Result(column="buyer_id", property="buyerId", jdbcType=JdbcType.BIGINT),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="pay_status", property="payStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="deliver_status", property="deliverStatus", jdbcType=JdbcType.TINYINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="buyer_finish_time", property="buyerFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="order_finish_time", property="orderFinishTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="order_amount", property="orderAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="pay_amount", property="payAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="buyer_name", property="buyerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_name", property="createName", jdbcType=JdbcType.VARCHAR),
            @Result(column="vip_level", property="vipLevel", jdbcType=JdbcType.TINYINT),
            @Result(column="warn_status", property="warnStatus", jdbcType=JdbcType.TINYINT),
            @Result(property = "items",column = "order_id",
                    many = @Many(select = "person.johnson.hardware.dao.SysOrderItemMapper.getItemsByOrderId",fetchType = FetchType.LAZY))
    })
    List<OrderVo> getWarnOrders();

}