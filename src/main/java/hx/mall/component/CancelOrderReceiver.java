package hx.mall.component;

import hx.mall.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的 receiver
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {

    private static Logger LOGGER = LoggerFactory.getLogger(CancelOrderSender.class);

    @Autowired
    private OmsPortalOrderService portalOrderService;

    /**
     * 取消订单消息的处理者
     * @param orderId 订单id
     * @param delayTimes 延迟时间
     */
    @RabbitHandler
    public void handle(Long orderId, final long delayTimes) {
        // 给延迟队列发送消息
        LOGGER.info("receiver delay message orderId:{}", orderId);
        portalOrderService.cancelOrder(orderId);
    }
}
