package hx.mall.controller;

import hx.mall.common.api.CommonPage;
import hx.mall.common.api.CommonResult;
import hx.mall.nosql.elasticsearch.document.EsProduct;
import hx.mall.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 商品查询功能，通过es
 */
@Api(tags = "EsProductController")
@RequestMapping("/esProduct")
@RestController
public class EsProductController {
    @Autowired
    private EsProductService esProductService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @PostMapping(value = "/importAll")
    @ResponseBody
    public CommonResult<Integer> importAllList(){
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "根据id删除es中的商品")
    @DeleteMapping(value = "/{id}")
    public CommonResult<Object> delete(@PathVariable Long id){
        esProductService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id创建es商品")
    @PostMapping(value = "/{id}")
    public CommonResult<EsProduct> create(@PathVariable Long id){
        EsProduct esProduct = esProductService.create(id);
        if(esProduct != null){
            return  CommonResult.success(esProduct);
        }else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "简单搜索")
    @GetMapping(value = "/simple")
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false,defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false,defaultValue = "5") Integer pageSize){
        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }
}
