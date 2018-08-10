import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayList<Integer> arr = new ArrayList<>();
            int total = 0;
            while(st.hasMoreTokens()){
                int value = Integer.parseInt(st.nextToken());
                arr.add(value);
                total += value;
            }
     
            boolean dp[] = new boolean[201];
            dp[0] = true;
            for(int j=0;j<arr.size();j++){
                for(int k=200;k>=arr.get(j);k--){
                    if(dp[k-arr.get(j)]){
                        dp[k] = true;
                    }
                }
            }
            if(total%2!=0){
                System.out.println("NO");
            }else{
                if(dp[total/2]){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }
    }
       
}

