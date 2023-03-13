package person.johnson.hardware.service;

import person.johnson.hardware.model.SysCustomer;
import person.johnson.hardware.model.vo.CustomerVo;

import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/25
 */
public interface CustomerService {
    //查询客户列表
    List<CustomerVo> getAll();
    //模糊查询客户信息（ByName）
    List<CustomerVo> getBodyByName(String name);
    //根据id查询客人信息
    SysCustomer getOneCustomer(Long id);
    //添加客人信息
    int insertCustomer(SysCustomer customer);
    //修改客人信息
    int updateCustomer(SysCustomer customer);
    //删除客人信息
    int delete(List<Object> ids);

}
