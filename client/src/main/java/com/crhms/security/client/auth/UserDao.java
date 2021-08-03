package com.crhms.security.client.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author XieShaoping
 */
@Mapper
public interface UserDao extends BaseMapper<SysUser> {
}
