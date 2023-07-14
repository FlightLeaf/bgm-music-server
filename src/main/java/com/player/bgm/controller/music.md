## 音乐合集接口

### 1. 查询所有音乐

- URL: /music
- Method: GET
- Query 参数:
    - id: 音乐ID（可选）
    - name: 音乐名称（可选）
    - url: 音乐URL（可选）
    - author: 音乐作者（可选）
    - information: 音乐信息（可选）
    - thumbnail: 缩略图URL（可选）
    - pageNum: 当前页码，默认为1（可选）
    - pageSize: 每页数量，默认为20（可选）
- 响应:
    - 成功: HTTP状态码 200
        - 返回符合条件的音乐列表
    - 失败: HTTP状态码 500
        - 查询失败或发生错误时的响应

### 2. 根据ID查询音乐

- URL: /music/{id}
- Method: GET
- 路径变量:
    - id: 音乐ID
- 响应:
    - 成功: HTTP状态码 200
        - 返回对应ID的音乐信息
    - 失败: HTTP状态码 404
        - 找不到对应ID的音乐

### 3. 新增音乐

- URL: /music
- Method: POST
- 请求体:
    - music: 音乐实体对象
- 响应:
    - 成功: HTTP状态码 200
        - 返回新增的音乐信息
    - 失败: HTTP状态码 500
        - 新增失败或发生错误时的响应

### 4. 编辑音乐

- URL: /music
- Method: PUT
- 请求体:
    - music: 音乐实体对象
- 响应:
    - 成功: HTTP状态码 200
        - 返回编辑后的音乐信息
    - 失败: HTTP状态码 500
        - 编辑失败或发生错误时的响应

### 5. 删除音乐

- URL: /music
- Method: DELETE
- Query 参数:
    - id: 音乐ID
- 响应:
    - 成功: HTTP状态码 200
        - 返回删除操作是否成功
    - 失败: HTTP状态码 500
        - 删除失败或发生错误时的响应
