import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int dx[] = { 0,-1,-1,-1, 0, 1, 1, 1};
        int dy[] = { 1, 1, 0,-1,-1,-1, 0, 1};
        while(!(s=br.readLine()).equals("-1")){
            StringTokenizer st = new StringTokenizer(s);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            Map<Point,Integer> m = new HashMap<>();
            for(int i=0;i<8;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                Point p = new Point(a,b);
                m.put(p,c);
            }
            int next = (d+6)%8;
            int tx = x+dx[next];
            int ty = y+dy[next];
            while(m.get(new Point(tx,ty))==0){
                next = (next+1)%8;
                tx = x+dx[next];
                ty = y+dy[next];
            }
            System.out.println(next);
        }
    }
}

class Point{
    int x;
    int y;
    Point(int x,int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public int hashCode(){
        return x*10+y;
    }
    @Override
    public boolean equals(Object obj){
        return hashCode()==obj.hashCode();
    }
}