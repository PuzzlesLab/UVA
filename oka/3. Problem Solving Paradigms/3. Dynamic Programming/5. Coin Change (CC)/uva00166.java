import java.util.*;
import java.io.*;
public class Main {                  
       
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int limit = 1000;
        // limit is use initialize seller and buyer arr
        // setting for ans var also
        int coin[] = {5,10,20,50,100,200};
        int seller[] = new int[501];
        Arrays.fill(seller,limit);
        seller[0] = 0;
        for(int i=0;i<coin.length;i++){
            for(int j=coin[i];j<seller.length;j++){
                seller[j] = Math.min(seller[j],seller[j-coin[i]]+1);
            }
        }
        
        while(!(s=br.readLine()).equals("0 0 0 0 0 0")){
            int coefficient[] = new int[6];
            StringTokenizer st = new StringTokenizer(s);
            int value = 0;
            for(int i=0;i<6;i++){
                coefficient[i] = Integer.parseInt(st.nextToken());
                value += coefficient[i]*coin[i];
            }
            double target = Double.parseDouble(st.nextToken());
            // Rounding Error Fix
            int buyer[] = new int[value+1];
            int convert = (int)(target*100+0.005);
            Arrays.fill(buyer,limit);
            buyer[0] = 0;
            for(int i=0;i<coin.length;i++){
                for(int j=0;j<coefficient[i];j++){
                    for(int k=value;k>=coin[i];k--){
                        buyer[k] = Math.min(buyer[k],buyer[k-coin[i]]+1);
                    }
                }
            }
            int ans = limit;
            
            for(int i=0;i<=value-convert&&i<seller.length;i++){
                ans = Math.min(ans,buyer[convert+i]+seller[i]);
            }
            System.out.printf("%3d\n",ans);
        }
    }
}