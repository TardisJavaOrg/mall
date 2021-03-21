package hx.mall.service.impl;

import hx.mall.common.api.CommonResult;
import hx.mall.service.RedisService;
import hx.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * 会员管理Service实现类
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public CommonResult generateAuthCode(String tel) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        // 验证码绑定手机号，并储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + tel,sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+tel,AUTH_CODE_EXPIRE_SECONDS);

        return CommonResult.success(sb.toString(),"获取验证码成功");
    }

    /**
     *  对输入的验证码进行校验
     * @param tel 电话号码
     * @param authCode 验证码
     * @return
     */
    @Override
    public CommonResult verifyAuthCode(String tel, String authCode) {
        if (StringUtils.isEmpty(authCode)){
            return  CommonResult.failed("请输入验证码");
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + tel);
        if (authCode.equals(realAuthCode)){
            return  CommonResult.success(null,"验证码校验成功");
        }else{
            return CommonResult.failed("验证码不正确");
        }
    }


}
