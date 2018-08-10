import java.util.*;
import java.io.*;
public class Main {         
    public static int track[];
    public static int max;
    public static int length;
    public static int N;
    public static boolean select[];
    public static boolean maxSelect[];
    
    public static boolean next(int next){
        if(length+next>N)
            return false;
        else
            return true;
    }
    
    public static void backtrack(int current){
        boolean full = true;
        for(int i=current;i<select.length;i++){
            if(!select[i]&&next(track[i])){
                select[i] = true;
                length += track[i];
                backtrack(i);
                select[i] = false;
                length -= track[i];
                full = false;
            }
        }
        if(full){
            if(max<length){
                max = length;
                for(int i=0;i<select.length;i++){
                    maxSelect[i] = select[i];
                }
            }
        }
    }
    
    public static void main(String[] args)throws IOException{   
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String s;
       while((s=br.readLine())!=null){
           StringTokenizer st = new StringTokenizer(s);
           N = Integer.parseInt(st.nextToken());
           int t = Integer.parseInt(st.nextToken());
           track = new int[t];
           select = new boolean[t];
           maxSelect = new boolean[t];
           for(int i=0;i<t;i++){
               track[i] = Integer.parseInt(st.nextToken());
           }
           length = 0;
           max = 0;
           backtrack(0);
           for(int i=0;i<t;i++){
               if(maxSelect[i]){
                   System.out.print(track[i]+" ");
               }
           }
           System.out.println("sum:"+max);
       }
    }
}
