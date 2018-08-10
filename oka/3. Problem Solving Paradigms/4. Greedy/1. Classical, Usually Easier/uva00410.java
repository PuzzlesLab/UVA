import java.io.*;
import java.util.*;
 
public class Main {
     
    public static void main(String args[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String t;
        int test = 0;
        while((t=br.readLine())!=null&&!t.isEmpty()){
            test++;
            System.out.printf("Set #%d\n",test);
            StringTokenizer st = new StringTokenizer(t);
            int C = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            int arr[] = new int[2*C];
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int total = 0;
            for(int i=0;i<S;i++){
                arr[i] = Integer.parseInt(st2.nextToken());
                total += arr[i];
            }
            Arrays.sort(arr);
            double AM = (double)total/C;
            double sigma = 0;
            for(int j=0;j<C;j++){
                double tmp = (double)arr[j]-AM;
                System.out.printf("%2d:",j);
                // because the min is 1
                if(arr[j]!=0)
                    System.out.printf(" %d",arr[j]);
                int last = 2*C-j-1;
                if(arr[last]!=0)
                    System.out.printf(" %d",arr[last]);
                tmp += arr[last];
                System.out.printf("\n");
                sigma += Math.abs(tmp);
            }
            System.out.printf("IMBALANCE = %.5f\n",sigma);
            // blank line after test case
            System.out.println("");
        }
    }
}
 
