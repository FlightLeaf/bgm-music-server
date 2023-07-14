package com.player.bgm.service.impl;

import com.player.bgm.entity.Admin;
import com.player.bgm.dao.AdminDao;
import com.player.bgm.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Admin)表服务实现类
 *
 * @author makejava
 * @since 2023-07-06 09:17:58
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    /**
     * 通过ID查询单条数据
     *
     * @param email 主键
     * @return 实例对象
     */
    @Override
    public Admin queryById(String email) {
        return this.adminDao.queryById(email);
    }

    /**
     * 登录
     *
     * @param email 邮箱
     * @param password 密码
     * @return 实例对象
     */
    @Override
    public Admin login(@Param("email")String email, @Param("password")String password) {
        return this.adminDao.login(email,password);
    }

    /**
     * 分页查询
     *
     * @param admin       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Admin> queryByPage(Admin admin, PageRequest pageRequest) {
        long total = this.adminDao.count(admin);
        return new PageImpl<>(this.adminDao.queryAllByLimit(admin, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin insert(Admin admin) {
        this.adminDao.insert(admin);
        return admin;
    }

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin update(Admin admin) {
        this.adminDao.update(admin);
        return this.queryById(admin.getEmail());
    }

    /**
     * 通过主键删除数据
     *
     * @param email 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String email) {
        return this.adminDao.deleteById(email) > 0;
    }
}
