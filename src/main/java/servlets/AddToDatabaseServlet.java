package servlets;

import classes.Modal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/AddToDatabaseServlet")
public class AddToDatabaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] dataFromForm = request.getParameterValues("json[]");
        ArrayList<Modal> modal = (ArrayList<Modal>)request.getSession().getAttribute("modalToSend");

        String opis = "";

        for(Modal tmp : modal){
            System.out.println(tmp.getFilmwebId());
            System.out.println(tmp.getPolishTitle());
            System.out.println(tmp.getOriginalTitle());
            System.out.println(tmp.getImage_6());
            System.out.println(tmp.getYear());
            System.out.println(tmp.getCast());
            System.out.println(tmp.getDuration());
            System.out.println(tmp.getPlot());
            System.out.println(tmp.getType());
            System.out.println(tmp.getProductionCountry());
            System.out.println(tmp.getFilmwebGenre());

            for(String tmp2 : tmp.getDescriptionList()){
                opis += tmp2;
                opis += " ";
            }

            System.out.println("-----------------------");
            System.out.println(opis);
        }


        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Działa");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
