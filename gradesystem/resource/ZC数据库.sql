/*1用户（学生）表 */

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    `user_acount` varchar(30) NOT NULL COMMENT '帐号/学号',  /*成绩管理系统所需属性*/
    `password` varchar(20) NOT NULL COMMENT '密码',          /*成绩管理系统所需属性*/
    `realname` varchar(16) DEFAULT NULL COMMENT '姓名',		 /*成绩管理系统所需属性*/
    `nickname` varchar(12) DEFAULT NULL COMMENT '昵称',
    `score` int(11) DEFAULT NULL COMMENT '用户积分',
    `telephone` varchar(20) DEFAULT NULL COMMENT '手机号',   /*成绩管理系统所需属性*/
    `qq` varchar(20) DEFAULT NULL COMMENT 'QQ号',
    `email` varchar(45) DEFAULT NULL COMMENT 'email',		/*成绩管理系统所需属性*/
    `weixin` varchar(20) DEFAULT NULL COMMENT '微信号',
    `age` int(10) DEFAULT NULL COMMENT '年龄',
    `city` varchar(30) DEFAULT NULL COMMENT '城市',          /*成绩管理系统所需属性*/
    `occupation` varchar(30) DEFAULT NULL COMMENT '职业',
    `education` varchar(20) DEFAULT NULL COMMENT '学历',
    `level` varchar(6) DEFAULT NULL COMMENT '会员级别',
    `scores` int(10) DEFAULT NULL COMMENT '运费',
    `state` int(1) DEFAULT NULL COMMENT '现状态',
    `regist_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
    `usercode` varchar(12) DEFAULT NULL COMMENT '用户类型编码',
    `marry` int(1) DEFAULT NULL COMMENT '婚否',
    `hobby` varchar(30) DEFAULT NULL COMMENT '爱好',
    `province` varchar(30) DEFAULT NULL COMMENT '省',
    `address` varchar(150) DEFAULT NULL COMMENT '通信地址',  /*成绩管理系统所需属性*/
    `postCode` varchar(6) DEFAULT NULL COMMENT '邮编',      /*成绩管理系统所需属性*/
    `paymethod` varchar(8) DEFAULT '在线支付' COMMENT '支付方法',
    `user_type` int(11) DEFAULT NULL COMMENT '\'用户类型1-操作和编辑（要批准）\'',
    `username` varchar(15) DEFAULT NULL,
    `dzyj` varchar(50) DEFAULT NULL,
    `lxdh` varchar(20) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `sex` tinyint(1) DEFAULT NULL COMMENT '性别',           /*成绩管理系统所需属性*/
    PRIMARY KEY (`user_acount`) USING BTREE
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table users add birthday date DEFAULT NULL COMMENT '出生年月';   /*成绩管理系统所需属性*/
alter table users add  class_id varchar(9)  DEFAULT NULL COMMENT '所在班级编号';/*成绩管理系统所需属性*/
alter table users add Nj Varchar(5)  DEFAULT NULL COMMENT '年级';/*成绩管理系统所需属性*/
alter table users add  Zkzh	Varchar(18)	DEFAULT NULL COMMENT '准考证号';	/*成绩管理系统所需属性*/
alter table users add  Ksh	Varchar(20)	DEFAULT NULL COMMENT '考生号';	/*成绩管理系统所需属性*/
alter table users add  Mz	Varchar(12)	DEFAULT NULL COMMENT '民族';	/*成绩管理系统所需属性*/
alter table users add  Zzmm	Varchar(18)	DEFAULT NULL COMMENT '政治面貌';	/*成绩管理系统所需属性*/
alter table users add  Byrq	datetime	DEFAULT NULL COMMENT '已毕业日期';	
alter table users add  Gzrq	datetime	DEFAULT NULL COMMENT '工作日期';	
alter table users add  Byxx	Varchar(40)	DEFAULT NULL COMMENT '毕业学校';		
alter table users add  Dw	Varchar(40)	DEFAULT NULL COMMENT '工作单位';
alter table users add  zhiwu Varchar(16)	DEFAULT NULL COMMENT '职务';	
alter table users add  `photo` blob COMMENT '相片';                        /*成绩管理系统所需属性*/
alter table users add  xxdd	Varchar(20)	DEFAULT NULL COMMENT '学习地点';
alter table users add  Wyyz	Varchar(8)	DEFAULT NULL COMMENT '外语语种';
alter table users add  Sfzh	Varchar(18)	DEFAULT NULL COMMENT '身份证号';   /*成绩管理系统所需属性*/
alter table users add  Xfzh	Varchar(6)	DEFAULT NULL COMMENT '行政区号代码'; 
alter table users add Lqzy	Varchar(6)	DEFAULT NULL COMMENT '专业代码';/*成绩管理系统所需属性*/
alter table users add  Tdzyh Varchar(1)	DEFAULT NULL COMMENT '投档志愿号';
alter table users add  Rxcj	Smallint	DEFAULT NULL COMMENT '入学成绩';
alter table users add  Wyyz	Varchar(6)	DEFAULT NULL COMMENT '学习形式';
alter table users add Rxrq	datetime	DEFAULT NULL COMMENT '入学日期';/*成绩管理系统所需属性*/
alter table users add  Ljsf	Smallint	DEFAULT NULL COMMENT '累积学分';	
alter table users add Bysj	datetime	DEFAULT NULL COMMENT '毕业日期';	
alter table users add  swzh	Varchar(15)	DEFAULT NULL COMMENT '学位书号';	
	

/*2专家（教师）表 */	
DROP TABLE IF EXISTS `experts`;
CREATE TABLE `experts` (
    `expacount` varchar(20) NOT NULL COMMENT '专家帐号',    /*成绩管理系统所需属性*/
    `password` varchar(20) NOT NULL COMMENT '密码',         /*成绩管理系统所需属性*/
    `name` varchar(20) DEFAULT NULL COMMENT '姓名',         /*成绩管理系统所需属性*/
    `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
    `sex` varchar(2) DEFAULT NULL COMMENT '性别',           /*成绩管理系统所需属性*/
    `description` varchar(1000) DEFAULT NULL COMMENT '简介',
    `photo` blob COMMENT '相片',/*成绩管理系统所需属性*/
    `title` varchar(40) DEFAULT NULL COMMENT '职称',/*成绩管理系统所需属性*/
    `education` varchar(20) DEFAULT NULL COMMENT '学历',/*成绩管理系统所需属性*/
    `qq` varchar(20) DEFAULT NULL COMMENT 'qq',
    `telephone` varchar(20) DEFAULT NULL COMMENT '电话',/*成绩管理系统所需属性*/
    `email` varchar(30) DEFAULT NULL COMMENT 'email',
    `misigal` varchar(20) DEFAULT NULL COMMENT '微信号',
    `city` varchar(20) DEFAULT NULL COMMENT '城市',
    `state` int(1) DEFAULT NULL COMMENT '状态',/*成绩管理系统所需属性*/
    `regtime` datetime DEFAULT NULL COMMENT '注册时间',/*成绩管理系统所需属性*/
    `expcode` varchar(10) DEFAULT NULL COMMENT '专家类型编码',/*成绩管理系统所需属性*/
    PRIMARY KEY (`expacount`) USING BTREE
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table experts add  Deparid	Varchar(2)	DEFAULT NULL COMMENT '所属单位编号';/*成绩管理系统所需属性*/




