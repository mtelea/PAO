package main;

import model.Account;
import model.Artist;
import model.Event;
import model.Ticket;
import repository.AccountRepository;
import repository.ArtistRepository;
import repository.EventRepository;
import repository.TicketRepository;
import service.ClientService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ClientService clientService = new ClientService();

        AccountRepository accountRepository = new AccountRepository();
        EventRepository eventRepository = new EventRepository();
        ArtistRepository artistRepository = new ArtistRepository();
        TicketRepository ticketRepository = new TicketRepository();

        accountRepository.createTable();
        eventRepository.createTable();
        artistRepository.createTable();
        ticketRepository.createTable();

        System.out.println("------ ACCOUNTS ------");
        accountRepository.displayAccounts();
        System.out.println("------ EVENTS ------");
        eventRepository.displayEvents();
        System.out.println("------ ARTISTS ------");
        artistRepository.displayArtists();
        System.out.println("------ TICKETS ------");
        ticketRepository.displayTickets();


        Scanner scanner = new Scanner(System.in);

        int o1 = 1;
        System.out.println("Buna ziua!");

        while (o1 != 0) {
            System.out.println("Ce operatie doriti sa efectuati?" +
                    "\n1 - Inregistreaza-te ca user nou" + // DONE
                    "\n2 - sterge cont" + //DONE
                    "\n3 - afiseaza toti userii" + // DONE
                    "\n4 - adauga eveniment" + // DONE
                    "\n5 - sterge eveniment" + // DONE
                    "\n6 - afiseaza toate evenimentele" + // DONE
                    "\n7 - afiseaza toti artistii care participa la un eveniment" + //DONE
                    "\n8 - sterge un artist din lineup-ul unui eveniment " + //DONE
                    "\n9 - achizitioneaza un bilet pentru un anumit eveniment " + //DONE
                    "\n10 - returneaza un bilet pentru un anumit eveniment " + // DONE
                    "\n11 - adauga un artist " + //DONE
                    "\n12 - afiseaza toti artistii" + //DONE
                    "\n13 - adauga bilet" + //DONE
                    "\n14 - afiseaza toate biletele disponibile" + //DONE
                    "\n15 - adauga un artist in lineup-ul unui eveniment " + //DONE
                    "\n16 - modifica username" + // DONE
                    "\n0 - exit");

            int o2;
            o2 = scanner.nextInt();

            if (o2 != 0) {
                switch (o2) {

                    // ADAUGA USER NOU
                    case 1: {

                        scanner.nextLine();
                        System.out.println("Introduceti username-ul: ");
                        String username = scanner.nextLine();
                        Account account = accountRepository.getAccount(username);

                        if (account == null) {

                            System.out.println("Nu sunteti inca inregistrat");
                            clientService.addAccount(accountRepository,scanner);
                        }
                        break;

                    }

                    // STERGE CONT
                    case 2:{

                        clientService.deleteAccount(accountRepository,scanner);
                        break;
                    }

                    // AFISEAZA TOTI USERII

                    case 3: {
                        clientService.showAccount(accountRepository);
                        break;
                    }


                    // ADAUGA EVENIMENT
                    case 4: {
                        clientService.addEvent(eventRepository,scanner);
                        break;
                    }

                    // STERGE EVENIMENT
                    case 5:{
                        clientService.deleteEvent(eventRepository,scanner);
                        break;
                    }

                    // AFISEAZA EVENIMENTELE

                    case 6: {
                        clientService.showEvents(eventRepository);
                        break;
                    }

                    // AFISEAZA ARTISTII DE LA UN EVENIMENT

                    case 7: {
                        System.out.println("Cum se numeste evenimentul?");
                        String s1 = scanner.nextLine();
                        Event e = clientService.getEventByName(s1);

                        if (e == null) {
                            System.out.println("Nu am gasit niciun eveniment cu acest nume");
                        }
                        else{
                            clientService.showEvent(e);
                        }
                    }

                    // STERGE UN ARTIST DIN LINEUP-UL UNUI EVENIMENT

                    case 8:{
                        System.out.println("Din cadrul carui eveniment doriti sa eliminati un artist?");
                        String s1 = scanner.nextLine();
                        scanner.nextLine();
                        Event e = clientService.getEventByName(s1);
                        if (e == null) {
                            System.out.println("Nu este niciun eveniment cu aceasta denumire!");
                        }
                        else {
                            System.out.println("Care este numele artistului pe care doriti sa il eliminati din lineup?");
                            String a1 = scanner.nextLine();
                            Artist a = e.getArtistByName(a1);
                            if (a == null){
                                System.out.println("Nu am gasit acest artist in lineup");
                            }
                            else {
                                e.deleteArtist(a);
                            }
                        }
                        break;
                    }


                    // CUMPARA BILET PENTRU UN ANUMIT EVENIMENT
                    case 9:{
                        System.out.println("Introduceti username-ul: ");
                        String username = scanner.nextLine();
                        Account account = accountRepository.getAccount(username);

                        if(account == null) {
                            System.out.println("Creati un cont inainte de a cumpara un bilet!");
                            break;
                        }
                        else {
                            scanner.nextLine();
                            System.out.println("Inroduceti parola: ");
                            String password = scanner.nextLine();

                            if(account.getPassword().equals(password)) {

                                scanner.nextLine();
                                System.out.println("Pentru ce eveniment doriti sa achizitionati biletul?");
                                String s1 = scanner.nextLine();
                                Event e = clientService.getEventByName(s1);
                                if (e == null) {
                                    System.out.println("Nu este niciun eveniment cu acest nume");
                                } else {

                                    scanner.nextLine();
                                    Ticket t = clientService.getTicketByEvent(e.getName());
                                    System.out.println("Pretul acestui bilet este de " + t.getPrice() + " RON. Confirmati ca doriti sa cumparati biletul? 1 - DA , 2 - NU ");
                                    Integer option = scanner.nextInt();

                                    if (option == 1) {
                                        account.buyTicket(t);
                                    }
                                    else {
                                        break;
                                    }

                                    break;

                                }
                            }
                            else{
                                System.out.println("Parola introdusa este gresita! Incercati iar. ");
                                break;
                            }
                        }

                    }

                    // RETURNEAZA BILETUL PENTRU UN ANUMIT EVENIMENT
                    case 10:{

                        scanner.nextLine();
                        System.out.println("Introduceti username-ul: ");
                        String username = scanner.nextLine();
                        Account account = accountRepository.getAccount(username);

                        if(account == null) {
                            System.out.println("Creati un cont inainte!");
                            break;
                        }
                        else {
                            scanner.nextLine();
                            System.out.println("Inroduceti parola: ");
                            String password = scanner.nextLine();

                            if(account.getPassword().equals(password)) {

                                scanner.nextLine();
                                System.out.println("Pentru ce eveniment doriti sa returnati biletul?");
                                String s1 = scanner.nextLine();
                                Event e = clientService.getEventByName(s1);
                                if (e == null) {
                                    System.out.println("Nu este niciun eveniment cu acest nume");
                                } else {
                                    scanner.nextLine();
                                    Ticket t = clientService.getTicketByEvent(e.getName());
                                    System.out.println("Pretul acestui bilet este de " + t.getPrice() + " RON. Confirmati ca doriti sa returnati biletul? 1 - DA , 2 - NU ");
                                    Integer option = scanner.nextInt();

                                    if (option == 1) {
                                        account.refundTicket(t);
                                    }
                                    else {
                                        break;
                                    }

                                    break;

                                }
                            }
                            else{
                                System.out.println("Parola introdusa este gresita! Incercati iar. ");
                                break;
                            }
                        }

                        break;

                    }

                    case 11: {
                        clientService.addArtist(artistRepository,scanner);
                        break;
                    }

                    case 12: {
                        clientService.showArtists(artistRepository);
                        break;
                    }

                    case 13: {
                        clientService.addTicket(ticketRepository, scanner);
                        break;
                    }

                    case 14: {
                        clientService.showTickets(ticketRepository);
                        break;
                    }

                    case 15: {
                        System.out.println("Cum se numeste evenimentul?");
                        String s1 = scanner.nextLine();
                        Event e = clientService.getEventByName(s1);

                        if (e == null) {
                            System.out.println("Nu am gasit niciun eveniment cu acest nume");
                        }
                        else{
                            System.out.println("Cum se numeste artistul?");
                            String s2 = scanner.nextLine();
                            Artist a = artistRepository.getArtist(s2);

                            e.addArtist(a);
                        }
                        break;

                    }

                    case 16: {
                        clientService.updateAccount(accountRepository,scanner);
                        break;
                    }



                    default:
                        System.out.println("");

                }

            }

        }


    }

}
