package com.player.bgm.service;

import com.player.bgm.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Admin)表服务接口
 *
 * @author makejava
 * @since 2023-07-06 09:17:57
 */
public interface AdminService {

    /**
     * 通过ID查询单条数据
     *
     * @param email 主键
     * @return 实例对象
     */
    Admin queryById(String email);

    /**
     * 登录
     *
     * @param email 邮箱
     * @param password 密码
     * @return 实例对象
     */
    Admin login(@Param("email")String email, @Param("password")String password);

    /**
     * 分页查询
     *
     * @param admin       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Admin> queryByPage(Admin admin, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin insert(Admin admin);

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin update(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param email 主键
     * @return 是否成功
     */
    boolean deleteById(String email);

}
