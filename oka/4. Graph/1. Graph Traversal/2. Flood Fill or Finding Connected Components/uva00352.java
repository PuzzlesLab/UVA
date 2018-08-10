import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static boolean board[][];
    public static boolean visited[][];
    public static int dx[] = {-1,-1,-1, 0,0, 1,1,1};
    public static int dy[] = {-1, 0, 1,-1,1,-1,0,1};
    
    static class Pair{
        int f;
        int s;
        Pair(int first,int second){
            f = first;
            s = second;
        }
    }
    public static int count(){
        int count = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(visited[i][j])
                    continue;
                if(!board[i][j])
                    continue;
                count++;
                Queue<Pair> q = new LinkedList<>();
                Pair p;
                visited[i][j] = true;
                q.add(new Pair(i,j));
                while(!q.isEmpty()){
                    p = q.poll();
                    for(int t=0;t<8;t++){
                        int x = p.f+dx[t];
                        int y = p.s+dy[t];
                        if(x<0||x>=N)
                            continue;
                        if(y<0||y>=N)
                            continue;
                        if(visited[x][y])
                            continue;
                        if(board[x][y]){
                            visited[x][y]=true;
                            q.add(new Pair(x,y));
                        }
                    }
                }
            }
        } 
        return count;
    }
    
    public static void main(String args[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int test = 0;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            N = Integer.parseInt(s);
            board = new boolean[N][N];
            visited = new boolean[N][N];
            for(int i=0;i<N;i++){
                String im = br.readLine();
                for(int j=0;j<N;j++){
                    board[i][j] = im.charAt(j)=='1';
                }
            }
            test++;
            System.out.printf("Image number %d contains %d war eagles.\n",test,count());
        }
    }
}
 
