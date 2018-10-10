package ajerhamre.bontouchinterviewtask;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by antonjerhamre
 */

public class Dictionary {
    ArrayList<String> dictionary = new ArrayList<String>();

    public Dictionary(){
        Log.d("dict","created dictionary");
    }

    public void addWord(String word){
        dictionary.add(word);
    }

    public List<String> getWord(String prefix){

        // Linear search for finding all words in the dictionary with prefix
        List<String> prefixWords = new ArrayList<>();
        for(String s : dictionary)
            if(s.startsWith(prefix))
                prefixWords.add(s);

        /*
        Binary search for finding all words in the dictionary
        TODO: Does not work properly
         */

        /*
        int start = Collections.binarySearch(dictionary, prefix);
        Log.d("result search start index", Integer.toString(start));
        // index of prefix OR -(insertion point) - 1
        if (start < 0)  // prefix is not contained as a whole word
            start = -start - 1;
        int end = start;
        while (end < dictionary.size() && dictionary.get(end).startsWith(prefix))
            end++;
        List<String> prefixWords = dictionary.subList(start, end);
        //Log.d("result",prefixWords.get(0));
        Log.d("result size",Integer.toString(prefixWords.size()));
        */

        return prefixWords;
    }


}
