import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PopularWords {
    
    public static void main(String[] args) {
        run();
    }
    
    static void run() {
        Connection connect = Jsoup.connect("http://www.onet.pl/");
        List<String> allTitles = new ArrayList<>();

        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            for (Element elem : links) {
                String[] words = elem.text().split(" ");
                for (int i = 0; i < words.length; i++) {
                    String word = words[i].replaceAll("[^a-zA-Z0-9_-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]", "");
                    if (word.length() > 3) {
                        allTitles.add(word);
                        
                    }
                }
            }
            System.out.println(allTitles.toString());
            
        } catch (IOException e) {
            System.out.println("Problem z połączeniem");
        }
        Path dir = Paths.get(".popular_words.txt");
        saveToFile(dir, allTitles);
        filter(dir);
    
    }
    
    private static void saveToFile(Path dir, List<String> allTitles){
        
        if (Files.exists(dir)){
            System.out.println("Plik popular_words.txt już istnieje");
        } else {
            try {
                Files.write(dir, allTitles);
            } catch (IOException e) {
                System.out.println("Nie udało się zapisać do pliku popular_words.txt");
            }
        }
    }
    
    private static void filter(Path dir){
        String[] excluded = {"który", "która", "które", "których", "najlepszych", "jest", "były", "dziś", "mnie", "dwóch",
                "będzie", "jeszcze", "swoje", "taki", "taka", "albo", "przez", "jestem", "chce", "znacznie"};
        
        List<String> filteredList = new ArrayList<>();
        try {
            filteredList = Files.readAllLines(dir);
        } catch (IOException e) {
            System.out.println("Nie udało się otworzyć pliku .popular_words.txt");
            return;
        }
        filteredList.removeAll(Arrays.asList(excluded));
        Path dir2 = Paths.get(".filtered_words.txt");
        if (Files.exists(dir2)){
            System.out.println("Plik .filtered_words.txt już istnieje");
        } else {
            try {
                Files.write(dir2, filteredList);
            } catch (IOException e) {
                System.out.println("Nie udało się zapisać do pliku filtered_words.txt");
            }
        }
    }
}
