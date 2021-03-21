package hx.mall.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单超时取消并解锁库存的定时器
 */
@Component
public class OrderTimeOutCancelTask {
    private Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    /**
     * cron表达式：Seconds Minutes Hours DayOfMonth Month DayOfWeek [year]
     * 每10分钟烧苗一次，烧苗设定超时时间之前下单的订单，如果没有支付则取消该订单
     */
    @Scheduled(cron = "0 0/10 * ? * ?")
    private void cancelTimeOutOrder(){
        // TODO: 2020/11/8 增加取消订单任务代码
        LOGGER.info("取消订单，并根据sku编号释放库存");
    }
}
