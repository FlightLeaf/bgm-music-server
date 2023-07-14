package com.player.bgm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.player.bgm.dao")
public class BgmMusicServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BgmMusicServerApplication.class, args);
    }
}
