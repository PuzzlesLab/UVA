import java.util.*;
import java.io.*;
import java.math.BigInteger;
public class Main {            
    
    public static void main(String[] args)throws IOException{   
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String s;
       while((s=br.readLine())!=null&&!s.isEmpty()){
           StringTokenizer st = new StringTokenizer(s);
           String n;
           ArrayList<BigInteger> arr = new ArrayList<>();
           while(!(n=st.nextToken()).equals("-999999")){
               arr.add(new BigInteger(n));
           }
           BigInteger max_so_far = arr.get(0);
           BigInteger max_ending = BigInteger.ONE;
           
           for(int i=0;i<arr.size();i++){
               max_ending = max_ending.multiply(arr.get(i));
               if(max_ending.compareTo(max_so_far)>0){
                   max_so_far = max_ending;
               }
               if(max_ending.equals(BigInteger.ZERO)){
                   max_ending = BigInteger.ONE;
               }              
           }
           max_ending = BigInteger.ONE;
           for(int i=arr.size()-1;i>=0;i--){
               max_ending = max_ending.multiply(arr.get(i));               
               if(max_ending.compareTo(max_so_far)>0){
                   max_so_far = max_ending;
               }
               if(max_ending.equals(BigInteger.ZERO)){
                   max_ending = BigInteger.ONE;
               }
           }
           System.out.println(max_so_far);
       }
    }
}
