package daiBeans;

import java.io.BufferedReader;
import java.io.DataOutputStream;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.altaprise.vawr.ui.RootFrame;

public class PhoneHome {

    private final static String USER_AGENT = "Mozilla/5.0";


    public PhoneHome() {
        //SendMsg msg = new SendMsg();
        Thread t = new Thread(new SendMsg());
        t.start();
    }


    private static class SendMsg implements Runnable {

        public void run() {
            //PhoneHome http = new PhoneHome();
            //System.out.println("Testing 1 - Send Http GET request");
            try {
                Thread.sleep(2000);
                PhoneHome.sendGet();
            } catch (Exception e) {
                System.out.println("local mode");
                //e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {

        
        PhoneHome ph = new PhoneHome();
        //for (int i = 0; i < 10; i++) {
            //System.out.println(i);
            Thread.sleep(1000);         
            
        //}

    }

    // HTTP GET request
    public static void sendGet() throws Exception {

        String url = "http://www.altaprise.com/vawr-phone-home.php?" + "host=" + getHostName() +
            //"&os=" + System.getProperty("os.name") +
            "&user=" + System.getProperty("user.name") + "&vawr-version=" + RootFrame.getProgramVersion();


        //System.out.println(url);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        //System.out.println(response.toString());
    }

    // HTTP POST request
    private static void sendPost() throws Exception {

        //String url = "https://selfsolve.apple.com/wcResults.do";
        String url = "http://localhost/vawr-phone-home.php";
        URL obj = new URL(url);
        //HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
        //String urlParameters = "";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    public static String getHostName() {
        InetAddress ip = null;
        String hostname = "unknown";
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.toString();
            //System.out.println("Your current IP address : " + ip);
            //System.out.println("Your current Hostname : " + hostname);
        } catch (UnknownHostException e) {
            //e.printStackTrace();
        }
        return hostname;
    }
}
