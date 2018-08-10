import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            br.readLine();
            int limit = Integer.parseInt(br.readLine().trim())*100;
            ArrayList<Integer> arr = new ArrayList<>();
            String s;
            while(!(s=br.readLine().trim()).equals("0")){
                arr.add(Integer.parseInt(s));
            }    
            int size = arr.size();
            int dp[][] = new int[size][limit+1];
            //0 no solution, 1 port, starboard
            // second dimension is store the state of starboard
            int hold = 0;
            int count = 0;
            for(int j=0;j<size;j++){
                count++;
                hold += arr.get(j);
                boolean can = false;
                for(int k=0;k<=limit;k++){
                    if(j==0){
                        if(hold-arr.get(j)<=limit && arr.get(j)<=limit){
                            dp[j][0] = 1;
                            can = true;
                            break;
                        }
                    }else{
                        if(k>hold)
                            break;
                        if(dp[j-1][k]!=0 && hold-k<=limit){
                            dp[j][k] = 1;// port
                            can = true;
                        }
                        if(k-arr.get(j)>=0 && dp[j-1][k-arr.get(j)]!=0 && hold-k<=limit){
                            dp[j][k] = 2;// starboard
                            can = true;
                        }
                    }
                    
                }
                if(!can){
                    count--;
                    break;
                }
            }
            if(count<=0){
                System.out.println("0");
            }else{
                System.out.println(count);
                int index = count-1;
                int end = 0;
                for(int j=limit;j>=0;j--){
                    if(dp[index][j]!=0){
                        end = j;
                        break;
                    }
                }
                String ans = "";
                while(index>=0){
                    int tmp = dp[index][end];
                    if(tmp==1){
                        ans = "port\n"+ans;
                    }else{
                        ans = "starboard\n"+ans;
                        end -= arr.get(index);
                    }
                    index--;
                }
                System.out.print(ans);
            }
            if(i<T-1){
                System.out.println();// blank line consecutive cases
            }
        }
    }
       
}

