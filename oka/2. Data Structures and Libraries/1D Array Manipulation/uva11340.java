import java.util.*;
import java.io.*;
public class Main {            
    
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            int K = Integer.parseInt(br.readLine());
            HashMap<Character,Integer> money = new HashMap<>();
            for(int j=0;j<K;j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                money.put(st.nextToken().charAt(0),Integer.parseInt(st.nextToken()));
            }
            int M = Integer.parseInt(br.readLine());
            double total = 0;
            for(int j=0;j<M;j++){               
                for(char g:br.readLine().toCharArray()){
                    if(money.containsKey(g)){
                        total += money.get(g);
                    }    
                }               
            }
            pr.printf("%.2f$\n",total/100);
        }
        pr.close();
    }
}
