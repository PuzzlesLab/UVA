import java.util.*;
import java.io.*;

public class Main{     
    public static void main(String arg[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int t = 0;
        while(!(s=br.readLine()).equals("0 0")){
            StringTokenizer st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            Island land [] = new Island[n];
            boolean sol = true;
            for(int i=0;i<n;i++){
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st2.nextToken());
                int y = Integer.parseInt(st2.nextToken());
                double v = d*d-y*y;
                if(v<0){
                    sol = false;
                    continue;
                }
                double z = Math.sqrt(v);
                land[i] = new Island((double)x-z,(double)x+z);
            }
            if(!sol){
                t++;
                System.out.printf("Case %d: %d\n",t,-1);
                br.readLine();
                continue;
            }
            Arrays.sort(land);
            int count = 1;
            double selected = land[0].min;
            double maxRange = land[0].max;
            for(int i=0;i<n;i++){
                if(land[i].min<=maxRange){
                    selected = Math.max(selected,land[i].min);
                    maxRange = Math.min(maxRange,land[i].max);
                }else{
                    count++;
                    selected = land[i].min;
                    maxRange = land[i].max;
                }
            }
            t++;
            System.out.printf("Case %d: %d\n",t,count);
            br.readLine();
        }
    }
}

class Island implements Comparable<Island>{
    double min;
    double max;
    Island(double min, double max){
        this.min = min;
        this.max = max;
    }
    @Override
    public int compareTo(Island i){
        return (this.min-i.min)>0.0?1:-1;
    }
}
