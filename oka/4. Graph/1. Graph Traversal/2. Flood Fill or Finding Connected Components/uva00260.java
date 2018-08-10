import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static char board[][];
    public static boolean visited[][];
    public static int di[] = {-1,-1,0,0,1,1};
    public static int dj[] = {-1,0,-1,1,0,1};
    
    static class Pair{
        int first;
        int second;
        Pair(int f,int s){
            first = f;
            second = s;
        }
    }
    
    public static boolean black(){
        Queue<Pair> S = new LinkedList<>();
        Pair p;
        for(int i=0;i<N;i++){
            S.add(new Pair(0,i));
            visited[0][i] = true;
        }
        while(!S.isEmpty()){
            p = S.remove();
            if(p.first==N-1)
                return true;
            for(int i=0;i<6;i++){
                int I = p.first+di[i];
                int J = p.second+dj[i];
                
                if(I<0||I>=N)
                    continue;
                if(J<0||J>=N)
                    continue;
                if(board[I][J]!='b')
                    continue;
                if(!visited[I][J]){
                    S.add(new Pair(I,J));
                    visited[I][J] = true;
                }
            }
        }
        return false;
    }
    
    public static void main(String args[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        while(true){
            N = Integer.parseInt(br.readLine());
            if(N==0)
                break;
            board = new char[N][N];
            visited = new boolean[N][N];
            for(int i=0;i<N;i++){
                board[i] = br.readLine().toCharArray();
            }
            count++;
            System.out.printf("%d ",count);
            if(black())
                System.out.printf("B\n");
            else
                System.out.printf("W\n");
        }
    }
}
 
