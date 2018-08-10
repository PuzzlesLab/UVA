import java.util.*;
import java.io.*;
public class Main {                  
       
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int coin[] = {5,10,20,50,100,200,500,1000,2000,5000,10000};
        long dp[] = new long[30001];       
        dp[0] = 1;
        
        for(int i=0;i<coin.length;i++){
            for(int j=coin[i];j<=30000;j++){
                dp[j] += dp[j-coin[i]];
            }
        }
        while(!(s=br.readLine()).equals("0.00")){
            double n = Double.parseDouble(s);
            // Fix Rounding Error
            int N = (int)(n*100+0.005);
            System.out.printf("%6.2f%17d\n",n,dp[N]);
        }
    }
}