package servlets;

import classes.Gallery;
import classes.Movie;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import info.talacha.filmweb.api.FilmwebApi;
import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.models.Series;
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

        ArrayList<Gallery> movies = new ArrayList<Gallery>();
        ArrayList<Gallery> series = new ArrayList<Gallery>();
        ArrayList<Gallery> moviesGallery = new ArrayList<Gallery>();
        ArrayList<Gallery> seriesGallery = new ArrayList<Gallery>();
        ArrayList<Gallery> gallery = new ArrayList<Gallery>(); // Lista elementow, ktore zostana wyswietlone w postaci galerii
        ArrayList<Gallery> moviesAndSeries = new ArrayList<Gallery>(); // Lista elementow, ktore zostana przeslane do AddToDatabase
        String bigPicture;

        // ----------- Pobieram liste filmow, ktore pasuja do wpisanego tytulu -----------
        for(FilmSearchResult movie : filmList){
            bigPicture = movie.getImageURL();
            String[] bigPictureParts = bigPicture.split("[.]");
            bigPictureParts[bigPictureParts.length - 2] = "6";
            bigPicture = bigPictureParts[0];

            for(int i = 0; i < bigPictureParts.length-1; i++){
                bigPicture +=  "." + bigPictureParts[i+1];
            }

            movies.add(new Gallery(movie.getId(), movie.getCast(), movie.getTitle(), movie.getPolishTitle(), movie.getYear(), bigPicture, movie.getImageURL(), "Film"));
            moviesGallery.add(new Gallery(movie.getId(), bigPicture));
        }

        // ----------- Pobieram liste seriali, ktore pasuja do wpisanego tytulu -----------
        for(FilmSearchResult TVseries : seriesList){
            bigPicture = TVseries.getImageURL();
            String[] bigPictureParts = bigPicture.split("[.]");
            bigPictureParts[bigPictureParts.length - 2] = "6";
            bigPicture = bigPictureParts[0];

            for(int i = 0; i < bigPictureParts.length-1; i++){
                bigPicture +=  "." + bigPictureParts[i+1];
            }

            series.add(new Gallery(TVseries.getId(), TVseries.getCast(), TVseries.getTitle(), TVseries.getPolishTitle(), TVseries.getYear(), bigPicture, TVseries.getImageURL(), "Serial"));
            seriesGallery.add(new Gallery(TVseries.getId(), bigPicture));
        }

        // ----------- Lacze uzyskane listy w 2, gallery zostanie zwrocone jako galeria okladek, natomiast moviesAndSeries zostana przeslane do AddToDatabase -----------

        gallery.addAll(moviesGallery);
        gallery.addAll(seriesGallery);

        moviesAndSeries.addAll(movies);
        moviesAndSeries.addAll(series);

        String arrayID = "moviesAndSeries";
        request.getSession().setAttribute(arrayID, moviesAndSeries);

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(gallery, new TypeToken<List<Gallery>>() {}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonArray);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}