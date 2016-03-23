package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by allen on 3/23/16.
 */
public class Crawler {

    private static final String URL = "http://www.lsdag.cn/ces/action/fileInfo/v_list.do?root=03";
    private static final String RESULT_FILE = "result.html";
    private final String USER_AGENT = "Mozilla/5.0";

    private void sendGet() throws Exception {

        String url = "http://www.lsdag.cn/ces/action/fileInfo/v_list.do?root=04-01-01";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        //System.out.println(response.toString());

        PrintWriter writer = new PrintWriter(RESULT_FILE, "UTF-8");
        writer.println(response.toString());
        writer.close();

    }

    public static void main(String[] args) throws Exception {

        Crawler crawler = new Crawler();

        crawler.sendGet();

        System.out.println("Start Parsing...");
        Parser parser = new Parser();
        parser.parse(URL);
    }
}
