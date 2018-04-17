import java.util.*;
import java.io.*;

public class UVA_01225_DigitCounting {

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numCases = Integer.parseInt(br.readLine());

        for(int i=0; i<numCases; i++){
            int [] digit = new int[10];
            int N = Integer.parseInt(br.readLine());

            int j = 1;
            while(j <= N){
                int temp = j;
                while(temp > 0){
                    digit[temp%10]++;
                    temp/=10;
                }
                j++;
            }
            StringBuilder sb = new StringBuilder();
            for(int k : digit){
                sb.append(k+" ");
            }
            System.out.println(sb.toString().trim());
        }
    }
}
