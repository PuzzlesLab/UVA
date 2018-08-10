import java.util.*;
import java.io.*;
public class Main {                  
       
    public static void main(String[] args)throws IOException{   
        int coin[] = {1,5,10,25,50};
        long way[] = new long[30001];
        way[0] = 1;
        
        for(int i=0;i<coin.length;i++){
            for(int j=coin[i];j<=30000;j++){
                way[j] += way[j-coin[i]];
            }
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            int n = Integer.parseInt(s);
            if(way[n]==1){
                System.out.printf("There is only %d way to produce %d cents change.\n",way[n],n);
            }else{
                System.out.printf("There are %d ways to produce %d cents change.\n",way[n],n);
            }
        }
    }
}