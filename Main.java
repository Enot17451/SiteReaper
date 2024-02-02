import java.io.*;
import java.net.URL;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        String url = "https://newlms.magtu.ru/mod/folder/view.php?id=219208";
        //Downloader downloader = new Downloader(url);

        char[] c = "<html><a></a><a href=\"adgfad.com\"></a><head><html>".toCharArray();
        byte[] b = new byte[c.length];
        for (int i = 0; i < c.length; i++) {
            b[i] = (byte) c[i];
        }
        Help h = new Help();
        var a = h.getLinksFromArray(b);
    }
}

class Downloader {

    String downloadPath;
    ArrayList<String> fileNames = new ArrayList<>();


    public Downloader(String downloadPath) {
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
            ArrayList<String> a = help.getLinksFromArray(bytes);

        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}

class Help {

    ArrayList<String> getLinksFromArray(byte[] array) {
        ArrayList<String> temp = new ArrayList<>();
        byte[] begin = "<a href=".getBytes();
        byte[] end = "</a>".getBytes();
        int i = 0;
        while (i < array.length) {
            int indexA = getWordIndex(array, begin, i);
            if (indexA != -1) {
                i += indexA;
                int indexB = getWordIndex(array, end, indexA);
                temp.add(getWordFromArray(array, indexA, indexB));
            }
            i++;
        }
        return temp;
    }

    String getWordFromArray(byte[] array, int indexA, int indexB) {
        return "";
    }

    int getWordIndex(byte[] array, byte[] word, int begin) {
        for (int i = begin; i < array.length; i++) {
            if (array[i] == word[0]) {
                for (int j = 1; j < word.length; j++) {
                    char a = (char) array[i + j];
                    char b = (char) word[j];
                    if (array[i + j] != word[j]) {
                        return -1;
                    }
                }
            }
        }
        return -1;
    }
}
