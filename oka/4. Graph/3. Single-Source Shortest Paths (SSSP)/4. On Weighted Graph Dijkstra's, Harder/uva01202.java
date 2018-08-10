import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(!(s=br.readLine()).equals("-1 -1")){
            StringTokenizer st = new StringTokenizer(s);
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            Room rm[][] = new Room[200][200];
            for(int i=0;i<200;i++){
                for(int j=0;j<200;j++){
                    rm[i][j] = new Room(i,j);
                    rm[i][j].count = Integer.MAX_VALUE;
                }
            }
            for(int i=0;i<M;i++){
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st2.nextToken());
                int y = Integer.parseInt(st2.nextToken());
                int d = Integer.parseInt(st2.nextToken());
                int t = Integer.parseInt(st2.nextToken());
                if(d==0){
                    for(int len=0;len<t;len++){
                        rm[x+len][y].down = -1;
                        rm[x+len][y-1].up = -1;
                    }                    
                }else{
                    for(int len=0;len<t;len++){
                        rm[x][y+len].left = -1;
                        rm[x-1][y+len].right = -1;
                    }
                }
            }
            for(int j=0;j<N;j++){
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st2.nextToken());
                int y = Integer.parseInt(st2.nextToken());
                int d = Integer.parseInt(st2.nextToken());
                if(d==0){
                    rm[x][y].down = 1;
                    rm[x][y-1].up = 1;
                }else{
                    rm[x][y].left = 1;
                    rm[x-1][y].right = 1;
                }
            }
            
            // use int only
            // we not using coordinate system, but use grid system
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int nx = (int)Float.parseFloat(st2.nextToken());
            int ny = (int)Float.parseFloat(st2.nextToken());
            if(nx==0||nx>=199||ny==0||ny>=199){
                System.out.println(0);
                continue;
            }
            Queue<Room>q = new PriorityQueue<>();
            rm[nx][ny].count = 0;
            q.add(rm[nx][ny]);
            int dx[] = { 0,-1, 1, 0};
            int dy[] = { 1, 0, 0,-1};
            boolean path = false;
            int door = 0; 
            outer:
            while(!q.isEmpty()){
                // Room r without configure the door and wall
                Room r = q.poll();
                if(r.x==0||r.x==199){
                    door = r.count;
                    path = true;
                    break;
                }
                if(r.y==0||r.y==199){
                    door = r.count;
                    path = true;
                    break;
                }          
                if(r.visited)
                    continue;
                r.visited = true;
                for(int i=0;i<4;i++){
                    int x = r.x+dx[i];
                    int y = r.y+dy[i];
                    Room next = rm[x][y];
                    if(next.visited){
                        continue;
                    }
                    int count = -1;
                    switch(i){
                        //up
                        case 0:
                            if(r.up==-1){
                                break;
                            }
                            count = r.count+r.up;
                            break;
                        //left
                        case 1:
                            if(r.left==-1){
                                break;
                            }
                            count  = r.count+r.left;
                            break;
                        //right
                        case 2:
                            if(r.right==-1){
                                break;
                            }
                            count = r.count+r.right;
                            break;
                        //down
                        default:
                            if(r.down==-1){
                                break;
                            }
                            count = r.count+r.down;
                    }
                    if(count==-1){
                        continue;
                    }
                    if(next.count>count){
                        next.count = count;
                    }
                    q.add(next);
                }
            }
            if(!path){
                System.out.println(-1);
            }else{
                System.out.println(door);
            }
        }
    }       
}

class Room implements Comparable<Room>{
    int x;
    int y;
    
    int left = 0;
    int right = 0;
    int up = 0;
    int down = 0;
    
    int count;
    
    boolean visited = false;
    
    Room(int x,int y){
        this.x = x;
        this.y = y;
    }
    Room(int x,int y,int count){
        this.x = x; 
        this.y = y;
        this.count = count;
    }
    
    @Override
    public int compareTo(Room r){
        return this.count-r.count;
    }
    
}
