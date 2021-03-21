package hx.mall.service;

import hx.mall.mbg.model.PmsProductCategory;

import java.util.List;

public interface PmsProductCategoryService<T> extends BaseService<T>{

        List<PmsProductCategory> selectByPage(Integer pageNum, Integer pageSize, String orderBy);
}
