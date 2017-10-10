CREATE DATABASE IF NOT EXISTS Filmoteka;

USE Filmoteka;

CREATE TABLE IF NOT EXISTS Movie (
    movie_id INT NOT NULL AUTO_INCREMENT,
    filmweb_id INT NOT NULL,
    foreign_title VARCHAR(200) NOT NULL,
    polish_Title VARCHAR(200) NOT NULL,
    image_0 VARCHAR(200),
    image_1 VARCHAR(200),
    image_2 VARCHAR(200),
    image_3 VARCHAR(200),
    image_4 VARCHAR(200),
    image_5 VARCHAR(200),
    image_6 VARCHAR(200),
    production_year VARCHAR(20) NOT NULL,
    cast VARCHAR(200) NOT NULL,
    duration VARCHAR(20) NOT NULL,
    production_country VARCHAR(200) NOT NULL,
    filmweb_genre VARCHAR(200) NOT NULL,
    description LONGTEXT NOT NULL,
    plot LONGTEXT NOT NULL,
    genre VARCHAR(50),
    position_column INT,
    position_row INT,
    country_type VARCHAR(20),
    PRIMARY KEY (movie_id)
);


CREATE TABLE IF NOT EXISTS Series (
    series_id INT NOT NULL AUTO_INCREMENT,
    filmweb_id INT NOT NULL,
    foreign_title VARCHAR(200) NOT NULL,
    polish_Title VARCHAR(200) NOT NULL,
    image_0 VARCHAR(200),
    image_1 VARCHAR(200),
    image_2 VARCHAR(200),
    image_3 VARCHAR(200),
    image_4 VARCHAR(200),
    image_5 VARCHAR(200),
    image_6 VARCHAR(200),
    production_year VARCHAR(20) NOT NULL,
    cast VARCHAR(200) NOT NULL,
    duration VARCHAR(20) NOT NULL,
    production_country VARCHAR(200) NOT NULL,
    filmweb_genre VARCHAR(200) NOT NULL,
    description LONGTEXT NOT NULL,
    plot LONGTEXT NOT NULL,
    genre VARCHAR(50),
    position_column INT,
    position_row INT,
    country_type VARCHAR(20),
    number_of_episodes VARCHAR(20) NOT NULL,
    number_of_seasons VARCHAR(20) NOT NULL,
    PRIMARY KEY (series_id)
);