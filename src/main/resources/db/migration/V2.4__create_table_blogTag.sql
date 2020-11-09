CREATE TABLE `blog_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `blog_id` bigint(20) NOT NULL ,
  `tag_id` bigint(20) NOT NULL ,
  PRIMARY KEY (`id`)
)ENGINE = InnoDB  CHARACTER SET = utf8 ;