package repository;

import config.DataBaseConfig;
import model.Ticket;

import java.sql.*;

public class TicketRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS tickets" +
                "(id int PRIMARY KEY AUTO_INCREMENT, eventName varchar(20), price double)" ;

        Connection connection = DataBaseConfig.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTicket(Ticket t){
        String insertTicketSql = "INSERT INTO tickets(eventName, price) VALUES(?,?)";

        Connection connection = DataBaseConfig.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertTicketSql);
            preparedStatement.setString(1, t.getEventName());
            preparedStatement.setBigDecimal(2,t.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Ticket getTicketByEventName(String eventName) {
        String selectSql = "SELECT * FROM tickets WHERE name=?";

        Connection connection = DataBaseConfig.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, eventName);


            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToTicket(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    private Ticket mapToTicket(ResultSet resultSet) throws SQLException{
        if (resultSet.next()) {
            return new Ticket(resultSet.getString(2),resultSet.getBigDecimal(3));
        }
        return null;
    }

    public void updateTicketPrice(Double price, String name) {
        String updatePriceSql = "UPDATE tickets SET price=? WHERE name=?";

        Connection connection = DataBaseConfig.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updatePriceSql);
            preparedStatement.setDouble(1, price);
            preparedStatement.setString(2, name);

            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteTicket(Ticket t){
        String deleteTicketSql = "DELETE FROM tickets WHERE name=?";

        Connection connection = DataBaseConfig.getDatabaseConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(deleteTicketSql);
            preparedStatement.setString(1,t.getEventName());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void displayTickets(){
        String selectSql = "SELECT * FROM tickets";

        Connection connection = DataBaseConfig.getDatabaseConnection();

        try{
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while(resultSet.next()) {
                System.out.println("Id: " + resultSet.getString(1));
                System.out.println("Event name: " + resultSet.getString(2));
                System.out.println("Price: " + resultSet.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
