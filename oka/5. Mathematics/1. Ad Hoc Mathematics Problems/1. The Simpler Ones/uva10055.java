import java.util.*;
import java.io.*;
public class Main {         
    
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            StringTokenizer st = new StringTokenizer(s);
            System.out.println(Math.abs(Long.parseLong(st.nextToken())-Long.parseLong(st.nextToken())));
        }
    }
}
