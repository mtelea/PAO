package repository;

import config.DataBaseConfig;
import model.Address;
import model.Event;
import model.Place;

import javax.xml.crypto.Data;
import java.sql.*;

public class EventRepository {

    public void createTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS event" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30)," +
                "place varchar(30), country varchar(30),city varchar(30)," +
                "street varchar(30, number int, date varchar(30), details varchar(30))";

        Connection connection = DataBaseConfig.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addEvent(Event event){
        String insertEvent ="";
        insertEvent = "INSERT INTO events"+
                "(name,place,country,city,street,number,date,details)" +
                "VALEUS(?,?,?,?,?,?,?,?)";

        Connection connection = DataBaseConfig.getDatabaseConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(insertEvent);

            preparedStatement.setString(1,event.getName());
            preparedStatement.setString(2,event.getPlace().getName());
            preparedStatement.setString(3,event.getPlace().getAddress().getCountry());
            preparedStatement.setString(4,event.getPlace().getAddress().getCity());
            preparedStatement.setString(5,event.getPlace().getAddress().getStreet());
            preparedStatement.setInt(6,event.getPlace().getAddress().getNumber());
            preparedStatement.setString(7,event.getDate());
            preparedStatement.setString(8,event.getDetails());

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Event getEventByName(String name) {
        String select = "SELECT * FROM events WHERE name=?";

        Connection connection = DataBaseConfig.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToEvent(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    private Event mapToEvent(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Event(resultSet.getString(2), new Place( resultSet.getString(3),
                    new Address(resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7))),
                    resultSet.getString(8), resultSet.getString(9));

        }
        return null;
    }

    public void deleteEvent(Event ev) {
        String deleteEventSql = "DELETE FROM events WHERE name=?";

        Connection connection = DataBaseConfig.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteEventSql);
            preparedStatement.setString(1, ev.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayEvents() {
        String selectSql = "SELECT * FROM events";

        Connection connection = DataBaseConfig.getDatabaseConnection();

        try{
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next()) {
                System.out.println("Id: " + resultSet.getString(1));
                System.out.println("Name: " + resultSet.getString(2));
                System.out.println("Location name: " + resultSet.getString(3));
                System.out.println("Country: " + resultSet.getString(4));
                System.out.println("City: " + resultSet.getString(5));
                System.out.println("Street: " + resultSet.getString(6));
                System.out.println("Street number: " + resultSet.getString(7));
                System.out.println("Date: " + resultSet.getString(8));
                System.out.println("Details: " + resultSet.getString(9));

                System.out.println("");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");


    }



}
