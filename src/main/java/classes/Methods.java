package classes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Methods {
    public static String preventNull(String object){
        if(object == null || object.equals("999")){
            object = "Brak danych";
        }
        return object;
    }

    public static Integer preventNullInteger(Integer object){
        if(object == null){
            object = 999;
        }

        return object;
    }

    public static String changeImageSize(String url, String imageSize){
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

    public static String saveImage(String imgPath, String finalPath, Long filmwebId){ //finalPath np.: image_0, image_1 itp.
        String path = "";
        try{
            String home = System.getProperty("user.home");
            new File(home + "/Documents/Filmoteka/src/main/webapp/img/" + finalPath).mkdir();
            URL url = new URL(imgPath);
            BufferedImage img = ImageIO.read(url);
            File file = new File(home + "/Documents/Filmoteka/src/main/webapp/img/" + finalPath + "/" + Long.toString(filmwebId) + ".jpg");
            ImageIO.write(img, "jpg", file);
            path = "img/" + finalPath + "/" + Long.toString(filmwebId) + ".jpg";
            /*
            String home = System.getProperty("user.home");
            new File(home + "/Documents/FilmotekaImages/" + finalPath).mkdirs();
            URL url = new URL(imgPath);
            BufferedImage img = ImageIO.read(url);
            //File file = new File("D:\\image\\downloaded.jpg");
            File file = new File(home + "/Documents/FilmotekaImages/" + finalPath + "/" + Long.toString(filmwebId) + ".jpg");
            ImageIO.write(img, "jpg", file);

            //new File(home + "/Downloads/Folder").mkdir();
            //new File(home + "/Downloads/Folder2/Zaglebienie/Zaglebienie2").mkdirs();
            path = home + "/Documents/FilmotekaImages/" + finalPath + "/" + Long.toString(filmwebId) + ".jpg";
            */
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return path;
    }
}
