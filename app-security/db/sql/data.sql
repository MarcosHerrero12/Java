--insert into users (username, password, enabled) VALUES
--                                                    ('admin', 'MarcosHerrero', true),
--                                                    ('user', 'MarcosHerrero', true);
--
--insert into authorities (username, authority) VALUES
--                                                  ('admin', 'admin'),
--                                                  ('user', 'user');

insert into customers (email, pwd, rol) VALUES
  ('super_user@debuggeandoieas.com', 'to_be_encoded', 'admin'),
  ('basic_user@debuggeandoieas.com', 'to_be_encoded', 'user');