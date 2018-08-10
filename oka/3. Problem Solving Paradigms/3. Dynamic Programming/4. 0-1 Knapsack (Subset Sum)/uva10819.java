import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            StringTokenizer st = new StringTokenizer(s);
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if(m+200>2000){
                m += 200;
            }
            int W[] = new int[m+1];
            Arrays.fill(W,-1);
            W[0] = 0;
            for(int i=0;i<n;i++){
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st2.nextToken());
                int f = Integer.parseInt(st2.nextToken());
                for(int j=m;j>=p;j--){
                    if(W[j-p]!=-1){
                        W[j] = Math.max(W[j],W[j-p]+f);
                    }
                }
            }
            int max = -1;
            for(int j=m;j>=0;j--){
                if(m<2000||j>2000||j<=m-200){
                    max = Math.max(W[j],max);
                }
            }
            if(max==-1){
                System.out.println(0);
            }else{
                System.out.println(max);
            }
        }
    }
       
}

