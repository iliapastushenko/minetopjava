DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User1', 'user1@yandex.ru', 'password'),
  ('User2', 'user2@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_ADMIN', 100002);


INSERT INTO meals (datetime, description, calories, user_id) VALUES
  ('2015-08-05 10:00:00', 'Завтрак юзера 1', 500, 100000),
  ('2015-08-05 13:00:00', 'Обед юзера 1', 1000, 100000),
  ('2015-08-05 20:00:00', 'Ужин юзера 1', 500, 100000),
  ('2015-08-06 10:00:00', 'Завтрак юзера 1', 500, 100000),
  ('2015-08-06 13:00:00', 'Обед юзера 1', 1000, 100000),
  ('2015-08-06 20:00:00', 'Ужин юзера 1', 501, 100000),

  ('2015-08-05 10:00:00', 'Завтрак юзера 2', 500, 100001),
  ('2015-08-05 13:00:00', 'Обед юзера 2', 1000, 100001),
  ('2015-08-05 20:00:00', 'Ужин юзера 2', 500, 100001),
  ('2015-08-06 10:00:00', 'Завтрак юзера 2', 500, 100001),
  ('2015-08-06 13:00:00', 'Обед юзера 2', 1000, 100001),
  ('2015-08-06 20:00:00', 'Ужин юзера 2', 501, 100001)