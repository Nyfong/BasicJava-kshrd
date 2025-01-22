package controlflow.decision;

public class Decision6 {
    public static void main(String[] args) {
        String url = "https://www.kshrd.com.kh/";

        // Ternary operator to check if the URL uses HTTPS

        System.out.println( url.startsWith("https") ? "Secure URL (HTTPS)" : "Not Secure (HTTP)");
    }
}
