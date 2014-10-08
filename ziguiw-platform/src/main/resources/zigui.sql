--新闻表
create table news(
id number(19), --主键，由s_news_id序列生成
title varchar(120), --新闻标题
subtitle varchar(160), --新闻副标题
keywords varchar(120), --关键字
description varchar(400), --描述
auto_description varchar(400), --系统自动生成的描述，一般是内容的前200字
editors varchar(32), --责任编辑
click_count number(9), --点击次数
url varchar(128), --新闻url，自动生成，管理后台不能修改
first_image varchar(128), --新闻图片，一篇新闻最多一般一个图片
province varchar(10), --省ID
city varchar(12), --城市ID
content clob,
source_type number(3), --消息来源，原创、转载等
channle_id number(9), --频道ID
recommend_region_id number(19), --推荐位置ID
state number(3), --新闻状态，标识是否被删除
creator_id number(19), --新闻创建者ID
creator_nick varchar(32), --新闻创建者昵称
create_time date, --新闻创建时间
last_modify_id number(19), --新闻最后修改者ID
last_modify_nick varchar(32), --新闻最后修改者昵称
last_modify_time date --新闻最后修改时间
);
create sequence news_seq increment by 1;
create index new_id_index ON news(id);
create index new_recommend_region_id_index ON news(recommend_region_id);
create index new_channle_id_index ON news(channle_id);

create table news_channle(
id number(19), --
name varchar(120), --频道
state number(3), --状态，标识是否被删除
creator_id number(19), 
creator_nick varchar(32),
create_time date,
last_modify_id number(19),
last_modify_nick varchar(32),
last_modify_time date 
);

insert into news_channle(id, name, state, creator_id, creator_nick, last_modify_id) values(1, '焦点话题', 1, 1, 'admin', 0);
insert into news_channle(id, name, state, creator_id, creator_nick, last_modify_id) values(2, '校园新闻', 1, 1, 'admin', 0);
insert into news_channle(id, name, state, creator_id, creator_nick, last_modify_id) values(3, '升学考试', 1, 1, 'admin', 0);

create sequence news_channle_seq increment by 1;
create index new_channle_name_index ON news_channle(name);

--新闻推荐表
create table news_recommend_region(
id number(19),
name varchar(120), --推荐区标题
state number(3), 
creator_id number(19), 
creator_nick varchar(32),
create_time date, 
last_modify_id number(19), 
last_modify_nick varchar(32),
last_modify_time date
);
create index news_region_name_index ON news_recommend_region(name);
create index news_region_id_index ON news_recommend_region(id);

insert into news_recommend_region(id, name, state, creator_id, creator_nick, last_modify_id) values(1, '新闻首页幻灯片新闻', 1, 1, 'admin', 0);
insert into news_recommend_region(id, name, state, creator_id, creator_nick, last_modify_id) values(2, '新闻首页深度阅读', 1, 1, 'admin', 0);
insert into news_recommend_region(id, name, state, creator_id, creator_nick, last_modify_id) values(3, '新闻页小编推荐', 1, 1, 'admin', 0);
insert into news_recommend_region(id, name, state, creator_id, creator_nick, last_modify_id) values(4, '新闻页热门点击', 1, 1, 'admin', 0);
insert into news_recommend_region(id, name, state, creator_id, creator_nick, last_modify_id) values(5, '新闻首页焦点话题频道', 1, 1, 'admin', 0);
insert into news_recommend_region(id, name, state, creator_id, creator_nick, last_modify_id) values(6, '新闻首页校园新闻频道', 1, 1, 'admin', 0);
insert into news_recommend_region(id, name, state, creator_id, creator_nick, last_modify_id) values(7, '新闻首页升学考试频道', 1, 1, 'admin', 0);
insert into news_recommend_region(id, name, state, creator_id, creator_nick, last_modify_id) values(8, '新闻热门推荐', 1, 1, 'admin', 0);
insert into news_recommend_region(id, name, state, creator_id, creator_nick, last_modify_id) values(9, '新闻人气排行', 1, 1, 'admin', 0);
insert into news_recommend_region(id, name, state, creator_id, creator_nick, last_modify_id) values(10, '新闻地方资讯', 1, 1, 'admin', 0);

