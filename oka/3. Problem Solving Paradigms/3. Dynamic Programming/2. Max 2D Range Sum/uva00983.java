import java.util.*;
import java.io.*;
public class Main {            
    
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        String s;
        int line = 0;
        while((s=br.readLine())!=null){
            line++;
            if(line>1){
                s=br.readLine();
                pr.println("");
            }                  
            StringTokenizer st = new StringTokenizer(s);
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            long arr[][] = new long[N][N];
            long arr2[][] = new long[N-M+1][N];
            long convolution[][] = new long[N-M+1][N-M+1];
            long total = 0;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    arr[i][j] = Long.parseLong(br.readLine());
                }
            }
            
            for(int j=0;j<N;j++){
                for(int i=0;i<M;i++){
                    arr2[0][j] += arr[i][j];
                }
                for(int i=1;i<N-M+1;i++){
                    arr2[i][j] = arr2[i-1][j]-arr[i-1][j]+arr[i+M-1][j];
                }
            }
            
            for(int i=0;i<N-M+1;i++){
                for(int j=0;j<M;j++){
                    convolution[i][0] += arr2[i][j];
                }
                total += convolution[i][0];
                pr.println(convolution[i][0]);
                for(int j=1;j<N-M+1;j++){
                    convolution[i][j] = convolution[i][j-1]-arr2[i][j-1]+arr2[i][j+M-1];
                    total += convolution[i][j];
                    pr.println(convolution[i][j]);
                }
            }
            pr.println(total);
        }
        pr.close();
    }
}
