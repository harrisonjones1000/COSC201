package cosc201.lab06;

import java.util.*;

public class myClass{
    public static void main(String[] args){
        d(1);
    }

    public static String generateString(char[] c, int n){
        Random r = new Random();
        String s = "";
        for(int i=0; i<n; i++){
            s += c[r.nextInt(c.length-6)];
        }
        return s;
    }

    public static int distinctStrings(String s){
        HashSet<String> h = new HashSet<>();
        h.add("");
        for(int n=s.length(); n>0; n--){
            distinctStrings(s, n, h);
        }
        return h.size();
    }

    public static void distinctStrings(String s, int n, HashSet h){
        int length = s.length();
        for(int i = 0; i<=length-n; i++){
            h.add(s.substring(i,i+n));
        }
    }

    public static void c(){
        char[] c = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    
        for(int l=2; l<=20; l+=2){
            double avg=0;
            String s;
            for(int k=0; k<100; k++){
                s = generateString(c,l);
                avg+=(double)distinctStrings(s)/100;
            }
        System.out.println(avg);
        }
    }

    public static String generateAB(int n, double p){
        Random r = new Random();
        String s = "";
        for(int i=0; i<n; i++){
            if(r.nextInt(1000)+1>p*1000){
                s+="A";
            }else{
                s+="B";
            }
        }
        return s;
    }

    public static void d(double p){
        for(int l=2; l<=20; l+=2){
            double avg=0;
            String s;
            for(int k=0; k<100; k++){
                s = generateAB(l,p);
                avg+=(double)distinctStrings(s)/100;
            }
        System.out.println(avg);
        }
    }
}