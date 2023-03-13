package person.johnson.hardware.service;

import person.johnson.hardware.model.SysSupplier;

import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/25
 */
public interface SupplierService {
    //查询所有供应商信息
    List<SysSupplier> getAll();
    //根据id查询供应商信息
    SysSupplier getOneSupplier(Long id);
    //添加供应商信息
    int insertSupplier(SysSupplier sysSupplier) throws Exception;
    //修改供应商信息
    int updateSupplier(SysSupplier sysSupplier);
    //删除供应商信息
    int delete(List<Object> ids);
    //模糊查询供应商（Name）
    List<SysSupplier> getBodyByName(String name);
}
