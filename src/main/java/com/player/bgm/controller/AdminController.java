package com.player.bgm.controller;

import com.player.bgm.entity.Admin;
import com.player.bgm.service.AdminService;
import com.player.bgm.util.MD5;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2023-07-06 09:17:53
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;


    /**
     *管理员登录
     *
     * @param json 邮箱+密码
     * @return 登录结果
     **/
    @PostMapping("login")
    public ResponseEntity<?> adminLogin(@RequestBody Map<String,Object> json, HttpServletResponse response) throws Exception {
        String email = (String) json.get("email");
        String surePassword = MD5.md5( (String) json.get("password"),"player");
        if(adminService.login(email,surePassword) != null){
            Cookie nameCookie = new Cookie("name", this.adminService.queryById(email).getName());
            Cookie stateCookie = new Cookie("state", "success");
            response.addCookie(nameCookie);
            response.addCookie(stateCookie);
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("mistake");
        }
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Admin> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.adminService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param admin 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Admin> add(@RequestBody Admin admin) throws Exception {
        Admin newAdmin = new Admin();
        newAdmin.setName(admin.getName());
        newAdmin.setEmail(admin.getEmail());
        newAdmin.setPassword(MD5.md5(admin.getPassword(),"player"));
        return ResponseEntity.ok(this.adminService.insert(newAdmin));
    }

    /**
     * 编辑数据
     *
     * @param admin 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Admin> edit(Admin admin) {
        return ResponseEntity.ok(this.adminService.update(admin));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.adminService.deleteById(id));
    }

}

