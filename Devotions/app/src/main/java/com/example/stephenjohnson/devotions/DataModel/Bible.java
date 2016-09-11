package com.example.stephenjohnson.devotions.DataModel;

/**
 * Created by stephenjohnson on 9/10/16.
 */

import android.content.Context;
import android.util.Log;

import com.example.stephenjohnson.devotions.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class Bible {

    private Map<String,String> mBibleDict;
    private ArrayList<ArrayList<String>> mChapters;
    private ArrayList<String> mVerses;
    private String mBook; //The current book to get
    private String mainTitleString; //every story has a title
    private String wholeStory;
    private Context ctx;
    private int currentChapter;

    public Bible(Context activity) {

        ctx = activity;
        mChapters = new ArrayList<ArrayList<String>>();
        mVerses = new ArrayList<String>();

//        setBook("tobit");
//        mChapters.add("Tobit");

    }

    public void setBook(String bookValue) {
        mBook = bookValue;
        mVerses.clear();
        mChapters.clear();

        int resID = 0;
        switch (mBook) {
            case "tobit":
                resID = R.raw.psalms;
                break;
            default:
                break;
        }

        currentChapter = 1; //for parsing
        wholeStory = readTextFromResource(resID);
        int lastIndex = 0;
        int nextIndex = 0;
        nextIndex = wholeStory.indexOf("{", lastIndex);
        mainTitleString = wholeStory.substring(lastIndex, nextIndex);
        mainTitleString = mainTitleString.trim().replaceAll("\\s{2,}", " ");

        lastIndex = nextIndex;

        nextIndex = wholeStory.indexOf("{", lastIndex+1);
        Log.d("tagbible1","Printing the Bible Story 1 " + wholeStory + "\n");

        mVerses = new ArrayList<String>();
        mChapters.add(mVerses); //first chapter

        while (nextIndex != -1) {
            addVerseUsingSubstring(lastIndex, nextIndex);
            lastIndex = nextIndex;
            nextIndex = wholeStory.indexOf("{", lastIndex+1);
        }
        addVerseUsingSubstring(lastIndex, wholeStory.length());

        Log.d("tagbible1","Printing the Bible Story 1 " + wholeStory + "\n");

        Log.d("tagbible1","Printing the Bible Story " + mainTitleString + "\n");
        for (ArrayList<String> versesList : mChapters) {
            for (String s : versesList) {
               // Log.d("tagbible1", s + "\n");
            }
        }
    }

    private void addVerseUsingSubstring(int startIndex, int endIndex)
    {
        int chapterBreakIndex = wholeStory.indexOf(":", startIndex);
        int verseCloseIndex = wholeStory.indexOf("}", chapterBreakIndex);
        int readingChapter = Integer.parseInt(wholeStory.substring(startIndex+1, chapterBreakIndex));
        if (readingChapter != currentChapter)
        {
            mVerses = new ArrayList<String>();
            mChapters.add(mVerses);
            currentChapter = readingChapter;
        }
        //replace spacing in Psalms
        String verseString = wholeStory.substring(verseCloseIndex+1, endIndex-1).trim().replaceAll("\\s{2,}", " ");
        verseString = verseString.replaceAll("\\r\\n|\\r|\\n", " ");

        mVerses.add(verseString);
        Log.d("tagbible1","Printing the Bible verse: " + verseString + "\n");

    }


    private String readTextFromResource(int resourceID) {

        InputStream inputStream = ctx.getResources().openRawResource(resourceID);//getting the .txt file
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }
}
