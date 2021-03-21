package hx.mall.controller;

import hx.mall.common.api.CommonPage;
import hx.mall.common.api.CommonResult;
import hx.mall.mbg.model.PmsBrand;
import hx.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "PmsBrandController")
@RequestMapping("/brand")
@RestController
public class PmsBrandController {

    @Autowired
    private PmsBrandService brandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    /**
     * 查询所有brand
     *
     * @return 品牌列表
     */
    @ApiOperation("获取品牌列表")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    @GetMapping
    public CommonResult<List<PmsBrand>> select() {
        return CommonResult.success(brandService.listAllBrand());
    }

    /**
     * 创建brand
     *
     * @param pmsBrand 品牌
     * @return 标准结果
     */
    @ApiOperation("创建品牌")
    @PreAuthorize("hasAuthority('pms:brand:create')")
    @PostMapping
    public CommonResult create(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = brandService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug(String.format("createBrand success:%s", pmsBrand.toString()));
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("create failed:{0}");
        }
        return commonResult;
    }

    /**
     * 删除brand
     *
     * @param id
     * @return
     */
    @ApiOperation("删除品牌")
    @PreAuthorize("hasAuthority('pms:brand:delete')")
    @DeleteMapping(value = "/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = brandService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    /**
     * 更新brand
     *
     * @param id
     * @param pmsBrand
     * @param result
     * @return
     */
    @ApiOperation("更新品牌")
    @PreAuthorize("hasAuthority('pms:brand:update')")
    @PutMapping(value = "/{id}")
    public CommonResult update(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrand, BindingResult result) {
        CommonResult commonResult;
        int count = brandService.updateBrand(id, pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug(String.format("updateBrand success:%s",pmsBrand.toString()));
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.error(String.format("updateBrand failed:%s", pmsBrand.toString()));
        }
        return commonResult;
    }

    /**
     * 分页查询
     *
     * @param pageNum  第几页
     * @param pageSize 每页数量
     * @return
     */
    @ApiOperation("分页查询品牌")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    @GetMapping("/listByPage")
    public CommonResult select(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<PmsBrand> brands = brandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brands));
    }

    /**
     * 查询单个brand
     *
     * @param id
     * @return
     */
    @ApiOperation("获取指定id品牌")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public CommonResult select(@PathVariable("id") Long id) {
        CommonResult commonResult;
        PmsBrand brand = brandService.getBrand(id);
        if (brand != null) {
            commonResult = CommonResult.success(brand);
            LOGGER.debug(String.format("查询brand,id:%d", id));
            return commonResult;
        } else {
            commonResult = CommonResult.failed("查询不到");
            return commonResult;
        }
    }
}