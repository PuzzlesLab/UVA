import java.util.*;
import java.io.*;

public class Main { 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int  t[][];
        while(!(s=br.readLine()).equals("0")){
            int n = Integer.parseInt(s);
            t = new int[1000][1000];
            long count = 0;
            for(int i=0;i<n;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(t[b][a]>0){
                    t[b][a]--;
                    count+=2;
                }else{
                    t[a][b]++;
                }
            }
            if(count==n){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }
}


