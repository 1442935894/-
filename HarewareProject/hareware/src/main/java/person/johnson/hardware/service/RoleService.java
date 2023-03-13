package person.johnson.hardware.service;

import person.johnson.hardware.model.SysRole;
import person.johnson.hardware.model.vo.UserVo;

import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/27
 */
public interface RoleService {
    List<SysRole> getAll();
}
