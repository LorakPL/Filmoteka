package classes;

public class Gallery {
    private Long filmwebId;
    //private String cast;
    //private String title;
    private String polishTitle;
    //private int year;
    //private String bigPicture;
    //private String smallPicture;
    private String image_6;
    private String type;

    //public Gallery(){};

    public  Gallery(Long id, String image_6, String polishTitle, String type){
        this.setFilmwebId(id);
        this.setImage_6(image_6);
        this.setPolishTitle(polishTitle);
        this.setType(type);
    }

    /*
    public Gallery(Long filmwebId, String cast, String title, String polishTitle, int year, String bigPicture, String smallPicture, String type){
        this.filmwebId = filmwebId;
        this.cast = cast;
        this.title = title;
        this.polishTitle = polishTitle;
        this.year = year;
        this.bigPicture = bigPicture;
        this.smallPicture = smallPicture;
        this.type = type;
    }
    */

    public void setFilmwebId(Long filmwebId){
        this.filmwebId = filmwebId;
    }

    public Long getFilmwebId(){
        return filmwebId;
    }

    /*

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCast() {
        return cast;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    */

    public void setPolishTitle(String polishTitle) {
        this.polishTitle = polishTitle;
    }

    public String getPolishTitle() {
        return polishTitle;
    }

    /*

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setBigPicture(String bigPicture) {
        this.bigPicture = bigPicture;
    }

    public String getBigPicture() {
        return bigPicture;
    }

    public void setSmallPicture(String smallPicture) {
        this.smallPicture = smallPicture;
    }

    public String getSmallPicture() {
        return smallPicture;
    }

    */

    public void setImage_6(String image_6) {
        this.image_6 = image_6;
    }

    public String getImage_6() {
        return image_6;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}