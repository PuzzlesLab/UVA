import java.util.*;
import java.io.*;

public class Main {
    // int T, int C parameter because road.length and road[0].length cos runtime error in uva
    public static int dijkstra(int from,int end,int start,int T,int C,Railroad road[][]){
        Queue<Railroad>q = new PriorityQueue<>();
        Railroad r = new Railroad(from,-1,start);
        q.add(r);
        int t[] = new int[C];
        Arrays.fill(t,Integer.MAX_VALUE);
        t[from] = start;
        while(!q.isEmpty()){
            Railroad select = q.poll();
            for(int i=0;i<T;i++){
                if(road[i][select.dest]==null)
                    continue;
                Railroad tmp = road[i][select.dest];
                if(tmp.start<t[select.dest])
                    continue;
                if(tmp.end<=t[tmp.dest]){
                    t[tmp.dest] = tmp.end;
                    q.add(new Railroad(tmp.dest,tmp.start,tmp.end));
                }
            }
        }   
        return t[end];
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(!(s=br.readLine()).equals("0")){
            int C = Integer.parseInt(s);
            Map<String,Integer> m = new HashMap<>();
            for(int i=0;i<C;i++){
                m.put(br.readLine(),i);
            }
            int T = Integer.parseInt(br.readLine());
            
            Railroad forward [][] = new Railroad[T][C];
            Railroad backward [][] = new Railroad[T][C];
            
            for(int i=0;i<T;i++){
                int t = Integer.parseInt(br.readLine());
                StringTokenizer st = new StringTokenizer(br.readLine());
                int t1 = Integer.parseInt(st.nextToken());
                int city = m.get(st.nextToken());
                for(int j=1;j<t;j++){
                    StringTokenizer st2 = new StringTokenizer(br.readLine());
                    int t2 = Integer.parseInt(st2.nextToken());
                    int dest = m.get(st2.nextToken());
                    forward[i][city] = new Railroad(dest,t1,t2);
                    // the negative -t1, -t2 is use for priority queue
                    backward[i][dest] = new Railroad(city,-t2,-t1);
                    city = dest;
                    t1 = t2;
                }
            }
            int start = Integer.parseInt(br.readLine());
            int from = m.get(br.readLine());
            int to = m.get(br.readLine());
            
            int time = dijkstra(from,to,start,T,C,forward);
            if(time==Integer.MAX_VALUE){
                System.out.println("No connection");
                continue;
            }            
            int time2 = dijkstra(to,from,-time,T,C,backward);    
            System.out.printf("%04d %04d\n",-time2,time);          
        }
    }       
}

class Railroad implements Comparable<Railroad>{
    int dest = 0;
    int start = 0;
    int end = 0;
    
    Railroad(int dest,int start,int end){
        this.dest = dest;
        this.start = start;
        this.end = end;
    }
    
    @Override
    public int compareTo(Railroad r){
        return this.end - r.end;
    }
}