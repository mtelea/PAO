package model;

public class Artist {

    private String name;
    private String description;
    private String activity;

    public Artist(){

    }

    public Artist(String name, String description, String activity) {
        this.name = name;
        this.description = description;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", activity='" + activity + '\'' +
                '}';
    }
}
