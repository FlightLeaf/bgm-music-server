package com.player.bgm.service;

import com.player.bgm.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-06-29 12:02:09
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param email 主键
     * @return 实例对象
     */
    User queryById(String email);

    /**
     * 分页查询
     *
     * @param user        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<User> queryByPage(User user, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param email 主键
     * @return 是否成功
     */
    boolean deleteById(String email);

    User login(@Param("email")String email, @Param("password")String password);


    int updateLove(@Param("love") String love,@Param("email") String email);
}
