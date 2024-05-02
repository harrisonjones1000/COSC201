package cosc201.a2;

import java.util.*;

public class ApplicationClass {
    public static void main(String[] args){
        MyWordBank wb = new MyWordBank();

        wb.addWord("ant", 5);
        wb.addWord("bee", 6);

        ArrayList<String> h = new ArrayList<>(Arrays.asList("dog", "bat", "zebra", "yak"));

        wb.addWord("emu", 1);
        wb.addWord("horse", 2);

        wb.addWords(h, 10);

        System.out.println(wb.altToString());

        wb.addWord("horse", 11);

        System.out.println(wb.altToString());
    }
}
