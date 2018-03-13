CREATE TABLE `book` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '图书id',
  `name` varchar(255) NOT NULL COMMENT '图书名',
  `description` varchar(255) NOT NULL COMMENT '图书简介',
  `inventory` int(12) NOT NULL COMMENT '库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;