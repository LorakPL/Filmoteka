package classes;

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
}
