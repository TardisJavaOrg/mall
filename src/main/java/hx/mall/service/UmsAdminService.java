package hx.mall.service;

import hx.mall.mbg.model.UmsAdmin;
import hx.mall.mbg.model.UmsPermission;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台管理员 Service
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 获取用户所有权限（包括角色权限和 +- 权限）
     */
    List<UmsPermission> getPermissionList(Long adminId);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登录功能
     */
    String login(String username, String password);
}
