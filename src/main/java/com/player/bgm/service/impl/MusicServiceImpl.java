package com.player.bgm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.player.bgm.entity.Music;
import com.player.bgm.dao.MusicDao;
import com.player.bgm.service.MusicService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 音乐合集(Music)表服务实现类
 *
 * @author makejava
 * @since 2023-06-27 19:03:45
 */
@Service("musicService")
public class MusicServiceImpl implements MusicService {
    @Resource
    private MusicDao musicDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Music queryById(Integer id) {
        return this.musicDao.queryById(id);
    }

    /**
     * 音乐信息查询
     *
     * @param id,name,url,author,information,thumbnail 音乐信息
     * @param pageNum, pageSize 页面信息
     */
    @Override
    public List<Music> queryAllWithPage(Integer id, String name, String url, String author, String information,
                                        String thumbnail, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Music> musicList = musicDao.queryAll(id, name, url, author, information, thumbnail, 0, 0);
        PageInfo<Music> pageInfo = new PageInfo<>(musicList);
        return pageInfo.getList();
    }

    /**
     * 分页查询
     *
     * @param music       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Music> queryByPage(Music music, PageRequest pageRequest) {
        long total = this.musicDao.count(music);
        return new PageImpl<>(this.musicDao.queryAllByLimit(music, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param music 实例对象
     * @return 实例对象
     */
    @Override
    public Music insert(Music music) {
        this.musicDao.insert(music);
        return music;
    }

    /**
     * 修改数据
     *
     * @param music 实例对象
     * @return 实例对象
     */
    @Override
    public Music update(Music music) {
        this.musicDao.update(music);
        return this.queryById(music.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.musicDao.deleteById(id) > 0;
    }

    /**
     * 音乐检索
     *
     * @param name 检索内容
     * @return res
     * */
    @Override
    public List<Music> search(String name){ return this.musicDao.search(name);}
}
