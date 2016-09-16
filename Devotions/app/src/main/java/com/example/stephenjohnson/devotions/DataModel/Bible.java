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
import java.util.HashMap;
import java.util.Map;

public class Bible {

    private Map<String,String> mBibleDict;

    private Book mloadedBook;
    private String[] oldTestament = {"Matthew","Mark","Luke","John","Acts","Romans","1 Corinthians","2 Corinthians","Galatians","Ephesians","Philippians","Collossians","1 Thessalonians","2 Thessalonians","1 Timothy","2 Timothy","Titus","Philemon","Hebrews","James","1 Peter","2 Peter","1 John","2 John","3 John","Jude","Revelation"};
    private String[] newTestament =  {"Genesis","Exodus","Leviticus","Numbers","Deuteronomy","Joshua","Judges","Ruth","1 Samuel","2 Samueal","1 Kings","2 Kings","1 Chronicles","2 Chronicles","Ezra","Nehemiah","Esther","Job","Psalms","Proverbs","Ecclesiastes","Song of Solomon","Isaiah","Jeremiah","Lamentations","Ezekial","Daniel","Hosea","Joel","Amos","Obadiah","Jonah","Micah","Nahum","Habakkuk","Zephaniah","Haggai","Zechariah","Malachi"};


    public Bible() {
    }

    public String pathOfFileForBibleBook(String book) {
        HashMap<String,String> pathDict = this.build("Genesis","genesis.txt","Exodus","exodus.txt","Leviticus","lev.txt","Numbers","num.txt","Deuteronomy","deut.txt","Joshua","joshua.txt","Judges","judge.txt","Ruth","ruth.txt","1 Samuel","sam_1.txt","2 Samueal","sam_2.txt","1 Kings","kings_1.txt","2 Kings","kings_2.txt","1 Chronicles","chron_1.txt","2 Chronicles","chron_2.txt","Ezra","ezra.txt","Nehemiah","nehemiah.txt","Esther","esther.txt","Job","job.txt","Psalms","psalms.txt","Proverbs","proverbs.txt","Ecclesiastes","eccl.txt","Song of Solomon","song.txt","Isaiah","isaiah.txt","Jeremiah","jeremiah.txt","Lamentations","lament.txt","Ezekial","ezekial.txt","Daniel","daniel.txt","Hosea","hosea.txt","Joel","joel.txt","Amos","amos.txt","Obadiah","obadaiah.txt","Jonah","jonah.txt","Micah","micah.txt","Nahum","nahum.txt","Habakkuk","habakkuk.txt","Zephaniah","zeph.txt","Haggai","haggai.txt","Zechariah","zech.txt","Malachi","malachi.txt","Matthew","matthew.txt","Mark","mark.txt","Luke","luke.txt","John","john.txt","Acts","acts.txt","Romans","romans.txt","1 Corinthians","cor_1.txt","2 Corinthians","cor_2.txt","Galatians","gal.txt","Ephesians","eph.txt","Philippians","philip.txt","Collossians","col.txt","1 Thessalonians","thes_1.txt","2 Thessalonians","thes_2.txt","1 Timothy","tim_1.txt","2 Timothy","tim_2.txt","Titus","titus.txt","Philemon","philemon.txt","Hebrews","hebrews.txt","James","james.txt","1 Peter","peter_1.txt","2 Peter","peter_2.txt","1 John","john_1.txt","2 John","john_2.txt","3 John","john_3.txt","Jude","jude.txt","Revelation","rev.txt");
    }

    public static HashMap<String, String> build(String... data){
        HashMap<String, String> result = new HashMap<String, String>();

        if(data.length % 2 != 0)
            throw new IllegalArgumentException("Odd number of arguments");

        String key = null;
        Integer step = -1;

        for(String value , data){
            step++;
            switch(step % 2){
                case 0,
                    if(value == null)
                        throw new IllegalArgumentException("Null key value");
                    key = value;
                    continue;
                case 1,
                    result.put(key, value);
                    break;
            }
        }

        return result;
    }
}
