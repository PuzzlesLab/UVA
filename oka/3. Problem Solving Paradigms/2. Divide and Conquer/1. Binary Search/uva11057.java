import java.util.*;
import java.io.*;

public class Main {
    public static int arr[];
    public static int N;
    public static int search;
    
    public static boolean binarySearch(int start,int end){
        if(start>=end){
            if(arr[start]==search)
                return true;
            return false;
        }
        int mid = (start+end)/2;
        if(arr[mid]==search){
            return true;
        }else if(arr[mid]>search){
            return binarySearch(start,mid-1);
        }else{
            return binarySearch(mid+1,end);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            N = Integer.parseInt(s);
            arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);
            int M = Integer.parseInt(br.readLine());
            int ans = 0;
            for(int i=0;i<N-1;i++){
                search = M-arr[i];
                if(arr[i]<=M/2){
                    boolean get = binarySearch(i+1,N-1);
                    if(get){
                        ans = Math.max(ans,arr[i]);
                    }
                }
            }
            System.out.println("Peter should buy books whose prices are "+ans+" and "+(M-ans)+".");
            System.out.println();
            if(br.readLine()==null){
                break;
            }
        }
    }
}
