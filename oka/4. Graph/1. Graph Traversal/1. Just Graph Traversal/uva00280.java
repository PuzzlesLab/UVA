import java.util.*;
import java.io.*;

public class Main {
    public static boolean visit[];
    
    public static void dfs(int i,boolean g[][]){
        for(int j=0;j<g.length;j++){
            if(g[i][j]){
                if(!visit[j]){
                    visit[j] = true;
                    dfs(j,g);
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(!(s=br.readLine()).equals("0")){
            int n = Integer.parseInt(s);
            boolean g[][] = new boolean[n][n];
            String line;
            while(!(line=br.readLine()).equals("0")){
                StringTokenizer st = new StringTokenizer(line);
                int start = Integer.parseInt(st.nextToken())-1;
                String token;
                while(!(token=st.nextToken()).equals("0")){
                    int end = Integer.parseInt(token)-1;
                    g[start][end] = true;
                }
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            for(int i=0;i<t;i++){
                visit = new boolean[n];
                int start = Integer.parseInt(st.nextToken())-1;
                dfs(start,g);
                StringBuilder sb = new StringBuilder();
                int count = 0;
                for(int j=0;j<n;j++){
                    if(!visit[j]){
                        sb.append(" "+(j+1));
                        count++;
                    }
                }
                System.out.println(count+sb.toString());
            }            
        }
        
    }
   
}
