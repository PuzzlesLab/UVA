import java.io.*;
import java.util.*;

class Main{
    public static boolean square(int n){
        int sqrt = (int)Math.sqrt(n);
        return (sqrt*sqrt==n);
    }
    
    public static void main(String args[])throws IOException{
        ArrayList <Integer>[] peg = new ArrayList[50];
        int p[] = new int[50];
        int count = 0;
        
        for(int i=0;i<50;i++){
            peg[i] = new ArrayList<>();
        }
        
        outerloop:
        for(int i=1;;i++){
            for(int j=0;j<=50;j++){
                if(j>=50){
                    p[j-1] = count;
                    break outerloop;
                }
                else if(peg[j].isEmpty()){
                    if(j>0){
                        p[j-1] = count;
                    }
                    peg[j].add(i);
                    count++;
                    break;
                }else{
                    int lastIndex = peg[j].size()-1;
                    int last = peg[j].get(lastIndex);
                    if(square(last+i)){
                        peg[j].add(i);
                        count++;
                        break;
                    }
                }               
            }
        }
        
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i=1;i<=T;i++){
            int N = Integer.parseInt(br.readLine());
            System.out.println(p[N-1]);
        }
    }
     
}

