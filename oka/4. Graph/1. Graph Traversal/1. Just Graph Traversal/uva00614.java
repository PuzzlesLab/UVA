import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int T = 0;
        while(!(s=br.readLine()).equals("0 0 0 0 0 0")){
            if(s.isEmpty()) continue;// each test case have different blank lines 
            T++; // test case
            StringTokenizer st = new StringTokenizer(s);
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(r==0&&c==0)break; // this line make whole program correct =.=
            int sr = Integer.parseInt(st.nextToken())-1;
            int sc = Integer.parseInt(st.nextToken())-1;
            int fr = Integer.parseInt(st.nextToken())-1;
            int fc = Integer.parseInt(st.nextToken())-1;
            int g[][] = new int[r][c];
            for(int i=0;i<r;i++){
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                for(int j=0;j<c;j++){
                    g[i][j] = Integer.parseInt(st2.nextToken());
                }
            }
            Robot rb = new Robot(g);
            rb.setStart(sr, sc);
            rb.setEnd(fr, fc);
            rb.dfs(sr,sc);
            System.out.printf("Maze %d\n\n",T);
            rb.print();
            System.out.println();
            System.out.println();
        }
    }   
}

class Robot{
    public int graph[][];
    public int walk[][];
    public int sr = -1;
    public int sc = -1;
    public int fr = -1;
    public int fc = -1;
    //order of dr,dc is W,N,E,S
    public int dr[] = { 0,-1, 0, 1};
    public int dc[] = {-1, 0, 1, 0};
    public boolean finish = false;
    public Robot(int g[][]){
        graph = g;
        walk = new int[g.length][g[0].length];
    }
    public void setStart(int r,int c){
        sr = r;
        sc = c;
    }
    public void setEnd(int r,int c){
        fr = r;
        fc = c;
    }
    
    public void dfs(int r,int c){
        walk[r][c]++;
        if(r==fr&&c==fc){
           finish = true;
           return; 
        }
        for(int i=0;i<4;i++){
            int curR = r + dr[i];
            int curC = c + dc[i];
            if(!finish&&validMove(curR,curC,i)&&walk[curR][curC]==0){
                walk[curR][curC] = walk[r][c];
                dfs(curR,curC);
                if(!finish){
                    walk[curR][curC] = -1;
                }
            }
        }
    }
    
    public boolean validMove(int r,int c,int dir){       
        switch(dir){
            case 0: return !(c<0||graph[r][c]==1||graph[r][c]==3);
            case 1: return !(r<0||graph[r][c]==2||graph[r][c]==3);
            case 2: return !(c>=graph[0].length||graph[r][c-1]==1||graph[r][c-1]==3);
            case 3: return !(r>=graph.length||graph[r-1][c]==2||graph[r-1][c]==3);
        }            
        return false;
    }
    
    public void printFrame(int row){
        System.out.print("+");
        for(int i=0;i<graph[0].length;i++){
            if(row==-1||graph[row][i]==2||graph[row][i]==3){
                System.out.print("---+");
            }else{
                System.out.print("   +");
            }
        }
        System.out.println();
    }
    public void printRoom(int row){
        System.out.print("|");
        for(int i=0;i<graph[row].length;i++){
            String path = " "; // condition unvisited
            if(walk[row][i]==-1){
                path = "???";
            }else if(walk[row][i]>0){
                path = String.format("%3d",walk[row][i]);
            }
            if(i==graph[row].length-1||graph[row][i]==1||graph[row][i]==3){
                System.out.printf("%3s|",path);
            }else{
                System.out.printf("%3s ",path);
            }
        }
        System.out.println();
    }
    public void print(){
        printFrame(-1);
        for(int i=0;i<graph.length;i++){
            printRoom(i);
            if(i<graph.length-1){
                printFrame(i);
            }else{
                printFrame(-1);
            }
        }
    }
}