import java.util.*;
import java.io.*;

public class Main { 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(!(s=br.readLine()).equals("0")){
            int N = Integer.parseInt(s);
            boolean can = false;
            for(int i=0;i<N;i++){
                int tmp = Integer.parseInt(br.readLine());
                if(tmp>0){
                    if(can)
                        System.out.print(" ");
                    
                    System.out.print(tmp);
                    can = true;
                }
            }
            if(can)
                System.out.println();
            else
                System.out.println(0);
        }
    }
}


