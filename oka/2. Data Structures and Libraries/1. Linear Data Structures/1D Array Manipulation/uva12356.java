import java.util.*;
import java.io.*;
public class Main {            
    
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        String s;
        while(!(s=br.readLine()).equals("0 0")){
            StringTokenizer st = new StringTokenizer(s);
            int S = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int L[] = new int[S+2];
            int R[] = new int[S+2];
            
            for(int i=1;i<=S;i++){
                L[i] = i-1;
                R[i] = i+1;
            }
            
            for(int i=0;i<B;i++){
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int left = Integer.parseInt(st2.nextToken());
                int right = Integer.parseInt(st2.nextToken());
                if(L[left]>0){
                    pr.print(L[left]+" ");
                }else{
                    pr.print("* ");
                }
                if(R[right]<S+1){
                    pr.println(R[right]);
                }else{
                    pr.println("*");
                }               
                L[R[right]] = L[left];
                R[L[left]] = R[right];
            }
            pr.println("-");
        }
        pr.close();
    }
}
