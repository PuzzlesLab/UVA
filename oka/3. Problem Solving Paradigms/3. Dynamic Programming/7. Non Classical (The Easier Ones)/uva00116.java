import java.util.*;
import java.io.*;

public class Main { 
    public static boolean track(int path[][],int r1,int r2,int c,int base){
        if(base>=c||c<=0){
            return r1<=r2;
        }
        int sr1 = r1;
        int sr2 = r2;
        for(int i=c;i>base;i--){
            sr1 = path[sr1][i];
            sr2 = path[sr2][i];
        }
        if(sr1<sr2)
            return true;
        else if(sr1>sr2)
            return false;
        else
            return track(path,r1,r2,c,base+1);
        
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            StringTokenizer st = new StringTokenizer(s);
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int g[][] = new int[r][c];
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    while(!st.hasMoreTokens())
                        st = new StringTokenizer(br.readLine());                  
                    g[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int dp[][] = new int[r][c];
            int path[][] = new int[r][c];
            for(int j=0;j<c;j++){
                for(int k=0;k<r;k++){
                    if(j==0){
                        dp[k][j] = g[k][j];
                    }else{
                        int index = k;
                        int min = dp[index][j-1];
                        int minIndex = index;
                        int up = (r+index-1)%r;
                        int down = (r+index+1)%r;
                        
                        if(min>dp[up][j-1]){
                            min = dp[up][j-1];
                            minIndex = up;
                        }else if(min==dp[up][j-1]){
                            boolean can = track(path,minIndex,up,j-1,0);
                            if(!can){
                                minIndex = up;
                            }
                        }
                        if(min>dp[down][j-1]){
                            path[k][j] = down;
                            min = dp[down][j-1];
                            minIndex = down;
                        }else if(min==dp[down][j-1]){
                            boolean can = track(path,minIndex,down,j-1,0);
                            if(!can){
                                minIndex = down;
                            }
                        }
                        dp[k][j] = min;
                        path[k][j] = minIndex;
                        dp[k][j] += g[k][j];
                    }
                }
            }
            int end = 0;
            int min = dp[0][c-1];
            for(int i=1;i<r;i++){
                if(dp[i][c-1]<min){
                    end = i;
                    min = dp[i][c-1];
                }else if(dp[i][c-1]==min){
                    if(!track(path,end,i,c-1,0)){
                        end = i;
                    }
                }
            }
            String p = Integer.toString(end+1);
            for(int i=c-1;i>0;i--){
                end = path[end][i];
                p = (end+1)+" "+p;
            }
            System.out.println(p);
            System.out.println(min);
        }
    }
}

