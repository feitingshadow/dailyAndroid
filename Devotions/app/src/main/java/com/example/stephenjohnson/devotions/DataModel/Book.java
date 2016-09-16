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

public class Book {

    private Map<String,String> mBibleDict;
    private ArrayList<ArrayList<String>> mChapters;
    private ArrayList<String> mVerses;
    private String mBook; //The current book to get
    private String mainTitleString; //every story has a title
    private String wholeStory;
    private Context ctx;
    private int currentChapter;

    public Book(Context activity) {

        ctx = activity;
        mChapters = new ArrayList<ArrayList<String>>();
        mVerses = new ArrayList<String>();

//        setBook("tobit");
//        mChapters.add("Tobit");

    }

    public int getResourceIDForBook(String name)
    {
        int resID = 0;
        switch (name) {
            case "Genesis":
                resID = R.raw.genesis;
                break;
            case "Exodus":
                resID = R.raw.exodus;
                break;
            case "Leviticus":
                resID = R.raw.lev;
                break;
            case "Numbers":
                resID = R.raw.num;
                break;
            case "Deuteronomy":
                resID = R.raw.deut;
                break;
            case "Joshua":
                resID = R.raw.joshua;
                break;
            case "Judges":
                resID = R.raw.judges;
                break;
            case "Ruth":
                resID = R.raw.ruth;
                break;
            case "1 Samuel":
                resID = R.raw.sam_1;
                break;
            case "2 Samuel":
                resID = R.raw.sam_2;
                break;
            case "1 Kings":
                resID = R.raw.kings_1;
                break;
            case "2 Kings":
                resID = R.raw.kings_2;
                break;
            case "1 Chronicles":
                resID = R.raw.chron_1;
                break;
            case "2 Chronicles":
                resID = R.raw.chron_2;
                break;
            case "Ezra":
                resID = R.raw.ezra;
                break;
            case "Nehemiah":
                resID = R.raw.psalms;
                break;
            case "Esther":
                resID = R.raw.esther;
                break;
            case "Job":
                resID = R.raw.job;
                break;
            case "Psalms":
                resID = R.raw.psalms;
                break;
            case "Proverbs":
                resID = R.raw.proverbs;
                break;
            case "Ecclesiastes":
                resID = R.raw.eccl;
                break;
            case "Song of Solomon":
                resID = R.raw.song;
                break;
            case "Isaiah":
                resID = R.raw.isaiah;
                break;
            case "Jeremiah":
                resID = R.raw.jeremiah;
                break;
            case "Lamentations":
                resID = R.raw.lament;
                break;
            case "Ezekial":
                resID = R.raw.ezekiel;
                break;
            case "Daniel":
                resID = R.raw.daniel;
                break;
            case "Hosea":
                resID = R.raw.hosea;
                break;
            case "Joel":
                resID = R.raw.joel;
                break;
            case "Amos":
                resID = R.raw.amos;
                break;
            case "Obadiah":
                resID = R.raw.obadiah;
                break;
            case "Jonah":
                resID = R.raw.jonah;
                break;
            case "Micah":
                resID = R.raw.micah;
                break;
            case "Nahum":
                resID = R.raw.nahum;
                break;
            case "Habakuuk":
                resID = R.raw.habakkuk;
                break;
            case "Zephaniah":
                resID = R.raw.zeph;
                break;
            case "Haggai":
                resID = R.raw.haggai;
                break;
            case "Zechariah":
                resID = R.raw.zech;
                break;
            case "Malachi":
                resID = R.raw.malachi; //End of old testament, begin old one.
                break;
            case "Matthew":
                resID = R.raw.matthew;
                break;
            case "Mark":
                resID = R.raw.mark;
                break;
            case "Luke":
                resID = R.raw.luke;
                break;
            case "John":
                resID = R.raw.john;
                break;
            case "Acts":
                resID = R.raw.acts;
                break;
            case "Romans":
                resID = R.raw.romans;
                break;
            case "1 Corinthians":
                resID = R.raw.cor_1;
                break;
            case "2 Corinthians":
                resID = R.raw.cor_2;
                break;
            case "Galatians":
                resID = R.raw.gal;
                break;
            case "Ephesians":
                resID = R.raw.eph;
                break;
            case "Philippians":
                resID = R.raw.philip;
                break;
            case "Collossians":
                resID = R.raw.col;
                break;
            case "1 Thessalonians":
                resID = R.raw.thes_1;
                break;
            case "2 Thessalonians":
                resID = R.raw.thes_2;
                break;
            case "1 Timothy":
                resID = R.raw.tim_1;
                break;
            case "2 Timothy":
                resID = R.raw.tim_2;
                break;
            case "Titus":
                resID = R.raw.titus;
                break;
            case "Philemon":
                resID = R.raw.philemon;
                break;
            case "Hebrews":
                resID = R.raw.hebrews;
                break;
            case "James":
                resID = R.raw.james;
                break;
            case "1 Peter":
                resID = R.raw.peter_1;
                break;
            case "2 Peter":
                resID = R.raw.peter_2;
                break;
            case "1 John":
                resID = R.raw.john_1;
                break;
            case "2 John":
                resID = R.raw.john_2;
                break;
            case "3 John":
                resID = R.raw.john_3;
                break;
            case "Jude":
                resID = R.raw.jude;
                break;
            case "Revelation":
                resID = R.raw.rev;
                break;
            default:
                break;
            return resID;
        }

    }

    public void setBook(String bookValue) {
        mBook = bookValue;
        mVerses.clear();
        mChapters.clear();


        currentChapter = 1; //for parsing
        wholeStory = readTextFromResource(this.getResourceIDForBook(mBook));
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
