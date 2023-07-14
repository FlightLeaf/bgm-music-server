## Admin Controller API

### 1. 管理员登录

- URL: /admin/login
- Method: POST
- Request Body:
  ```json
  {
    "email": "admin@example.com",
    "password": "password123"
  }
  ```
- Response:
    - Success: HTTP Status Code 200
      ```text
      "success"
      ```
    - Failure: HTTP Status Code 409
      ```text
      "mistake"
      ```

### 2. 查询管理员信息

- URL: /admin/{id}
- Method: GET
- Path Variable:
    - id: 管理员 ID
- Response:
    - Success: HTTP Status Code 200
      ```json
      {
        "id": "admin-id",
        "name": "Admin Name",
        "email": "admin@example.com"
      }
      ```

### 3. 新增管理员

- URL: /admin
- Method: POST
- Request Body:
  ```json
  {
    "name": "Admin Name",
    "email": "admin@example.com",
    "password": "password123"
  }
  ```
- Response: HTTP Status Code 200
  ```json
  {
    "id": "admin-id",
    "name": "Admin Name",
    "email": "admin@example.com"
  }
  ```

### 4. 编辑管理员信息

- URL: /admin
- Method: PUT
- Request Body:
  ```json
  {
    "id": "admin-id",
    "name": "New Admin Name",
    "email": "newadmin@example.com"
  }
  ```
- Response: HTTP Status Code 200
  ```json
  {
    "id": "admin-id",
    "name": "New Admin Name",
    "email": "newadmin@example.com"
  }
  ```

### 5. 删除管理员

- URL: /admin?id=admin-id
- Method: DELETE
- Query Parameter:
    - id: 管理员 ID
- Response: HTTP Status Code 200
  ```json
  true
  ```
