package person.johnson.hardware.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.johnson.hardware.dao.SysCategoryMapper;
import person.johnson.hardware.login.util.TokenUtil;
import person.johnson.hardware.login.util.WebUtils;
import person.johnson.hardware.model.SysCategory;
import person.johnson.hardware.model.vo.CategoryVo;
import person.johnson.hardware.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/25
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired(required = false)
    private SysCategoryMapper categoryMapper;


    List<SysCategory> categories = new ArrayList<SysCategory>();
    SysCategory category = new SysCategory();

    @Override
    public SysCategory getOneCategory(Long id) {
        category = categoryMapper.selectByPrimaryKey(id);
        return category;
    }

    @Override
    public int insertCategory(SysCategory category) throws Exception {
        List<SysCategory> categories = new ArrayList<>();
        categories = categoryMapper.getOneByName(category.getCategoryName());
        if(categories.size() > 0){
            throw new Exception("已存在该分类名");
        }
        return categoryMapper.insert(category);
    }

    @Override
    public int updateCategory(SysCategory category) {
        return categoryMapper.updateSomeByKey(category);
    }

    @Override
    public int delete(List<Object> ids) {
        int sum=0;
        for(Object id:ids)
            sum = sum + categoryMapper.deleteByPrimaryKey((Long) id);
        return sum;
    }

    @Override
    public List<SysCategory> getAll() {
        categories = categoryMapper.selectAll();
        return categories;
    }

    @Override
    public List<SysCategory> getBodyByName(String name) {
        return categoryMapper.getBodyByName(name);
    }

    @Override
    public List<CategoryVo> getProductByCid(Long cid) {
        return categoryMapper.getAll(cid);
    }
}
