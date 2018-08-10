import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int T = 0;
        while(!(s=br.readLine()).equals("0 0")){
            T++;
            System.out.printf("SET %d:\n",T);
            StringTokenizer st = new StringTokenizer(s);
            int N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());
            int arr[] = new int[N];
            for(int i=0;i<N;i++){
                arr[i] = Integer.parseInt(br.readLine());
            }
            for(int i=0;i<Q;i++){
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int D = Integer.parseInt(st2.nextToken());
                int M = Integer.parseInt(st2.nextToken());
                int dp[][][] = new int[N+1][N+1][D];
                for(int j=0;j<=N;j++){
                    dp[j][0][0] = 1;
                }
                for(int j=1;j<=N;j++){
                    for(int k=1;k<=j;k++){
                        for(int m=0;m<D;m++){
                            dp[j][k][m] += dp[j-1][k-1][(D+(m-arr[j-1])%D)%D];
                            dp[j][k][m] += dp[j-1][k][m];
                        }
                    }
                }
                if(M>N){
                    System.out.printf("QUERY %d: 0\n",i+1);
                }else{
                    System.out.printf("QUERY %d: %d\n",i+1,dp[N][M][0]);
                }
            }
        }
    }
       
}

