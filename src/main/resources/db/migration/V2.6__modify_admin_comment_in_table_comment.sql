ALTER TABLE `comment`
MODIFY COLUMN `admin_comment` bit(1) NOT NULL DEFAULT false AFTER `parent_comment_id`;