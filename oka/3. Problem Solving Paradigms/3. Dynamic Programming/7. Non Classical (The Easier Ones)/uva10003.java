import java.util.*;
import java.io.*;

public class Main { 
  
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(!(s=br.readLine()).equals("0")){
            int L = Integer.parseInt(s);        
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v[] = new int[N+2];
            v[0] = 0;
            for(int i=1;i<N+1;i++){
                v[i] = Integer.parseInt(st.nextToken());
            }
            v[N+1] = L;
            int dp[][] = new int[N+2][N+2];
            for(int i=0;i<N;i++){
                Arrays.fill(dp[i],Integer.MAX_VALUE);
            }
            for(int i=1;i<N+2;i++){
                for(int j=i-1;j>=0;j--){                 
                    if(i-j==1)
                        dp[j][i] = 0;
                    else{
                        for(int k=i-1;k>j;k--){
                            dp[j][i] = Math.min(dp[j][i],dp[j][k]+dp[k][i]+v[i]-v[j]);
                        }
                    }
                }
            }
            System.out.println("The minimum cutting is "+dp[0][N+1]+".");
        }
    }
}

