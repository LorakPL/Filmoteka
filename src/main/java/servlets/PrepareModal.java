package servlets;

import classes.Gallery;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PrepareModal")
public class PrepareModal extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] clickedPhoto = request.getParameterValues("json[]");
        ArrayList<Gallery> gallery = (ArrayList<Gallery>)request.getSession().getAttribute("gallery");
        Long id = Long.parseLong(clickedPhoto[0]);
        FilmwebApi filmwebApi = new FilmwebApi();
        Film movieInfo = new Film();
        info.talacha.filmweb.models.Series seriesInfo = new info.talacha.filmweb.models.Series();
        List<String> descriptionList = null;
        List<FilmSearchResult> filmList;
        List<FilmSearchResult> seriesList;
        ArrayList<Modal> modal = new ArrayList<Modal>();

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
