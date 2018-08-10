import java.util.*;
import java.io.*;
public class Main {            
    
    public static void main(String[] args)throws IOException{   
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int i=1;i<=T;i++){
            int n = in.nextInt()-1;
            int r[] = new int[n];
            for(int j=0;j<n;j++){
                r[j] = in.nextInt();
            }
            int max_ending = 0;
            int max_so_far = 0;
            int start = 1;
            int end = 1;
            int max_start = 0;
            int max_end = 0;
            for(int j=0;j<n;j++){
                max_ending = max_ending+r[j];
                if(max_ending<0){
                    max_ending = 0;
                    start = j+2;
                    end = j+2;
                }
                if(max_so_far<max_ending){
                    max_so_far = max_ending;
                    end = j+2;
                    max_start = start;
                    max_end = end;
                }else if(max_so_far==max_ending){
                    end = j+2;
                    if(max_end-max_start<end-start){
                        max_start = start;
                        max_end = end;
                    }
                }
            }
            if(max_so_far>0){
                System.out.printf("The nicest part of route %d is between stops %d and %d\n",i,max_start,max_end);
            }else{
                System.out.println("Route "+i+" has no nice parts");
            }
          
        }
    }
}