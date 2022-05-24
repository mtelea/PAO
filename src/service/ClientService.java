package service;

import model.*;
import repository.AccountRepository;
import repository.ArtistRepository;
import repository.EventRepository;
import repository.TicketRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ClientService {

    private ArrayList<Event> events;
    private ArrayList<User> users;
    private ArrayList<Artist> artists;
    private ArrayList<Account> accounts;
    private ArrayList<Ticket> tickets;
    private ArrayList<Voucher> vouchers;

    public ClientService(ArrayList<Event> events, ArrayList<User> users, ArrayList<Artist> artists, ArrayList<Account> accounts, ArrayList<Ticket> tickets, ArrayList<Voucher> vouchers) {
        this.events = events;
        this.users = users;
        this.artists = artists;
        this.accounts = accounts;
        this.tickets = tickets;
        this.vouchers = vouchers;

    }

    public ClientService() {
        this.events = new ArrayList<Event>();
        this.users = new ArrayList<User>();
        this.artists = new ArrayList<Artist>();
        this.accounts = new ArrayList<Account>();
        this.tickets = new ArrayList<Ticket>();
        this.vouchers = new ArrayList<Voucher>();
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public ArrayList<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(ArrayList<Voucher> vouchers) {
        this.vouchers = vouchers;
    }

    public void addEvent(EventRepository eventRepository, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Cum se numeste evenimentul?");
        String s1 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("Cum se numeste locatia evenimentului?");
        String s2 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("Introdu adresa locatiei: ");

        System.out.println("Tara: ");
        String a1 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("Oras: ");
        String a2 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("Strada: ");
        String a3 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("Numar: ");
        int a4 = scanner.nextInt();


        Address ad = new Address(a1, a2, a3, a4);

        Place l = new Place(s2, ad);

        scanner.nextLine();
        System.out.println("Care este data evenimentului? (DD/MM/YYYY)");
        String s3 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("Introdu detaliile evenimentului");
        String s4 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("Este un eveniment de tip festival? 1 - DA , 2 - NU");

        Integer option = scanner.nextInt();

        if (option == 1) {
            scanner.nextLine();
            System.out.println("Introdu numarul de scene ale festivalului: ");
            int n1 = scanner.nextInt();

            scanner.nextLine();
            System.out.println("Introdu limita de varsta pentru acces: ");
            int n2 = scanner.nextInt();

            Event e = new Festival(s1, l, s3, s4, n1, n2);
            eventRepository.addEvent(e);

        } else {
            Event e = new Event(s1, l, s3, s4);
            eventRepository.addEvent(e);
        }

    }

    public void deleteEvent(EventRepository eventRepository, Scanner scanner) {
        System.out.println("Cum se numeste evenimentul?");
        String s1 = scanner.nextLine();
        Event e = eventRepository.getEventByName(s1);
        if (Objects.isNull(e)) {
            System.out.println("Nu am gasit acest eveniment");
        } else {
            eventRepository.deleteEvent(e);
        }
    }

    public void showEvents(EventRepository eventRepository) {
        eventRepository.displayEvents();
    }

    public void showEvent(Event event) {
        event.showArtists();
    }

    public Event getEventByName(String name) {
        for (Event e : events) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;

    }

    public void addUser(User new_user) {
        this.users.add(new_user);
    }

    public void showUsers() {
        for (User u : users) {
            System.out.println(u.getName() + '\n');
        }
    }

    public User getUserByName(String name){
        for(User u : users){
            if(u.getName().equals(name)){
                return u;
            }
        }
        return null;
    }

    public void addArtist(ArtistRepository artistRepository, Scanner scanner){
        scanner.nextLine();
        System.out.println("Introduceti numele artistului: ");
        String s1 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("Introduceti o descriere a acestuia: ");
        String s2 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("In ce domeniu lucreaza artistul? (Ex: Teatru, Muzica, Stand-up)");
        String s3 = scanner.nextLine();

        Artist a = new Artist(s1,s2,s3);
        artistRepository.addArtist(a);
    }

    public void showArtists(ArtistRepository artistRepository){
        artistRepository.displayArtists();
    }

    // Account ADD - DELETE - SHOW

    public void addAccount(AccountRepository accountRepository, Scanner scanner){
        scanner.nextLine();
        System.out.println("Introduceti un username: ");
        String a1 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("Introduceti o parola: ");
        String a2 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("Cum te numesti? Te rog introdu numele complet. ");
        String s1 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("Care este email-ul tau?");
        String s2 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("Care este numarul tau de telefon?");
        String s3 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("Ce varsta ai?");
        int s4 = scanner.nextInt();

        User u = new User(s1, s2, s3, s4);
        addUser(u);

        scanner.nextLine();
        System.out.println("Introdu detaliile cardului: ");
        System.out.println("Introdu numarul cardului: ");
        String l1 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("Introdu anul in care va expira cardul: ");
        int i2 = scanner.nextInt();

        scanner.nextLine();
        System.out.println("Introdu codul de siguranta de pe spatele cardului: ");
        int i3 = scanner.nextInt();

        scanner.nextLine();
        System.out.println("Introdu suma din cont: ");
        BigDecimal f4 = scanner.nextBigDecimal();

        CreditCard c = new CreditCard(l1, i2,i3,f4);

        Account a = new Account(a1,a2,u,c);

        accountRepository.addAccount(a);
    }

    public void deleteAccount(AccountRepository accountRepository, Scanner scanner){
        scanner.nextLine();
        System.out.println("Care este username-ul?");
        String s1 = scanner.nextLine();
        Account a = accountRepository.getAccount(s1);
        if (Objects.isNull(a)){
            System.out.println("Nu am gasit acest user");
        }
        else{
            accountRepository.deleteAccount(a);
        }
    }

    public void updateAccount(AccountRepository accountRepository, Scanner scanner){
        scanner.nextLine();
        System.out.println("Care este username-ul contului pe care doriti sa il modificati?");
        String s1 = scanner.nextLine();
        Account a = accountRepository.getAccount(s1);
        if (Objects.isNull(a)){
            System.out.println("Nu am gasit acest user");
        }
        else{
            System.out.println("Care este noul username?");
            String s2 = scanner.nextLine();
            accountRepository.updateUsername(s2,a);
        }

    }

    public void showAccount(AccountRepository accountRepository){
        accountRepository.displayAccounts();
    }


    // TICKETS ADD - GET BY EVENT NAME
    public void addTicket(TicketRepository ticketRepository, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Cum se numeste evenimentul?");
        String s1 = scanner.nextLine();

        scanner.nextLine();
        System.out.println("Care este pretul biletului?");
        BigDecimal d1 = scanner.nextBigDecimal();

        Ticket t = new Ticket(s1,d1);

        ticketRepository.addTicket(t);

    }

    public void showTickets (TicketRepository ticketRepository)
    {
        ticketRepository.displayTickets();
    }

    public Ticket getTicketByEvent(String e){
        for(Ticket t : tickets){
            if(t.getEventName().equals(e)){
                return t;
            }
        }
        return null;
    }


    // VOUCHERS ADD

    public void addVoucher(Voucher new_voucher) {
        this.vouchers.add(new_voucher);
    }


}


