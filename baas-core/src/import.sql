
INSERT INTO operations(name) values ('GET')
INSERT INTO operations(name) values ('POST')
INSERT INTO operations(name) values ('PUT')
INSERT INTO operations(name) values ('DELETE')

INSERT INTO users(email, lastname, loggedin, name, password)VALUES ('aemail', 'alastname', false, 'aname', 'apassword');

INSERT INTO applications(name, apiclientid) VALUES ('aname', '7dc53df5-703e-49b3-8670-b1c468f47f1f');

INSERT INTO users_applications(applications_id, users_id) VALUES (1,1);

INSERT INTO entities(name, application_id) VALUES ('aentity', 1);

INSERT INTO roles (name, application_id) values ('Publico', 1);

INSERT INTO permissions (application_id, entity_id, operation_id, role_id) values (1,1,1,1)
INSERT INTO permissions (application_id, entity_id, operation_id, role_id) values (1,1,2,1)

INSERT INTO clients(email, lastname, name, loggedin, password, accessToken, application_id) VALUES ('a@a.com', 'alastname', 'aname', false, 'apassword', 'b86953fb-ed05-4ec2-9c2d-5629e1c29215', 1);

INSERT INTO clients_roles values (1,1);