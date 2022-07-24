CREATE TABLE devices
(
    device_id  int NOT NULL PRIMARY KEY ,
    description text
);

CREATE TABLE testers
(
    tester_id  int NOT NULL PRIMARY KEY ,
    first_name text,
    last_name text,
    country text,
    last_login text
);

CREATE TABLE bugs_devices_testers
(
    bug_id  int NOT NULL PRIMARY KEY ,
    device_id int NOT NULL references devices(device_id),
    tester_id int NOT NULL references testers(tester_id)
);

CREATE TABLE tester_device
(
    id SERIAL NOT NULL PRIMARY KEY,
    tester_id  int NOT NULL references testers(tester_id),
    device_id int NOT NULL references devices(device_id)
);