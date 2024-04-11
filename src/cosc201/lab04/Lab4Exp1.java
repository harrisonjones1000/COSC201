package cosc201.lab04;
import java.util.Random;

public class Lab4Exp1 {
    public static void main(String[] args){
        Q4(11,10000);
    }

    public static void shuffle(int[] d){
        Random rnd = new Random();
        int[] a = new int[(d.length+d.length%2)/2];
        int[] b = new int[(d.length-d.length%2)/2];
        for(int i=0; i<d.length; i++){
            if(i<a.length){
                a[i]=d[i];
            }else{
                b[i-a.length]=d[i];
            }
        }
        int pos = 0;
        int apos = 0;
        int bpos = 0;
        while(pos<d.length){
            if(apos==a.length){
                d[pos]=b[bpos];
                pos++;
                bpos++;
            }else if(bpos==b.length){
                d[pos]=a[apos];
                pos++;
                apos++;
            }else if(rnd.nextInt(2)==1){
                d[pos]=a[apos];
                pos++;
                apos++;
            }else{
                d[pos]=b[bpos];
                pos++;
                bpos++;
            }
        }
    }

    public static void shuffle(int[] d, int k){
        for(int i=0; i<k; i++){
            shuffle(d);
        }
    }

    public static void Q4(int K, int t){
        for(int k = 1; k<=K; k++){ 
            double avg = 0;
            for(int i=0; i<t; i++){
                
                int[] d = new int[52];
                for(int h = 0; h<d.length; h++){
                    d[h]=h;
                }

                shuffle(d,k);
                for(int j=0; j<52; j++){
                    if(d[j]==0){
                        avg+=(double)j/t;
                    }
                }
            }
            System.out.println(avg);   
        }
    }
}