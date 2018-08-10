import java.util.*;
import java.io.*;

public class Main {
    public static double dest;
    public static double c;
    public static double mpg;
    public static double initial;
    public static int n;
    public static double d[];
    public static double cost[];
    public static double total;
    
    public static double fill(double volume,double cost){
        double c = Math.round(volume*cost);
        return (c/100)+2;
    }
    
    public static void recursion(int index,double curGas,double curD,double sum){  
        double dt = d[index]-curD;
        double useGas = dt/mpg;
        curGas -= useGas;
        double mile = curGas*mpg;
        
        if(index==n-1){
            double dx = dest-d[index];
            if(mile<dx){
                sum += fill(c-curGas,cost[index]);
            }
            total = Math.min(total,sum);
            return;
        }        
        
        curD = d[index];
        double dx = d[index+1]-d[index];
        double fill = sum + fill(c-curGas,cost[index]);
        if(curGas>c/2.0){
            if(mile<dx)
                recursion(index+1,c,curD,fill);
            else
                recursion(index+1,curGas,curD,sum);
        }else{
            recursion(index+1,c,curD,fill);
            if(mile>=dx)
                recursion(index+1,curGas,curD,sum);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int T = 0;
        while(!(s=br.readLine()).equals("-1")){
            dest = Double.parseDouble(s);
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            c = Double.parseDouble(st.nextToken());
            mpg = Double.parseDouble(st.nextToken());
            initial = Double.parseDouble(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            
            d = new double[n];
            cost = new double[n];
            total = Double.MAX_VALUE;
            
            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine());
                d[i] = Double.parseDouble(st.nextToken());
                cost[i] = Double.parseDouble(st.nextToken());
            }           
            recursion(0,c,0,initial);
            T++;
            System.out.printf("Data Set #%d\n",T);
            System.out.printf("minimum cost = $%.2f\n",total);
        }
    }
}