import java.io.*;
import java.util.*;
public class Main {    
    public static void main(String[] args)throws IOException{      
       Scanner in = new Scanner(System.in);
       int n ;
       while((n=in.nextInt())!=0){
           int max = 0;
           int tmax = 0;
           for(int i=0;i<n;i++){
               max += in.nextInt();
               max = Math.max(0,max);
               tmax = Math.max(tmax,max);
           }
           if(tmax==0){
               System.out.println("Losing streak.");
           }else{
               System.out.printf("The maximum winning streak is %d.\n",tmax);
           }
       }
    }
}