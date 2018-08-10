import java.util.*;
import java.io.*;
public class Main {         
    public static int path[][];
    public static int n;
    public static int walk;
    public static boolean noWalk;
    
    public static void walk(boolean visited[],String hold,int current){
        if(walk<=n-1){
            for(int i=1;i<=path.length;i++){
                if(!visited[i-1]&&path[current-1][i-1]==1){
                    visited[current-1] = true;
                    String t = hold;       
                    walk++;                   
                    t += ","+i;                   
                    if(walk==n){
                        t += ")";
                    }
                    walk(visited,t,i);
                    walk--;                    
                    visited[current-1] = false;
                }
            }
        }else{
            noWalk = false;
            System.out.println(hold);
        }
    }
    
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int line = 0;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            if(s.equals("-9999")){
                s = br.readLine();
            }
            line++;
            if(line!=1){
                System.out.println();
            }
            StringTokenizer st = new StringTokenizer(s);
            int m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            path = new int[m][m];
            for(int i=0;i<m;i++){
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                for(int j=0;j<m;j++){
                    path[i][j] = Integer.parseInt(st2.nextToken());        
                }
            }
            walk = 0;
            boolean visited[] = new boolean[m];
            visited[0] = true;
            noWalk = true;
            walk(visited,"(1",1);
            if(noWalk){
                System.out.println("no walk of length "+n);
            }
        }
    }
}
