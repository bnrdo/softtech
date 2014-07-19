INSERT INTO role (roleName, description) VALUES ('ROLE_USER', 'User');
INSERT INTO role (roleName, description) VALUES ('ROLE_ADMIN', 'Admin');
INSERT INTO role (roleName, description) VALUES ('ROLE_SUPERVISOR', 'Supervisor');
INSERT INTO role (roleName, description) VALUES ('ROLE_JOB_SEEKER', 'Job Seeker');
INSERT INTO role (roleName, description) VALUES ('ROLE_RECRUITER_INTERNAL', 'Recruiter Internal');
INSERT INTO role (roleName, description) VALUES ('ROLE_RECRUITER', 'Recruiter');

INSERT INTO user(firstName) VALUES(null),(null);
INSERT INTO useraccount(username, password, status, email, owner_id) VALUES ('joseph','066f17dadaa4a2fa31c7ea8e8fb0ba80a3f4ca3da7024f9a6da28c5001f40c07','ACTIVE','',1),('geng','d970dba00e56a4fee53a8d3d69515fb4dca37e1925726647424ee106b0f12727','ACTIVE','',2);
INSERT INTO UserAccountsAndRoles(user_id, role_id) VALUES(1, 2),(2, 1);