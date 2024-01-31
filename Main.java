import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        String url = "https://newlms.magtu.ru/mod/folder/view.php?id=219208";
        Downloader downloader = new Downloader(url);
    }
}

class Downloader{

    String downloadPath;
    List<String> fileNames = new ArrayList<>();


    public Downloader(String downloadPath){
        this.downloadPath = downloadPath;
        parseSite();
    }

    void parseSite() {
        try {
            URL site = new URL(downloadPath);
            InputStream stream = site.openStream();
            byte[] bytes = stream.readAllBytes();
            String str = new String(bytes);
            System.out.println(str);

            Help help = new Help();
            String a = help.findInArray(bytes,"<a href=");

        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}

class Help{

    String findInArray(byte[] array,String word){
        StringBuilder builder = new StringBuilder();
        char[] wordArray = word.toCharArray();
        for (int i=0;i<array.length;i++){
            if(array[i]==wordArray[0]){
                builder.append(array[i]);
            }
        }
        return builder.toString();
    }
}