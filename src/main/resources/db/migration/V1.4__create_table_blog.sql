CREATE TABLE `blog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appreciation` bit(1) NOT NULL,
  `commentabled` bit(1) NOT NULL,
  `content` longtext  NULL,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255)  DEFAULT NULL,
  `first_picture` varchar(255)  DEFAULT NULL,
  `flag` varchar(255)  DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255)  DEFAULT NULL,
  `update_time` datetime  DEFAULT NULL,
  `views` int(11)  DEFAULT NULL,
  `type_id` bigint(20)  DEFAULT NULL,
  `user_id` bigint(20)  DEFAULT NULL,
  `comment_count` int(255)  DEFAULT NULL,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `FK292449gwg5yf7ocdlmswv9w4j` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`) ,
  CONSTRAINT `FK8ky5rrsxh01nkhctmo7d48p82` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB  CHARACTER SET = utf8 ;