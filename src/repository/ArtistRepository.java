package repository;

import config.DataBaseConfig;
import model.Artist;

import java.sql.*;

public class ArtistRepository {

    public void createTable(){
        String s = "CREATE TABLE IF NOT EXISTS artists" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30)," +
                "description varchar(50), field varchar(20))";

        Connection connection = DataBaseConfig.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addArtist(Artist artist) {
        String insertArtistSql = "INSERT INTO artists(name, description, field) VALUES(?,?,?)";

        Connection connection = DataBaseConfig.getDatabaseConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(insertArtistSql);
            ps.setString(1,artist.getName());
            ps.setString(2,artist.getDescription());
            ps.setString(3,artist.getActivity());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Artist mapToArtist(ResultSet res) throws SQLException{
        if(res.next()){
            return new Artist(res.getString(1),res.getString(2),res.getString(3));
        }
        return null;
    }

    public Artist getArtist(String name){
        String s = "SELECT * FROM artists WHERE name=?";

        Connection connection = DataBaseConfig.getDatabaseConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1, name);

            ResultSet res = preparedStatement.executeQuery();
            return mapToArtist(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateArtist(String name, Artist artist) {
        String updateNameSql = "UPDATE artists SET name=? WHERE name=?";

        Connection connection = DataBaseConfig.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, artist.getName());

            preparedStatement.executeUpdate();

            String updateArtistsSql = "UPDATE artists SET artist=? WHERE artist=?";

            try{
                PreparedStatement preparedStatement2 = connection.prepareStatement(updateArtistsSql);
                preparedStatement2.setString(1, name);
                preparedStatement2.setString(2, artist.getName());

                preparedStatement2.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            }
    }

    public void deleteArtist(Artist a) {
        String s = "DELETE FROM artists WHERE name=?";

        Connection connection = DataBaseConfig.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1, a.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void displayArtists() {
        String s = "SELECT * FROM artists";

        Connection connection = DataBaseConfig.getDatabaseConnection();

        try{
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(s);
            while (resultSet.next()) {
                System.out.println("Id: " + resultSet.getString(1));
                System.out.println("Name: " + resultSet.getString(2));
                System.out.println("Description: " + resultSet.getString(3));
                System.out.println("Activity: " + resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
