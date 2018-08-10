import java.util.*;
import java.io.*;

public class Main { 
    public static int N;
    public static Point p[];
    public static boolean visit[];
    public static int link[];
    public static int minLink[];
    public static int start;
    public static int minStart = -1;
    public static double min;
    public static void dfs(int cur,double sum,int use){
        if(sum>=min)
            return;
        if(use==N){
            min = sum;
            System.arraycopy(link, 0, minLink, 0, N);
            minStart = start;
        }
        for(int i=0;i<N;i++){
            if(i==cur)
                continue;
            double d = p[cur].dist(p[i]);
            if(!visit[i]){
                visit[i] = true;
                link[cur] = i;
                dfs(i,sum+d,use+1);
                link[cur] = 0;
                visit[i] = false;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int T = 0;
        while(!(s=br.readLine()).equals("0")){
            N = Integer.parseInt(s);
            p = new Point[N];
            visit = new boolean[N];
            link = new int[N];
            minLink = new int[N];
            min = Integer.MAX_VALUE;
            StringTokenizer st;
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                double x = Double.parseDouble(st.nextToken());
                double y = Double.parseDouble(st.nextToken());
                p[i] = new Point(x,y);
            }
            for(int i=0;i<N;i++){
                start = i;
                visit[i] = true;
                dfs(i,0.0,1);
                visit[i] = false;
            }
            System.out.println("**********************************************************");
            T++;
            System.out.println("Network #"+T);
            int i = minStart;
            for(int x=0;x<N-1;x++){                
                int to = minLink[i];
                System.out.printf("Cable requirement to connect (%.0f,%.0f) to (%.0f,%.0f) is %.2f feet.\n"
                                    ,p[i].x,p[i].y,p[to].x,p[to].y,p[i].dist(p[to]));
                i = to;                  
            }
            System.out.printf("Number of feet of cable required is %.2f.\n",min);
        }
    }
}

class Point{
    double x;
    double y;
    Point(double x,double y){
        this.x = x;
        this.y = y;
    }
    double dist(Point o){
        double dx = o.x-x;
        double dy = o.y-y;
        return Math.sqrt(dx*dx+dy*dy)+16;
    }
}

