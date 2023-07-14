package com.player.bgm.util;


import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.mysql.cj.util.StringUtils.getBytes;

public class MD5 {
    /**
     * @param password 明文
     * @param key      密钥
     * @return 密文
     * 对数据库内的密码存储进行md5的加密
     */
    public static String md5(String password, String key) throws Exception {
        String base64encodedString =
                Base64.getEncoder().encodeToString((password + key).getBytes(StandardCharsets.UTF_8));
        // 加密后的字符串
        return DigestUtils.md5DigestAsHex(getBytes(base64encodedString));
    }
}

