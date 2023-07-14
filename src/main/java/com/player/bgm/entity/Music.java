package com.player.bgm.entity;

import java.io.Serializable;

/**
 * 音乐合集(Music)实体类
 *
 * @author makejava
 * @since 2023-06-27 19:03:43
 */
public class Music implements Serializable {
    private static final long serialVersionUID = -48269279307766583L;
    /**
     * 音乐id
     */
    private Integer id;
    /**
     * 音乐名称
     */
    private String name;
    /**
     * 音乐地址
     */
    private String url;
    /**
     * 作者
     */
    private String author;
    /**
     * 详细信息json
     */
    private String information;
    /**
     * 图
     */
    private String thumbnail;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}

