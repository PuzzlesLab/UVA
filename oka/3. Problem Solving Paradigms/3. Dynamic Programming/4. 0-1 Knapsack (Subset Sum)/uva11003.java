import java.io.*;
import java.util.*;

public class Main {
    public static void main(String arg[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(!(s=br.readLine().trim()).equals("0")){
            int N = Integer.parseInt(s);
            int w[] = new int[N];
            int load[] = new int[N];
            for(int i=0;i<N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                w[i] = Integer.parseInt(st.nextToken());
                load[i] = Integer.parseInt(st.nextToken());
            }
            int dp[] = new int[6001];
            Arrays.fill(dp,-1);
            dp[0] = 0;
            int max = 0;
            for(int i=N-1;i>=0;i--){
                for(int j=load[i];j>=0;j--){
                    int cur = j+w[i];
                    if(dp[j]!=-1&&cur<=6000){
                        dp[cur] = Math.max(dp[cur],dp[j]+1);
                        max = Math.max(max,dp[cur]);
                    }
                }
            }
            System.out.println(max);
        }
    }
}