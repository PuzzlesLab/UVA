import java.util.*;
import java.io.*;

public class Main {
   
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while(true){
            int Y = Integer.parseInt(br.readLine());
            int N = Integer.parseInt(br.readLine());
            int a[] = new int[N];
            for(int i=0;i<N;i++){
                a[i] = Integer.parseInt(br.readLine());
            }
            int max = 0;
            int start = -1;
            int end = -1;
            for(int i=0;i<N;i++){
                int j = i;
                int count = 0;
                while(j<N&&a[j]<a[i]+Y){
                    count++;
                    j++;
                }
                if(count>max){
                    max = count;
                    start = a[i];
                    end = a[j-1];
                }
            }
            System.out.printf("%d %d %d\n",max,start,end);
            if(br.readLine()==null){
                break;
            }
        }
    }
       
}

