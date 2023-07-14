package com.player.bgm.controller;

import com.player.bgm.entity.User;
import com.player.bgm.service.UserService;
import com.player.bgm.util.MD5;
import com.player.bgm.util.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 用户表(User)表控制层
 *
 * @author makejava
 * @since 2023-06-29 12:02:08
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<User> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.userService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param json 实体
     * @return 新增结果
     */
    @PostMapping("/register")
    public ResponseEntity<?> add(@RequestBody Map<String, Object> json) throws Exception {
        SendMailUtil sendMailUtil = new SendMailUtil();
        User user = new User();
        user.setCode((String) json.get("code"));
        user.setName((String) json.get("name"));
        user.setEmail((String) json.get("email"));
        user.setPassword((String) json.get("password"));
        user.setImg("1");
        user.setLove("[\"1\"]");
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String get_code = operations.get(user.getEmail());
        String message = SendMailUtil.CheckInformation(user.getPassword(), user.getEmail());
        if (!Objects.equals(message, "填写正确")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        } else if (get_code == null || !Objects.equals(user.getCode(), get_code)) {
            System.out.println(get_code);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("验证码不正确");
        } else if (this.userService.queryById(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("用户已存在");
        } else {
            String sure_password = MD5.md5(user.getPassword(), "player");
            user.setPassword(sure_password);
            this.userService.insert(user);
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }
    }

    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody Map<String, Object> json) {
        String message = "";
        try {
            String name = (String) json.get("name");
            String email = (String) json.get("email");
            SendMailUtil sendMailUtil = new SendMailUtil();
            //判断邮箱格式是否正确
            if (sendMailUtil.isEmail(email)) {
                StringBuilder code = sendMailUtil.CreateCode();
                ValueOperations<String, String> operations = redisTemplate.opsForValue();
                if (operations.get(email) != null) {
                    message = "验证码已发送,请三分钟后重试";
                } else {
                    operations.set(email, String.valueOf(code));
                    redisTemplate.expire(email, 3, TimeUnit.MINUTES);
                    message = sendMailUtil.sendQQEmail("718856370@qq.com", "mpezusommzzabffe", email, String.valueOf(code), name);
                }
            } else {
                message = "邮箱格式不正确";
            }
        } catch (Exception e) {
            System.out.println(e);
            message = "出现未知错误";
        }
        if (Objects.equals(message, "验证码发送成功")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
        }
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<User> edit(User user) {
        return ResponseEntity.ok(this.userService.update(user));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.userService.deleteById(id));
    }


    @PostMapping("addLove")
    public ResponseEntity<?> addLove(@RequestBody Map<String, Object> json) {
        String email = (String) json.get("email");
        String love = (String) json.get("love");
        if (this.userService.updateLove(love, email) > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("fail");
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> json) throws Exception {
        String email = json.get("email");
        String surePassword = MD5.md5(json.get("password"),"player");
        if(this.userService.login(email,surePassword) != null){
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("mistake");
        }
    }
}

