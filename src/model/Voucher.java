package model;

public class Voucher {

    private String code;
    private int discount;

    public Voucher(){

    }

    public Voucher(String code, int discount) {
        this.code = code;
        this.discount = discount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
