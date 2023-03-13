package person.johnson.hardware.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import person.johnson.hardware.dao.SysCustomerMapper;
import person.johnson.hardware.dao.SysUserMapper;
import person.johnson.hardware.model.SysCustomer;
import person.johnson.hardware.model.SysUser;
import person.johnson.hardware.model.vo.CustomerVo;
import person.johnson.hardware.model.vo.UserVo;
import person.johnson.hardware.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/25
 */

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired(required = false)
    private SysCustomerMapper customerMapper;
    @Autowired(required = false)
    private SysUserMapper userMapper;
    List<SysCustomer> customers = new ArrayList<SysCustomer>();
    SysCustomer customer=new SysCustomer();
    SysUser user=new SysUser();

    @Override
    public List<CustomerVo> getAll() {
        List<CustomerVo> customerVos = new ArrayList<CustomerVo>();
        customerVos = customerMapper.getAll();
        return customerVos;
    }

    @Override
    public List<CustomerVo> getBodyByName(String name) {
        List<CustomerVo> customerVos = new ArrayList<CustomerVo>();
        customerVos = customerMapper.getBodyByName(name);
        return customerVos;
    }

    @Override
    public SysCustomer getOneCustomer(Long id) {
        customer = customerMapper.selectByPrimaryKey(id);
        return customer;
    }

    @Override
    public int insertCustomer(SysCustomer customer) {
        return customerMapper.insert(customer);
    }

    @Override
    public int updateCustomer(SysCustomer customer) {
        return customerMapper.updateSomeByKey(customer);
    }

    @Override
    public int delete(List<Object> ids) {
        int sum=0;
        for(Object id:ids)
            sum = sum + customerMapper.deleteByPrimaryKey((Long) id);
        return sum;
    }


}