insert into news_recommend_region(id, name, state, creator_id, creator_nick, last_modify_id) values(11, '新闻地方资讯', 1, 1, 'admin', 0);
insert into news_recommend_region(id, name, state, creator_id, creator_nick, last_modify_id) values(12, '新闻地方资讯', 1, 1, 'admin', 0);
insert into news_recommend_region(id, name, state, creator_id, creator_nick, last_modify_id) values(13, '新闻地方资讯', 1, 1, 'admin', 0);
insert into news_recommend_region(id, name, state, creator_id, creator_nick, last_modify_id) values(14, '新闻地方资讯', 1, 1, 'admin', 0);
insert into news_recommend_region(id, name, state, creator_id, creator_nick, last_modify_id) values(15, '新闻地方资讯', 1, 1, 'admin', 0);

create sequence news_recommend_region_seq increment by 1;

--新闻来源表，一般是作为常量表，不需要改动
create table news_source(
id number(19), --主键
name varchar(120),
state number(3),
creator_id number(19), 
creator_nick varchar(32),
create_time date,
last_modify_id number(19),
last_modify_nick varchar(32), 
last_modify_time date 
);
create sequence news_source_seq increment by 1;

--模版表
create table template(
id number(19), --主键
name varchar(120), --模版名称
description varchar(400), --模版描述
state number(3), --状态，标识是否被删除
content clob, --模版内容
creator_id number(19), 
creator_nick varchar(32), 
create_time date,
last_modify_id number(19),
last_modify_nick varchar(32), 
last_modify_time date
);
create sequence template_seq increment by 1;

--模版函数
create table data_function(
id number(19),
name varchar(120),
description varchar(400),
parameter varchar(200),
content varchar(1024),
creator_id number(19), 
creator_nick varchar(32),
create_time date, 
last_modify_id number(19), 
last_modify_nick varchar(32),
last_modify_time date
);

create sequence knowledge_channle_seq start with 10000 increment by 1;

--用户表
create table user_base(
id number(19),
nick_name varchar(32), --昵称
email varchar(64), --邮箱
passwd varchar(62), --两次md5
birthday varchar(32), --生日
province varchar(32),
city varchar(32),
points number(9),  --积分
location varchar(256), --家庭住址
sex number(1), --性别
state number(3), --状态，标识是否被删除
create_time date, --注册时间
last_modify_time date, --资料最后修改时间
last_login_time date, --最后登录时间
create_ip varchar(50),
last_login_ip varchar(50),
role_id number(19),
portrait varchar(256),
space_pv number(9),
signature varchar(1024),
recommend_region_id number(19)
);
create sequence user_base_seq increment by 1;
alter table user_base add constraint pk_user_base primary key(id);
create unique index idx_user_nick on user_base(nick_name);
create unique index idx_user_email on user_base(email);

alter table user_base add type number(3);
alter table user_base add foreign_key number(19);
alter table user_base add display_name varchar(32);
alter table user_base add real_name varchar(32);
alter table user_base add region_time date;

--好友表，好友是单向关系
create table user_friend(
id number(19),
user_id number(19), --用户ID
friend_user_id number(19), --好友用户ID
create_time date
);
create sequence user_friend_seq increment by 1;


create sequence user_seq increment by 1;

--积分历史
create table points_history(
id number(19),
user_id number(19),  --用户id
flag number(3), --增加或者减少
points_change number(9), --增加或者减少积分的数量
change_type number(3), --改变积分的动作
change_time date, --积分改变发生时间
state number(3),
audit_opinion varchar(256),
audit_user_id number(19),
audit_time date
);
create sequence points_history_seq increment by 1;
alter table points_history add constraint pk_points_history primary key(id);

--角色表
create table role(
id number(19),
name varchar(32),
description varchar(128),
state number(3), --角色状态，标识是否被删除
creator_id number(19), --角色创建者ID
creator_nick varchar(32), --角色创建者昵称
create_time date, --角色创建时间
last_modify_id number(19), --角色最后修改者ID
last_modify_nick varchar(32), --角色最后修改者昵称
last_modify_time date --角色最后修改时间
);

