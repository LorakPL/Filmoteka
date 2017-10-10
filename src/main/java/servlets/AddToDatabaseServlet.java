package servlets;

import classes.Modal;
import classes.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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

            opis = String.join(" ", tmp.getDescriptionList());

            System.out.println("-----------------------");
            System.out.println(opis);
            if("Film".equals(tmp.getType())) {   //Change "Film" to constant
                Movie movie = new Movie();
                movie.setFilmwebId(tmp.getFilmwebId());
                movie.setOriginalTitle(tmp.getOriginalTitle());
                movie.setPolishTitle(tmp.getPolishTitle());
                movie.setImage_6(tmp.getImage_6()); //add set another images
                movie.setYear(tmp.getYear());
                movie.setCast(tmp.getCast());
                movie.setDuration(tmp.getDuration());
                movie.setProductionCountry(tmp.getProductionCountry());
                movie.setFilmwebGenre(tmp.getFilmwebGenre());
                movie.setDescription(opis);
                movie.setPlot(tmp.getPlot());
                //movie.setGenre(tmp.getGenre()); //tmp.getGenre() not implemented yet
                //movie.setColumn(tmp.getColumn()); //like above
                //movie.setRow(tmp.getRow()) //like above
                //movie.setCountryType(tmp.getCountryType()); //like above
                SessionFactory sessionFactory;
                sessionFactory = new Configuration()
                        .configure() // configures settings from hibernate.cfg.xml
                        .buildSessionFactory();

                Session session = sessionFactory.openSession();
                Transaction tx = session.beginTransaction();
                session.save(movie);
                tx.commit();
                session.close();
            } else if ("Serial".equals(tmp.getType())) {

            }
        }


        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Działa");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
