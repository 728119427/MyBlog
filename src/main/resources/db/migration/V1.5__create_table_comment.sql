CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255)  DEFAULT NULL,
  `email` varchar(255)  DEFAULT NULL,
  `content` varchar(255)  DEFAULT NULL,
  `avatar` varchar(255)  DEFAULT NULL,
  `create_time` datetime  DEFAULT NULL,
  `blog_id` bigint(20)  DEFAULT NULL,
  `parent_comment_id` bigint(20)  DEFAULT NULL,
  `admin_comment` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB  CHARACTER SET = utf8 ;