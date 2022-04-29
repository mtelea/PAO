package model;

import java.util.Arrays;

public class Event {

    private String name;
    private String date;
    private Place place;
    private String details;

    private Artist[] artists;

    public Event(){

    }

    public Event(String name, String date, Place place, String details, Artist[] artists) {
        this.name = name;
        this.date = date;
        this.place = place;
        this.details = details;
        this.artists = artists;
    }

    public Event(String name, Place place, String date, String details){
        this.name = name;
        this.place = place;
        this.date = date;
        this.details = details;
        this.artists = new Artist[0];
    }

    public void addArtist(Artist new_artist){
        Artist[] new_artists = new Artist[artists.length + 1];
        new_artists[artists.length] = new_artist;
        System.arraycopy(artists, 0, new_artists, 0 ,artists.length);
        Arrays.sort(new_artists);
        artists = new_artists;
        System.out.println("Artistul a fost adaugat cu succes!");
    }

    public void deleteArtist(Artist a) {
        int i;

        for (i = 0; i < artists.length; i++) {
            if (artists[i].equals(a))
                break;
        }
        if (i != artists.length) {
            Artist[] new_artists = new Artist[artists.length - 1];
            System.arraycopy(artists, 0, new_artists, 0, i);
            for (i = i; i < artists.length - 1; i++)
                new_artists[i] = artists[i + 1];

            artists = new_artists;
            System.out.println("Artistul nu va mai participa la acest eveniment");
        }
    }

    public String deleteArtist(String artistName){
        int i;

        for(i=0; i< artists.length ; i++){
            if(artists[i].getName().equals(artistName))
                break;
            }

        if(i == artists.length){
            return "Nu am gasit artistul in lineup-ul acestui eveniment. Verificati daca ati scris numele corect\n";
            }

            else{
                Artist[] new_artists = new Artist[artists.length - 1];
                System.arraycopy(artists, 0, new_artists, 0 , i);
                for(i = i; i < artists.length - 1 ; i++)
                    new_artists[i] = artists[i+1];

                artists = new_artists;
                return "Artistul nu va mai participa la acest eveniment";

            }
    }

    public void showArtists() {
        System.out.println('\n' + this.name + ':');

        for(Artist a : artists){
            System.out.println(a);
        }
    }

    public Artist getArtistByName(String name){
        for (Artist a : artists){
            if(a.getName().equals(name)){
                return a;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Artist[] getArtists() {
        return artists;
    }

    public void setArtists(Artist[] artists) {
        this.artists = artists;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", place=" + place +
                ", details='" + details + '\'' +
                ", artists=" + Arrays.toString(artists) +
                '}';
    }
}
