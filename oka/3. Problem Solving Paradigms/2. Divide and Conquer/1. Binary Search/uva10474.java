import java.util.*;
import java.io.*;

public class Main {
    public static int arr[];
    public static int search;
    public static int binarySearch(int start,int end){
        if(start>=end){
            if(arr[start]==search)
                return start;
            return -1;
        }
        int mid = (start+end)/2;
        if(arr[mid]==search){
            return binarySearch(start,mid);
        }else if(arr[mid]<search){
            return binarySearch(start+1,end);
        }else{
            return binarySearch(start,mid-1);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int T = 0;
        while(!(s=br.readLine()).equals("0 0")){
            StringTokenizer st = new StringTokenizer(s);
            int N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());
            arr = new int[N];
            for(int i=0;i<N;i++){
                arr[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(arr);
            T++;
            StringBuilder sb = new StringBuilder();
            sb.append("CASE# ").append(T).append(":\n");
            //sb.append("CASE# "+T+":\n");
            for(int i=0;i<Q;i++){
                search = Integer.parseInt(br.readLine());
                int ans = binarySearch(0,N-1);
                if(ans==-1){
                    sb.append(search).append(" not found\n");
                    //sb.append(search+" not found\n");
                }else{
                    sb.append(search).append(" found at ").append(ans+1).append("\n");
                    //sb.append(search+" found at "+(ans+1)+"\n");
                }
            }
            System.out.print(sb.toString());
        }
    }
}
