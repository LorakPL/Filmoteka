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
    private String originalTitle;
    private String productionCountry;
    private String filmwebGenre;
    private String numberOfEpisodes;
    private String numberOfSeasons;

    public Modal(Long filmwebId, String polishTitle, String originalTitle, String image,
                 String year, String cast, String duration, List<String> descriptionList,
                 String plot, String type, String productionCountry, String filmwebGenre){
        this.setFilmwebId(filmwebId);
        this.setPolishTitle(Methods.preventNull(polishTitle));
        this.setOriginalTitle(Methods.preventNull(originalTitle));
        this.setImage_6(Methods.changeImageSize(image, "6"));
        this.setYear(year);
        this.setCast(Methods.preventNull(cast));
        this.setDuration(duration);
        this.setDescriptionList(descriptionList);
        this.setPlot(Methods.preventNull(plot));
        this.setType(type);
        this.setProductionCountry(Methods.preventNull(productionCountry));
        this.setFilmwebGenre(Methods.preventNull(filmwebGenre));

    } // ------------- > Ten Modal bedzie castowany na Movie

    public Modal(Long filmwebId, String polishTitle, String originalTitle, String image,
                 String year, String cast, String duration, List<String> descriptionList,
                 String plot, String type, String productionCountry, String filmwebGenre,
                 String numberOfEpisodes, String numberOfSeasons){
        this.setFilmwebId(filmwebId);
        this.setPolishTitle(Methods.preventNull(polishTitle));
        this.setOriginalTitle(Methods.preventNull(originalTitle));
        this.setImage_6(Methods.changeImageSize(image, "6"));
        this.setYear(year);
        this.setCast(Methods.preventNull(cast));
        this.setDuration(duration);
        this.setDescriptionList(descriptionList);
        this.setPlot(Methods.preventNull(plot));
        this.setType(type);
        this.setProductionCountry(Methods.preventNull(productionCountry));
        this.setFilmwebGenre(Methods.preventNull(filmwebGenre));
        this.setNumberOfEpisodes(Methods.preventNull(numberOfEpisodes));
        this.setNumberOfSeasons(Methods.preventNull(numberOfSeasons));

    } // ------------- > Ten Modal bedzie castowany na Series


    public Modal(Long filmwebId, String polishTitle, String image, String year, String cast, String duration,
                 String plot, String type){
        this.setFilmwebId(filmwebId);
        this.setPolishTitle(Methods.preventNull(polishTitle));
        this.setImage_6(Methods.changeImageSize(image, "6" ));
        this.setYear(Methods.preventNull(year));
        this.setCast(Methods.preventNull(cast));
        this.setDuration(Methods.preventNull(duration));
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

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setProductionCountry(String productionCountry) {
        this.productionCountry = productionCountry;
    }

    public String getProductionCountry() {
        return productionCountry;
    }

    public void setFilmwebGenre(String filmwebGenre) {
        this.filmwebGenre = filmwebGenre;
    }

    public String getFilmwebGenre() {
        return filmwebGenre;
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
