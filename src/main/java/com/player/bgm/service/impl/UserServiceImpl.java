package com.player.bgm.service.impl;

import com.player.bgm.entity.User;
import com.player.bgm.dao.UserDao;
import com.player.bgm.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-06-29 12:02:09
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param email 主键
     * @return 实例对象
     */
    @Override
    public User queryById(String email) {
        return this.userDao.queryById(email);
    }

    /**
     * 分页查询
     *
     * @param user        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getEmail());
    }

    /**
     * 通过主键删除数据
     *
     * @param email 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String email) {
        return this.userDao.deleteById(email) > 0;
    }

    /**
     * 登录操作
     *
     * @param email    邮箱
     * @param password 密码
     * @return 成功能登录的实体
     */
    @Override
    public User login(@Param("email")String email, @Param("password")String password) {
        return this.userDao.login(email, password);
    }

    /**
     * 添加我喜欢
     *
     * @我喜欢
     * */
    @Override
    public int updateLove(@Param("love") String love,@Param("email") String email) {
        return this.userDao.updateLove(love,email);
    }

}
