import java.util.*;
import java.io.*;

public class Main{     
    public static void main(String arg[])throws IOException{
        // Dijkstra Algo - single source, multi dest
        // Here change source to dest, and apply dijkstra
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i=0;i<t;i++){
            br.readLine();
            int N = Integer.parseInt(br.readLine());
            int E = Integer.parseInt(br.readLine())-1;
            int T = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            int path[][] = new int[N][N];
            int dist[] = new int[N];
            boolean visited[] = new boolean[N];
            for(int j=0;j<N;j++){
                Arrays.fill(path[j],-1);
            }
            Arrays.fill(dist,Integer.MAX_VALUE);
            dist[E] = 0;
            for(int j=0;j<M;j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken())-1;
                int v = Integer.parseInt(st.nextToken())-1;
                int w = Integer.parseInt(st.nextToken());
                //reverse the edge, since dest become source
                path[v][u] = w;
            }
            Queue<Point>q = new PriorityQueue<>();
            q.add(new Point(E,dist[E]));
            while(!q.isEmpty()){
                Point p = q.poll();
                if(visited[p.c])
                    continue;
                visited[p.c] = true;
                
                for(int j=0;j<N;j++){
                    if(path[p.c][j]>=0){
                        int d = dist[p.c]+path[p.c][j];
                        if(dist[j]>d){
                            dist[j] = d;
                        }
                        q.add(new Point(j,dist[j]));
                    }
                }
            }
            int count = 0;
            for(int j=0;j<N;j++){
                if(dist[j]<=T){
                    count++;
                }
            }
            System.out.println(count);
            if(i<t-1){
                System.out.println();
            }
        }
    }
}

class Point implements Comparable<Point>{
    int c;
    int d;
    Point(int c,int d){
        this.c = c;
        this.d = d;
    }
    @Override
    public int compareTo(Point p){
        return this.d-p.d;
    }
}

