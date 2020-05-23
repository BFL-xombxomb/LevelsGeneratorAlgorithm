import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    //dictionaries
    final static ArrayList<String> dictionary3 = DictionaryLoader.getLoadedArrayList("C:\\Users\\xombxomb\\IdeaProjects\\LevelsGeneratorAlgorithm\\src\\length3.txt");
    final static ArrayList<String> dictionary4 = DictionaryLoader.getLoadedArrayList("C:\\Users\\xombxomb\\IdeaProjects\\LevelsGeneratorAlgorithm\\src\\length4.txt");
    final static ArrayList<String> dictionary5 = DictionaryLoader.getLoadedArrayList("C:\\Users\\xombxomb\\IdeaProjects\\LevelsGeneratorAlgorithm\\src\\length5.txt");

    //paths
    static String goalWordsJSONFile = "C:\\Users\\xombxomb\\IdeaProjects\\LevelsGeneratorAlgorithm\\src\\GoalWords.json";

    public static void main(String[] args) {


        int length = getChainLength(10,1);
        System.out.println(length);
        /* LocationJSON[] locationJSONS = getArrayFromJSON(goalWordsJSONFile);

         if (locationJSONS != null) {

             //create GameData file to have an ability to write data to JSON file step by step, not by the one iteration
             GameData fullData = new GameData();

            ArrayList<Location> locations = new ArrayList<>();

             //a body of function
             for (int j = 0; j < locationJSONS.length; j++) {

                ArrayList<String> levels = new ArrayList<>();

                for (String string : locationJSONS[j].getGoalWords()) {
                    levels.add(string);
                }

                Collections.shuffle(levels);
                 //mix end words

                 for (int i = 0; i < levels.size(); i++) {

                     //method to assign the difficulty
                     int chainLength = getChainLength(levels.size(), i);

                     //generate 0-5 chain in List how it possible until 5
                     //if 0 word will be excluded

                     List<List<String>> chains = getAllChains(levels.get(i), chainLength, getNeededDictionary(levels.get(i).length()));

                     if (chains != null && chains.size() >= 2) {

                         Level level = new Level(levels.get(i), chainLength);
                         ArrayList<Instance> instances = new ArrayList<>();

                         for (List<String> chain : chains) {

                             ArrayList<String> c = new ArrayList<>(chain);
                             Instance instance = new Instance(chain.get(0), c);
                             instances.add(instance);
                         }


                         level.setInstances(instances);
                     }


                 }

             }

         } else {
             System.out.println("Array with goal words is null");
         }
*/



    }

    //create a location object ind put into it data from json

    //load json array of locations from the goal words
    //create level object from every object from the array
    //one final dictionary and several for changing
    //save chains of words to json

    private static LocationJSON[] getArrayFromJSON(String filePath) {

        LocationJSON[] locations;


        try {

            Gson gson = new Gson();

            locations = new GsonBuilder().create().fromJson(new FileReader(filePath), LocationJSON[].class);

            for (int i = 0; i < locations[0].getGoalWords().length; i++) {
                System.out.println(locations[0].getGoalWords()[i]);
            }

            return locations;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }



    private static List<List<String>> getAllChains(String endWord, int chainLength, ArrayList<String> dictionary) {

        LinkedList<String> unvisitedStartWords = new LinkedList<>(dictionary);

        List<List<String>> result = new ArrayList<List<String>>();


        while (!unvisitedStartWords.isEmpty() && result.size() < 5) {

            Random random = new Random();
            int index = random.nextInt(unvisitedStartWords.size());


            System.out.println("Index is " + index);
            String randomWord = unvisitedStartWords.get(index);
            System.out.println("Word is " + randomWord);
            System.out.println("Amount of remaining words :" + unvisitedStartWords.size());


            List<List<String>> ladders = findLadders2(randomWord, endWord);

            if (ladders == null || ladders.size() < 1) {
                unvisitedStartWords.remove(randomWord);
                System.out.println("ladder is null");
                continue;
            }

            if(ladders.get(0).size() == chainLength) {
                result.add(ladders.get(0));
                unvisitedStartWords.remove(randomWord);
                System.out.println("Подошло");

            } else {
                unvisitedStartWords.remove(randomWord);
                System.out.println("Не подошло");
            }

        }
        return result;
    }

    private static List<List<String>> findLadders(String beginWord, String endWord, ArrayList<String> dictionary) {
        List<List<String>> result = new ArrayList<List<String>>();

        HashSet<String> unvisited = new HashSet<>();
        unvisited.addAll(dictionary);

        LinkedList<Node> queue = new LinkedList<>();
        Node node = new Node(beginWord,0,null);
        queue.offer(node);

        int minLen = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            Node top = queue.poll();

            //top if have shorter result already
            if(result.size()>0 && top.depth>minLen){
                return result;
            }

            for(int i=0; i<top.word.length(); i++){
                char c = top.word.charAt(i);
                char[] arr = top.word.toCharArray();
                for(char j='Z'; j>='A'; j--){
                    if(j==c){
                        continue;
                    }
                    arr[i]=j;
                    String t = new String(arr);

                    if(t.equals(endWord)){
                        //add to result
                        List<String> aResult = new ArrayList<>();
                        aResult.add(endWord);
                        Node p = top;
                        while(p!=null){
                            aResult.add(p.word);
                            p = p.prev;
                        }

                        Collections.reverse(aResult);
                        result.add(aResult);

                        //stop if get shorter result
                        if(top.depth<=minLen){
                            minLen=top.depth;
                        }else{
                            return result;
                        }
                    }

                    if(unvisited.contains(t)){
                        Node n=new Node(t,top.depth+1,top);
                        queue.offer(n);
                        unvisited.remove(t);
                    }
                }
            }
        }
        return result;
    }

   static private List<List<String>> findLadders2(String beginWord, String endWord) {
        List<List<String>> result = new ArrayList<List<String>>();

        HashSet<String> unvisited = new HashSet<>();
        unvisited.addAll(getNeededDictionary(endWord.length()));

        LinkedList<Node> queue = new LinkedList<>();
        Node node = new Node(beginWord,0,null);
        queue.offer(node);

        int minLen = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            Node top = queue.poll();

            //top if have shorter result already
            if(result.size()>0 && top.depth>minLen){
                return result;
            }

            for(int i=0; i<top.word.length(); i++){
                char c = top.word.charAt(i);
                char[] arr = top.word.toCharArray();
                for(char j='Z'; j>='A'; j--){
                    if(j==c){
                        continue;
                    }
                    arr[i]=j;
                    String t = new String(arr);

                    if(t.equals(endWord)){
                        //add to result
                        List<String> aResult = new ArrayList<>();
                        aResult.add(endWord);
                        Node p = top;
                        while(p!=null){
                            aResult.add(p.word);
                            p = p.prev;
                        }

                        Collections.reverse(aResult);
                        result.add(aResult);

                        //stop if get shorter result
                        if(top.depth<=minLen){
                            minLen=top.depth;
                        }else{
                            return result;
                        }
                    }

                    if(unvisited.contains(t)){
                        Node n=new Node(t,top.depth+1,top);
                        queue.offer(n);
                        unvisited.remove(t);
                    }
                }
            }
        }
        return result;
    }

    private static String[] getStartWords(String endWord) {

        return null;
    }

    private static int getChainLength(int arrayLength, int index) {


        double complexity = (double)index/(double)arrayLength * 100;
        System.out.println(complexity);

       if (complexity <= 10) {

           return randInt(2,3);

       } else if (complexity > 10 && complexity <= 30) {

           return randInt(3,5);

       } else if (complexity > 30 && complexity <= 50) {

           return randInt(4,6);

       } else if (complexity > 50 && complexity <= 80) {

           return randInt(5,8);

       } else if (complexity > 80) {

           return randInt(8,10);
       }

           return 4;
    }

    public static int randInt(int min, int max) {


        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    private static ArrayList<String> getNeededDictionary(int wordLength) {
        switch (wordLength) {
            case 3: return dictionary3;
            case 4: return  dictionary4;
            case 5: return dictionary5;
        }

        return null;
    }



}

class Node{
    public String word;
    public int depth;
    public Node prev;

    public Node(String word, int depth, Node prev){
        this.word=word;
        this.depth=depth;
        this.prev=prev;
    }
}
