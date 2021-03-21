package hx.mall.controller;

import hx.mall.common.api.CommonResult;
import hx.mall.nosql.mongodb.document.MemberReadHistory;
import hx.mall.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员商品浏览记录管理 Controller
 */
@Api(tags = "MemberReadHistoryController")
@RequestMapping("/member/readHistory")
@RestController
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    @ApiOperation("创建浏览记录")
    @PostMapping
    public CommonResult create(@RequestBody MemberReadHistory memberReadHistory) {
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    /**
     * 查询指定memberId的浏览记录
     * @param memberId 会员Id
     * @return 浏览记录列表
     */
    @ApiOperation("展示浏览记录")
    @GetMapping
    public CommonResult<List<MemberReadHistory>> list(Long memberId) {
        List<MemberReadHistory> memberReadHistoryList = memberReadHistoryService.list(memberId);
        return CommonResult.success(memberReadHistoryList);
    }

    @ApiOperation("删除浏览记录")
    @DeleteMapping
    public CommonResult delete(@RequestParam("ids") List<String> ids) {
        // 删除需要传入的是uuid
        int count = memberReadHistoryService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
