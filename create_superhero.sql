DROP DATABASE IF EXISTS superhero;

CREATE DATABASE superhero;
USE superhero;

CREATE TABLE city (
	id INT NOT NULL AUTO_INCREMENT, 
    cityname VARCHAR(40), 
    PRIMARY KEY(id)
);

CREATE TABLE superhero (
	id INT NOT NULL AUTO_INCREMENT, 
    heroname VARCHAR(30),
    realname VARCHAR(30),
    creationyear INT, 
    cityid INT NOT NULL,
    PRIMARY KEY(id),	
    FOREIGN KEY (cityid) REFERENCES city(id)
);

CREATE TABLE superpower (
	id INT NOT NULL AUTO_INCREMENT, 
    powername VARCHAR(50),
    PRIMARY KEY(id)
);

CREATE TABLE superheropower(
	superheroid INT NOT NULL, 
    superpowerid INT NOT NULL,
	FOREIGN KEY (superheroid) REFERENCES superhero(id),
	FOREIGN KEY (superpowerid) REFERENCES superpower(id),
    PRIMARY KEY (superheroid, superpowerid)
);

INSERT INTO city (cityname) VALUES 
	('Gotham City'),
	('Metropolis'),
	('New York'),
	('Central City'),
	('New York'),
	('Peru'),
	('New York');

INSERT INTO superhero (heroname, realname, creationyear, cityid) VALUES
	('Batman', 'Bruce Wayne', 1950, 1),
	('Supermand', 'Clark Kent', 1938, 2),
	('Spiderman', 'Peter Parker', 1960, 3),
	('Flash', 'Barry Allen', 1962, 4),
	('Iron Man', 'Tony Starks', 1945, 5),
	('Wonder Woman', null, 1956, 6),
	('Doctor Strange', 'Stephen Strange', 1975, 7);

INSERT INTO superpower(powername) VALUES 
	('Martial arts'),
	('Lasereyes'),
	('Spiderpower'),
	('Superspeed'),
	('Techsuit'),
	('Lasso of Truth'),
	('Magic');

INSERT INTO superheropower(superheroid, superpowerid) VALUES 
	(1,1),
	(2,2),
	(3,3),
	(4,4),
	(5,5),
	(6,6),
	(7,7);
    
commit;
