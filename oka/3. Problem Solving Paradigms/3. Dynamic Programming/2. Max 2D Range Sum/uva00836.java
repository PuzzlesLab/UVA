import java.util.*;
import java.io.*;
public class Main {            
    
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int i=1;i<=T;i++){
            br.readLine();
            String s = br.readLine();
            int N = s.length();
            int arr[][] = new int[N][N];
            
            for(int j=0;j<N;j++){
                if(s.charAt(j)=='0'){
                    // if set Integer.MIN_VALUE might be overflow
                    arr[0][j] = -25;
                }else{
                    arr[0][j] = 1;
                }
            }
            
            for(int j=1;j<N;j++){
                s = br.readLine();
                for(int k=0;k<N;k++){
                    if(s.charAt(k)=='0'){
                        arr[j][k] = -25;
                    }else{
                        arr[j][k] = 1;
                    }

                }
            }
            
            int max_ending;
            int max_so_far = arr[0][0];
            
            for(int j=0;j<N;j++){
                int tmp[] = new int[N];
                for(int j1=j;j1<N;j1++){
                    for(int k=0;k<N;k++){
                        tmp[k] += arr[j1][k];
                    }
                    max_ending = tmp[0];
                    max_so_far = Math.max(max_so_far,max_ending);
                    
                    for(int k=1;k<N;k++){
                        max_ending = Math.max(tmp[k],max_ending+tmp[k]);
                        max_so_far = Math.max(max_so_far,max_ending);
                    }
                }
            }
            
            if(max_so_far<0){
                System.out.println("0");
            }else{
                System.out.println(max_so_far);
            }
            
            if(i<T){
                System.out.println();
            }
        }
    }
}
