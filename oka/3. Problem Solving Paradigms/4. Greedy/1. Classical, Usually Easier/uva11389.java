import java.util.*;
import java.io.*;

public class Main { 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(!(s=br.readLine()).equals("0 0 0")){
            StringTokenizer st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int mon[] = new int[n];
            int night[] = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){
                mon[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){
                night[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(mon);
            // ideally is reverse sort
            // but primitive type din have
            Arrays.sort(night);
            int total = 0;
            for(int i=0;i<n;i++){
                int sum = mon[i]+night[n-i-1];
                if(sum>d){
                    total += (sum-d)*r;
                }
            }
            System.out.println(total);        
        }
    }
}


