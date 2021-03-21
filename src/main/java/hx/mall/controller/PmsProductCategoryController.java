package hx.mall.controller;

import hx.mall.common.api.CommonPage;
import hx.mall.common.api.CommonResult;
import hx.mall.mbg.model.PmsProductCategory;
import hx.mall.service.PmsProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "PmsProductCategoryController")
@RequestMapping("/productCategory")
@RestController
public class PmsProductCategoryController {

    @Autowired
    private PmsProductCategoryService productCategoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductCategoryController.class);


    @ApiOperation("获取商品分类列表")
    @GetMapping
    public CommonResult<List<PmsProductCategory>> select() {
        return CommonResult.success(productCategoryService.select());
    }


    @ApiOperation("删除商品分类")
    @PreAuthorize("hasAuthority('pms:productCategory:delete')")
    @DeleteMapping(value = "/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {

        int count = productCategoryService.delete(id);
        if (count == 1) {
            LOGGER.debug("deleteProductCategory success :id{}", id);
            return CommonResult.success();
        } else {
            LOGGER.debug("deleteProductCategory :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("创建商品分类")
    @PreAuthorize("hasAuthority('pms:productCategory:create')")
    @PostMapping
    public CommonResult insert(@RequestBody PmsProductCategory productCategory) {
        CommonResult commonResult;
        int count = productCategoryService.insert(productCategory);
        if (count == 1) {
            commonResult = CommonResult.success(productCategory);
            LOGGER.debug(String.format("create productCategory success:%s", productCategory.toString()));
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.error("create failed:{0}", productCategory.toString());
        }
        return commonResult;
    }

    @ApiOperation("更新商品分类")
    @PreAuthorize("hasAuthority('pms:productCategory:update')")
    @PutMapping("/{id}")
    public CommonResult update(@PathVariable("id") Long id, @RequestBody PmsProductCategory productCategory) {
        CommonResult commonResult;
        int count = productCategoryService.update(id, productCategory);
        if (count == 1) {
            commonResult = CommonResult.success(productCategory);
            LOGGER.error(String.format("update productCategory success:%s", productCategory.toString()));
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.error(String.format("update productCategory failed:%s", productCategory.toString()));
        }
        return commonResult;
    }

    @ApiOperation("分页查询商品分类")
    @GetMapping("/listByPage")
    public CommonResult select(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize,
                               @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        List<PmsProductCategory> productCategories = productCategoryService.selectByPage(pageNum, pageSize, orderBy);
        return CommonResult.success(CommonPage.restPage(productCategories));
    }
}
