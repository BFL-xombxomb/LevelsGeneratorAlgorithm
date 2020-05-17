public class LocationJSON {

    private String locationName;
    private String[] goalWords;

    public LocationJSON(String locationName, String[] goalWords) {
        this.locationName = locationName;
        this.goalWords = goalWords;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String[] getGoalWords() {
        return goalWords;
    }

    public void setGoalWords(String[] goalWords) {
        this.goalWords = goalWords;
    }
}