insert into administrator(id, nick_name, password, creator_id, last_modify_id) values(1000000000, 'admin', '123456', 0, 0);

create sequence role_seq increment by 1;

--授权表
create table authority(
id number(19),
code varchar(12),
name varchar(64),
description varchar(128),
state number(3), --授权状态，标识是否被删除
url varchar(256),
creator_id number(19), --授权创建者ID
creator_nick varchar(32), --授权创建者昵称
create_time date, --授权创建时间
last_modify_id number(19), --授权最后修改者ID
last_modify_nick varchar(32), --授权最后修改者昵称
last_modify_time date --授权最后修改时间
);

insert into authority(id, name, code, url) values(1, '查询频道', 00010001, '/admin/news/newsChannle_getAll');
insert into authority(id, name, code, url) values(2, '增加频道', 00010002, '/admin/news/newsChannle_saveOrUpdate');
insert into authority(id, name, code, url) values(3, '频道迁移', 00010003, '/admin/news/newsChannleManager_move');
insert into authority(id, name, code, url) values(4, '查询频道位', 00010004, '/admin/news/newsRecommendRegion_getAll');
insert into authority(id, name, code, url) values(5, '推荐推荐位', 00010005, '/admin/news/newsRecommendRegion_saveOrUpdate');
insert into authority(id, name, code, url) values(6, '查询新闻', 00010006, '/news/news_listNewsByCondition.jsp');
insert into authority(id, name, code, url) values(7, '审批新闻', 00010007, '/admin/news/news_approve');
insert into authority(id, name, code, url) values(8, '恢复新闻', 00010008, '/admin/news/news_restore');
insert into authority(id, name, code, url) values(9, '增加新闻', 00010009, '/admin/news/news_saveOrUpdate');
insert into authority(id, name, code, url) values(10, '推荐新闻', 00010010, '/admin/news/news_recommend');

insert into authority(id, name, code, url) values(11, '查询知识频道', 00020001, '/admin/knowledge/knowledgeChannle_getAll');
insert into authority(id, name, code, url) values(12, '保存知识频道', 00020002, '/admin/knowledge/knowledgeChannle_saveOrUpdate');
insert into authority(id, name, code, url) values(13, '知识频道管理', 00020003, '/admin/knowledge/knowledgeChannleManager_move');
insert into authority(id, name, code, url) values(14, '查询知识推荐区', 00020004, '/admin/knowledge/knowledgeRecommendRegion_getAll');
insert into authority(id, name, code, url) values(15, '保存知识推荐区', 00020005, '/admin/knowledge/knowledgeRecommendRegion_saveOrUpdate');
insert into authority(id, name, code, url) values(16, '查询知识', 00020006, '/admin/knowledge/knowledge_listKnowledgesByCondition');
insert into authority(id, name, code, url) values(17, '问题审批', 00020007, '/admin/knowledge/question_approve');
insert into authority(id, name, code, url) values(18, '辩论保存', 00020008, '/admin/knowledge/debate_saveOrUpdate');
insert into authority(id, name, code, url) values(19, '辩论查询', 00020009, '/admin/knowledge/debate_listByCondition');


update authority set state = 0, creator_id = 0, last_modify_id = 0;

create sequence authority_seq increment by 1;

--角色授权映射表
create table role_authority(
role_id number(19),
authority_id number(19),
state number(3)
);

--管理员表
create table administrator(
id number(19),
nick_name varchar(64),
real_name varchar(64),
password varchar(64),
role_id number(19),
creator_id number(19), --新闻创建者ID
creator_nick varchar(32), --新闻创建者昵称
create_time date, --新闻创建时间
last_modify_id number(19), --新闻最后修改者ID
last_modify_nick varchar(32), --新闻最后修改者昵称
last_modify_time date --新闻最后修改时间
);

create sequence administrator_seq increment by 1;


