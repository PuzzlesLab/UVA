import java.util.*;
import java.io.*;

public class Main {
    public static int MAX;
    public static int BS(int node, int ball){
        int left = node*2; 
        int right = left+1;
        if(left<MAX&&right<MAX){
            if(ball%2==0){
                return BS(right,ball/2);
            }else{
                return BS(left,ball/2+1);
            }    
        }else{
            return node;
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        br.readLine();
        while(!(s=br.readLine()).equals("-1")){
            StringTokenizer st = new StringTokenizer(s);
            int D = Integer.parseInt(st.nextToken());
            int I = Integer.parseInt(st.nextToken());
            MAX = (int)Math.pow(2.0,D);
            System.out.println(BS(1,I));
        }
    }
       
}

