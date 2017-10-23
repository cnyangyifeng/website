package com.mocktpo.orm.mapper;

import com.mocktpo.orm.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface OrderMapper {

    @Select({
            "SELECT",
            "MT_ID AS id,",
            "MT_ORDER_NUMBER AS orderNumber,",
            "MT_PID AS pid,",
            "MT_EMAIL AS email,",
            "MT_PAYMENT_TYPE AS paymentType,",
            "MT_PRICE AS price,",
            "MT_STATUS AS status,",
            "MT_ACTIVATION_CODE AS activationCode,",
            "MT_HARDWARE AS hardware",
            "FROM MT_ORDER",
            "WHERE",
            "MT_ORDER_NUMBER = #{orderNumber}"
    })
    Order findByOrderNumber(@Param("orderNumber") String orderNumber);

    @Select({
            "SELECT",
            "MT_ID AS id,",
            "MT_ORDER_NUMBER AS orderNumber,",
            "MT_PID AS pid,",
            "MT_EMAIL AS email,",
            "MT_PAYMENT_TYPE AS paymentType,",
            "MT_PRICE AS price,",
            "MT_STATUS AS status,",
            "MT_ACTIVATION_CODE AS activationCode,",
            "MT_HARDWARE AS hardware",
            "FROM MT_ORDER",
            "WHERE",
            "MT_ACTIVATION_CODE = #{activationCode}"
    })
    Order findByActivationCode(@Param("activationCode") String activationCode);

    @Insert({
            "INSERT INTO MT_ORDER (",
            "MT_ID,",
            "MT_ORDER_NUMBER,",
            "MT_PID,",
            "MT_EMAIL,",
            "MT_PAYMENT_TYPE,",
            "MT_PRICE,",
            "MT_STATUS,",
            "MT_ACTIVATION_CODE,",
            "MT_HARDWARE",
            ") VALUES (",
            "#{id},",
            "#{orderNumber},",
            "#{pid},",
            "#{email},",
            "#{paymentType},",
            "#{price},",
            "#{status},",
            "#{activationCode},",
            "#{hardware}",
            ")"
    })
    void create(Order order);

    @Update({
            "UPDATE MT_ORDER",
            "SET",
            "MT_ID = #{id},",
            "MT_ORDER_NUMBER = #{orderNumber},",
            "MT_PID = #{pid},",
            "MT_EMAIL = #{email},",
            "MT_PAYMENT_TYPE = #{paymentType},",
            "MT_PRICE = #{price},",
            "MT_STATUS = #{status},",
            "MT_ACTIVATION_CODE = #{activationCode},",
            "MT_HARDWARE = #{hardware}",
            "WHERE",
            "MT_ORDER_NUMBER = #{orderNumber}"

    })
    void update(Order order);
}