--问题表
create table question(
id number(19),
title varchar(120), --问题标题
keywords varchar(120), --关键字
click_count number(9), --点击次数
answer_count number(9), --答案个数
pending_time number(3), --等待解决时间
point number(9), --悬赏积分
status number(3), --问题类型，已经解决，未解决，过期等
best_answer_id number(19), --最佳答案
best_answer_author number(19), --最佳答案作者
state number(3), --问题状态，标识是否被删除
creator_id number(19), --问题创建者ID
creator_nick varchar(32), --问题创建者昵称
create_time date, --问题创建时间
last_modify_time date, --问题最后修改时间
last_answer_time date, --问题最后修改时间
content clob
);

create sequence question_seq increment by 1;

--问题答案表
create table answer(
id number(19),
question_id number(19),
good_comment_count number(9), --好评次数
state number(3), --答案状态，标识是否被删除
creator_id number(19), --答案创建者ID
creator_nick varchar(32), --答案创建者昵称
create_time date, --答案创建时间
content clob
);

create sequence answer_seq increment by 1;

--辩论
create table debate(
id number(19),
title varchar(256),
positive_topic varchar(2048), --正方观点
negative_topic varchar(2048), --反方观点
positive_support_count number(9), --正方支持人数
negative_support_count number(9), --反方支持人数
click_count number(9), --点击次数
description varchar(400), --辩论的描述
open_days number(9),
state number(3), --辩论状态，标识是否被删除
creator_id number(19), --辩论创建者ID
creator_nick varchar(32), --辩论创建者昵称
create_time date, --辩论创建时间
last_modify_id number(19), --辩论最后修改者ID
last_modify_nick varchar(32), --辩论最后修改者昵称
last_modify_time date --辩论最后修改时间
);

create sequence debate_seq increment by 1;



create table debate_opinion(
id number(19),
topic_id number(19),--debateid
status number(3), --支持正方or方法
opinion varchar(512), --具体意见
state number(3), --辩论状态，标识是否被删除
creator_id number(19), --辩论创建者ID
creator_nick varchar(32), --辩论创建者昵称
create_time date --辩论创建时间
);
create sequence debate_opinion_seq increment by 1;

--知识表
create table knowledge(
id number(19), --主键，由s_news_id序列生成
title varchar(120), --知识标题
subtitle varchar(160), --知识副标题
keywords varchar(120), --关键字
description varchar(400), --描述
auto_description varchar(400), --系统自动生成的描述，一般是内容的前200字
editors varchar(32), --责任编辑
click_count number(9), --点击次数
url varchar(128), --知识url，自动生成，管理后台不能修改
first_image varchar(128), --知识图片，一篇知识最多一般一个图片
province varchar(10), --省ID
city varchar(12), --城市ID
content clob,
source_type number(3), --消息来源，原创、转载等
channle_id number(9), --频道ID
recommend_region_id number(19), --推荐位置ID
state number(3), --知识状态，标识是否被删除
creator_id number(19), --知识创建者ID
creator_nick varchar(32), --知识创建者昵称
create_time date, --知识创建时间
last_modify_id number(19), --知识最后修改者ID
last_modify_nick varchar(32), --知识最后修改者昵称
last_modify_time date --知识最后修改时间
);
create sequence knowledge_seq increment by 1;

--知识频道
create table knowledge_channle(
id number(19), --主键，由s_news_id序列生成
name varchar(120), --知识标题
state number(3), --知识状态，标识是否被删除
creator_id number(19), --知识创建者ID
creator_nick varchar(32), --知识创建者昵称
create_time date, --知识创建时间
last_modify_id number(19), --知识最后修改者ID
last_modify_nick varchar(32), --知识最后修改者昵称
last_modify_time date --知识最后修改时间
);
create sequence knowledge_channle_seq start with 10000 increment by 1;
create index knowledge_channle_name_index ON knowledge_channle(name);

--知识推荐表
create table knowledge_recommend_region(
id number(19), --主键，由s_news_id序列生成
name varchar(120), --新闻标题
state number(3), --新闻状态，标识是否被删除
creator_id number(19), --新闻创建者ID
creator_nick varchar(32), --新闻创建者昵称
create_time date, --新闻创建时间
last_modify_id number(19), --新闻最后修改者ID
last_modify_nick varchar(32), --新闻最后修改者昵称
last_modify_time date, --新闻最后修改时间
type             number(3) default 1 --1知识，2论坛
);
create index knowledge_region_name_index ON knowledge_recommend_region(name);

