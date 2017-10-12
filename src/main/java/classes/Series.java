package classes;

import java.util.List;

public class Series {
    /*-------- API --------
    ID
    Angielski tytuł
    Polski tytuł
    Zdjecie 0-6
    Rok produkcji
    Obsada



    Liczba sezonow
    Liczba odcinkow
    Czas trwania
    Kraje produkcji

    Gatunek
    Krótki opis
    */

    /*-------- Informacje od Ojca --------

    Gatunek ???????
    Regał/Rząd
    Kraj produkcji ????????

    */

    private int id; //ogarnac jak to jest zrobione w IDF, bo tu ma byc autoIncrement
    private Long filmwebId;
    private String originalTitle;
    private String polishTitle;
    private String image_0;
    private String image_1;
    private String image_2;
    private String image_3;
    private String image_4;
    private String image_5;
    private String image_6;
    private String year;
    private String cast;
    private String duration;
    private String productionCountry;
    private String filmwebGenre;
    private String description;
    private String plot;
    private String genre;
    private int column;
    private int row;
    private String countryType; //Polish or foreign
    private String numberOfEpisodes;
    private String numberOfSeasons;

    public Series(){}

    public Series (int id, Long filmwebId, String originalTitle, String polishTitle,
                   String image_0, String image_1, String image_2, String image_3,
                   String image_4, String image_5, String image_6, String year, String cast,
                   String duration, String productionCountry, String filmwebGenre, String description,
                   String plot, String numberOfEpisodes, String numberOfSeasons){
        this.setId(id);
        this.setFilmwebId(filmwebId);
        this.setOriginalTitle(originalTitle);
        this.setPolishTitle(polishTitle);
        this.setImage_0(image_0);
        this.setImage_1(image_1);
        this.setImage_2(image_2);
        this.setImage_3(image_3);
        this.setImage_4(image_4);
        this.setImage_5(image_5);
        this.setImage_6(image_6);
        this.setYear(year);
        this.setCast(cast);
        this.setDuration(duration);
        this.setProductionCountry(productionCountry);
        this.setFilmwebGenre(filmwebGenre);
        this.setDescription(description);
        this.setPlot(plot);
        this.setNumberOfEpisodes(numberOfEpisodes);
        this.setNumberOfSeasons(numberOfSeasons);
    }

    public Series (Long filmwebId, String originalTitle, String polishTitle,
                  String image_0, String image_1, String image_2, String image_3,
                  String image_4, String image_5, String image_6, String year, String cast,
                  String duration, String productionCountry, String filmwebGenre, String description,
                  String plot, String genre, int column, int row, String countryType, String numberOfEpisodes, String numberOfSeasons){
        this.setFilmwebId(filmwebId);
        this.setOriginalTitle(originalTitle);
        this.setPolishTitle(polishTitle);
        this.setImage_0(image_0);
        this.setImage_1(image_1);
        this.setImage_2(image_2);
        this.setImage_3(image_3);
        this.setImage_4(image_4);
        this.setImage_5(image_5);
        this.setImage_6(image_6);
        this.setYear(year);
        this.setCast(cast);
        this.setDuration(duration);
        this.setPlot(plot);
        this.setProductionCountry(productionCountry);
        this.setFilmwebGenre(filmwebGenre);
        this.setDescription(description);
        this.setGenre(genre);
        this.setColumn(column);
        this.setRow(row);
        this.setCountryType(countryType);
        this.setNumberOfEpisodes(numberOfEpisodes);
        this.setNumberOfSeasons(numberOfSeasons);
    }

    public Series (int id, Long filmwebId, String originalTitle, String polishTitle,
                   String image_0, String image_1, String image_2, String image_3,
                   String image_4, String image_5, String image_6, String year, String cast,
                   String duration, String productionCountry, String filmwebGenre, String description,
                   String plot, String genre, int column, int row, String countryType, String numberOfEpisodes, String numberOfSeasons){
        this.setId(id);
        this.setFilmwebId(filmwebId);
        this.setOriginalTitle(originalTitle);
        this.setPolishTitle(polishTitle);
        this.setImage_0(image_0);
        this.setImage_1(image_1);
        this.setImage_2(image_2);
        this.setImage_3(image_3);
        this.setImage_4(image_4);
        this.setImage_5(image_5);
        this.setImage_6(image_6);
        this.setYear(year);
        this.setCast(cast);
        this.setDuration(duration);
        this.setPlot(plot);
        this.setProductionCountry(productionCountry);
        this.setFilmwebGenre(filmwebGenre);
        this.setDescription(description);
        this.setGenre(genre);
        this.setColumn(column);
        this.setRow(row);
        this.setCountryType(countryType);
        this.setNumberOfEpisodes(numberOfEpisodes);
        this.setNumberOfSeasons(numberOfSeasons);
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setFilmwebId(Long filmwebId) {
        this.filmwebId = filmwebId;
    }

    public Long getFilmwebId() {
        return filmwebId;
    }

    public void setOriginalTitle(String originalTitle){
        this.originalTitle = originalTitle;
    }

    public String getOriginalTitle(){
        return originalTitle;
    }

    public void setPolishTitle(String polishTitle){
        this.polishTitle = polishTitle;
    }

    public String getPolishTitle(){
        return polishTitle;
    }

    public void setImage_0(String image_0) {
        this.image_0 = image_0;
    }

    public String getImage_0() {
        return image_0;
    }

    public void setImage_1(String image_1) {
        this.image_1 = image_1;
    }

    public String getImage_1() {
        return image_1;
    }

    public void setImage_2(String image_2) {
        this.image_2 = image_2;
    }

    public String getImage_2() {
        return image_2;
    }

    public void setImage_3(String image_3){
        this.image_3 = image_3;
    }

    public String getImage_3(){
        return image_3;
    }

    public void setImage_4(String image_4){
        this.image_4 = image_4;
    }

    public String getImage_4(){
        return image_4;
    }

    public void setImage_5(String image_5){
        this.image_5 = image_5;
    }

    public String getImage_5(){
        return image_5;
    }

    public void setImage_6(String image_6){
        this.image_6 = image_6;
    }

    public String getImage_6(){
        return image_6;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setCast(String cast){
        this.cast = cast;
    }

    public String getCast(){
        return cast;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setProductionCountry(String productionCountry){
        this.productionCountry = productionCountry;
    }

    public String getProductionCountry() {
        return productionCountry;
    }

    public void setFilmwebGenre(String filmwebGenre){
        this.filmwebGenre = filmwebGenre;
    }

    public String getFilmwebGenre(){
        return filmwebGenre;
    }

    public void setDescription(String _description){
        this.description = _description;
    }

    public String getDescription(){
        return this.description;
    }

    /*
    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
    */

    public void setPlot(String plot){
        this.plot = plot;
    }

    public String getPlot() {
        return plot;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setColumn(int column){
        this.column = column;
    }

    public int getColumn(){
        return column;
    }

    public void setRow(int row){
        this.row = row;
    }

    public int getRow(){
        return row;
    }

    public void setCountryType(String countryType){
        this.countryType = countryType;
    }

    public String getCountryType(){
        return countryType;
    }

    public void setNumberOfEpisodes(String numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public String getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfSeasons(String numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getNumberOfSeasons() {
        return numberOfSeasons;
    }
}
