package classes;

import java.util.List;

public class Modal {
    private Long filmwebId;
    private String polishTitle;
    private String image_6;
    private String year;
    private String cast;
    private String duration;
    private List<String> descriptionList;
    private String plot;
    private String type;

    public Modal(Long filmwebId, String polishTitle, String image, String year, String cast, String duration,
                 List<String> descriptionList, String plot, String type){
        this.setFilmwebId(filmwebId);
        this.setPolishTitle(Methods.preventNull(polishTitle));
        this.setImage_6(Methods.changeImageSize(image, "6" ));
        this.setYear(Methods.preventNull(year));
        this.setCast(Methods.preventNull(cast));
        this.setDuration(Methods.preventNull(duration));
        this.setDescriptionList(descriptionList);
        this.setPlot(Methods.preventNull(plot));
        this.setType(type);
    }

    public void setFilmwebId(Long filmwebId) {
        this.filmwebId = filmwebId;
    }

    public Long getFilmwebId() {
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

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear(){
        return year;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCast() {
        return cast;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDescriptionList(List<String> descriptionList) {
        this.descriptionList = descriptionList;
    }

    public List<String> getDescriptionList() {
        return descriptionList;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPlot() {
        return plot;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
