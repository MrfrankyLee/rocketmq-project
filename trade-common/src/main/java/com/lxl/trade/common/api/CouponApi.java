package com.lxl.trade.common.api;

import com.lxl.trade.common.protocol.coupon.ChangeCouponStatusReq;
import com.lxl.trade.common.protocol.coupon.ChangeCouponStatusRes;
import com.lxl.trade.common.protocol.coupon.QueryCouponReq;
import com.lxl.trade.common.protocol.coupon.QueryCouponRes;

/**
 * @author lixiaole
 * @date 2018/9/8
 * @Description
 */
public interface CouponApi {
    public QueryCouponRes queryCoupon(QueryCouponReq queryCouponReq);

    public ChangeCouponStatusRes changeCouponStatus(ChangeCouponStatusReq changeCouponStatusRes);
}
