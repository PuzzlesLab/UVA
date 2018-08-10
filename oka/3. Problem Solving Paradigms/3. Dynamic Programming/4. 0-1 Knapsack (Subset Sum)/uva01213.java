import java.util.*;
import java.io.*;
public class Main {                  
    public static boolean composite[];
    public static int prime[];
    public static int count;
    public static void SieveOfEratosthenes(int upbound){
        int sqrt = (int)Math.sqrt(upbound);
        for(int i=2;i<=sqrt;i++){
            if(!composite[i]){
                prime[count++] = i;
                for(int j=i*i;j<=upbound;j+=i){
                    composite[j] = true;
                }
            }
        }
        for(int i=sqrt+1;i<=upbound;i++){
            if(!composite[i]){
                prime[count++] = i;
            }
        }
    }
    
    public static int dp[][][];
    
    public static int nWay(int n,int i,int k){
        if(n==0&&k==0)      return 1;
        else if(n<0||i>=count||k==0)    return 0;
        else if(dp[n][i][k]!=-1)        return dp[n][i][k];
        else return dp[n][i][k] = nWay(n,i+1,k)+nWay(n-prime[i],i+1,k-1);
    }
    
    public static void main(String[] args)throws IOException{   
        int upbound = 1120;
        composite = new boolean[upbound+1];
        prime = new int[upbound+1];
        SieveOfEratosthenes(upbound);
        dp = new int[1121][count][15];
        
        for(int i[][]:dp){
            for(int j[]:i){
                Arrays.fill(j,-1);
            }
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(!(s=br.readLine()).equals("0 0")){
            StringTokenizer st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            System.out.println(nWay(n,0,k));
        }
    }
}