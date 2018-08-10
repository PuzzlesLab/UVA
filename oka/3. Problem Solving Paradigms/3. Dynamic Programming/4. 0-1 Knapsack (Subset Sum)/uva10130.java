import java.util.*;
import java.io.*;
public class Main {                  
       
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            int N = Integer.parseInt(br.readLine());
            int p[] = new int[N];
            int w[] = new int[N];
            for(int j=0;j<N;j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                p[j] = Integer.parseInt(st.nextToken());
                w[j] = Integer.parseInt(st.nextToken());               
            }
            int dp[] = new int[31];
            
            for(int j=0;j<N;j++){
                for(int k=30;k>=0;k--){
                    if(k-w[j]>=0){
                        dp[k] = Math.max(dp[k],dp[k-w[j]]+p[j]);
                    }   
                }
            }
            
            int G = Integer.parseInt(br.readLine());
            int MW[] = new int[G];
            for(int j=0;j<G;j++){
                MW[j] = Integer.parseInt(br.readLine());
            }
            int max = 0;
            for(int j=0;j<G;j++){
                max += dp[MW[j]];
            }
            System.out.println(max);
        }
    }
}