create sequence knowledge_recommend_region_seq start with 10000 increment by 1;

--community 社区
create table board
(
  id             number(19) not null,
  parentid       number(19),
  boardname      varchar2(60) default '' not null,
  explains       varchar(400),--版区说明
  bulletin       varchar(400),--版区公告
  state          number(3), --状态，标识是否被删除
  needpasswd     number(1) default 0,--是否需要密码访问
  passwd         varchar2(100) default '',--访问密码
  boardtype      number(3) default 0,--版区类型
  mainpostnum    number(11) default 0,
  creator_id     number(19), --创建者id
  creator_nick   varchar(32), --创建者昵称  
  create_time    date, --开版时间
  postnum        number(11) default 0,
  primary key  (id)
);
create sequence board_seq  start with 10000 increment by 1;

--讨论
create table forum
(
  id                             number(19), --主键，由s_news_id序列生成
  parentid                       number(19),
  boardid                        number(19),
  renum                          number(11) default 0,--回复数量
  creator_id                     number(19), --创建者id
  creator_nick                   varchar(32), --创建者昵称                            
  title                          varchar2(150),
  content                        clob,--帖子内容
  click_count                    number(11) default 0,--点击数
  create_time                    date, --帖子创建时间
  last_post_id                   number(19), --创建者id
  last_post_nick                 varchar(32), --创建者昵称 
  last_post_time                 date, --最后回复时间
  ipaddress                      varchar2(20),
  isnew                          number(1) default 0,--是否主贴
  elite                          number(1) default 0,--是否精华
  isTop                          number(1) default 0,--是否精华
  state                          number(3), --状态，标识是否被删除
  tag                            varchar(255),
  recommend_region_id            number(19), --推荐位置ID
  first_image                    varchar(255), --首图
  auto_description               varchar(400), --系统自动生成的描述，一般是内容的前200字      
  primary key  (id)
);
create index forum_boardid_index on forum (boardid);
create index forum_lastposttime_index on forum (last_post_time);
create index forum_create_time_index on forum (create_time);
create index forum_creator_nick_index on forum (creator_nick);

create sequence forum_seq increment by 1;


--爱心榜表
create table love(
id number(19), --主键
donate_user_id number(19),--捐赠人id
to_nick varchar(32),--受益人昵称
info varchar(400),--捐赠信息
description varchar(400), --描述
reserve1 varchar(400), 
reserve2 varchar(400),
state number(3), --状态，标识是否被删除
creator_id number(19), --创建者ID
creator_nick varchar(32), --创建者昵称
create_time date, --创建时间
last_modify_id number(19), --最后修改者ID
last_modify_nick varchar(32), --最后修改者昵称
last_modify_time date --最后修改时间
);
create sequence love_seq increment by 1;


--广告表
create table ad(
id number(19), --主键
name varchar(64),--广告区名称
width number(9),--宽
height number(9),--高
description varchar(400), --描述
image_url varchar(400),  --url
link varchar(400), --广告链接
state number(3), --状态，标识是否被删除
creator_id number(19), --创建者ID
creator_nick varchar(32), --创建者昵称
create_time date, --创建时间
last_modify_id number(19), --最后修改者ID
last_modify_nick varchar(32), --最后修改者昵称
last_modify_time date --最后修改时间
);
create sequence ad_seq increment by 1;

--广告执行计划
create table ad_plan(
id number(19),
ad_id number(19),
image_url varchar(400), 
link varchar(400),
start_time date, --开始时间
end_time date, --结束时间
state number(3),
creator_id number(19),
creator_nick varchar(32),
create_time date,
last_modify_id number(19),
last_modify_nick varchar(32),
last_modify_time date
);
create sequence ad_plan_seq increment by 1;

create table school_info(
school_id number(19),
summary varchar(2048),
contact varchar(256),
master_contact varchar(256)
);

