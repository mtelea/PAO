package model;

import java.util.ArrayList;

public class Account {

    private String username;
    private String password;
    private User user;
    private CreditCard card;

    private ArrayList<Ticket> tickets;

    public Account(){

    }

    public Account(String username, String password, User user, CreditCard card){
        this.username = username;
        this.password = password;
        this.user = user;
        this.card = card;
        this.tickets = new ArrayList<Ticket>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                ", card=" + card +
                ", tickets=" + tickets +
                '}';
    }
}
