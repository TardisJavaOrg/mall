package hx.mall.service.impl;

import hx.mall.common.api.CommonResult;
import hx.mall.component.CancelOrderSender;
import hx.mall.dto.OrderParam;
import hx.mall.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 前台订单管理Service
 */
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderService.class);

    // 取消订单的消息队列Sender
    @Autowired
    private CancelOrderSender cancelOrderSender;

    /**
     * 生成订单
     *
     * @param orderParam 订单参数
     * @return
     */
    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        // TODO: 2020/11/7 执行一系列下单操作
        LOGGER.info("process generateOrder");
        // 下单完成后开启一个延迟消息，用于当前用户没有付款时，取消订单(orderId应该在下单后生成)
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null, "下单成功");
    }

    /**
     * 取消订单
     *
     * @param orderId 订单id
     */
    @Override
    public void cancelOrder(Long orderId) {
        // TODO: 2020/11/7 执行一系列取消订单操作
        LOGGER.info("process cancelOrder orderId:{}", orderId);
    }

    /**
     * 发送延迟消息取消订单
     *
     * @param orderId 订单id
     */
    private void sendDelayMessageCancelOrder(Long orderId) {
        // 获取订单超时时间，假设为60分钟
        long delayTimes = 30 * 1000; // 测试用时 30秒
        // 发送延迟消息 到MQ，用于自动取消超时的订单
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }
}
