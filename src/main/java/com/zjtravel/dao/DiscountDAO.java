package com.zjtravel.dao;

import com.zjtravel.pojo.po.DiscountPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hunger on 2017/3/3.
 */
@Repository
public interface DiscountDAO {

    Integer createDiscount(@Param("discountPO")DiscountPO discountPO);

    Integer updateDiscount(@Param("discountPO")DiscountPO discountPO);

    void deleteDiscount(Long discountId);

    DiscountPO findOne(Long discountId);

    List<DiscountPO> findAll();

}
