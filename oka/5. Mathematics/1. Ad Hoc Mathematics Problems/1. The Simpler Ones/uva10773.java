import java.util.*;
import java.io.*;

public class Main {
    public static void main(String arg[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            double d = Double.parseDouble(st.nextToken());
            double v = Double.parseDouble(st.nextToken());
            double u = Double.parseDouble(st.nextToken());
            
            double h = Math.sqrt(u*u+v*v);
            double D = h/u*d;
            
            double fastTime = D/h;
            
            if(v<u&&v!=0){
                double cos = v/u;
                double sin = Math.sin(Math.acos(cos));
                double speed = u*sin;
                double stime = d/speed;
                double dif = Math.abs(fastTime-stime);
                System.out.printf("Case %d: %.3f\n",i,dif);
            }else{
                System.out.printf("Case %d: can't determine\n",i);
            }
        }
    } 
}