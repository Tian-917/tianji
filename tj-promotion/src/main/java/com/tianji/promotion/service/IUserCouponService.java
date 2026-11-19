package com.tianji.promotion.service;

import com.tianji.common.domain.dto.PageDTO;
import com.tianji.common.domain.query.PageQuery;
import com.tianji.promotion.domain.dto.UserCouponDTO;
import com.tianji.promotion.domain.po.Coupon;
import com.tianji.promotion.domain.po.UserCoupon;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tianji.promotion.domain.vo.CouponVO;
import com.tianji.promotion.enums.UserCouponStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户领取优惠券的记录，是真正使用的优惠券信息 服务类
 * </p>
 *
 * @author Tian
 * @since 2026-11-19
 */
public interface IUserCouponService extends IService<UserCoupon> {

    void receiveCoupon(Long couponId);

    void checkAndCreateUserCoupon(UserCouponDTO userCouponDTO);

    void exchangeCoupon(String code);

    PageDTO<CouponVO> queryMyCouponPage(PageQuery pageQuery, UserCouponStatus status);

    void refundCoupon(List<Long> couponIds);

    List<String> queryDiscountRules(List<Long> couponIds);

    void useCoupon(List<Long> couponIds);

    void expireCoupon();
}
