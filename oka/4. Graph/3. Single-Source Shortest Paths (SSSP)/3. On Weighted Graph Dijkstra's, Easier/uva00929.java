import java.util.*;
import java.io.*;

public class Main {
   
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            int r = Integer.parseInt(br.readLine());
            int c = Integer.parseInt(br.readLine());
            int grid[][] = new int[r][c];
            for(int j=0;j<r;j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int k=0;k<c;k++){
                    grid[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            boolean visited[][] = new boolean[r][c];
            int dist[][] = new int[r][c];
            for(int k[]:dist){
                Arrays.fill(k,Integer.MAX_VALUE);
            }
            dist[0][0] = grid[0][0];
            Queue<Point> q = new PriorityQueue<>();
            int dr[] = {-1, 0, 0, 1};
            int dc[] = { 0,-1, 1, 0};
            q.add(new Point(0,0,dist[0][0]));
            visited[0][0] = true;
            outer:
            while(!q.isEmpty()){
                Point p = q.poll();
                for(int j=0;j<4;j++){
                    int cr = p.r+dr[j];
                    int cc = p.c+dc[j];
                    if(cr<0||cr>=r){
                        continue;
                    }
                    if(cc<0||cc>=c){
                        continue;
                    }
                    
                    int d = dist[p.r][p.c]+grid[cr][cc];
                    
                    if(dist[cr][cc]>d){
                        dist[cr][cc] = d;
                    }
                    
                    if(!visited[cr][cc]){
                        visited[cr][cc] = true;
                        if(cr==r-1&&cc==c-1)
                            break outer;
                        q.add(new Point(cr,cc,dist[cr][cc]));
                    }
                }
            }
            System.out.println(dist[r-1][c-1]);
        }
       
    }
       
}

class Point implements Comparable<Point>{
    int r;
    int c;
    int w;
    Point(int r,int c,int w){
        this.r = r;
        this.c = c;
        this.w = w;
    }
    
    @Override
    public int compareTo(Point p){
        return this.w-p.w;
    }
}

