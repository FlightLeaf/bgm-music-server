## Download Controller API

### 1. 文件下载

- URL: /download/{target}
- Method: GET
- Path Variable:
    - target: 文件名或路径
- Response:
    - Success: HTTP Status Code 200
        - 文件存在时，将会返回对应的文件进行下载。Response Headers 中包含 Content-Disposition 属性，指定文件名。
    - Failure: HTTP Status Code 404
        - 文件不存在时，返回空响应体。
