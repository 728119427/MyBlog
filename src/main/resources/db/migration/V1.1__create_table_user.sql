CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255),
  `create_time` datetime,
  `email` varchar(255),
  `nickname` varchar(255),
  `password` varchar(255),
  ` type` int(11),
  `update_time` datetime,
  `username` varchar(255),
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8;