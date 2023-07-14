package com.player.bgm.service;

import com.player.bgm.entity.Music;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 音乐合集(Music)表服务接口
 *
 * @author makejava
 * @since 2023-06-27 19:03:44
 */
public interface MusicService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Music queryById(Integer id);

    public List<Music> queryAllWithPage(Integer id, String name, String url, String author, String information,
                                        String thumbnail, int pageNum, int pageSize);

    /**
     * 分页查询
     *
     * @param music       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Music> queryByPage(Music music, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param music 实例对象
     * @return 实例对象
     */
    Music insert(Music music);

    /**
     * 修改数据
     *
     * @param music 实例对象
     * @return 实例对象
     */
    Music update(Music music);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 音乐检索
     *
     * @param name 检索内容
     * @return res
     * */
    List<Music> search(@Param("name")String name);
}
