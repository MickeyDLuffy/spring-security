INSERT INTO users(username, password, enabled)
  VALUES ('jhey', 'jhey', true);

INSERT INTO users(username, password, enabled)
  VALUES ('dluffy', 'juu', true);


INSERT INTO authorities (username, authority)
   VALUES('jhey', 'ROLE_ADMIN');


INSERT INTO authorities (username, authority)
VALUES('dluffy', 'ROLE_USER');