alter table school_info add speech varchar(2048);
alter table school_info add job varchar(2048);

alter table school_info drop column summary;

alter table school_info drop column speech;

alter table school_info drop column job;

alter table school_info add summary clob;

alter table school_info add speech clob;

alter table school_info add job clob;

create sequence school_info_seq increment by 1;

create table config_center(
id number(19), --主键
content clob --配置中心内容
);

insert into config_center values(1, '');

create sequence config_center_seq increment by 1;
create sequence hibernate_sequence increment by 1;

--健康档案
create table health_archives(
id number(19), 
user_id number(19), --用户ID，必须是学生用户
blood_type varchar(32), --
drug_allergy_history varchar(128),?
vision varchar(32),
listening_comprehension varchar(32),
height varchar(32),
weight varchar(32),
local_history varchar(400),
medical_history varchar(400),
genetic_history varchar(400),
reserve1 varchar(400), 
reserve2 varchar(400),
state number(3), 
creator_id number(19), 
creator_nick varchar(32),
create_time date,
last_modify_id number(19),
last_modify_nick varchar(32),?
last_modify_time date ?
);
create sequence health_archives_seq increment by 1;

--健康信息的基本信息
create table base_health(
id number(19), --主键
user_id number(19),--
blood_type varchar(32),--血型
drug_allergy_history varchar(128),--过敏史
vision varchar(32),--视力
listening_comprehension varchar(32),--听力
height varchar(32),--身高
weight varchar(32),--体重
local_history varchar(400),--
medical_history varchar(400),--
genetic_history varchar(400),--
reserve1 varchar(400), 
reserve2 varchar(400),
state number(3), --
creator_id number(19), 
creator_nick varchar(32), 
create_time date, 
last_modify_id number(19),
last_modify_nick varchar(32),
last_modify_time date
);
create sequence base_health_seq increment by 1;


