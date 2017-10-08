package servlets;

import classes.Gallery;
import classes.Movie;
import classes.Series;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import info.talacha.filmweb.api.FilmwebApi;
import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.search.models.FilmSearchResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilmwebApi filmwebApi = new FilmwebApi();
        String[] title = request.getParameterValues("json[]");

        List<FilmSearchResult> filmList = filmwebApi.findFilm(title[0]);
        List<FilmSearchResult> seriesList = filmwebApi.findSeries(title[0]);

        ArrayList<Gallery> moviesGallery = new ArrayList<Gallery>();
        ArrayList<Gallery> seriesGallery = new ArrayList<Gallery>();
        ArrayList<Gallery> gallery = new ArrayList<Gallery>(); // Lista elementow, ktore zostana wyswietlone w postaci galerii

        for(FilmSearchResult movie : filmList){
            moviesGallery.add(new Gallery(movie.getId(), changeImageSize(movie.getImageURL(), "6"), movie.getPolishTitle(), "Film"));
        }

        for(FilmSearchResult TVseries : seriesList){
            seriesGallery.add(new Gallery(TVseries.getId(), changeImageSize(TVseries.getImageURL(), "6"), TVseries.getPolishTitle(), "Serial"));
        }

        gallery.addAll(moviesGallery);
        gallery.addAll(seriesGallery);

        request.getSession().setAttribute("gallery", gallery); // "gallery" - id po ktorym bede szukal w sesji

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(gallery, new TypeToken<List<Gallery>>() {}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonArray);

        /*


        FilmwebApi filmwebApi = new FilmwebApi();
        String[] title = request.getParameterValues("json[]");

        List<FilmSearchResult> filmList = filmwebApi.findFilm(title[0]);
        List<FilmSearchResult> seriesList = filmwebApi.findSeries(title[0]);

        ArrayList<Gallery> moviesGallery = new ArrayList<Gallery>();
        ArrayList<Gallery> seriesGallery = new ArrayList<Gallery>();
        ArrayList<Gallery> gallery = new ArrayList<Gallery>(); // Lista elementow, ktore zostana wyswietlone w postaci galerii
        Film movieInfo = new Film();
        info.talacha.filmweb.models.Series seriesInfo = new info.talacha.filmweb.models.Series();
        List<String> descriptionList = null;
        ArrayList<Movie> movies = new ArrayList<Movie>();
        ArrayList<classes.Series> series = new ArrayList<classes.Series>();


        // ----------- Pobieram liste filmow, ktore pasuja do wpisanego tytulu -----------
        for(FilmSearchResult movie : filmList){
            try{
                movieInfo = filmwebApi.getFilmData(movie.getId());
                descriptionList = filmwebApi.getDescriptions(movie.getId());
            }
            catch (Exception e){ }

            movies.add(new Movie(1, movie.getId(), movie.getTitle(), movie.getPolishTitle(),
                        changeImageSize(movie.getImageURL(), "0"), changeImageSize(movie.getImageURL(), "1"),
                        changeImageSize(movie.getImageURL(), "2"), changeImageSize(movie.getImageURL(), "3"),
                        changeImageSize(movie.getImageURL(), "4"), changeImageSize(movie.getImageURL(), "5"),
                        changeImageSize(movie.getImageURL(), "6"), movie.getYear(), movie.getCast(), movieInfo.getDuration(),
                        movieInfo.getCountries(), movieInfo.getGenre(), descriptionList, movieInfo.getPlot()));

            moviesGallery.add(new Gallery(movie.getId(), changeImageSize(movie.getImageURL(), "6")));
        }

        // ----------- Pobieram liste seriali, ktore pasuja do wpisanego tytulu -----------
        for(FilmSearchResult TVseries : seriesList){
            try{
                seriesInfo = filmwebApi.getSeriesData(TVseries.getId());
                descriptionList = filmwebApi.getDescriptions(TVseries.getId());
            }
            catch (Exception e){ }

            series.add(new classes.Series(1, TVseries.getId(), TVseries.getTitle(), TVseries.getPolishTitle(),
                    changeImageSize(TVseries.getImageURL(), "0"), changeImageSize(TVseries.getImageURL(), "1"),
                    changeImageSize(TVseries.getImageURL(), "2"), changeImageSize(TVseries.getImageURL(), "3"),
                    changeImageSize(TVseries.getImageURL(), "4"), changeImageSize(TVseries.getImageURL(), "5"),
                    changeImageSize(TVseries.getImageURL(), "6"), TVseries.getYear(), TVseries.getCast(),
                    seriesInfo.getDuration(), seriesInfo.getCountries(), seriesInfo.getGenre(), descriptionList, seriesInfo.getPlot(),
                    seriesInfo.getEpisodesCount(), seriesInfo.getSeasonsCount()));

            seriesGallery.add(new Gallery(TVseries.getId(), changeImageSize(TVseries.getImageURL(), "6")));
        }

        // ----------- Lacze uzyskane listy w 2, gallery zostanie zwrocone jako galeria okladek, natomiast moviesAndSeries zostana przeslane do AddToDatabase -----------

        gallery.addAll(moviesGallery);
        gallery.addAll(seriesGallery);

        request.getSession().setAttribute("movies", movies); // "movies" - id po ktorym bede szukal w sesji
        request.getSession().setAttribute("series", series);

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(gallery, new TypeToken<List<Gallery>>() {}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonArray);




        */

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private String changeImageSize(String url, String imageSize){
        String image;
        String[] imageParts;

        imageParts = url.split("[.]");
        imageParts[imageParts.length - 2] = imageSize;
        image = imageParts[0];

        for(int i = 0; i < imageParts.length - 1; i++){
            image += "." + imageParts[i + 1];
        }

        return image;
    }
}