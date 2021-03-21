package hx.mall.controller;

import hx.mall.mbg.model.PmsProductAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pms/productAttrCategory")
public class PmsProductAttrCategoryController implements BaseController<PmsProductAttribute> {


    @Override
    public PmsProductAttribute select(Long id) {
        return null;
    }

    @Override
    public List<PmsProductAttribute> select(Long[] ids) {
        return null;
    }

    @Override
    public int insert(PmsProductAttribute obj) {
        return 0;
    }

    @Override
    public int insert(List<PmsProductAttribute> obj) {
        return 0;
    }

    @Override
    public PmsProductAttribute update(Long id, PmsProductAttribute obj) {
        return null;
    }

    @Override
    public PmsProductAttribute delete(Long id) {
        return null;
    }
}
