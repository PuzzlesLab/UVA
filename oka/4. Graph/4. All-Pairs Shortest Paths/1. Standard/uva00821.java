import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int T = 0;
        while((s=br.readLine())!=null){
            if(s.equals("0 0")){
               break; 
            }
            T++;
            int p[][] = new int[100][100];
            for (int i = 0; i < 100; i++) {
                Arrays.fill(p[i], 1000);
            }
            
            StringTokenizer st = new StringTokenizer(s);
            while(true){
                int u = Integer.parseInt(st.nextToken())-1;
                int v = Integer.parseInt(st.nextToken())-1;
                if(u==-1&&v==-1){
                    break;
                }
                p[u][v] = 1;
            }
            
            for(int k=0;k<100;k++){
                for(int i=0;i<100;i++){
                    for(int j=0;j<100;j++){
                        p[i][j] = Math.min(p[i][j],p[i][k]+p[k][j]);
                    }
                }
            }
            float total = 0;
            int count = 0;
            for(int i=0;i<100;i++){
                for(int j=0;j<100;j++){
                    if(i!=j&&p[i][j]!=1000){
                        total += p[i][j];
                        count++;
                    }
                }
            }
            System.out.printf("Case %d: average length between pages = %.3f clicks\n",T,total/count);
        }
    }
}