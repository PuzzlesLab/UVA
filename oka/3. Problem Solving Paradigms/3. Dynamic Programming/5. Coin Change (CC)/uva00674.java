import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c[] = {1,5,10,25,50};
        int dp[] = new int[7490];
        dp[0] = 1;
        for(int i=0;i<c.length;i++){
            for(int j=c[i];j<=7489;j++){
                dp[j] += dp[j-c[i]];
            }
        }
        String s;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            int n = Integer.parseInt(s);
            System.out.println(dp[n]);
        }
    }
       
}