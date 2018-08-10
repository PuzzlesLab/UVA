import java.util.*;
import java.io.*;
public class Main {                  
   
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int line = 0;
        String s;
        while(!(s=br.readLine()).equals("-1")){
            line++;
            if(line>1){
                System.out.println();
            }
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(Integer.parseInt(s));
            while(!(s=br.readLine()).equals("-1")){
                arr.add(Integer.parseInt(s));
            }
            int size = arr.size();
            int lis[] = new int[size];
            lis[0] = 1;
            for(int i=1;i<size;i++){
                //initialize all lis[] = 1 (can remove this line if the all lis[] already assign
                lis[i] = Math.max(lis[i],1);
                for(int j=0;j<i;j++){
                    if(arr.get(j)>=arr.get(i)){
                        lis[i] = Math.max(lis[i],lis[j]+1);
                    }
                }
            }
            int max = 0;
            for(int i=0;i<size;i++){
                max = Math.max(max,lis[i]);
            }
            System.out.printf("Test #%d:\n",line);
            System.out.println("  maximum possible interceptions: "+max);
        }
    }
}
