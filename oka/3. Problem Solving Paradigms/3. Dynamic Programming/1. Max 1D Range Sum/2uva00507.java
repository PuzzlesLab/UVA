import java.io.*;
import java.util.*;

public class Main {
      
    public static void main(String[] args)throws IOException{      
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int i=1;i<=n;i++){
            int r = in.nextInt();           
            int max = 0;
            int tmax = 0;
            int start = 1;// start with stop 1
            int end = 0;
            int tstart = 0;
            int tend = 0;            
            for(int j=0;j<r-1;j++){
                max += in.nextInt();
                if(max<0){
                    max = 0;
                    start = j+2;
                }else if(max>tmax){
                    tmax = max;
                    end = j+2;
                    tstart = start;
                    tend = end;
                }else if(max==tmax){
                    end = j+2;
                    if(end-start>tend-tstart){
                        tstart = start;
                        tend = end;
                    }
                }
            }
            if(tmax==0){
                System.out.printf("Route %d has no nice parts\n",i);
            }else{
                System.out.printf("The nicest part of route %d is between stops %d and %d\n",i,tstart,tend);
            }
        }
    }
}
