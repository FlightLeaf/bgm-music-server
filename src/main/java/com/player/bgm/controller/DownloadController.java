package com.player.bgm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Objects;

/**
 * 下载控制层
 *
 * @author makejava
 * @since 2023-06-29 12:02:08
 */
@RestController
@RequestMapping("download")
public class DownloadController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("{target}")
    public ResponseEntity<Resource> download(@PathVariable("target") String target) {
        String filePath = uploadDir + File.separator + target;

        System.out.println(filePath);

        File file = new File(filePath);
        Resource resource = new FileSystemResource(file);

        if (file.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + target);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
