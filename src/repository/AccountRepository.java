package repository;

import config.DataBaseConfig;
import model.Account;
import model.CreditCard;
import model.User;

import javax.xml.crypto.Data;
import java.sql.*;

public class AccountRepository {

    public void createTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS accounts" +
                "(id int PRIMARY KEY AUTO_INCREMENT,username varchar(30)," +
                "password varchar(30), name varchar(30), mail varchar(30)," +
                "phoneNumber varchar(30), age int, cardNumber varchar(30), expirationYear int," +
                "CVV int, sold float)";

        Connection connection = DataBaseConfig.getDatabaseConnection();

        try {
            Statement s = connection.createStatement();
            s.execute(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(Account account) {
        String type = account.getClass().getSimpleName();
        String insertAccount = "INSERT INTO accounts" +
                "(username,password, name, mail, phoneNumber, age, cardNumber, expirationYear," +
                "CVV, sold)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";

        Connection connection = DataBaseConfig.getDatabaseConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(insertAccount);

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getUser().getName());
            ps.setString(4, account.getUser().getMail());
            ps.setString(5, account.getUser().getPhoneNumber());
            ps.setInt(6, account.getUser().getAge());
            ps.setString(7, account.getCard().getNumber());
            ps.setInt(8, account.getCard().getExpirationYear());
            ps.setInt(9, account.getCard().getCVV());
            ps.setBigDecimal(10, account.getCard().getSold());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Account mapToAccount(ResultSet res) throws SQLException {
        if (res.next()) {
            return new Account(res.getString(2), res.getString(3), new User(res.getString(4),
                    res.getString(5), res.getString(6), res.getInt(7)),
                    new CreditCard(res.getString(8), res.getInt(9), res.getInt(10),
                            res.getBigDecimal(11)));
        }
        return null;
    }

    public Account getAccount(String username) {
        String s = "SELECT * from accounts where username=?";
        Connection connection = DataBaseConfig.getDatabaseConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(s);
            ps.setString(1, username);
            ResultSet res = ps.executeQuery();
            return mapToAccount(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAccount(Account a){
        String s = "DELETE FROM accounts WHERE username=?";

        Connection connection = DataBaseConfig.getDatabaseConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(s);
            ps.setString(1, a.getUsername());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAccounts() {
        String s = "SELECT * FROM accounts";

        Connection connection = DataBaseConfig.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(s);

            while (res.next()){
                System.out.println("Username: " + res.getString(2));
                System.out.println("Name: " + res.getString(4));
                System.out.println("Email: " + res.getString(5));
                System.out.println("Phone: " + res.getString(6));
                System.out.println("Age: " + res.getInt(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUsername(String username, Account account) {
        String s = "UPDATE accounts SET username=? WHERE username=?";

        Connection connection = DataBaseConfig.getDatabaseConnection();
        try{
            PreparedStatement ps = connection.prepareStatement(s);
            ps.setString(1,username);
            ps.setString(2,account.getUsername());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
