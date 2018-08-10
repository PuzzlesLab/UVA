import java.util.*;
import java.io.*;

public class Main { 
    public static int vessel[];
    public static int binarySearch(int limit){
        int container = 0;
        int count = 1;
        for(int i=0;i<vessel.length;i++){
            if(container+vessel[i]<=limit){
                container += vessel[i];
            }else if(vessel[i]>limit){
                count = Integer.MAX_VALUE;
                break;
            }else{
                container = vessel[i];
                count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s=br.readLine())!=null){
            if(s.isEmpty())
                break;
            StringTokenizer st = new StringTokenizer(s);
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            vessel = new int[N];
            int max = 0;
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                vessel[i] = Integer.parseInt(st.nextToken());
                max += vessel[i];
            }
            int min = 0;
            int ans = Integer.MAX_VALUE;
            while(min<=max){
                int mid = (min+max)/2;
                if(binarySearch(mid)<=M){
                    ans = Math.min(ans,mid);
                    max = mid-1;
                }else{
                    min = mid+1;
                }
            }
            System.out.println(ans);
        }
    }
}

