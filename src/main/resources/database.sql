CREATE DATABASE IF NOT EXISTS Filmoteka DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

USE Filmoteka;

CREATE TABLE IF NOT EXISTS Movie (
    movie_id INT NOT NULL AUTO_INCREMENT,
    filmweb_id INT NOT NULL,
    foreign_title VARCHAR(200) NOT NULL,
    polish_title VARCHAR(200) NOT NULL,
    image_0 VARCHAR(200) NOT NULL,
    image_1 VARCHAR(200) NOT NULL,
    image_2 VARCHAR(200) NOT NULL,
    image_3 VARCHAR(200) NOT NULL,
    image_4 VARCHAR(200) NOT NULL,
    image_5 VARCHAR(200) NOT NULL,
    image_6 VARCHAR(200) NOT NULL,
    production_year VARCHAR(20) NOT NULL,
    cast VARCHAR(200) NOT NULL,
    duration VARCHAR(20) NOT NULL,
    production_country VARCHAR(200) NOT NULL,
    filmweb_genre VARCHAR(200) NOT NULL,
    description LONGTEXT NOT NULL,
    plot LONGTEXT NOT NULL,
    genre VARCHAR(50) NOT NULL,
    position_column INT NOT NULL,
    position_row INT NOT NULL,
    country_type VARCHAR(20) NOT NULL,
    PRIMARY KEY (movie_id)
);


CREATE TABLE IF NOT EXISTS Series (
    series_id INT NOT NULL AUTO_INCREMENT,
    filmweb_id INT NOT NULL,
    foreign_title VARCHAR(200) NOT NULL,
    polish_title VARCHAR(200) NOT NULL,
    image_0 VARCHAR(200) NOT NULL,
    image_1 VARCHAR(200) NOT NULL,
    image_2 VARCHAR(200) NOT NULL,
    image_3 VARCHAR(200) NOT NULL,
    image_4 VARCHAR(200) NOT NULL,
    image_5 VARCHAR(200) NOT NULL,
    image_6 VARCHAR(200) NOT NULL,
    production_year VARCHAR(20) NOT NULL,
    cast VARCHAR(200) NOT NULL,
    duration VARCHAR(20) NOT NULL,
    production_country VARCHAR(200) NOT NULL,
    filmweb_genre VARCHAR(200) NOT NULL,
    description LONGTEXT NOT NULL,
    plot LONGTEXT NOT NULL,
    genre VARCHAR(50) NOT NULL,
    position_column INT NOT NULL,
    position_row INT NOT NULL,
    country_type VARCHAR(20) NOT NULL,
    number_of_episodes VARCHAR(20) NOT NULL,
    number_of_seasons VARCHAR(20) NOT NULL,
    PRIMARY KEY (series_id)
);