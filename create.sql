create table answer (question_id bigint not null, created_at datetime, answer_description varchar(255), answer_title varchar(255), updated_at datetime, user_id bigint not null, primary key (question_id)) engine=MyISAM;
create table question (id bigint not null, created_at datetime, question_description varchar(255), question_title varchar(255), updated_at datetime, user_id bigint not null, primary key (id)) engine=MyISAM;
create table `question-tags,` (question_id bigint not null, tag_id bigint not null, primary key (question_id, tag_id)) engine=MyISAM;
create table question_seq (next_val bigint) engine=MyISAM;
insert into question_seq values ( 1 );
create table tags (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=MyISAM;
create table users (user_id bigint not null, e_mail varchar(255), f_name varchar(255), l_name varchar(255), u_role varchar(255), u_password varchar(255), primary key (user_id)) engine=MyISAM;
create table users_seq (next_val bigint) engine=MyISAM;
insert into users_seq values ( 1 );
alter table answer add constraint FKsdj8jab9t00diflkysw22k7bv foreign key (user_id) references users (user_id);
alter table answer add constraint FK8frr4bcabmmeyyu60qt7iiblo foreign key (question_id) references question (id);
alter table question add constraint FK7rnpup7eaonh2ubt922ormoij foreign key (user_id) references users (user_id);
alter table `question-tags,` add constraint FKsr008ctva1g0cf69t589ursfq foreign key (tag_id) references tags (id);
alter table `question-tags,` add constraint FKk286q98bbdpil9lfuk3dbh6nl foreign key (question_id) references question (id);
create table answer (question_id bigint not null, created_at datetime, answer_description varchar(255), answer_title varchar(255), updated_at datetime, user_id bigint not null, primary key (question_id)) engine=MyISAM;
create table question (id bigint not null, created_at datetime, question_description varchar(255), question_title varchar(255), updated_at datetime, user_id bigint not null, primary key (id)) engine=MyISAM;
create table `question-tags,` (question_id bigint not null, tag_id bigint not null, primary key (question_id, tag_id)) engine=MyISAM;
create table question_seq (next_val bigint) engine=MyISAM;
insert into question_seq values ( 1 );
create table tags (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=MyISAM;
create table users (user_id bigint not null, e_mail varchar(255), f_name varchar(255), l_name varchar(255), u_password varchar(255), u_role varchar(255), primary key (user_id)) engine=MyISAM;
create table users_seq (next_val bigint) engine=MyISAM;
insert into users_seq values ( 1 );
alter table answer add constraint FKsdj8jab9t00diflkysw22k7bv foreign key (user_id) references users (user_id);
alter table answer add constraint FK8frr4bcabmmeyyu60qt7iiblo foreign key (question_id) references question (id);
alter table question add constraint FK7rnpup7eaonh2ubt922ormoij foreign key (user_id) references users (user_id);
alter table `question-tags,` add constraint FKsr008ctva1g0cf69t589ursfq foreign key (tag_id) references tags (id);
alter table `question-tags,` add constraint FKk286q98bbdpil9lfuk3dbh6nl foreign key (question_id) references question (id);
create table answer (question_id bigint not null, created_at datetime, answer_description varchar(255), answer_title varchar(255), updated_at datetime, user_id bigint not null, primary key (question_id)) engine=MyISAM;
create table question (id bigint not null, created_at datetime, question_description varchar(255), question_title varchar(255), updated_at datetime, user_id bigint not null, primary key (id)) engine=MyISAM;
create table `question-tags,` (question_id bigint not null, tag_id bigint not null, primary key (question_id, tag_id)) engine=MyISAM;
create table question_seq (next_val bigint) engine=MyISAM;
insert into question_seq values ( 1 );
create table tags (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=MyISAM;
create table users (user_id bigint not null, e_mail varchar(255), f_name varchar(255), l_name varchar(255), u_password varchar(255), u_role varchar(255), primary key (user_id)) engine=MyISAM;
create table users_seq (next_val bigint) engine=MyISAM;
insert into users_seq values ( 1 );
alter table answer add constraint FKsdj8jab9t00diflkysw22k7bv foreign key (user_id) references users (user_id);
alter table answer add constraint FK8frr4bcabmmeyyu60qt7iiblo foreign key (question_id) references question (id);
alter table question add constraint FK7rnpup7eaonh2ubt922ormoij foreign key (user_id) references users (user_id);
alter table `question-tags,` add constraint FKsr008ctva1g0cf69t589ursfq foreign key (tag_id) references tags (id);
alter table `question-tags,` add constraint FKk286q98bbdpil9lfuk3dbh6nl foreign key (question_id) references question (id);
create table answer (question_id bigint not null, created_at datetime, answer_description varchar(255), answer_title varchar(255), updated_at datetime, user_id bigint not null, primary key (question_id)) engine=MyISAM;
create table question (id bigint not null, created_at datetime, question_description varchar(255), question_title varchar(255), updated_at datetime, user_id bigint not null, primary key (id)) engine=MyISAM;
create table `question-tags,` (question_id bigint not null, tag_id bigint not null, primary key (question_id, tag_id)) engine=MyISAM;
create table question_seq (next_val bigint) engine=MyISAM;
insert into question_seq values ( 1 );
create table tags (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=MyISAM;
create table users (user_id bigint not null, e_mail varchar(255), f_name varchar(255), l_name varchar(255), u_password varchar(255), u_role varchar(255), primary key (user_id)) engine=MyISAM;
create table users_seq (next_val bigint) engine=MyISAM;
insert into users_seq values ( 1 );
alter table answer add constraint FKsdj8jab9t00diflkysw22k7bv foreign key (user_id) references users (user_id);
alter table answer add constraint FK8frr4bcabmmeyyu60qt7iiblo foreign key (question_id) references question (id);
alter table question add constraint FK7rnpup7eaonh2ubt922ormoij foreign key (user_id) references users (user_id);
alter table `question-tags,` add constraint FKsr008ctva1g0cf69t589ursfq foreign key (tag_id) references tags (id);
alter table `question-tags,` add constraint FKk286q98bbdpil9lfuk3dbh6nl foreign key (question_id) references question (id);