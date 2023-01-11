INSERT INTO role (id, name)
SELECT * FROM (SELECT 1, "ADMIN") AS tmp
WHERE NOT EXISTS(SELECT * FROM ROLE WHERE name="ADMIN");

INSERT INTO role (id, name)
SELECT * FROM (SELECT 2, "USER") AS tmp
WHERE NOT EXISTS(SELECT * FROM ROLE WHERE name="USER");