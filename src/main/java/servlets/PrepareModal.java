package servlets;

import classes.Gallery;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import info.talacha.filmweb.api.FilmwebApi;

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
        ArrayList<Gallery> moviesAndSeries = (ArrayList<Gallery>)request.getSession().getAttribute("moviesAndSeries");
        ArrayList<Gallery> modal = new ArrayList<Gallery>();
        Long id = Long.parseLong(clickedPhoto[0]);

        for(Gallery tmp : moviesAndSeries){
            if(id.equals(tmp.getId())){
                modal.add(tmp);
            }
        }

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(modal, new TypeToken<List<Gallery>>() {}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonArray);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
