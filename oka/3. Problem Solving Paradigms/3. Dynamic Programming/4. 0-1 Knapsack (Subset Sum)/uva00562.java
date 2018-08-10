import java.util.*;
import java.io.*;
public class Main {                  
    
    public static void main(String[] args)throws IOException{   
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int T = Integer.parseInt(br.readLine());
       for(int i=0;i<T;i++){
           int n = Integer.parseInt(br.readLine());
           int arr[] = new int[n];
           StringTokenizer st = new StringTokenizer(br.readLine());
           int total = 0;
           for(int j=0;j<n;j++){
               arr[j] = Integer.parseInt(st.nextToken());
               total += arr[j];
           }
           int s = total/2;
           boolean v[] = new boolean[s+1];
           v[0] = true;
           for(int j=0;j<n;j++){
               for(int k=s;k>=0;k--){
                   if(!v[k]&&k-arr[j]>=0){
                       v[k] = v[k-arr[j]];
                   }
               }
           }
           for(int j=s;j>=0;j--){
               if(v[j]){
                   System.out.println(total-2*j);
                   break;
               }
           }
       }
    }
}