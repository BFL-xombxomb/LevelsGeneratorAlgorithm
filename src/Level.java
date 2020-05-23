import java.util.ArrayList;

public class Level {

    private int levelNumber;
    private String goalWord;
    private int pathLength;
    private ArrayList<Instance> instances;
    private static int lvlCounter = 1;
    private String magicQuote = "Your quote will be here";


    public Level(String goalWord, int pathLength) {


        this.levelNumber = lvlCounter;
        this.goalWord = goalWord;
        this.pathLength = pathLength;
        this.instances = new ArrayList<>();

        lvlCounter++;
    }


    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public String getGoalWord() {
        return goalWord;
    }

    public void setGoalWord(String goalWord) {
        this.goalWord = goalWord;
    }

    public int getPathLength() {
        return pathLength;
    }

    public void setPathLength(int pathLength) {
        this.pathLength = pathLength;
    }

    public ArrayList<Instance> getInstances() {
        return instances;
    }

    public void setInstances(ArrayList<Instance> instances) {
        this.instances = instances;
    }


}
