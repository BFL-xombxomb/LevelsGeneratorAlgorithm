import java.util.ArrayList;

public class Location {

    String locationName;
    ArrayList<Level> levels;



    public Location(String locationName) {
        this.locationName = locationName;
        this.levels = new ArrayList<>();


    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }
}
