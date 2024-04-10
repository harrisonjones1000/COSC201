package cosc201.a1;
import java.util.Random;
import cosc201.utilities.*;

public class Experiments {
    public static void main(String[] args){
        /*
        for(int i = 20; i<=160; i+=10){
          System.out.println(time(125,i,1000)*10e-9);
        }
        */
        
        //System.out.println(time(1000,500,1000)*10e-9);

        for(int i=5; i<=80; i+=5){
            System.out.println(time3(i,100));
        }
        
    }

    public static double time(int t, int n, int k){
        int count = 0;
        double avgTime = 0;
        Random rnd = new Random();
        int days;
        Timer time = new Timer();
        double minTime = 1e9;
        double maxTime = 0;
        double T = 0;
        while(count<k){
            time.start();
            BaseSimulator base = new BaseSimulator(new Base(n));
            days = 0;
            while(base.safe()&&days<t){
                base.open(rnd.nextInt(n*n)+1);
                days++;
            }
            T=time.stop();

            if(days==t){
                avgTime += T/k;
                if(T<minTime){
                    minTime=T;
                }
                if(T>maxTime){
                    maxTime=T;
                }
                count++;
                //System.out.println(count);
            }
        }
        System.out.println(minTime*10e-9 + "\n" + maxTime*10e-9);
        return avgTime;
    }

    public static double time2(int t, int n, int k){
        int count = 0;
        double avgTime = 0;
        Random rnd = new Random();
        int days;
        while(count<k){
            BaseSimulator base = new BaseSimulator(new Base(n));
            days = 0;
            while(base.safe()&&days<t){
                base.open(rnd.nextInt(n*n)+1);
                days++;
            }

            if(days==t){
                avgTime += base.time/k;
                count++;
            }
        }
        return avgTime;
    }

    public static double time3(int n, int k){
        int count = 0;
        double avgDays = 0;
        Random rnd = new Random();
        int days;
        while(count<k){
            BaseSimulator base = new BaseSimulator(new Base(n));
            days = 0;
            while(base.safe()){
                base.open(rnd.nextInt(n*n)+1);
                days++;
            }

            avgDays += (double)days/k;
            count++;
            
        }
        return avgDays;
    }
}