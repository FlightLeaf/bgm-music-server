## 用户表接口

### 1. 查询用户

- URL: /user/{id}
- Method: GET
- 路径变量:
    - id: 用户ID
- 响应:
    - 成功: HTTP状态码 200
        - 返回对应ID的用户信息
          ```json
          {
            "id": 1,
            "name": "John Doe",
            "email": "john@example.com"
          }
          ```
    - 失败: HTTP状态码 404
        - 找不到对应ID的用户

### 2. 注册用户

- URL: /user/register
- Method: POST
- 请求体:
    - user: 用户实体对象
        - name: 用户名
        - email: 用户电子邮箱
        - password: 用户密码
- 响应:
    - 成功: HTTP状态码 200
        - 返回注册成功信息
          ```text
          "注册成功"
          ```
    - 失败: HTTP状态码 409
        - 返回具体的错误信息
          ```json
          {
            "error": "用户名已存在"
          }
          ```

### 3. 修改用户信息

- URL: /user/{id}
- Method: PUT
- 路径变量:
    - id: 用户ID
- 请求体:
    - user: 用户实体对象
        - name: 用户名（可选）
        - email: 用户电子邮箱（可选）
        - password: 用户密码（可选）
- 响应:
    - 成功: HTTP状态码 200
        - 返回修改后的用户信息
          ```json
          {
            "id": 1,
            "name": "John Doe",
            "email": "john@example.com"
          }
          ```
    - 失败: HTTP状态码 404
        - 找不到对应ID的用户

### 4. 删除用户

- URL: /user/{id}
- Method: DELETE
- 路径变量:
    - id: 用户ID
- 响应:
    - 成功: HTTP状态码 200
        - 返回删除操作是否成功
          ```text
          "删除成功"
          ```
    - 失败: HTTP状态码 404
        - 找不到对应ID的用户
