import java.util.*;
import java.io.*;
public class Main {
   
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int dr[] = {-1,1,0,0};
        int dc[] = {0,0,-1,1};
        String s;
        while(!(s=br.readLine()).equals("0 0")){
            StringTokenizer st = new StringTokenizer(s);
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(br.readLine());
            int grid[][] = new int[r][c]; 
            int dist[][] = new int[r][c];
            for(int i=0;i<b;i++){
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int tr = Integer.parseInt(st2.nextToken());
                int num = Integer.parseInt(st2.nextToken());
                for(int j=0;j<num;j++){
                    int tc = Integer.parseInt(st2.nextToken());
                    grid[tr][tc] = -1;
                }               
            }
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st2.nextToken());
            int sc = Integer.parseInt(st2.nextToken());
            StringTokenizer st3 = new StringTokenizer(br.readLine());
            int er = Integer.parseInt(st3.nextToken());
            int ec = Integer.parseInt(st3.nextToken());
            
            // start bfs
            Queue<Coordinate> q = new LinkedList<>();
            Coordinate start = new Coordinate(sr,sc);
            q.add(start);
            grid[sr][sc] = -1;
            while(q.size()>0){          
                Coordinate cur = q.poll();
                int curX = cur.getX();
                int curY = cur.getY();
            
                for(int i=0;i<4;i++){
                    int tr = curX+dr[i];
                    int tc = curY+dc[i];
                    if(tr>=0&&tr<r&&tc>=0&&tc<c&&grid[tr][tc]!=-1){
                        q.add(new Coordinate(tr,tc));
                        grid[tr][tc] = -1;
                        dist[tr][tc] = dist[curX][curY]+1;                       
                    }else{
                        if(tr==er&&tc==ec){
                            break;
                        }
                    }
                }

            }
            // we assume at least have one solution
            System.out.println(dist[er][ec]);
        }
    }
    
}

class Coordinate{
    public int X = -1;
    public int Y = -1;
    public Coordinate(int x,int y){
        X = x;
        Y = y;
    }
    public int getX(){
        return this.X;
    }
    public int getY(){
        return this.Y;
    }
}