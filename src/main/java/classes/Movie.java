package classes;

public class Movie {
    private int id;
    private String originalTitle;
    private String polishTitle;
    private String smallPicture;
    private String bigPicture;
    //private String url;
    private String productionCountry;
    private String genre;
    private int duration;
    private int productionYear;

    /*-------- API --------
    ID
    Angielski tytuł
    Polski tytuł
    Zdjecie
    Rok produkcji
    Obsada


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

    public Movie(){

    }

    public Movie(String polishTitle, int productionYear, String productionCountry, String genre){
        this.setPolishTitle(polishTitle);
        this.setProductionYear(productionYear);
        this.setProductionCountry(productionCountry);
        this.setGenre(genre);
    }

    public Movie(int id, String originalTitle, String polishTitle, String smallPicture, String bigPicture, String productionCountry, String genre, int duration, int productionYear){
        this.setId(id);
        this.setOriginalTitle(originalTitle);
        this.setPolishTitle(polishTitle);
        this.setSmallPicture(smallPicture);
        this.setBigPicture(bigPicture);
        this.setProductionCountry(productionCountry);
        this.setGenre(genre);
        this.setDuration(duration);
        this.setProductionYear(productionYear);
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
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

    /*
    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return  url;
    }
    */

    public void setSmallPicture(String smallPicture) {
        this.smallPicture = smallPicture;
    }

    public String getSmallPicture() {
        return smallPicture;
    }

    public void setBigPicture(String bigPicture) {
        this.bigPicture = bigPicture;
    }

    public String getBigPicture() {
        return bigPicture;
    }

    public void setProductionCountry(String productionCountry){
        this.productionCountry = productionCountry;
    }

    public String getProductionCountry() {
        return productionCountry;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getProductionYear() {
        return productionYear;
    }
}