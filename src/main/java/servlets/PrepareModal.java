package servlets;

import classes.Gallery;
import classes.Methods;
import classes.Modal;
import classes.Movie;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import info.talacha.filmweb.api.FilmwebApi;
import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.search.models.FilmSearchResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PrepareModal")
public class PrepareModal extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] clickedPhoto = request.getParameterValues("json[]");
        Long id = Long.parseLong(clickedPhoto[0]);
        String polishTitle = clickedPhoto[1];
        String type = clickedPhoto[2];
        FilmwebApi filmwebApi = new FilmwebApi();
        List<FilmSearchResult> filmList = filmwebApi.findFilm(polishTitle);
        List<FilmSearchResult> seriesList = filmwebApi.findSeries(polishTitle);
        Film movieInfo = new Film();
        info.talacha.filmweb.models.Series seriesInfo = new info.talacha.filmweb.models.Series();
        List<String> descriptionList = null;
        ArrayList<Modal> modal = new ArrayList<Modal>();
        //ArrayList<Modal> modalToSend = new ArrayList<Modal>();
        Modal modalToSend = new Modal();

        if(type.equals("Film")) {
            for (FilmSearchResult movie : filmList) {
                if (movie.getId().equals(id)) {
                    try {
                        movieInfo = filmwebApi.getFilmData(movie.getId());
                        descriptionList = filmwebApi.getDescriptions(movie.getId());
                    } catch (Exception e) {
                    }

                    modal.add(new Modal(id, polishTitle, movie.getImageURL(), String.valueOf(Methods.preventNullInteger(movie.getYear())),
                                        movie.getCast(), String.valueOf(Methods.preventNullInteger(movieInfo.getDuration())),
                                        movieInfo.getPlot(), type));


                    modalToSend = new Modal(id, polishTitle, movie.getTitle(), movie.getImageURL(), String.valueOf(Methods.preventNullInteger(movie.getYear())),
                                            movie.getCast(), String.valueOf(Methods.preventNullInteger(movieInfo.getDuration())), descriptionList,
                                            movieInfo.getPlot(), type, movieInfo.getCountries(), movieInfo.getGenre());
                }
            }
        }
        else if(type.equals("Serial")){
            for (FilmSearchResult series : seriesList) {
                if (series.getId().equals(id)) {
                    try {
                        seriesInfo = filmwebApi.getSeriesData(series.getId());
                        descriptionList = filmwebApi.getDescriptions(series.getId());
                    } catch (Exception e) {
                    }

                    modal.add(new Modal(id, polishTitle, series.getImageURL(), String.valueOf(Methods.preventNullInteger(series.getYear())),
                                        series.getCast(), String.valueOf(Methods.preventNullInteger(seriesInfo.getDuration())),
                                        seriesInfo.getPlot(), type));

                    modalToSend = new Modal(id, polishTitle, series.getTitle(), series.getImageURL(), String.valueOf(Methods.preventNullInteger(series.getYear())),
                            series.getCast(), String.valueOf(Methods.preventNullInteger(seriesInfo.getDuration())), descriptionList,
                            seriesInfo.getPlot(), type, seriesInfo.getCountries(), seriesInfo.getGenre(), String.valueOf(Methods.preventNullInteger(seriesInfo.getEpisodesCount())),
                            String.valueOf(Methods.preventNullInteger(seriesInfo.getSeasonsCount())));
                    /*
                    modalToSend.add(new Modal(id, polishTitle, series.getTitle(), series.getImageURL(), String.valueOf(Methods.preventNullInteger(series.getYear())),
                            series.getCast(), String.valueOf(Methods.preventNullInteger(seriesInfo.getDuration())), descriptionList,
                            seriesInfo.getPlot(), type, seriesInfo.getCountries(), seriesInfo.getGenre(), String.valueOf(Methods.preventNullInteger(seriesInfo.getEpisodesCount())),
                            String.valueOf(Methods.preventNullInteger(seriesInfo.getSeasonsCount()))));
                            */
                }
            }
        }

        /*
        String[] clickedPhoto = request.getParameterValues("json[]");
        ArrayList<Gallery> gallery = (ArrayList<Gallery>)request.getSession().getAttribute("gallery");
        Long id = Long.parseLong(clickedPhoto[0]);
        String polishTitle = clickedPhoto[1];
        FilmwebApi filmwebApi = new FilmwebApi();
        Film movieInfo = new Film();
        info.talacha.filmweb.models.Series seriesInfo = new info.talacha.filmweb.models.Series();
        List<String> descriptionList = null;
        List<FilmSearchResult> filmList;
        List<FilmSearchResult> seriesList;
        ArrayList<Modal> modal = new ArrayList<Modal>();
        Gallery galleryObject = new Gallery();

        for(Gallery object : gallery){
            if(id.equals(object.getFilmwebId())){
                galleryObject = object;
            }
        }

        if(galleryObject.getType().equals("Film")){
            filmList = filmwebApi.findFilm(galleryObject.getPolishTitle());
            for(FilmSearchResult movie : filmList){
                if(movie.getId().equals(id)){
                    try{
                        movieInfo = filmwebApi.getFilmData(galleryObject.getFilmwebId());
                        descriptionList = filmwebApi.getDescriptions(galleryObject.getFilmwebId());
                    }
                    catch (Exception e){ }

                    modal.add(new Modal(galleryObject.getFilmwebId(), galleryObject.getPolishTitle(), galleryObject.getImage_6(),
                            movie.getYear(), movie.getCast(), movieInfo.getDuration(), descriptionList,
                            movieInfo.getPlot(), galleryObject.getType()));
                    break;
                }
            }
        }
        else if(galleryObject.getType().equals("Serial")){
            seriesList = filmwebApi.findSeries(galleryObject.getPolishTitle());
            for(FilmSearchResult series : seriesList){
                if(series.getId().equals(id)){
                    try{
                        seriesInfo = filmwebApi.getSeriesData(galleryObject.getFilmwebId());
                        descriptionList = filmwebApi.getDescriptions(galleryObject.getFilmwebId());
                    }
                    catch (Exception e){ }

                    modal.add(new Modal(galleryObject.getFilmwebId(), galleryObject.getPolishTitle(), galleryObject.getImage_6(),
                            series.getYear(), series.getCast(), seriesInfo.getDuration(), descriptionList,
                            seriesInfo.getPlot(), galleryObject.getType()));
                    break;
                }
            }
        }
        */

        /*
        for(Gallery object : gallery){
            if(id.equals(object.getFilmwebId())){
                if(object.getType().equals("Film")){
                    filmList = filmwebApi.findFilm(object.getPolishTitle());
                    for(FilmSearchResult movie : filmList){
                        if(movie.getId().equals(id)){
                            try{
                                movieInfo = filmwebApi.getFilmData(object.getFilmwebId());
                                descriptionList = filmwebApi.getDescriptions(object.getFilmwebId());
                            }
                            catch (Exception e){ }

                            modal.add(new Modal(object.getFilmwebId(), object.getPolishTitle(), object.getImage_6(),
                                    movie.getYear(), movie.getCast(), movieInfo.getDuration(), descriptionList,
                                    movieInfo.getPlot(), object.getType()));
                            break;
                        }
                    }
                    break;
                }
                else if(object.getType().equals("Serial")){
                    seriesList = filmwebApi.findSeries(object.getPolishTitle());
                    for(FilmSearchResult series : seriesList){
                        if(series.getId().equals(id)){
                            try{
                                seriesInfo = filmwebApi.getSeriesData(object.getFilmwebId());
                                descriptionList = filmwebApi.getDescriptions(object.getFilmwebId());
                            }
                            catch (Exception e){ }

                            modal.add(new Modal(object.getFilmwebId(), object.getPolishTitle(), object.getImage_6(),
                                    series.getYear(), series.getCast(), seriesInfo.getDuration(), descriptionList,
                                    seriesInfo.getPlot(), object.getType()));
                            break;
                        }
                    }
                    break;
                }
            }
        }

        for(String tmp : descriptionList){
            System.out.println(tmp);
        }

        */

        request.getSession().setAttribute("modalToSend", modalToSend); // "modalToSend" - id po ktorym bede szukal w sesji

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(modal, new TypeToken<List<Modal>>() {}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonArray);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}