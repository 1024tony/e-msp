-- auto-generated definition
create table account
(
    id          bigint unsigned auto_increment comment '主键ID'
        primary key,
    account_no  varchar(32)  default ''                not null comment '账户编号',
    username    varchar(32)  default ''                not null comment '用户名',
    password    varchar(128) default ''                not null comment '密码',
    email       varchar(64)  default ''                not null comment '邮箱',
    phone       varchar(16)  default ''                not null comment '手机号',
    secret      varchar(64)  default ''                not null comment '盐，用于个人敏感信息处理',
    avatar      varchar(255) default ''                not null comment '头像',
    auth_level  varchar(16)  default 'DEFAULT'         not null comment '认证级别 DEFAULT:默认级别 REAL_NAME:实名认证 ENTERPRISE:企业认证',
    status      tinyint(1)   default 0                 not null comment '状态 1:激活 2:停用',
    is_deleted  tinyint(1)   default 0                 not null comment '是否删除 0:否 1:是',
    creator     varchar(64)  default ''                not null comment '创建人',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    modifier    varchar(64)  default ''                not null comment '修改人',
    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '账户表' row_format = DYNAMIC;

create index idx_account_no
    on account (account_no);

create index idx_phone
    on account (phone);

-- auto-generated definition
create table card
(
    id          bigint unsigned auto_increment comment '主键ID'
        primary key,
    card_no     varchar(32) default ''                not null comment '卡片编码',
    account_id  bigint      default 0                 not null comment '账户ID',
    account_no  varchar(32) default ''                not null comment '账户编号',
    status      tinyint(1)  default 0                 not null comment '状态 1:激活 2:停用',
    is_deleted  tinyint(1)  default 0                 not null comment '是否删除 0:否 1:是',
    creator     varchar(64) default ''                not null comment '创建人',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    modifier    varchar(64) default ''                not null comment '修改人',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '卡片表' row_format = DYNAMIC;

create index idx_account_id
    on card (account_id);

create index idx_account_no
    on card (account_no);

create index idx_card_no
    on card (card_no);

-- auto-generated definition
create table country
(
    id                       bigint unsigned auto_increment comment '主键ID'
        primary key,
    country_code             varchar(64)  default ''                not null comment '国家编码',
    en_name                  varchar(64)  default ''                not null comment '英文名',
    zh_name                  varchar(64)  default ''                not null comment '中文名',
    default_area_code        varchar(16)  default ''                not null comment '默认国际区号',
    currency_code            varchar(16)  default ''                not null comment '货币代码',
    currency_symbol          varchar(16)  default ''                not null comment '货币符号',
    default_language_md_code varchar(16)  default ''                not null comment '默认语言',
    multi_language_code      varchar(128) default ''                not null comment '多语言编码',
    icon                     varchar(255) default ''                not null comment '图标',
    currency_decimal_places  tinyint      default 0                 not null comment '货币保留小数',
    default_time_zone        varchar(64)  default ''                not null comment '默认时区zoneId',
    default_time_offset      varchar(32)  default ''                not null comment '默认时区UTC偏移量',
    id_card_regex            varchar(128) default ''                not null comment '证件号正则',
    zip_code_regex           varchar(128) default ''                not null comment '邮编正则',
    phone_number_length      varchar(128) default ''                not null comment '电话号码长度（多个英文逗号分隔）',
    is_deleted               tinyint(1)   default 0                 not null comment '是否删除 0:否 1:是',
    creator                  varchar(64)  default ''                not null comment '创建人',
    create_time              datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    modifier                 varchar(64)  default ''                not null comment '修改人',
    update_time              datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '国家表' row_format = DYNAMIC;
