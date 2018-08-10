import java.util.*;
import java.io.*;
public class Main {            
    
    public static void main(String[] args){   
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            if(n==0){
                break;
            }
            int arr[] = new int[n];
           
            for(int i=0;i<n;i++){
                arr[i] = in.nextInt();
            }
            
            int max_so_far = arr[0];
            int max_ending = 0;
            
            for(int i=0;i<n;i++){
                max_ending = max_ending+arr[i];
                if(max_ending<=0){
                    max_ending = 0;
                }
                max_so_far = Math.max(max_ending,max_so_far);
            }
            if(max_so_far>0)
                System.out.println("The maximum winning streak is "+max_so_far+".");
            else
                System.out.println("Losing streak.");
        }
    }
}
