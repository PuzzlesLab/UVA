import java.util.*;
import java.io.*;

public class Main { 
    public static double p;
    public static double q;
    public static double r;
    public static double s;
    public static double t;
    public static double u;
    public static double eps = 1e-9;
    
    public static double f(double x){
        double total = p*Math.pow(Math.E,-x);
        total += q*Math.sin(x);
        total += r*Math.cos(x);
        total += s*Math.tan(x);
        total += t*x*x;
        total += u;
        return total;
    }
    
    // cant use recursion, must use loop (due to the precision problem)
    public static double binarySearch(double start,double end){
        if(Math.abs(f(1.0))<eps)
            return 1;
        if(Math.abs(f(0.0))<eps)
            return 0.0;
        double mid = 0;
        for(int i=0;i<100;i++){
            mid = (start+end)/2.0;
            if(f(mid)>0){
                start = mid;
            }else{
                end = mid;
            }              
        }
        if(Math.abs(f(mid))<eps){
            return mid;
        }else{
            return -1.0;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a;
        while((a=br.readLine())!=null&&!a.isEmpty()){
            StringTokenizer st = new StringTokenizer(a);
            p = Double.parseDouble(st.nextToken());
            q = Double.parseDouble(st.nextToken());
            r = Double.parseDouble(st.nextToken());
            s = Double.parseDouble(st.nextToken());
            t = Double.parseDouble(st.nextToken());
            u = Double.parseDouble(st.nextToken());
            double ans = binarySearch(0.0,1.0);
            if(ans==-1.0)
                System.out.println("No solution");
            else{
                System.out.printf("%.4f\n",ans);
            }
        }
    }
}
