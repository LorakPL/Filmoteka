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
            new File(finalPath).mkdirs();
            URL url = new URL(imgPath);
            BufferedImage img = ImageIO.read(url);
            File file = new File(finalPath + "/" + Long.toString(filmwebId) + ".jpg");
            ImageIO.write(img, "jpg", file);
            path = "images/image_" + finalPath.substring(finalPath.length() - 1) + "/" + Long.toString(filmwebId) + ".jpg";
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return path;
    }
}
