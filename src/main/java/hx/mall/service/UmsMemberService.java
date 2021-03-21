package hx.mall.service;

import hx.mall.common.api.CommonResult;

public interface UmsMemberService {
    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String tel);

    /**
     * 判断验证码和手机是否匹配
     */
    CommonResult verifyAuthCode(String tel, String authCode);

}
