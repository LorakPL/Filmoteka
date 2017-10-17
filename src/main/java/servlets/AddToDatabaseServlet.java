package servlets;

import classes.Methods;
import classes.Modal;
import classes.Series;
import classes.Movie;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddToDatabaseServlet")
public class AddToDatabaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] dataFromForm = request.getParameterValues("json[]");
        Modal modal = (Modal)request.getSession().getAttribute("modalToSend");
        int column = Integer.parseInt(dataFromForm[0]);
        int row = Integer.parseInt(dataFromForm[1]);
        String genre = dataFromForm[2];
        String country = dataFromForm[3];

        String description = String.join(" ", modal.getDescriptionList());
        String databaseResponse = "";
        int idCountMovies = 0;
        int idCountSeries = 0;

        try{
            SessionFactory sessionFactory;
            sessionFactory = new Configuration()
                    .configure() // configures settings from hibernate.cfg.xml
                    .buildSessionFactory();

            Session session = sessionFactory.openSession();

            Criteria criteria = session.createCriteria(Movie.class);
            criteria.add(Restrictions.eq("filmwebId", modal.getFilmwebId()));
            criteria.setProjection(Projections.rowCount());
            idCountMovies = ((Long)criteria.uniqueResult()).intValue();

            Criteria criteria2 = session.createCriteria(Series.class);
            criteria2.add(Restrictions.eq("filmwebId", modal.getFilmwebId()));
            criteria2.setProjection(Projections.rowCount());
            idCountSeries = ((Long)criteria2.uniqueResult()).intValue();

            if(idCountMovies != 0){
                databaseResponse = "Niestety taki film jest już w bazie";
            }
            else if(idCountSeries != 0){
                databaseResponse = "Niestety taki serial jest już w bazie";
            }
            else{
                String relativeWebPath = "/images";
                String home = getServletContext().getRealPath(relativeWebPath) + "/img/";
                if(modal.getType().equals("Film")){
                    Movie movie = new Movie(modal.getFilmwebId(), modal.getOriginalTitle(), modal.getPolishTitle(),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "0"),
                                    home + "image_0", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "1"),
                                    home + "image_1", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "2"),
                                    home + "image_2", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "3"),
                                    home + "image_3", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "4"),
                                    home + "image_4", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "5"),
                                    home + "image_5", modal.getFilmwebId()),
                            Methods.saveImage(modal.getImage_6(),
                                    home + "image_6", modal.getFilmwebId()), modal.getYear(), modal.getCast(),
                            modal.getDuration(), modal.getProductionCountry(), modal.getFilmwebGenre(), Methods.preventNull(description),
                            modal.getPlot(), genre, column, row, country);

                    Transaction tx = session.beginTransaction();
                    session.save(movie);
                    tx.commit();
                    session.close();
                    databaseResponse = "Film dodano do bazy";
                }
                else if(modal.getType().equals("Serial")){
                    Series series = new Series(modal.getFilmwebId(), modal.getOriginalTitle(), modal.getPolishTitle(),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "0"),
                                    home + "image_0", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "1"),
                                    home + "image_1", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "2"),
                                    home + "image_2", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "3"),
                                    home + "image_3", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "4"),
                                    home + "image_4", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "5"),
                                    home + "image_5", modal.getFilmwebId()),
                            Methods.saveImage(modal.getImage_6(),
                                    home + "image_6", modal.getFilmwebId()), modal.getYear(), modal.getCast(), modal.getDuration(), modal.getProductionCountry(),
                            modal.getFilmwebGenre(), Methods.preventNull(description), modal.getPlot(), genre, column, row, country,
                            modal.getNumberOfEpisodes(), modal.getNumberOfSeasons());

                    Transaction tx = session.beginTransaction();
                    session.save(series);
                    tx.commit();
                    session.close();
                    databaseResponse = "Serial dodano do bazy";
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            databaseResponse = "Niestety nie udało się dodać do bazy";
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(databaseResponse);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}