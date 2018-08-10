import java.io.*;
import java.util.*;

public class Main {
    public static boolean g[][];
    public static boolean use[];
    public static Stack<Integer> stack;
    
    public static void dfs(int u){
        use[u] = false;
        for(int i=0;i<26;i++){
            if(use[i]&&g[u][i]){
                dfs(i);
            }
        }
        stack.push(u);
    }
    
    public static void main(String args[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b;
        g = new boolean[26][26];
        use = new boolean[26];
        stack = new Stack<>();
        while(!(b=br.readLine()).equals("#")){
            for(int i=0;i<a.length();i++){
                if(i>=b.length())
                    break;
                int v1 = a.charAt(i)-'A';
                int v2 = b.charAt(i)-'A';
                if(v1!=v2){
                    use[v1] = true;
                    use[v2] = true;
                    g[v1][v2] = true;
                    break;
                }
            }     
            a = b;
        }
        for(int i=0;i<26;i++){   
            if(use[i])
                dfs(i);
        }
        while(!stack.isEmpty()){
            System.out.printf("%c",(char)(stack.pop()+'A'));
        }    
        System.out.println();
    }
}
 
