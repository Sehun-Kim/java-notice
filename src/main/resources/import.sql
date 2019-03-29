INSERT INTO user (id, user_name, password, user_id, created_at) values (1, 'sehun', '1234', 'tester', CURRENT_TIMESTAMP());
INSERT INTO user (id, user_name, password, user_id, created_at) values (2, 'dom', '1234', 'tester2', CURRENT_TIMESTAMP());

INSERT INTO notice (id, title, content, writer_id, created_at) values (1, '테스트입니다.', '이것은 테스트입니다. 열심히 작성하지 않은 공지지요.', 1, CURRENT_TIMESTAMP());
