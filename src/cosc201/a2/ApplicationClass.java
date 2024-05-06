package cosc201.a2;

import java.util.*;

public class ApplicationClass {
    public static void main(String[] args){
        MyWordBank wb = new MyWordBank();

        ArrayList<String> h = new ArrayList<>(Arrays.asList("dog", "yak", "emu", "cat"));

        wb.addWord("iguana",4);
        wb.addWord("jakal",5);
        wb.addWord("boba",6);
        wb.addWord("frog",1);
        wb.addWord("goat",2);
        wb.addWord("bat",8);
        wb.addWord("hippo",0);
        wb.addWord("ant",7);
        wb.addWord("elephant",11);
        wb.addWord("lepoard",6); 
        wb.addWords(h,10);

        System.out.println(wb.altToString());

        System.out.println(wb.getWords(10));
        System.out.println(wb.getWords(5,10));
        System.out.println(wb.getMaximumWord(wb.getDocument()));
        System.out.println(wb.getMinimumWord(wb.getDocument()));
        System.out.println(wb.getMedianWord(wb.getDocument()));
        System.out.println(wb.getMeanWord(wb.getDocument()));
        System.out.println(wb.getDocumentValue(wb.getDocument()));
    }
}
