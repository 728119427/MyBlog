CREATE TABLE `blog_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `blog_id` bigint(20) NOT NULL ,
  `tag_id` bigint(20) NOT NULL ,
  PRIMARY KEY (`id`),
	CONSTRAINT fk_bt_blog foreign key (blog_id) REFERENCES blog(id),
	CONSTRAINT fk_bt_tag foreign key (tag_id) REFERENCES tag(id)
)ENGINE = InnoDB  CHARACTER SET = utf8 ;