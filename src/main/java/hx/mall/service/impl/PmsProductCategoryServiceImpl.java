package hx.mall.service.impl;

import com.github.pagehelper.PageHelper;
import hx.mall.dao.PmsProductCategoryDao;
import hx.mall.mbg.mapper.PmsProductCategoryMapper;
import hx.mall.mbg.model.PmsProductCategory;
import hx.mall.mbg.model.PmsProductCategoryExample;
import hx.mall.service.PmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类Service
 */
@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService<PmsProductCategory> {

    @Autowired
    PmsProductCategoryMapper mapper;

    @Autowired
    PmsProductCategoryDao dao;

    @Override
    public int insert(PmsProductCategory obj) {
        return mapper.insertSelective(obj);
    }

    @Override
    public int insert(List<PmsProductCategory> objs) {
        return dao.insertBatch(objs);
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(List<Long> ids) {
        PmsProductCategoryExample example = getExample();
        example.createCriteria().andIdIn(ids);
        return mapper.deleteByExample(example);
    }

    @Override
    public int update(Long id, PmsProductCategory obj) {
        obj.setId(id);
        return mapper.updateByPrimaryKey(obj);
    }

    @Override
    public int update(List<Long> ids, PmsProductCategory obj) {
        PmsProductCategoryExample example = getExample();
        example.createCriteria().andIdIn(ids);
        return mapper.updateByExampleSelective(obj,example);
    }

    @Override
    public List<PmsProductCategory> select() {
        return mapper.selectByExampleWithBLOBs(getExample());
    }

    @Override
    public PmsProductCategory select(Long id) {
        return mapper.selectByPrimaryKey(id);
    }


    public PmsProductCategoryExample getExample() {
        return new PmsProductCategoryExample();
    }

    @Override
    public List<PmsProductCategory> selectByPage(Integer pageNum, Integer pageSize, String orderBy) {
        PageHelper.startPage(pageNum,pageSize,orderBy);
        return mapper.selectByExample(getExample());
    }
}
