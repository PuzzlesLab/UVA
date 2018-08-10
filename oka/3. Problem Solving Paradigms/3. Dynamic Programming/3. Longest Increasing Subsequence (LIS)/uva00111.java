import java.util.*;
import java.io.*;
public class Main {                  
    // question explain for event order, but input is chronological order
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int S[] = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            S[Integer.parseInt(st.nextToken())] = i;
        }
        String s;
        while((s=br.readLine())!=null){
            StringTokenizer st2 = new StringTokenizer(s);
            int T[] = new int[n+1];
            for(int i=0;i<n;i++){
                T[Integer.parseInt(st2.nextToken())] = i;
            }
            int G[][] = new int[n+1][n+1];
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(S[i]==T[j]){
                        G[i][j] = G[i-1][j-1]+1;
                    }else{
                        G[i][j] = Math.max(G[i][j-1],G[i-1][j]);
                    }
                }
            }
            System.out.println(G[n][n]);
        }
    }
}
