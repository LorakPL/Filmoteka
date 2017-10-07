package classes;

public class Gallery {
    private Long id;
    private String cast;
    private String title;
    private String polishTitle;
    private int year;
    private String bigPicture;
    private String smallPicture;
    private String type;

    public Gallery(){};

    public  Gallery(Long id, String smallPicture){
        this.id = id;
        this.smallPicture = smallPicture;
    }

    public Gallery(Long id, String cast, String title, String polishTitle, int year, String bigPicture, String smallPicture, String type){
        this.id = id;
        this.cast = cast;
        this.title = title;
        this.polishTitle = polishTitle;
        this.year = year;
        this.bigPicture = bigPicture;
        this.smallPicture = smallPicture;
        this.type = type;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

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

    public void setPolishTitle(String polishTitle) {
        this.polishTitle = polishTitle;
    }

    public String getPolishTitle() {
        return polishTitle;
    }

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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}