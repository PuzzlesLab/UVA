import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static boolean g[][];
    public static int color[];
    
    public static boolean bicolor(){
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        color[0] = 1;
        while(!q.isEmpty()){
            int p = q.poll();
            for(int i=0;i<N;i++){
                if(g[p][i]){
                    if(color[p]==color[i]){
                        return false;
                    }else if(color[i]==0){
                        color[i] = color[p]==1 ? 2:1;
                        q.add(i);
                    }
                }
            }
        }
        return true;
    }
    
    public static void main(String args[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(!(s=br.readLine()).equals("0")){
            N = Integer.parseInt(s);
            g = new boolean[N][N];
            int E = Integer.parseInt(br.readLine());
            for(int i=0;i<E;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                g[u][v] = true;
                g[v][u] = true;
            }
            color = new int[N];
            if(bicolor())
                System.out.println("BICOLORABLE.");
            else
                System.out.println("NOT BICOLORABLE.");
        }
    }
}
 
