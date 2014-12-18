/*
 * Copyright 2014 HP.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.w3blog.zpl.utils;

import fr.w3blog.zpl.model.ZebraLabel;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;

/**
 * this class generate real zebra label with all bar code by using web api
 *
 * web api adresse : http://api.labelary.com
 *
 * @author Billel LOUBAR loubar_billel@hotmail.com
 */
public class ZpltoImage {

    private final String USER_AGENT = "Mozilla/5.0";

    //GET http://api.labelary.com/v1/printers/{dpmm}/labels/{width}x{height}/{index}/{zpl}
    private static final String WEB_API_URL = "http://api.labelary.com/v1/printers/";

    /**
     * enumeration to specify file outputype
     */
    public enum FILE_TYPE {

        PDF, PNG;
    };

    public enum dpmm_config {

        SIX_DPMM("6dpmm"),
        HEIGHT_DPMM("8dpmm"),
        TWELVE_DPMM("12dpmm"),
        TWENTY_FOUR_DPMM("24dpmm");

        private String value = "";

        //Constructeur
        dpmm_config(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    };

    /**
     * Execute HTTP GET request Code source of this methode from
     * http://www.mkyong.com
     *
     * @param url
     * @throws Exception
     */
    private void sendGet(String url) throws Exception {
        System.out.println(url);               
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        con.getContent();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    // HTTP POST request
    private void sendPost(String url) throws Exception {

        //url = "https://selfsolve.apple.com/wcResults.do";
        
        URL obj = new URL(url.trim());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

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

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
    
    
   

    /**
     *
     * @param zebralabel the zebralabel after
     * @param filePath
     * @param file_type
     * @return true if all is ok
     */
    public static boolean getZplToPicture(ZebraLabel zebralabel, String filePath, FILE_TYPE file_type) throws Exception {

        //http://api.labelary.com/v1/printers/{dpmm}/labels/{width}x{height}/{index}/{zpl}
        if (zebralabel != null) {
            if (zebralabel.getImagePreview() != null) {

                if (zebralabel.getZplCode().length() < 500) {
                    System.out.println("Execute GET REQUEST");
                    //(new ZpltoImage()).sendPost(WEB_API_URL + dpmm_config.HEIGHT_DPMM + "/labels/4x3/0/" +URLEncoder.encode(zebralabel.getZplCode(),"UTF-8") );
                    (new ZpltoImage()).sendGet(WEB_API_URL + dpmm_config.HEIGHT_DPMM + "/labels/4x3/0/" + URLEncoder.encode(zebralabel.getZplCode(),"UTF-8"));
                } else {
                    System.out.println("Execute POST REQUEST");
                    (new ZpltoImage()).sendPost(WEB_API_URL + dpmm_config.HEIGHT_DPMM + "//labels//" + zebralabel.getWidthDots() + "x" + zebralabel.getHeightDots() + "" + zebralabel.getZplCode()+"/"+file_type);
                }

            }
        }

        return false;
    }

}
