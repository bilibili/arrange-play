drop table if exists http_api_info; create table http_api_info
(
    id                bigint not null auto_increment,
    busi_id           int  default 0                   not null,
    api_name          varchar(50)     default ''       not null,
    api_domain        varchar(200)    default ''       not null,
    api_uri           varchar(200)    default ''       not null,
    request_method    varchar(5)      default ''       not null,
    request_data_type varchar(10)     default ''       not null,
    ctime             datetime,
    mtime             datetime
);

DROP TABLE IF EXISTS http_request_module; create table http_request_module
(
    id             bigint not null auto_increment comment '主键id',
    api_id         bigint          default 0                   not null comment '接口id',
    module_name    varchar(200)    default ''                  not null comment '模板名称',
    header         varchar(2048)   default ''                  not null comment '请求头',
    module_content varchar(2048)   default ''                  not null comment '模板内容',
    param_keys     varchar(100)    default ''                  not null comment '必要传参,数组类型',
    module_desc    varchar(100)    default ''                  not null comment '模板描述',
    ctime          datetime        default now()               not null comment '添加时间',
    mtime          datetime        default now()               not null comment '修改时间'
);


DROP TABLE IF EXISTS mysql_info; create table mysql_info
(
    id          bigint not null auto_increment comment '主键id' primary key,
    driver_name varchar(50)  default ''                  not null comment '驱动类名',
    url         varchar(200) default ''                  not null comment 'mysql链接串',
    username    varchar(100) default ''                  not null comment '用户名',
    password    varchar(100) default ''                  not null comment '密码',
    ctime       datetime     default now()               not null comment '添加时间',
    mtime       datetime     default now()               not null comment '修改时间'
);


DROP TABLE IF EXISTS sql_module; create table sql_module
(
    id                  bigint not null auto_increment comment '主键id' primary key,
    mysql_connection_id varchar(50)   default ''                  not null comment '数据库连接信息id',
    sql_name            varchar(200)  default ''                  not null comment 'sql名',
    sql_type            varchar(200)  default ''                  not null comment '数据库操作类型',
    sql_module_content  varchar(2048) default ''                  not null comment 'sql模板',
    param_keys          varchar(100)  default ''                  not null comment '入参',
    module_desc         varchar(100)  default ''                  not null comment '描述',
    ctime               datetime      default now()               not null comment '添加时间',
    mtime               datetime      default now()               not null comment '修改时间'
);


DROP TABLE IF EXISTS request_flow; create table request_flow
(
    id            bigint not null auto_increment comment '主键ID' primary key,
    flow_content  varchar(2048) default ''                  not null comment '流程',
    replace_value varchar(2048) default ''                  not null comment '入参',
    flow_desc     varchar(200)  default ''                  not null comment '请求方式',
    ctime         datetime      default now()               not null comment '创建时间',
    mtime         datetime      default now()               not null comment '更新时间'
);

DROP TABLE IF EXISTS sample_table; create table sample_table
(
    id            bigint not null auto_increment comment '主键ID' primary key,
    sample  varchar(2048) default ''                  not null comment '流程',
);

