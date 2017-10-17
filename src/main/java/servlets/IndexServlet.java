package servlets;

import classes.Gallery;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import info.talacha.filmweb.api.FilmwebApi;
import info.talacha.filmweb.search.models.FilmSearchResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
        ArrayList<Gallery> gallery = new ArrayList<Gallery>(); // Gallery elements

        for(FilmSearchResult movie : filmList){
            moviesGallery.add(new Gallery(movie.getId(), movie.getImageURL(), movie.getPolishTitle(), "Film"));
        }

        for(FilmSearchResult TVseries : seriesList){
            seriesGallery.add(new Gallery(TVseries.getId(), TVseries.getImageURL(), TVseries.getPolishTitle(), "Serial"));
        }

        gallery.addAll(moviesGallery);
        gallery.addAll(seriesGallery);

        request.getSession().setAttribute("gallery", gallery); // "gallery" - id for session

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