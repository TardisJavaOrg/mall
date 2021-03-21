package hx.mall.dao;

import hx.mall.mbg.model.PmsProductCategory;

import java.util.List;

/**
 * 商品分类 自定义操作
 */
public interface PmsProductCategoryDao {

    int insertBatch(List<PmsProductCategory> objs);

}
