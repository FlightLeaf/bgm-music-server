package com.player.bgm.controller;

import com.player.bgm.entity.Music;
import com.player.bgm.service.MusicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 音乐合集(Music)表控制层
 *
 * @author makejava
 * @since 2023-06-27 19:03:42
 */
@RestController
@RequestMapping("music")
public class MusicController {
    /**
     * 服务对象
     */
    @Resource
    private MusicService musicService;

    @GetMapping
    public List<Music> queryAllMusic(@RequestParam(name = "id", required = false) Integer id,
                                     @RequestParam(name = "name", required = false) String name,
                                     @RequestParam(name = "url", required = false) String url,
                                     @RequestParam(name = "author", required = false) String author,
                                     @RequestParam(name = "information", required = false) String information,
                                     @RequestParam(name = "thumbnail", required = false) String thumbnail,
                                     @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(name = "pageSize", defaultValue = "20") int pageSize) {
        return musicService.queryAllWithPage(id, name, url, author, information, thumbnail, pageNum, pageSize);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Music> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.musicService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param music 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Music> add(@RequestBody Music music) {
        return ResponseEntity.ok(this.musicService.insert(music));
    }

    /**
     * 编辑数据
     *
     * @param music 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Music> edit(Music music) {
        return ResponseEntity.ok(this.musicService.update(music));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.musicService.deleteById(id));
    }

    @PostMapping("search")
    public ResponseEntity<List<Music>> search(@RequestParam(name = "name") String name){
        return ResponseEntity.ok(this.musicService.search(name));
    }

}

