import java.util.*;
import java.io.*;
public class Main {   
    public static int row[] = new int[8];    
    public static int a;
    public static int b;
    public static int T;
    
    public static boolean place(int r,int c){
        for(int prev=0;prev<c;prev++){
            if(row[prev]==r||Math.abs(row[prev]-r)==Math.abs(prev-c)){
                return false;
            }
        }
        return true;
    }
    
    public static void backtrack(int c){
        if(c==8&&row[b]==a){
            if(T==1){
                System.out.println("SOLN       COLUMN");
                System.out.println(" #      1 2 3 4 5 6 7 8");
                System.out.println("");
            }
            System.out.printf("%2d     ",T);
            for(int i=0;i<8;i++){
                System.out.print(" "+(row[i]+1));
            }
            T++;
            System.out.println("");
        }else{
            for(int r=0;r<8;r++){
                if(place(r,c)){
                    row[c] = r;
                    backtrack(c+1);
                }
            }
        }
    }
    
    public static void main(String[] args)throws IOException{    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        for(int i=1;i<=t;i++){
            br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken())-1;
            b = Integer.parseInt(st.nextToken())-1;
            T = 1;
            backtrack(0);
            if(i<t){
                System.out.println();
            }
        }
        
    }
}