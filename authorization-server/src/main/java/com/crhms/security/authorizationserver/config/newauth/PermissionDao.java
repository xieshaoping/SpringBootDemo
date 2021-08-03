package com.crhms.security.authorizationserver.config.newauth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author XieShaoping
 */
@Mapper
public interface PermissionDao extends BaseMapper<Permission> {

    List<Permission> findAll();

    List<Permission> findByAdminUserId(int userId);

}
