import java.util.*;
import java.io.*;

public class Main { 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            StringTokenizer st = new StringTokenizer(s);
            int v[] = new int[2];
            v[0] = Integer.parseInt(st.nextToken());
            v[1] = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int dp[] = new int[t+1];
            Arrays.fill(dp,-1);
            dp[0] = 0;
            for(int i=0;i<2;i++){
                for(int j=v[i];j<=t;j++){
                    if(dp[j-v[i]]!=-1){
                        dp[j] = Math.max(dp[j],dp[j-v[i]]+1);
                    }
                }
            }
            if(dp[t]<=0){
                int i = t;
                while(i>=0&&dp[i]<=0){
                    i--;
                }
                if(i>=0)
                    System.out.println(dp[i]+" "+(t-i));
                else
                    System.out.println(0+" "+t);
            }else
                System.out.println(dp[t]);
        }
    }
}

