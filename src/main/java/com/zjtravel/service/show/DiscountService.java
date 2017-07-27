package com.zjtravel.service.show;

import com.zjtravel.pojo.po.DiscountPO;

import java.util.List;

/**
 * Created by hunger on 2017/3/5.
 */
public interface DiscountService {

    /**
     * 添加优惠对象
     * @param discountPO
     * @return
     */
    Integer createDiscount(DiscountPO discountPO);

    /**
     * 更新优惠对象
     * @param discountPO
     * @return
     */
    Integer updateDiscount(DiscountPO discountPO);

    /**
     * 删除优惠对象
     * @param discountId
     */
    void deleteDiscount(Long discountId);

    /**
     * 查找单个优惠对象
     * @param discountId
     * @return
     */
    DiscountPO findOne(Long discountId);

    /**
     * 根据id数据批量查找优惠对象
     * @param discountIds
     * @return
     */
    List<DiscountPO> findDiscounts(Long[] discountIds);

    /**
     * 查找全部优惠对象
     * @return
     */
    List<DiscountPO> findAll();
}
