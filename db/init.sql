insert  into `auth_role`(`id`,`name`) values (1,'admin'),(2,'manager'),(3,'normal');
insert  into `auth_permission`(`id`,`name`,`role_id`) values (1,'add',2),(2,'del',1),(3,'update',2),(4,'query',3),(5,'user:query',1),(6,'user:edit',2);
insert  into `auth_user`(`id`,`username`,`password`,`salt`) values (1,'tom','123456','123456'),(2,'jack','123456','123456'),(3,'rose','123456','123456');
insert  into `auth_user_role`(`user_id`,`role_id`) values (1,1),(1,3),(2,2),(2,3),(3,3);

