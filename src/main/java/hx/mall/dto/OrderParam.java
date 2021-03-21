package hx.mall.dto;

import lombok.Getter;

@Getter
public class OrderParam {
    // 收货地址 id
    private Long memberReceiveAddressId;
    // 优惠劵 id
    private Long couponId;
    // 使用的积分数
    private  Integer useIntegration;
    // 支付方式
    private Integer payType;
}
