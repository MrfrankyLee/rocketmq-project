package com.lxl.trade.common.api;

import com.lxl.trade.common.protocol.goods.QueryGoodsReq;
import com.lxl.trade.common.protocol.goods.QueryGoodsRes;
import com.lxl.trade.common.protocol.goods.ReduceGoodsNumberReq;
import com.lxl.trade.common.protocol.goods.ReduceGoodsNumberRes;

/**
 * @author lixiaole
 * @date 2018/9/8
 * @Description
 */
public interface GoodsApi {
    public QueryGoodsRes queryGoods(QueryGoodsReq queryGoodsReq );
    public ReduceGoodsNumberRes reduceGoodsNumber(ReduceGoodsNumberReq reduceGoodsNumberReq);
}
