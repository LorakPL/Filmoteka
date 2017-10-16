package classes;

public class Gallery {
    private Long filmwebId;
    private String polishTitle;
    private String image_6;
    private String type;

    public Gallery(){}

    public  Gallery(Long filmwebId, String image, String polishTitle, String type){
        this.setFilmwebId(filmwebId);
        this.setImage_6(Methods.changeImageSize(image, "6"));
        this.setPolishTitle(Methods.preventNull(polishTitle));
        this.setType(type);
    }

    public void setFilmwebId(Long filmwebId){
        this.filmwebId = filmwebId;
    }

    public Long getFilmwebId(){
        return filmwebId;
    }

    public void setPolishTitle(String polishTitle) {
        this.polishTitle = polishTitle;
    }

    public String getPolishTitle() {
        return polishTitle;
    }

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