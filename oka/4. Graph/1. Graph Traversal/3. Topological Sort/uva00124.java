import java.io.*;
import java.util.*;

public class Main {
    public static boolean alpha[];
    public static boolean order[][];
    public static int output[];
    public static int count;
    
    public static boolean available(int a,int c){
        for(int i=0;i<c;i++){
            if(!order[output[i]][a]){
                return false;
            }
        }
        return true;
    }
    
    public static void backtrack(int c){
        if(c==count){
            for(int i=0;i<count;i++){
                System.out.printf("%c",(output[i]+'a'));
            }
            System.out.println();
        }else{
            for(int i=0;i<26;i++){
                if(alpha[i]&&available(i,c)){
                    output[c] = i;
                    alpha[i] = false;
                    backtrack(c+1);
                    alpha[i] = true;
                }
            }
        }
    }
    
    public static void main(String args[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int test = 1;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            if(test>1)
                System.out.println();
            test++;
            
            alpha = new boolean[26];
            StringTokenizer st = new StringTokenizer(s);
            count = 0;
            while(st.hasMoreTokens()){
                alpha[st.nextToken().charAt(0)-'a'] = true;
                count++;
            }
            order = new boolean[26][26];
            output = new int[26];
            for(int i=0;i<26;i++){
                Arrays.fill(order[i],true);
            }
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            while(st2.hasMoreTokens()){
                int v1 = st2.nextToken().charAt(0)-'a';
                int v2 = st2.nextToken().charAt(0)-'a';
                 order[v2][v1] = false;
            }
            backtrack(0);
        }
    }
}
 
