import java.util.*;
import java.io.*;
public class Main{            
    
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            StringTokenizer st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            int arr[] = new int[n];
            for(int i=0;i<n;i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            boolean result = true;
            boolean num[] = new boolean[n];
            for(int i=0;i<n-1;i++){
                int tmp = Math.abs(arr[i]-arr[i+1]);
                if(!(tmp>0&&tmp<n)){
                    result = false;
                    break;
                }else{
                    if(!num[tmp]){
                        num[tmp] = true;
                    }else{
                        result = false;
                        break;
                    }
                }
            }

            if(result){
                System.out.println("Jolly");
            }else{
                System.out.println("Not jolly");
            }
        }
    }
}
