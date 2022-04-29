package model;

import java.math.BigDecimal;

public class CreditCard {
    private String number;
    private int expirationYear;
    private int CVV;
    private BigDecimal sold;

    public CreditCard(){

    }

    public CreditCard(String number,int expirationYear,int CVV, BigDecimal sold){
        this.number = number;
        this.expirationYear = expirationYear;
        this.CVV = CVV;
        this.sold = sold;
    }

    public CreditCard(CreditCard card) {
        this.number = card.number;
        this.expirationYear = card.expirationYear;
        this.CVV = card.CVV;
        this.sold = card.sold;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public BigDecimal getSold() {
        return sold;
    }

    public void setSold(BigDecimal sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "number='" + number + '\'' +
                ", expirationYear=" + expirationYear +
                ", CVV=" + CVV +
                ", sold=" + sold +
                '}';
    }
}
