package ajerhamre.bontouchinterviewtask;


import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by antonjerhamre
 */

public class NetworkHandler implements Runnable {

    Dictionary dict;

    public NetworkHandler(Dictionary dict){
        this.dict = dict;
    }

    @Override
    public void run() {
        Log.d("dl", "starting download");
        ArrayList<String> dictionary = new ArrayList<String>();

        try {
            URL url = new URL("http://runeberg.org/words/ss100.txt");
            //opens the connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(60000); // timing out in a minute

            // For reading the textfile
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF8"));

            String str;

            // Store the content of the file in the dictionary
            while ((str = in.readLine()) != null) {
                dict.addWord(str);
            }
            in.close();
            Log.d("dl","download complete");
        } catch (Exception e) {
            Log.d("dl error",e.toString());
        }
    }


}
