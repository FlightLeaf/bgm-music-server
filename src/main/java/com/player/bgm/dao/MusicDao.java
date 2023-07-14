package com.player.bgm.dao;

import com.player.bgm.entity.Music;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 音乐合集(Music)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-27 19:03:43
 */
public interface MusicDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Music queryById(Integer id);

    List<Music> queryAll(@Param("id") Integer id, @Param("name") String name, @Param("url") String url,
                         @Param("author") String author, @Param("information") String information,
                         @Param("thumbnail") String thumbnail, @Param("offset") int offset,
                         @Param("pageSize") int pageSize);


    /**
     * 查询指定行数据
     *
     * @param music    查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Music> queryAllByLimit(Music music, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param music 查询条件
     * @return 总行数
     */
    long count(Music music);

    /**
     * 新增数据
     *
     * @param music 实例对象
     * @return 影响行数
     */
    int insert(Music music);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Music> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Music> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Music> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Music> entities);

    /**
     * 修改数据
     *
     * @param music 实例对象
     * @return 影响行数
     */
    int update(Music music);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 音乐检索
     *
     * @param name 检索内容
     * @return res
     * */
    List<Music> search(@Param("name")String name);

}

