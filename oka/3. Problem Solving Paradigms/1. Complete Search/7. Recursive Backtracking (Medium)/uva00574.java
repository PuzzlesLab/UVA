import java.util.*;
import java.io.*;

public class Main { 
    public static int t;
    public static int n;
    public static int num[];
    public static int total;
    public static int eqn[];
    public static int count;
    public static void sum(int index,int eqnIndex){
        if(total==t){
            count++;
            System.out.print(eqn[0]);
            for(int i=1;i<eqnIndex;i++){
                System.out.print("+"+eqn[i]);
            }
            System.out.println();
            return;
        }
        if(index==n)
            return;
        int last = -1;
        for(int i=index;i<n;i++){
            if(last==num[i])
                continue;
            if(total+num[i]<=t){
                total += num[i];
                eqn[eqnIndex] = num[i];
                sum(i+1,eqnIndex+1);
                eqn[eqnIndex] = 0;// actually can be omit these line
                total -= num[i];
            } 
            last = num[i];
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(!(s=br.readLine()).isEmpty()){
            StringTokenizer st = new StringTokenizer(s);
            t = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if(n==0)
                break;
            num = new int[n];
            eqn = new int[n];
            for(int i=0;i<n;i++){
                num[i] = Integer.parseInt(st.nextToken());
            }
            System.out.println("Sums of "+t+":");
            count = 0;
            total = 0;
            sum(0,0);
            if(count==0)
                System.out.println("NONE");
        }
    }
}

