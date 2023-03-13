package person.johnson.hardware.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.johnson.hardware.dao.SysSupplierMapper;
import person.johnson.hardware.model.SysSupplier;
import person.johnson.hardware.service.SupplierService;

import java.util.ArrayList;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/25
 */
@Slf4j
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired(required = false)
    private SysSupplierMapper supplierMapper;
    List<SysSupplier> suppliers=new ArrayList<SysSupplier>();
    SysSupplier supplier=new SysSupplier();

    @Override
    public List<SysSupplier> getAll() {
        suppliers = supplierMapper.selectAll();
        return suppliers;
    }

    @Override
    public SysSupplier getOneSupplier(Long id) {
        supplier = supplierMapper.selectByPrimaryKey(id);
        return supplier;
    }

    @Override
    public int insertSupplier(SysSupplier sysSupplier) throws Exception {
        List<SysSupplier> sysSuppliers = supplierMapper.selectByName(sysSupplier.getName());
        if(sysSuppliers.size() > 0){
            throw new Exception("已存在该供应商名");
        }
        return supplierMapper.insert(sysSupplier);
    }

    @Override
    public int updateSupplier(SysSupplier sysSupplier) {
        supplier = sysSupplier;
        return supplierMapper.updateSomeByKey(sysSupplier);
    }

    @Override
    public int delete(List<Object> ids) {
        int sum=0;
        for(Object id:ids)
            sum = sum + supplierMapper.deleteByPrimaryKey((Long) id);
        return sum;
    }

    @Override
    public List<SysSupplier> getBodyByName(String name) {
        return supplierMapper.getBodyByName(name);
    }
}
