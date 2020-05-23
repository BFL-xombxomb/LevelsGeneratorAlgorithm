import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class DictionaryLoader {

    public DictionaryLoader() {

    }

    public static ArrayList<String> getLoadedArrayList(String pathToFile) {

        ArrayList<String> arrayList = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToFile))) {
            while (bufferedReader.ready()) {
                arrayList.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return arrayList;
    }
}
