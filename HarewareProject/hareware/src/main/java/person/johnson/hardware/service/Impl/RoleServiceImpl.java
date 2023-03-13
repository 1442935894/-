package person.johnson.hardware.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import person.johnson.hardware.dao.SysRoleMapper;
import person.johnson.hardware.model.SysRole;
import person.johnson.hardware.model.vo.UserVo;
import person.johnson.hardware.service.RoleService;

import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/27
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
    @Autowired(required = false)
    private SysRoleMapper roleMapper;
    @Override
    public List<SysRole> getAll() {
        return roleMapper.selectAll();
    }
}
