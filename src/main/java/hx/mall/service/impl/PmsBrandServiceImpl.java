package hx.mall.service.impl;

import com.github.pagehelper.PageHelper;
import hx.mall.mbg.mapper.PmsBrandMapper;
import hx.mall.mbg.model.PmsBrand;
import hx.mall.mbg.model.PmsBrandExample;
import hx.mall.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌管理 Service
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper brandMapper;
    @Override
    public List<PmsBrand> listAllBrand() {
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    /**
     * 创建品牌
     * @param brand 品牌ID
     * @return 数量
     */
    @Override
    public int createBrand(PmsBrand brand) {
        return brandMapper.insertSelective(brand);
    }

    /**
     * 根据ID更新品牌
     * @param id 品牌ID
     * @param brand 更新后的品牌
     * @return 数量
     */
    @Override
    public int updateBrand(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 删除品牌
     * @param id 品牌ID
     * @return 数量
     */
    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 分页查询品牌
     * @param pageNum 第几页
     * @param pageSize 每页数量
     * @return 品牌列表
     */
    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    /**
     * 根据ID获取品牌
     * @param id 品牌ID
     * @return 品牌对象
     */
    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }
}
