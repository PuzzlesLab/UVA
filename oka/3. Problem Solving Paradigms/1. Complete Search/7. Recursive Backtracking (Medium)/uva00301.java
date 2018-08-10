import java.util.*;
import java.io.*;

public class Main {
    public static int n;
    public static int t;
    public static int people[];
    public static Ticket tk[];
    public static int total = 0;
    
    public static void recursion(int x){
        if(x==t){
            int sum = 0;
            for(int i=0;i<people.length;i++){
                sum += people[i];
            }
            total = Math.max(total,sum);
            return;
        }
        boolean can = true;
        for(int i=tk[x].a;i<tk[x].b;i++){
            if(people[i]+tk[x].ppl>n){
                can = false;
                break;
            }
        }
        recursion(x+1);
        if(can){
            for(int i=tk[x].a;i<tk[x].b;i++){
                people[i] += tk[x].ppl;
            }
            // add ppl for recursion
            recursion(x+1);
            // remove ppl after recursion
            for(int i=tk[x].a;i<tk[x].b;i++){
                people[i] -= tk[x].ppl;
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(!(s=br.readLine()).equals("0 0 0")){
            StringTokenizer st = new StringTokenizer(s);
            n = Integer.parseInt(st.nextToken());
            int station = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            people = new int[station+1];
            tk = new Ticket[t];
            for(int i=0;i<t;i++){
                st = new StringTokenizer(br.readLine());
                tk[i] = new Ticket(st.nextToken(),st.nextToken(),st.nextToken());
            }
            total = 0;
            recursion(0);
            System.out.println(total);
        }
    }
}

class Ticket{
    int a;
    int b;
    int ppl;
    Ticket(String a,String b,String ppl){
        this.a = Integer.parseInt(a);
        this.b = Integer.parseInt(b);
        this.ppl = Integer.parseInt(ppl);
    }
}