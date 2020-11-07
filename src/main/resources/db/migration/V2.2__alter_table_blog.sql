ALTER TABLE `blog`
MODIFY COLUMN `appreciation` bit(1) NOT NULL DEFAULT false AFTER `id`,
MODIFY COLUMN `commentabled` bit(1) NOT NULL DEFAULT false AFTER `appreciation`,
MODIFY COLUMN `published` bit(1) NOT NULL DEFAULT false AFTER `flag`,
MODIFY COLUMN `recommend` bit(1) NOT NULL DEFAULT false AFTER `published`,
MODIFY COLUMN `share_statement` bit(1) NOT NULL DEFAULT false AFTER `recommend`;