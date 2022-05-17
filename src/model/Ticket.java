package model;

import java.math.BigDecimal;

public class Ticket {

    private String eventName;
    private String event;
    private BigDecimal price;

    public Ticket(){

    }

    public Ticket(String event, BigDecimal price){
        this.event = event;
        this.price = price;
    }

    public String getEventName(){
        return eventName;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "event='" + event + '\'' +
                ", price=" + price +
                '}';
    }

}