--成长档案
create table grow_archives(
id number(19), 
user_id number(19), --学生用户的主键
hobby varchar(32),--爱好
specialty varchar(32),--特长
ideal varchar(32),--理想
famous varchar(1024),--兴趣
parents_send_word varchar(1024),--家长寄语
teacher_send_word varchar(1024),--老师寄语
honor_and_works varchar(1024),
favourite_work varchar(1024),
reading_ability_teacher varchar(32),--阅读能力
reading_ability_self varchar(32
reading_ability_parents varchar(32),
expression_ability_teacher varchar(32), --表达能力，老师评价
expression_ability_self varchar(32), --自评
expression_ability_parents varchar(32), --家长评价，下同
writing_ability_teacher varchar(32), --写作能力
writing_ability_self varchar(32),
writing_ability_parents varchar(32),
cooperation_ability_teacher varchar(32),--合作能力
cooperation_ability_self varchar(32),
cooperation_ability_parents varchar(32),
art_ability_teacher varchar(32),--艺术能力
art_ability_self varchar(32),
art_ability_parents varchar(32),
sport_ability_teacher varchar(32),--运动能力
sport_ability_self varchar(32
sport_ability_parents varchar(32),
daily_ability_teacher varchar(32),
daily_ability_self varchar(32),
daily_ability_parents varchar(32),
reserve1 varchar(400), 
reserve2 varchar(400),
state number(3), 
creator_id number(19),
creator_nick varchar(32),
create_time date, 
last_modify_id number(19), 
last_modify_nick varchar(32),
last_modify_time date
);
create sequence grow_archives_seq increment by 1;
alter table grow_archives add student_id number(9);

--成长足迹
create table grow_footmark(
id number(19), 
user_id number(19),
title varchar(120), --名称
text clob, --足迹内容
description varchar(120), --描述
keyword varchar(120), --关键词
type number(3), --类型
status number(3), 
state number(3), 
creator_id number(19), 
creator_nick varchar(32),
create_time date,
last_modify_id number(19), 
last_modify_nick varchar(32), 
last_modify_time date,
view_count number(8),
recommend_region_id number(19),
first_image  varchar(255),
click_count number(19)
);
create sequence grow_footmark_seq increment by 1;

--作业
create table homework(
id number(19),
user_id number(19),
subject number(19), --科目
homework_type number(19), --
point number(3), --
content  varchar(800), --作业内容
teacher  varchar(32), --老师
reserve1 varchar(400), 
reserve2 varchar(400),
state number(3), 
creator_id number(19), 
creator_nick varchar(32),
create_time date,
last_modify_id number(19),
last_modify_nick varchar(32),
last_modify_time date
);

create sequence homework_seq increment by 1;
alter table homework add class_id varchar(20);

--普通类型
create table common_type(
id number(19), 
name varchar(32), 
info varchar(400),
type number(3),
reserve1 varchar(400), 
reserve2 varchar(400),
state number(3),
creator_id number(19), 
creator_nick varchar(32), 
create_time date, 
last_modify_id number(19), 
last_modify_nick varchar(32), 
last_modify_time date
);
create sequence common_type_seq increment by 1;


create table common_message(
id number(19), 
to_user_id number(19),
from_user_id number(19), 
title varchar(64),
text varchar(400), 
kind number(3),
type number(19),
parent_msg_id number(19), 
status number(3), 
state number(3), 
create_time date
);

create sequence common_message_seq increment by 1;
alter table common_message add constraint pk_common_message primary key(id);
alter table common_message add class_id varchar(20);



--用户日记表
create table user_diary(
id number(19), --主键
title varchar(120), --日记标题
text clob, --日记正文
description varchar(120), --日记摘要
keyword varchar(120), --日记关键字
type number(3), --日记分类
status number(3), --日记状态，标识是否被(删除，屏蔽，推荐……)
user_id number(19), --日记作者ID
create_time date, --创建时间
last_modify_id number(19), --最后修改者ID
last_modify_time date, --最后修改时间
view_count number(8),
recommend_region_id number(19),
first_image  varchar(255),--首图
click_count number(19)
);
create sequence user_diary_seq increment by 1;
alter table user_diary add constraint pk_user_diary primary key(id);
create index idx_diary_user_id on user_diary(user_id);

--用户日记评论表
create table user_diary_comment(
id number(19), --主键
diary_id number(19), --评论日记ID
text varchar(400), --评论正文
user_id number(19), --用户id
status number(3), --评论状态，标识是否被(删除，屏蔽)
create_time date --创建时间
);
create sequence user_diary_comment_seq increment by 1;
alter table user_diary add constraint pk_user_diary primary key(id);

--用户相册表
create table user_album(
id number(19), --主键
title varchar(120), --相册标题
text varchar(400), --相册说明
keyword varchar(120), --关键字
type number(3), --相册分类
status number(3), --相册状态，标识是否被(删除，屏蔽)
user_id number(19), --相册创建者ID
create_time date, --创建时间
last_modify_time date, --最后修改时间
cover_photo_id number(19)
);
create sequence user_album_seq increment by 1;
alter table user_album add constraint pk_user_album primary key(id);

--用户照片表
create table user_photo(
id number(19), --主键
url varchar(120), --照片路径
title varchar(120), --照片标题
text varchar(400), --照片说明
type number(3), --照片分类
status number(3), --照片状态，标识是否被(删除，屏蔽)
user_id number(19), --照片创建者ID
album_id number(19), --所属相册ID
create_time date, --创建时间
last_modify_time date,
recommend_region_id number(19)
);
create sequence user_photo_seq increment by 1;
alter table user_photo add constraint pk_user_photo primary key(id);

--用户心情表
create table user_mood(
id number(19), --主键
text varchar(400), --心情内容
type number(3), --心情分类(主题，回复)
status number(3), --心情状态，标识是否被(删除，屏蔽)
user_id number(19), --心情创建者ID
nick_name varchar(32), --用户昵称
ref_id number(19), --回复心情相关ID
create_time date,
recommend_region_id number(19)
);
create sequence user_mood_seq increment by 1;
alter table user_mood add constraint pk_user_mood primary key(id);

--用户好友表
create table user_friend(
id number(19), --主键
user_id number(19), --用户ID
friend_id number(19), --好友ID
type number(3), --好友类型
create_time date --创建时间
);
create sequence user_friend_seq increment by 1;

--用户留言表
create table user_message(
id number(19), --主键
to_user_id number(19), --用户ID
from_user_id number(19), -- 留言者ID
text varchar(400), --留言内容
kind number(3), --留言类型：1留言/2回复
parent_msg_id number(19), --回复相关id
status number(3), --留言状态：未读/已读/已回复
create_time date --创建时间
);
create sequence user_message_seq increment by 1;
alter table user_message add constraint pk_user_message primary key(id);
alter table user_message add title varchar(400);

--用户留言表
create table user_comment(
id number(19), --主键
commented_id number(19), --用户ID
type number(9), --类型，1是图片，2是日志
from_user_id number(19), -- 留言者ID
text varchar(400), --留言内容
kind number(3), --留言类型：1留言/2回复
parent_msg_id number(19), --回复相关id
status number(3), --留言状态：未读/已读/已回复
create_time date --创建时间
);
create sequence user_comment_seq increment by 1;
alter table user_comment add constraint pk_user_comment primary key(id);

--参观历史
create table space_visit_history(
id number(19),
to_user_id number(19), --用户ID
from_user_id number(19), -- 留言者ID
visit_time date
);

create sequence space_visit_history_seq increment by 1;
alter table space_visit_history add constraint pk_space_visit_history primary key(id);

--历史积分
create table point_history(
id number(19), --主键
user_id number(19), --用户id
points_change number(3), --变化分值
flag number(3), --1是增加，2是减少
change_type number(3), --类型，例如是回答问题，还是**
change_time date, --创建时间
state number(3), --状态
audit_opinion varchar(120), --照片路径
audit_user_id number(19), --评审人id
audit_time date --创建时间
);
create sequence point_history_seq increment by 1;

drop table user_diary_comment;
drop sequence user_diary_comment_seq;

drop table user_album;
drop sequence user_album_seq;

drop table user_photo;
drop sequence user_photo_seq;

drop table user_mood;
drop sequence user_mood_seq;

drop table user_friend;
drop sequence user_friend_seq;

drop table user_message;
drop sequence user_message_seq;

drop table user_comment;
drop sequence user_comment_seq;

drop table space_visit_history;
drop sequence space_visit_history_seq;

create table parent(

)

create table student(
id number(19),
Xs_xming varchar(20),
Xj_bhao varchar(30),
Birthplace varchar(128),
PoliticalFace varchar(12),
IDCard varchar(20),
AccountPlace varchar(128),
birthday date,
Bj_ID varchar(24),
Accommodation varchar(10),
DateIntoSch date,
mzhu varchar(12),
Hobby varchar(64),
homephone varchar(15),
zip number(10),
homeaddress varchar(64),
otherlinks varchar(64),
Healthstate varchar(128),
XxID number(19),
groupID number(19),
banGanBuID number(19)
);

create table parent(
id number(19),
PARENTS_NAME varchar(20),
Xs_id number(19),
birthday date,
officePhone varchar(12),
mobliephone varchar(20),
PARENTS_CALLER varchar(128),
Professional varchar(24),
workaddress varchar(24),
remarks varchar(128)
);

create table teacher(
id number(19),
name varchar(20),
XxID number(19),
birthday date,
jobtilte varchar(12),
Education varchar(20),
graduatesch varchar(128),
duties varchar(24),
resume varchar(1024),
officephone varchar(12),
fax varchar(20),
homephone varchar(12),
moblie varchar(12),
zipcode varchar(12),
address varchar(20)
);

create table school(
id number(19),
sch_name varchar(64),
linkman varchar(64),
contactphone varchar(64),
ProId number(19),
CityId number(19),
reamrks varchar(1024)
);

--新闻审核信息表，用于存储新闻审核相关信息
--add by YeQiang 2012-12-13
create table news_audit
(
  id number(19),     --主键，oracle如何设置？
  news_id number(19),--关联的新闻id
  rejectreason varchar(400),--新闻审核失败理由
  creator_id number(19), --创建人id
  creator_nick varchar(64),--创建人昵称
  create_time date  --创建时间
);