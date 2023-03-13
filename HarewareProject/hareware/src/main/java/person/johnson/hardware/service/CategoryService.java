package person.johnson.hardware.service;

import person.johnson.hardware.model.SysCategory;
import person.johnson.hardware.model.vo.CategoryVo;

import java.util.ArrayList;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/25
 */
public interface CategoryService {
    //根据id查询分类信息
    SysCategory getOneCategory(Long id);
    //添加种类信息
    int insertCategory(SysCategory category) throws Exception;
    //修改商品种类信息
    int updateCategory(SysCategory category);
    //删除商品种类
    int delete(List<Object> ids);
    //获取商品种类列表
    List<SysCategory> getAll();
    //模糊查询获取商品分类
    List<SysCategory> getBodyByName(String name);
    //根据分类id获取商品信息
    List<CategoryVo> getProductByCid(Long cid);
}
