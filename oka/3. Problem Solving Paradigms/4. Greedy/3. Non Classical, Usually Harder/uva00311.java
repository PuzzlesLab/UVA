import java.util.*;
import java.io.*;

public class Main { 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int r;
        while(!(s=br.readLine().trim()).equals("0 0 0 0 0 0")){
            StringTokenizer st = new StringTokenizer(s);
            int a[] = new int[6];
            a[0] = Integer.parseInt(st.nextToken());
            a[1] = Integer.parseInt(st.nextToken());
            a[2] = Integer.parseInt(st.nextToken());
            a[3] = Integer.parseInt(st.nextToken());
            a[4] = Integer.parseInt(st.nextToken());
            a[5] = Integer.parseInt(st.nextToken());
            
            int box = a[5]+a[4]+a[3];
            a[0] -= a[4]*11;
            a[1] -= a[3]*5;
            
            box += a[2]/4;
            r = a[2]%4;
            if(r!=0)
                box++;
            
            int da[] = {0,5,3,1};// 2*2 
            int db[] = {0,7,6,5};// 1*1            
            
            a[1] -= da[r];
            a[0] -= db[r];
                        
            if(a[1]>0){
                r = a[1]%9;
                box += a[1]/9;
                if(r!=0){
                    box++;
                    a[1] = r-9; // negative value
                }
            }
            
            if(a[1]<0)
                a[0] += a[1]*4; // add a negative value
            
            if(a[0]>0){
                r = a[0]%36;
                box += a[0]/36;
                if(r!=0){
                    box++;
                }
            }
            
            System.out.println(box);
        }
    }
}


