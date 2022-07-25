CREATE TABLE basedevice
(
    device_id  int NOT NULL PRIMARY KEY ,
    description text,
    device_type int
);

CREATE TABLE testers
(
    tester_id  int NOT NULL PRIMARY KEY ,
    first_name text,
    last_name text,
    country text,
    last_login text
);

CREATE TABLE bugs
(
    bug_id  int NOT NULL PRIMARY KEY ,
    device_id int NOT NULL references basedevice(device_id),
    fk_tester_id INT,
    FOREIGN KEY (fk_tester_id) REFERENCES testers(tester_id)
);

CREATE TABLE tester_device
(
    id SERIAL NOT NULL PRIMARY KEY,
    tester_id  int NOT NULL references testers(tester_id),
    device_id int NOT NULL references basedevice(device_id)
);