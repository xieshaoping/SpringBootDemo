package com.crhms.security.authorizationserver.config.newauth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crhms.security.authorizationserver.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author XieShaoping
 */
@Mapper
public interface UserDao extends BaseMapper<SysUser> {
}
