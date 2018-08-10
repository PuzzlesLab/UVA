import java.util.*;
import java.io.*;
public class Main {         
    public static int max;
    public static int length;
    public static int edge[][];
    
    public static boolean next(int current,int next){
        if(edge[current][next]==0)
            return false;
        else
            return true;        
    }
    
    public static void longest(int current){
        boolean next = false;
        for(int i=0;i<edge.length;i++){
            if(next(current,i)){
                next = true;
                length++;
                edge[current][i] = 0;
                edge[i][current] = 0;
                longest(i);
                length--;
                edge[current][i] = 1;
                edge[i][current] = 1;
            }
        }
        if(!next){
            max = Math.max(max,length);
        }
    }
    
    
    public static void main(String[] args)throws IOException{   
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String s;
       while(!(s=br.readLine()).equals("0 0")){
           StringTokenizer st = new StringTokenizer(s);
           int n = Integer.parseInt(st.nextToken());
           int m = Integer.parseInt(st.nextToken());
           edge = new int[n][n];
           max = Integer.MIN_VALUE;
           for(int i=1;i<=m;i++){
               StringTokenizer st2 = new StringTokenizer(br.readLine());
               int a = Integer.parseInt(st2.nextToken());
               int b = Integer.parseInt(st2.nextToken());
               edge[a][b] = 1;
               edge[b][a] = 1;
           }
           for(int i=0;i<edge.length;i++){
               length = 0;
               longest(i);
           }
           System.out.println(max);
       }
       
    }
}
