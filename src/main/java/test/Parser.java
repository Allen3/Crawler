package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by allen on 3/23/16.
 */
public class Parser {

    public void parse(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements tables = document.select("body table");

            System.out.println(tables.size());

            for (int i = 0;i < tables.size();i ++) {
                Element table = tables.get(i);
//                System.out.print(table.attributes().size() + " | ");
//                for (int j = 0;j < table.attributes().size();j ++) {
//                    System.out.print(table.attributes().asList().get(j).getKey() + " ");
//                }
//                System.out.print("\n");

                if (table.attributes().size() == 6 && table.attributes().get("class").equals("pn-ltable")) {
                    Element thead = table.getElementsByAttributeValue("class", "pn-lthead").first();
                    Element tbody = table.getElementsByAttributeValue("class", "pn-ltbody").first();

                    //Elements titleList = thead.children().get(0).children();
                    Elements contentList = tbody.children();

                    //System.out.println("titleList.size = " + titleList.size());
                    System.out.println("contentList.size = " + contentList.size());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
