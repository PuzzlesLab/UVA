import java.util.*;
import java.io.*;

public class Main {
    public static boolean composite[] = new boolean [32];
    public static int n;
    public static int arr[];
    
    public static void soe(int limit){
        int sqrt = (int)Math.sqrt(limit);
        for(int i=2;i<=sqrt;i++){
            if(!composite[i]){
                for(int j=i*i;j<=limit;j+=i){
                    composite[j] = true;
                }
            }
        }
    }

    public static void pattern(int s,int a){
        int tmp = arr[a];
        for(int i=a;i>s;i--){
            arr[i] = arr[i-1];
        }
        arr[s] = tmp;
    }
    
    public static void revert(int s,int a){
        int tmp = arr[s];
        for(int i=s;i<a;i++){
            arr[i] = arr[i+1];
        }
        arr[a] = tmp;
    }
    
    public static void permutation(int s){   
        if(n<2)
            return;
        if(s==n){
            int num = arr[0]+arr[n-1];
            if(!composite[num]){
                StringBuilder sb = new StringBuilder();
                for(int i=0;i<arr.length-1;i++){
                    sb.append(arr[i]+" ");
                }
                sb.append(arr[n-1]);
                System.out.println(sb.toString());
            }
            return;
        }
        for(int i=s;i<arr.length;i++){
            int num = arr[s-1]+arr[i];
            if(!composite[num]){
                pattern(s,i);
                permutation(s+1);
                revert(s,i);
            }
        }        
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int T = 0;
        soe(31);
        while((s=br.readLine())!=null&&!s.isEmpty()){
            n = Integer.parseInt(s);
            arr = new int[n];
            for(int i=0;i<n;i++){
                arr[i] = i+1;
            }
            T++;
            if(T>1){
                System.out.println();
            }
            System.out.printf("Case %d:\n",T);
            permutation(1);
        }
    }
}
