import java.util.*;
import java.io.*;

public class Main { 
    public static int remainder(int a,int b){
        if(a>=0)
            return a%b;
        else{
            return (b+a%b)%b;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            boolean dp[][] = new boolean [N][K];
            for(int j=0;j<N;j++){
                int n = Integer.parseInt(st.nextToken());
                int num = remainder(n,K);
                if(j==0){
                    dp[j][num] = true;
                }else{
                    for(int k=0;k<K;k++){
                        if(dp[j-1][k]){
                            int r1 = remainder(k+num,K);
                            int r2 = remainder(k-num,K);
                            dp[j][r1] = true;
                            dp[j][r2] = true;
                        }
                    }
                }
            }
            if(dp[N-1][0]){
                System.out.println("Divisible");
            }else{
                System.out.println("Not divisible");
            }
        }
    }
}

