package tn.esprit.spring.entities;

public class QrCodeRequest {
    private static String phone;

    public static String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
