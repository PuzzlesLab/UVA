import java.util.*;
import java.io.*;
public class Main {            
    public static int N;
    public static int H;
    public static boolean num[];
    public static int hamming;
    
    public static void backtrack(int current){
        if(current<N&&hamming<H){
            backtrack(current+1);
            num[current] = true;
            hamming++;
            backtrack(current+1);
            num[current] = false;
            hamming--;
        }else{
            if(hamming==H){
                StringBuilder sb = new StringBuilder();
                for(boolean i:num){
                    if(i){
                        sb.append('1');
                    }else{
                        sb.append('0');
                    }
                }
                System.out.println(sb);
            }
        }
    }
      
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int T = Integer.parseInt(br.readLine());
        for(int i=1;i<=T;i++){
            br.readLine();
            if(i!=1){
                System.out.println();
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            num = new boolean[N];
            hamming = 0;
            backtrack(0);           
        }
        pr.close();
    }
}
