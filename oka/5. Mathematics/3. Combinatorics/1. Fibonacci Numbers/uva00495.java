import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main { 

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        BigInteger fib[] = new BigInteger[5001];
        for(int i=0;i<=5000;i++){
            if(i==0){
                fib[i] = new BigInteger("0"); 
            }else if(i==1){
                fib[i] = new BigInteger("1");
            }else{
                fib[i] = fib[i-1].add(fib[i-2]);
            }
        }
        while((s=br.readLine())!=null){
            if(s.isEmpty())
                break;
            int n = Integer.parseInt(s);
            System.out.printf("The Fibonacci number for %d is %s\n",n,fib[n].toString());
        }
    }
}

