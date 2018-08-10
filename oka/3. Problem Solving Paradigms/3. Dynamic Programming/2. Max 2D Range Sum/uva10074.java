import java.util.*;
import java.io.*;
public class Main {            
    
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        String s;
        while(!(s=br.readLine()).equals("0 0")){
            StringTokenizer st = new StringTokenizer(s);
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int arr[][] = new int[M][N];
            for(int i=0;i<M;i++){
                s = br.readLine();
                for(int j=0;j<2*N;j+=2){
                    if(s.charAt(j)=='0'){
                        arr[i][j/2] = 1;
                    }else{
                        arr[i][j/2] = -100;
                    }
                }
            }
            
            int max_so_far = 0;
            int max_ending;
            
            for(int i=0;i<M;i++){
                int tmp[] = new int[N];
                for(int i1=i;i1<M;i1++){
                    for(int j=0;j<N;j++){
                        tmp[j] += arr[i1][j];
                    }
                    max_ending = tmp[0];
                    max_so_far = Math.max(max_so_far,tmp[0]);
                    for(int j=1;j<N;j++){
                        max_ending = Math.max(tmp[j],max_ending+tmp[j]);
                        max_so_far = Math.max(max_ending,max_so_far);
                    }
                }
                //System.out.println(max_so_far);
            }
            pr.println(max_so_far);
        }
        pr.close();
    }
}
