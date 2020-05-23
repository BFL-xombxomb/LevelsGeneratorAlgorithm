import java.util.ArrayList;

public class GameData {

    public ArrayList<Location> locations;


    public GameData() {

        locations = new ArrayList<>();

    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }
}
