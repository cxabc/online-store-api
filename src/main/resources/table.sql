CREATE TABLE `weapon` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `weapon_name` varchar(32) NOT NULL,
                          `price` int(121) NOT NULL,
                          `img` varchar(1024) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- （BEGIN; 和 COMMIT;）来确保原子性操作，即要么所有的插入操作都成功执行，要么全部回滚。
begin;
INSERT INTO `weapon` VALUES (1,  'AK47', '18539', 'https://csgoskins.gg/weapons/ak-47');
INSERT INTO `weapon` VALUES (2,  '虎皮坦克', '88888539', 'http://cpwk.oss-cn-beijing.aliyuncs.com/image/f/2019/9/22/5d876a04aeb1db3b93e9995f61jjc2vw.jpg');
INSERT INTO `weapon` VALUES (3,  '高爆手雷', '6539', 'http://cpwk.oss-cn-beijing.aliyuncs.com/image/f/2019/9/22/5d876a04aeb1db3b93e9995f61jjc2vw.jpg');
INSERT INTO `weapon` VALUES (4,  '燃烧弹', '7539', 'http://cpwk.oss-cn-beijing.aliyuncs.com/image/f/2019/9/22/5d876a04aeb1db3b93e9995f61jjc2vw.jpg');
INSERT INTO `weapon` VALUES (5,  '火箭筒', '18539', 'http://cpwk.oss-cn-beijing.aliyuncs.com/image/f/2019/9/22/5d876a04aeb1db3b93e9995f61jjc2vw.jpg');
INSERT INTO `weapon` VALUES (6,  '战斗机', '9998539', 'https://csgoskins.gg/weapons/ak-47');
commit;