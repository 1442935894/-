package person.johnson.hardware.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import person.johnson.hardware.dao.SysUserMapper;
import person.johnson.hardware.model.SysUser;

//@Mapper
public interface LoginUserMapper extends SysUserMapper {

}

