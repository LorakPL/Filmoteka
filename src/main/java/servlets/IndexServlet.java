package servlets;

import classes.Movie;
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
    static class CustomPage {
        private String title;
        private String content;
        private CustomPage(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        String jsonString = request.getParameter("pageData");


        JsonParser parser = new JsonParser();
        JsonElement tradeElement = parser.parse(jsonString);
        JsonArray json = tradeElement.getAsJsonArray();


        Gson gson1 = new Gson();
        Type collectionType = new TypeToken<List<CustomPage>>(){}.getType();
        List<CustomPage> jsonNodes = gson1.fromJson(json, collectionType);

        Iterator<CustomPage> iterator = jsonNodes.iterator();
        while(iterator.hasNext()){
            CustomPage node = (CustomPage) iterator.next();

            System.out.println("Title: " + node.title);
            System.out.println("Content: " + node.content);

        }

        */

        /*
        String[] myJsonData = request.getParameterValues("json[]");

        System.out.println(myJsonData[0]);
        System.out.println(myJsonData[1]);
        System.out.println(myJsonData[2]);
        System.out.println(myJsonData[3]);
*/

        FilmwebApi filmwebApi = new FilmwebApi();
        String[] movieTitle = request.getParameterValues("json[]");
        List<FilmSearchResult> filmInfoList = filmwebApi.findFilm(movieTitle[0]);
        ArrayList<Movie> movies = new ArrayList<Movie>();
        String bigPicture;
        Film movieInfo = new Film();

        for(FilmSearchResult movie : filmInfoList){

            try{
                movieInfo = filmwebApi.getFilmData(movie.getId());
                System.out.println(movie.getImageURL());
            }
            catch (Exception e){ }

            bigPicture = movie.getImageURL();
            String[] bigPictureParts = bigPicture.split("[.]");
            bigPictureParts[bigPictureParts.length - 2] = "6";
            bigPicture = bigPictureParts[0];

            for(int i = 0; i < bigPictureParts.length-1; i++){
                bigPicture +=  "." + bigPictureParts[i+1];
            }

            movies.add(new Movie(movie.getId().intValue(), movie.getTitle(), movie.getPolishTitle(), movie.getImageURL(), bigPicture, movieInfo.getCountries(), movieInfo.getGenre(), movieInfo.getDuration(), movie.getYear()));
        }

        //movies.add(new Movie(1, "test", "test", "http://1.fwcdn.pl/po/25/73/712573/7756329.4.jpg", "http://1.fwcdn.pl/po/25/73/712573/7756329.3.jpg", "test", "test", 1, 1));
        //movies.add(new Movie(1, "test", "test", "http://1.fwcdn.pl/po/25/73/712573/7756329.4.jpg", "http://1.fwcdn.pl/po/25/73/712573/7756329.3.jpg", "test", "test", 1, 1));

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(movies, new TypeToken<List<Movie>>() {}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonArray);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}