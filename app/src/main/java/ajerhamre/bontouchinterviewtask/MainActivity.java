package ajerhamre.bontouchinterviewtask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonjerhamre
 */

public class MainActivity extends AppCompatActivity{

    EditText search;
    ListView searchResults;
    private ArrayAdapter<String> arrayAdapter;

    List<String> suggestedWords = new ArrayList<String>();

    Dictionary dict;
    NetworkHandler net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI elements
        search = (EditText)findViewById(R.id.input_search);
        searchResults = (ListView)findViewById(R.id.search_results);

        // Creates dictionary
        dict = new Dictionary();

        // downloads dictionary file to device
        net = new NetworkHandler(dict);
        Thread t = new Thread(net);
        t.start();

        // Setup search result in a listView
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestedWords);
        searchResults.setAdapter(arrayAdapter);

        // TextChangeListener for listening to changes in searchbar
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                List<String> res = dict.getWord(charSequence.toString());

                if (res != null) {
                    suggestedWords = res;

                    // update data in searchResults ListView
                    arrayAdapter.clear();
                    arrayAdapter.addAll(suggestedWords);

                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
