import java.util.*;
import java.io.*;
public class Main {                  
    public static int dp[][];
    public static boolean select[][];
    public static int d[],v[],time[];
 
    public static int maxGold(int n,int t){
        if(n==d.length||t==0)
            return 0;
      
        if(dp[n][t]!=-1){
            return dp[n][t];
        }
           
        if(time[n]>t){
            return dp[n][t] = maxGold(n+1,t);
        }else{
            int a = maxGold(n+1,t);
            int b = maxGold(n+1,t-time[n])+v[n];
            select[n][t] = (b>=a);
            return dp[n][t] = Math.max(a,b);
        }       
    }
    
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int line = 0;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            line++;
            
            if(line>1){
                System.out.println();
            }
            
            StringTokenizer st = new StringTokenizer(s);
            int t = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(br.readLine());
            select = new boolean[n][t+1];
            dp = new int[n+1][t+1];
            d = new int[n];
            v = new int[n];
            time = new int[n];
            for(int i=0;i<dp.length;i++)
                Arrays.fill(dp[i],-1);
            
            for(int i=0;i<n;i++){
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                d[i] = Integer.parseInt(st2.nextToken());
                v[i] = Integer.parseInt(st2.nextToken());
                time[i] = 3*w*d[i];
            }
            System.out.println(maxGold(0, t));
            int count = 0;
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<n;i++){
                if(select[i][t]){
                    sb.append(d[i]+" "+v[i]+"\n");
                    t -= time[i];
                    count++;
                }
            }
            System.out.println(count);
            System.out.print(sb);
            
            if(br.readLine()==null)
                break;
        }
    }
}