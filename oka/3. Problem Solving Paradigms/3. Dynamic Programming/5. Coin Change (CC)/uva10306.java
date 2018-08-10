import java.util.*;
import java.io.*;

public class Main {
    public static void main(String arg[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for(int i=0;i<T;i++){
            if(i>0)
                br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            int dp[][] = new int[S+1][S+1];
            for(int j=0;j<=S;j++){
                Arrays.fill(dp[j],-1);
            }
            dp[0][0] = 0;
            int min = Integer.MAX_VALUE;
            for(int j=0;j<m;j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                for(int x=0;x<=S;x++){
                    for(int y=0;y<=S;y++){
                        if(dp[x][y]==-1)
                            continue;
                        int nx = x+a;
                        int ny = y+b;
                        if(nx<=S&&ny<=S){
                            if(dp[nx][ny]==-1){
                                dp[nx][ny] = dp[x][y]+1;
                            }else{
                                dp[nx][ny] = Math.min(dp[nx][ny],dp[x][y]+1);
                            }
                            if(nx*nx+ny*ny==S*S){
                                min = Math.min(min,dp[nx][ny]);
                            }
                        }
                    }
                }
            }
            if(min==Integer.MAX_VALUE){
                System.out.println("not possible");
            }else{
                System.out.println(min);
            }
        }
    }  
}

