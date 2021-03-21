package hx.mall.controller;

import hx.mall.common.api.CommonResult;
import hx.mall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * 会员管理Controller
 */
@Api(tags = "UmsMemberController")
@RequestMapping("/sso")
@RestController
public class UmsMemberController {
    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("获取验证码")
    @GetMapping(value = "/getAuthCode")
    public CommonResult getAuthCode(@RequestParam String tel) {
        return memberService.generateAuthCode(tel);
    }

    @ApiOperation("判断验证码是否正确")
    @PostMapping(value = "/verifyAuthCode")
    public CommonResult updatePassword(@RequestParam String tel, @RequestParam String authCode) {
        return memberService.verifyAuthCode(tel, authCode);
    }
}
