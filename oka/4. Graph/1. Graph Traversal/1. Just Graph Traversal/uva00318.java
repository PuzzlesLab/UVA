import java.util.*;
import java.io.*;

public class Main {  
    public static double adj[][];
    public static boolean visit[];//set to 1 if after visited;
    public static double d[];
    
    public static void init(){
        for(int i=0;i<adj.length;i++){
            Arrays.fill(adj[i],-1);
        }
        Arrays.fill(d,-1);
        d[0] = 0;// starting point
    }
    
    public static int min_index(){
        double min = Integer.MAX_VALUE;
        int node = 0;
        for(int i=0;i<d.length;i++){
            if(d[i]!=-1&&d[i]<min&&!visit[i]){
                min = d[i];
                node = i;
            }
        }
        return node;
    }
    
    public static void relaxation(int u,int v){
        if(d[v]>d[u]+adj[u][v]||d[v]==-1){
            d[v] = d[u]+adj[u][v];
        }
    }
    
    public static void dijkstra(){
        for(int i=0;i<d.length;i++){
            int u = min_index();
            visit[u] = true;
            for(int v=0;v<d.length;v++){
                if(adj[u][v]!=-1){
                    relaxation(u,v);
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        
        String s; 
        int count = 0;
        while(!(s=br.readLine()).equals("0 0")){
            StringTokenizer st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            adj = new double[n][n];
            visit = new boolean[n];
            d = new double[n];
            init();
            for(int i=1;i<=m;i++){
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st2.nextToken())-1;
                int v = Integer.parseInt(st2.nextToken())-1;
                double l = Double.parseDouble(st2.nextToken());
                        
                adj[u][v] = l;
                adj[v][u] = l;
            }
            dijkstra();
            
            double max = -1;
            int domino = -1;
            for(int i=0;i<d.length;i++){
                if(max<d[i]){
                    max = d[i];
                    domino = i+1;
                }
            }
            
            double max2 = -1;
            int domino1 = -1;
            int domino2 = -1;
            for(int i=0;i<d.length-1;i++){
                for(int j=i+1;j<d.length;j++){
                    if(adj[i][j]!=-1){
                        double p = d[i];
                        double q = d[j];
                        if(Math.abs(p-q)<adj[i][j]){
                            double end = Math.max(p,q)+(adj[i][j]-Math.abs(p-q))/2; 
                            if(end>max2){
                                max2 = end;
                                domino1 = i+1;
                                domino2 = j+1;
                            }
                        }
                    }
                }
            }
            
            count++;
                     
            pr.printf("System #%d\n",count);
            if(max>=max2){
               pr.printf("The last domino falls after %.1f seconds, ",max);
               pr.printf("at key domino %d.\n\n",domino);
            }else{
               pr.printf("The last domino falls after %.1f seconds, ",max2);
               pr.printf("between key dominoes %d and %d.\n\n",domino1,domino2);
            }
        }
        pr.close();
    }  
}

