INSERT INTO http_api_info (id, busi_id, api_name, api_domain, api_uri, request_method, request_data_type, ctime, mtime)
VALUES (1, 1, 'getSample', 'http://localhost:8081', '/sample/getSample', 'get', 'Uri', '2022-11-28 16:20:32', '2022-11-28 16:20:32');
INSERT INTO http_api_info (id, busi_id, api_name, api_domain, api_uri, request_method, request_data_type, ctime, mtime)
VALUES (2, 1, 'getSample', 'http://localhost:8081', '/sample/postSample', 'post', 'Json', '2022-11-28 16:20:32', '2022-11-28 16:20:32');

INSERT INTO http_request_module (id, api_id, module_name, header, module_content, param_keys, module_desc, ctime, mtime)
VALUES (1, 1, 'sampleGet', '{}', '{"name":"${name}","value":"${value}"}', 'name,value', 'get请求sample', '2022-11-28 16:20:32', '2022-11-28 16:20:32');
INSERT INTO http_request_module (id, api_id, module_name, header, module_content, param_keys, module_desc, ctime, mtime)
VALUES (2, 2, 'samplePost', '{}', '{"name":"${namePost}","value":"${valuePost}"}', 'namePost,valuePost', 'post请求sample', '2022-11-28 16:20:32', '2022-11-28 16:20:32');

INSERT INTO mysql_info (id, driver_name, url, username, password, ctime, mtime)
VALUES (1, 'org.h2.Driver', 'jdbc:h2:mem:test', 'root', 'test', '2022-11-28 16:20:32', '2022-11-28 16:20:32');

INSERT INTO sql_module (id, mysql_connection_id, sql_name, sql_type, sql_module_content, param_keys, module_desc, ctime, mtime)
VALUES (1, '1', '查询httpApiInfo', 'select', 'select * from http_api_info where id = ${id}', 'id', '查询指定id的httpApiInfo', '2022-11-28 16:20:32', '2022-11-28 16:20:32');

INSERT INTO request_flow (id, flow_content, replace_value, flow_desc, ctime, mtime)
VALUES (1,
        '[{"apiId":1,"requestModuleId":1,"stepName":"查询GetSample","stepType":"Http"},{"apiId":2,"requestModuleId":2,"stepName":"查询PostSample","stepType":"Http","waitTime":1000},{"apiId":1,"requestModuleId":1,"stepName":"查询接口数据","stepType":"Mysql"}]',
        '{"name":{"aim":"param","from":"local"},"value":{"aim":"param","from":"local"},"namePost":{"aim":"param","from":"step","value":"查询GetSample=$.resName"},"valuePost":{"aim":"param","from":"step","value":"查询GetSample=$.resContent"},"id":{"aim":"param","from":"local"}}',
        'sample流程', '2022-11-28 16:20:32', '2022-11-28 16:20:32');
