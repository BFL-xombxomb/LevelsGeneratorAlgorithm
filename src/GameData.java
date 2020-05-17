import java.util.ArrayList;

public class GameData {

    public ArrayList<Location> locations;


    public GameData() {

        locations = new ArrayList<>();
    }

    public void addLocation(Location location) {
        locations.add(location);
    }
}
