import java.util.*;
import java.io.*;
public class Main{         
    
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            StringTokenizer st = new StringTokenizer(s);
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            System.out.println(2*v*t);
        }
    }
}
