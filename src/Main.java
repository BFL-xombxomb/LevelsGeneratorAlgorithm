import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        loadGameData();
    }

    //create a location object ind put into it data from json

    //load json array of locations from the goal words
    //create level object from every object from the array
    //one final dictionary and several for changing
    //save chains of words to json

    public static void loadGameData() {


        try {

            Gson gson = new Gson();

            GameData test = new GameData();

            LocationJSON[] locations = new GsonBuilder().create().fromJson(new FileReader("C:\\Users\\xombxomb\\IdeaProjects\\LevelsGeneratorAlgorithm\\src\\GoalWords.json"), LocationJSON[].class);

            for (int i = 0; i < locations[0].getGoalWords().length; i++) {
                System.out.println(locations[0].getGoalWords()[i]);
            }



        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
