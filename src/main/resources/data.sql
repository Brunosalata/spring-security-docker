INSERT INTO tb_role (role_id, role) VALUES (1, 'ADMIN') ON CONFLICT (role_id) DO NOTHING;;
INSERT INTO tb_role (role_id, role) VALUES (2, 'BASIC') ON CONFLICT (role_id) DO NOTHING;;