/*3 学科课程（知识专题）表 */		
DROP TABLE IF EXISTS `objcenter`;
CREATE TABLE `objcenter` (
    `visit_count` int(11) NOT NULL AUTO_INCREMENT COMMENT '学科课程、知识专题号',  /*成绩管理系统所需属性*/
	`title` varchar(40)CHARACTER SET utf8 DEFAULT NULL COMMENT '学科、知识专题名称',  /*成绩管理系统所需属性*/
    `description` varchar(1000)CHARACTER SET utf8 DEFAULT NULL COMMENT '学科、知识专题',
    `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
    `states` int(1) NOT NULL DEFAULT '1' COMMENT '使用状态',
    `remark` varchar(100)CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
    `pictures` varchar(200)CHARACTER SET utf8 DEFAULT NULL COMMENT '图片目录位置',
    PRIMARY KEY (`visit_count`)
)  ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE = utf8_bin;

alter table objcenter add  Coursehour	Smallint	DEFAULT NULL COMMENT '课时数';
alter table objcenter add  Theoryhour	Smallint	DEFAULT NULL COMMENT '理论课时';
alter table objcenter add  Labhour	Smallint	NOT NULL DEFAULT '0' COMMENT '实验课时';
alter table objcenter add doself	Smallint	NOT NULL DEFAULT '0' COMMENT '作业课时';
alter table objcenter add  Zydm	varchar(6)	DEFAULT NULL COMMENT '专业代码';
alter table objcenter add Courselei	varchar(4)	DEFAULT NULL COMMENT '课程类型(必修选修限选)';
alter table objcenter add  scores	Smallint	DEFAULT NULL COMMENT '课程学分';
alter table objcenter add Term	Smallint	NOT NULL DEFAULT '1' COMMENT '开设学期';
alter table objcenter add  precourse	Varchar(4)	DEFAULT NULL COMMENT '先行课编号';

/*4、Maijor （专业设置表）*/
DROP TABLE IF EXISTS `Maijor`;
create table `Maijor` (
    `Zydm` varchar(6) not null COMMENT '专业编号',  /*成绩管理系统所需属性*/
    primary key (`Zydm`),
    `Zymc` varchar(16) not null COMMENT '专业名称', /*成绩管理系统所需属性*/
    `Xxxs` varchar(8) not null comment '学习形式',
    `xxxdm` varchar(1) DEFAULT NULL comment '学习形式代码',
    `Pycc` varchar(1) DEFAULT NULL comment '培养层次代码',
    `Xznx` varchar(1) not null comment '学制年限',/*成绩管理系统所需属性*/
    `Xz` smallint not null comment '学制',/*成绩管理系统所需属性*/
    `Bz` Varchar(255) DEFAULT NULL comment '备注'/*成绩管理系统所需属性*/
);

/*5、Xuexid 学习地点表	*/
DROP TABLE IF EXISTS `Xuexid`;
create table `Xuexid` (
    `Deparid` Varchar(6) DEFAULT NULL comment '学习地点编码',  /*成绩管理系统所需属性*/
    `xxdd` Varchar(20) DEFAULT NULL comment '学习点名称',      /*成绩管理系统所需属性*/
    `dizhi` Varchar(40) DEFAULT NULL comment '学习地址',
    `lianxi` Varchar(40) DEFAULT NULL comment '联系电话',
    `lianxiren` Varchar(20) DEFAULT NULL comment '联系人',
    `email` Varchar(20) DEFAULT NULL comment '邮箱地址',
    `ytdw` Varchar(40) DEFAULT NULL comment '依托单位',
    `leixing` Varchar(6) DEFAULT NULL comment '单位类型',
    `Yongdate` datetime DEFAULT NULL comment '建立日期',
    `Huze` Varchar(10) DEFAULT NULL comment '负责人',
    `Tell` varchar(40) DEFAULT NULL comment '联系电话'
);

/*6、doCourse表（课程表）*/
DROP TABLE IF EXISTS `doCourse`;
create table doCourse (
    `visit_count` varchar(4) not null comment '课程编号',/*成绩管理系统所需属性*/
    primary key (`visit_count`),
    `title` varchar(20) not null comment '课程名称',/*成绩管理系统所需属性*/
    `Coursehour` smallint not null comment '课时数',/*成绩管理系统所需属性*/
    `expacount` varchar(20) default null comment '授课教师账号',
    `State` varchar(4) comment '执行情况' /* 启动，停用*/
);

 create index IDX_conut on `doCourse`(`visit_count`);

/*7、StudentGrade表（学生成绩表）此表为成绩管理系统重点表*/
DROP TABLE IF EXISTS `StudentGrade`;
create table StudentGrade(
    `user_acount` varchar(30) not null comment '学号',  
    `visit_count` varchar(4) not null comment '课程编号',  
	`Psgrade` Smallint default null comment '平时成绩',   
	`ksgrade` Smallint default null comment '考试成绩',   
	`Grade` Smallint  null comment '成绩',              
	`Nf` varchar(5) default null comment '年级',        
	`Gradelei` varchar(8) default null comment '考核类型（正考补考重修换证免考）',  
	`scores` smallint(200) default null comment '积分',
	`expacount` varchar(20) default null comment '任教教师号',
	`oper` varchar(6) default null comment '操作员代码',
	`shenhe` varchar(6) default null comment '审核人员代码',
	`optime` datetime default null comment '操作日期'
);

alter table StudentGrade 
add constraint fk_visit_count foreign key(`visit_count`) references doCourse(`visit_count`);



	/*8省表*/
	DROP TABLE IF EXISTS `province`;
	CREATE TABLE `province` (
    `pronos` int(11) NOT NULL,
    `province` varchar(40) DEFAULT NULL,
    PRIMARY KEY (`pronos`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*9市表 */
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
    `id` int(11) NOT NULL,
    `pronos` int(11) DEFAULT NULL,
    `city` varchar(40) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_city_1` (`pronos`),
    CONSTRAINT `FK_city_1` FOREIGN KEY (`pronos`)
        REFERENCES `province` (`pronos`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*10、教育程度（学历）表 */
DROP TABLE IF EXISTS `education`;
CREATE TABLE `education` (
    `id` int(11) NOT NULL,
    `education` varchar(20) DEFAULT NULL
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*11职业表 */
DROP TABLE IF EXISTS `ocupation`;
CREATE TABLE `ocupation` (
    `id` int(11) NOT NULL,
    `ocupation` varchar(40) DEFAULT NULL
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*12管理员表*/
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
    `admin_acount` varchar(20) NOT NULL COMMENT '管理员帐号',    /*成绩管理系统所需属性*/
    `password` varchar(20) NOT NULL COMMENT '密码',         /*成绩管理系统所需属性*/
    `name` varchar(20) DEFAULT NULL COMMENT '姓名',         /*成绩管理系统所需属性*/
    `sex` varchar(2) DEFAULT NULL COMMENT '性别',           /*成绩管理系统所需属性*/
    `description` varchar(1000) DEFAULT NULL COMMENT '简介',
    `photo` blob COMMENT '相片',/*成绩管理系统所需属性*/
    `title` varchar(40) DEFAULT NULL COMMENT '职称',/*成绩管理系统所需属性*/
    `education` varchar(20) DEFAULT NULL COMMENT '学历',/*成绩管理系统所需属性*/
    `qq` varchar(20) DEFAULT NULL COMMENT 'qq',
    `telephone` varchar(20) DEFAULT NULL COMMENT '电话',/*成绩管理系统所需属性*/
    `email` varchar(30) DEFAULT NULL COMMENT 'email',
    `misigal` varchar(20) DEFAULT NULL COMMENT '微信号',
    `city` varchar(20) DEFAULT NULL COMMENT '城市',
    `state` int(1) DEFAULT NULL COMMENT '状态',/*成绩管理系统所需属性*/
    `regtime` datetime DEFAULT NULL COMMENT '注册时间',/*成绩管理系统所需属性*/
    `admincode` varchar(10) DEFAULT NULL COMMENT '管理员类型编码',/*成绩管理系统所需属性*/
    PRIMARY KEY (`admin_acount`) USING BTREE
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*alter table experts add  Deparid    Varchar(2)  DEFAULT NULL COMMENT '所属单位编号';