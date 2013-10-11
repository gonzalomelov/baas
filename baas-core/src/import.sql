
INSERT INTO users(email, lastname, loggedin, name, password)VALUES ('aemail', 'alastname', false, 'aname', 'apassword');

INSERT INTO applications(name) VALUES ('aname');

INSERT INTO users_applications(applications_id, users_id) VALUES (1,1);

INSERT INTO entities(id, name, application_id) VALUES (1, 'aentity', 1);

INSERT INTO clients(email, lastname, name, loggedin, password, application_id) VALUES ('a@a.com', 'alastname', 'aname', false, 'apassword', 1);