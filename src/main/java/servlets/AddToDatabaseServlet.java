package servlets;

import classes.Methods;
import classes.Modal;
import classes.Series;
import classes.Movie;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

@WebServlet("/AddToDatabaseServlet")
public class AddToDatabaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] dataFromForm = request.getParameterValues("json[]");
        //ArrayList<Modal> modal = (ArrayList<Modal>)request.getSession().getAttribute("modalToSend");
        Modal modal = (Modal)request.getSession().getAttribute("modalToSend");
        int column = Integer.parseInt(dataFromForm[0]);
        int row = Integer.parseInt(dataFromForm[1]);
        String genre = dataFromForm[2];
        String country = dataFromForm[3];

        String description = String.join(" ", modal.getDescriptionList());
        String databaseResponse = "";
        int positionCountMovies = 0;
        int positionCountSeries = 0;
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

            Criteria criteria3 = session.createCriteria(Movie.class);
            criteria3.add(Restrictions.eq("column", column));
            criteria3.add(Restrictions.eq("row", row));
            criteria3.setProjection(Projections.rowCount());
            positionCountMovies = ((Long)criteria3.uniqueResult()).intValue();

            Criteria criteria4 = session.createCriteria(Series.class);
            criteria4.add(Restrictions.eq("column", column));
            criteria4.add(Restrictions.eq("row", row));
            criteria4.setProjection(Projections.rowCount());
            positionCountSeries = ((Long)criteria4.uniqueResult()).intValue();

            if(idCountMovies != 0){
                databaseResponse = "Niestety taki film jest już w bazie";
            }
            else if(idCountSeries != 0){
                databaseResponse = "Niestety taki serial jest już w bazie";
            }
            else if(positionCountMovies != 0 || positionCountSeries != 0){
                databaseResponse = "Niestety jakaś inna pozycja znajduje się już na tym miejscu";
            }
            else{
                if(modal.getType().equals("Film")){
                    Movie movie = new Movie(modal.getFilmwebId(), modal.getOriginalTitle(), modal.getPolishTitle(),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "0"), "image_0", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "1"), "image_1", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "2"), "image_2", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "3"), "image_3", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "4"), "image_4", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "5"), "image_5", modal.getFilmwebId()),
                            Methods.saveImage(modal.getImage_6(), "image_6", modal.getFilmwebId()), modal.getYear(), modal.getCast(),
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
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "0"), "image_0", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "1"), "image_1", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "2"), "image_2", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "3"), "image_3", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "4"), "image_4", modal.getFilmwebId()),
                            Methods.saveImage(Methods.changeImageSize(modal.getImage_6(), "5"), "image_5", modal.getFilmwebId()),
                            Methods.saveImage(modal.getImage_6(), "image_6", modal.getFilmwebId()), modal.getYear(), modal.getCast(), modal.getDuration(), modal.getProductionCountry(),
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


        /*
        if(modal.getType().equals("Film")){
            try{
                SessionFactory sessionFactory;
                sessionFactory = new Configuration()
                        .configure() // configures settings from hibernate.cfg.xml
                        .buildSessionFactory();

                Session session = sessionFactory.openSession();
                Criteria criteria = session.createCriteria(Movie.class);
                criteria.add(Restrictions.eq("filmwebId", modal.getFilmwebId()));
                criteria.setProjection(Projections.rowCount());
                count = ((Long)criteria.uniqueResult()).intValue();

                if(count == 0){
                    Criteria criteria2 = session.createCriteria(Movie.class);
                    criteria2.add(Restrictions.eq("column", column));
                    criteria2.add(Restrictions.eq("row", row));
                    criteria2.setProjection(Projections.rowCount());
                    count = ((Long)criteria2.uniqueResult()).intValue();
                    //session.getTransaction().commit();
                    if(count == 0){
                        Movie movie = new Movie(modal.getFilmwebId(), modal.getOriginalTitle(), modal.getPolishTitle(),
                                Methods.changeImageSize(modal.getImage_6(), "0"), Methods.changeImageSize(modal.getImage_6(), "1"),
                                Methods.changeImageSize(modal.getImage_6(), "2"), Methods.changeImageSize(modal.getImage_6(), "3"),
                                Methods.changeImageSize(modal.getImage_6(), "4"), Methods.changeImageSize(modal.getImage_6(), "5"),
                                modal.getImage_6(), modal.getYear(), modal.getCast(), modal.getDuration(), modal.getProductionCountry(),
                                modal.getFilmwebGenre(), Methods.preventNull(description), modal.getPlot(), genre, column, row, country);

                        Transaction tx = session.beginTransaction();
                        session.save(movie);
                        tx.commit();
                        session.close();
                        databaseResponse = "Film dodano do bazy";
                    }
                    else{
                        databaseResponse = "Niestety jakiś inny film znajduje się na tej pozycji";
                        session.close();
                    }
                }
                else{
                    databaseResponse = "Niestety taki film jest już w bazie";
                    session.close();
                }
            }
            catch(Exception e){
                e.printStackTrace();
                databaseResponse = "Niestety nie udało się dodać filmu do bazy";
            }
        }
        else if(modal.getType().equals("Serial")){
            try{
                SessionFactory sessionFactory;
                sessionFactory = new Configuration()
                        .configure() // configures settings from hibernate.cfg.xml
                        .buildSessionFactory();

                Session session = sessionFactory.openSession();
                Criteria criteria = session.createCriteria(Series.class);
                criteria.add(Restrictions.eq("filmwebId", modal.getFilmwebId()));
                criteria.setProjection(Projections.rowCount());
                count = ((Long)criteria.uniqueResult()).intValue();

                if(count == 0){
                    Criteria criteria2 = session.createCriteria(Series.class);
                    criteria2.add(Restrictions.eq("column", column));
                    criteria2.add(Restrictions.eq("row", row));
                    criteria2.setProjection(Projections.rowCount());
                    count = ((Long)criteria2.uniqueResult()).intValue();
                    if(count == 0){
                        Series series = new Series(modal.getFilmwebId(), modal.getOriginalTitle(), modal.getPolishTitle(),
                                Methods.changeImageSize(modal.getImage_6(), "0"), Methods.changeImageSize(modal.getImage_6(), "1"),
                                Methods.changeImageSize(modal.getImage_6(), "2"), Methods.changeImageSize(modal.getImage_6(), "3"),
                                Methods.changeImageSize(modal.getImage_6(), "4"), Methods.changeImageSize(modal.getImage_6(), "5"),
                                modal.getImage_6(), modal.getYear(), modal.getCast(), modal.getDuration(), modal.getProductionCountry(),
                                modal.getFilmwebGenre(), Methods.preventNull(description), modal.getPlot(), genre, column, row, country,
                                modal.getNumberOfEpisodes(), modal.getNumberOfSeasons());

                        Transaction tx = session.beginTransaction();
                        session.save(series);
                        tx.commit();
                        session.close();
                        databaseResponse = "Serial dodano do bazy";
                    }
                    else{
                        databaseResponse = "Niestety jakiś inny serial znajduje się na tej pozycji";
                        session.close();
                    }
                }
                else{
                    databaseResponse = "Niestety taki serial jest już w bazie";
                    session.close();
                }
            }
            catch(Exception e){
                databaseResponse = "Niestety nie udało się dodać serialu bo bazy";
            }
        }

        */



        /*
        if(modal.getType().equals("Film")){
            try {
                SessionFactory sessionFactory;
                sessionFactory = new Configuration()
                        .configure() // configures settings from hibernate.cfg.xml
                        .buildSessionFactory();

                Session session = sessionFactory.openSession();
                Transaction tx = session.beginTransaction();
                session.save(movie);
                tx.commit();
                session.close();

                databaseResponse = "Dodawanie do bazy zakończyło się sukcesem";
            }
            catch(Exception e){
                databaseResponse = "Niestety nie udało się dodać do bazy";
            }

        }
        else if(modal.getType().equals("Serial")){

            Series series = new Series(modal.getFilmwebId(), modal.getOriginalTitle(), modal.getPolishTitle(),
                    Methods.changeImageSize(modal.getImage_6(), "0"), Methods.changeImageSize(modal.getImage_6(), "1"),
                    Methods.changeImageSize(modal.getImage_6(), "2"), Methods.changeImageSize(modal.getImage_6(), "3"),
                    Methods.changeImageSize(modal.getImage_6(), "4"), Methods.changeImageSize(modal.getImage_6(), "5"),
                    modal.getImage_6(), modal.getYear(), modal.getCast(), modal.getDuration(), modal.getProductionCountry(),
                    modal.getFilmwebGenre(), Methods.preventNull(description), modal.getPlot(), genre, column, row, country,
                    modal.getNumberOfEpisodes(), modal.getNumberOfSeasons());

            try {
                SessionFactory sessionFactory;
                sessionFactory = new Configuration()
                        .configure() // configures settings from hibernate.cfg.xml
                        .buildSessionFactory();

                Session session = sessionFactory.openSession();
                Transaction tx = session.beginTransaction();
                session.save(series);
                tx.commit();
                session.close();

                databaseResponse = "Dodawanie do bazy zakończyło się sukcesem";
            }
            catch(Exception e){
                databaseResponse = "Niestety nie udało się dodać do bazy";
            }
        }
        */

        /*

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
                movie.setImage_0(Methods.changeImageSize(tmp.getImage_6(), "0"));
                movie.setImage_1(Methods.changeImageSize(tmp.getImage_6(), "1"));
                movie.setImage_2(Methods.changeImageSize(tmp.getImage_6(), "2"));
                movie.setImage_3(Methods.changeImageSize(tmp.getImage_6(), "3"));
                movie.setImage_4(Methods.changeImageSize(tmp.getImage_6(), "4"));
                movie.setImage_5(Methods.changeImageSize(tmp.getImage_6(), "5"));
                movie.setImage_6(tmp.getImage_6()); //add set another images
                movie.setYear(tmp.getYear());
                movie.setCast(tmp.getCast());
                movie.setDuration(tmp.getDuration());
                movie.setProductionCountry(tmp.getProductionCountry());
                movie.setFilmwebGenre(tmp.getFilmwebGenre());
                movie.setDescription(opis);
                movie.setPlot(tmp.getPlot());
                movie.setGenre(genre); //tmp.getGenre() not implemented yet
                movie.setColumn(column); //like above
                movie.setRow(row); //like above
                movie.setCountryType(country); //like above
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
        */

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(databaseResponse);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
