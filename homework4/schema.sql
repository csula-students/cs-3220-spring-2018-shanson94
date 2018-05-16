CREATE TABLE generators (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	description TEXT NULL,
	rate INTEGER NULL,
	base_cost INTEGER NULL,
	unlock_at INTEGER NULL
);


CREATE TABLE events (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	description TEXT NULL,
	trigger_at INTEGER NULL
);

INSERT INTO generators (id, name, description, rate, base_cost, unlock_at) VALUES 
(1, "Pickaxe", "It mines ore", 5, 10, 0), 
(2, "Goblin", "Goblin miner to help mine ore", 10, 50, 50), 
(3, "Machine Miner", "Machine built by goblins to mine ore", 50, 500, 500);


INSERT INTO events (id, name, description, trigger_at) VALUES 
(1, "Pickaxe", "You bought a Pickaxe.", 5), 
(2, "Goblin shows up", "They came to mine ore.", 50),
(3, "Machine Miner", "Goblins made you this.", 500);