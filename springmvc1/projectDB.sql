CREATE DATABASE hb_student_tracker CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;
SET SESSION sql_mode = 'TRADITIONAL';

USE hb_student_tracker;

create table role(authority varchar(50) not null, description varchar(100),
primary key (authority)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO role values('ROLE_ADMIN', 'Administrator');
INSERT INTO role values('ROLE_EMPLOYEE', 'Employee');
INSERT INTO role values('ROLE_EMPLO_MANAGER', 'Manager');
INSERT INTO role values('ROLE_STUDENT', 'Student');


create table user(username varchar(50) not null, password varchar(100) not null default 'pass123', enabled tinyint(1) not null default 0, 
		  authority varchar(50) default null, 
		  am varchar(20) default null, first_name varchar(50) default null, last_name varchar(100) default null, email varchar(100) default null,
            PRIMARY KEY (username),
		CONSTRAINT fk_user_role FOREIGN KEY (authority) REFERENCES role (authority) ON DELETE SET NULL ON UPDATE CASCADE
            ) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


create table authorities(id int not null AUTO_INCREMENT, username varchar(50) not null, authority varchar(50) not null,
primary key (id),
UNIQUE key ix_auth_username (username,authority),
 CONSTRAINT fk_authorities_user FOREIGN KEY (username) REFERENCES user (username) ON DELETE CASCADE ON UPDATE CASCADE,
 CONSTRAINT fk_authorities_role FOREIGN KEY (authority) REFERENCES role (authority) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO user values('emp', '$2y$12$YVwLJWFWKCrvaVFwRep1LurUHJ0jKOyX5jFWGCKwemVVw3GlmHHuW', 1, 'ROLE_EMPLOYEE', 'Ρ721607', 'Antonis', 'Antoniou', 'antonis@yahoo.gr');
INSERT INTO user values('man', '$2y$12$YVwLJWFWKCrvaVFwRep1LurUHJ0jKOyX5jFWGCKwemVVw3GlmHHuW', 1, 'ROLE_EMPLO_MANAGER', 'L210907', 'Konstantinos', 'Papadopoulos', 'kpapado@yahoo.gr');
INSERT INTO user values('root', '$2y$12$YVwLJWFWKCrvaVFwRep1LurUHJ0jKOyX5jFWGCKwemVVw3GlmHHuW', 1, 'ROLE_ADMIN', 'admin', 'admin', 'admin', 'admin@server.gr');
INSERT INTO user values('stu', '$2y$12$YVwLJWFWKCrvaVFwRep1LurUHJ0jKOyX5jFWGCKwemVVw3GlmHHuW', 1, 'ROLE_STUDENT', '10000', 'Dimitris', 'Horeftaris', 'dxoreftis@yahoo.gr');
INSERT INTO user values('test', '$2y$12$YVwLJWFWKCrvaVFwRep1LurUHJ0jKOyX5jFWGCKwemVVw3GlmHHuW', 0, 'ROLE_STUDENT', '99999', 'Test', 'Test', 'test@test.gr');

INSERT INTO authorities values(null, 'root', 'ROLE_ADMIN');
INSERT INTO authorities values(null, 'emp', 'ROLE_EMPLOYEE');
INSERT INTO authorities values(null, 'man', 'ROLE_EMPLO_MANAGER');
INSERT INTO authorities values(null, 'stu', 'ROLE_STUDENT');
INSERT INTO authorities values(null, 'test', 'ROLE_STUDENT');


create table service (service_name varchar(100), description varchar(100),
primary key (service_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO service (service_name, description) VALUES ('login', 'Log into the system');
INSERT INTO service (service_name, description) VALUES ('logout', 'Log out of the system');
INSERT INTO service (service_name, description) VALUES ('list-users', 'List of users');
INSERT INTO service (service_name, description) VALUES ('list-roles', 'List of roles');
INSERT INTO service (service_name, description) VALUES ('list-services', 'List of services');


create table role_access_service (authority varchar(50) not null, service_name varchar(100) not null,
primary key (authority, service_name),
CONSTRAINT fk_ras_role FOREIGN KEY (authority) REFERENCES role (authority) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT fk_ras_service FOREIGN KEY (service_name) REFERENCES service (service_name) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO role_access_service (authority, service_name) VALUES ('ROLE_ADMIN', 'login');
INSERT INTO role_access_service (authority, service_name) VALUES ('ROLE_STUDENT', 'login');
INSERT INTO role_access_service (authority, service_name) VALUES ('ROLE_EMPLOYEE', 'login');
INSERT INTO role_access_service (authority, service_name) VALUES ('ROLE_EMPLO_MANAGER', 'login');
INSERT INTO role_access_service (authority, service_name) VALUES ('ROLE_ADMIN', 'logout');
INSERT INTO role_access_service (authority, service_name) VALUES ('ROLE_STUDENT', 'logout');
INSERT INTO role_access_service (authority, service_name) VALUES ('ROLE_EMPLOYEE', 'logout');
INSERT INTO role_access_service (authority, service_name) VALUES ('ROLE_EMPLO_MANAGER', 'logout');


create table employee (username varchar(50) not null, description varchar(50),
primary key (username),
CONSTRAINT fk_employee_user FOREIGN KEY (username) REFERENCES user (username) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO employee (username, description) VALUES ('emp', '');
INSERT INTO employee (username, description) VALUES ('man', 'Manager of employees');


create table department (id int not null AUTO_INCREMENT, dep_name varchar(100), orio_dorean_sitisis int default 80, xoritikotita int default 200, location varchar(50) default 'Athina',
	supervisor_username varchar(50) default null,
primary key (id),
CONSTRAINT fk_deparment_employee FOREIGN KEY (supervisor_username) REFERENCES employee (username) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO department (id, dep_name) VALUES (null, 'Pliroforikis');
INSERT INTO department (id, dep_name) VALUES (null, 'Mathimatikon');
INSERT INTO department (id, dep_name) VALUES (null, 'Fysikis');
INSERT INTO department (id, dep_name, location) VALUES (null, 'Viologias', 'Piraias');
INSERT INTO department (id, dep_name, location) VALUES (null, 'Chimias', 'Chalkida');


create table student (username varchar(50) not null, dep_id int,
primary key (username),
CONSTRAINT fk_student_user FOREIGN KEY (username) REFERENCES user (username) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT fk_student_department FOREIGN KEY (dep_id) REFERENCES department (id) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO student (username, dep_id) VALUES ('stu', 1);
INSERT INTO student (username, dep_id) VALUES ('test', 1);


create table student_application(
	app_id int not null AUTO_INCREMENT, 
	username varchar(50) not null,
	onoma_patros varchar(50),
	onoma_mitros varchar(50),
	pateras_ergazetai tinyint(1) default 0,
	mitera_ergazetai tinyint(1) default 0,
	oikogeneiako_eisodima int default 0,
	adelfia_spoudazoun tinyint(2) default 0,
	entopiotita tinyint(1) default 0,
	address_epik varchar(80),
	til_epik varchar(20),
	email_epik varchar(100),
	eggrisi	tinyint(1),
	seira int,
	vathmos int default 0,
	result tinyint(1) default 0,
	primary key (app_id),
	CONSTRAINT fk_stuapp_student FOREIGN KEY (username) REFERENCES student (username) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;






DELIMITER //

CREATE TRIGGER user_after_insert
AFTER INSERT
   ON user FOR EACH ROW

BEGIN
	
    -- Insert record into table authorities
   INSERT INTO authorities
   ( username,
     authority)
   VALUES
   ( NEW.username,
     NEW.authority
     );

END; //

DELIMITER ;




