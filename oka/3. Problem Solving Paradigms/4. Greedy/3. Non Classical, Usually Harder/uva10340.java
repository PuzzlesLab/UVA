import java.util.*;
import java.io.*;

public class Main { 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            StringTokenizer st = new StringTokenizer(s);
            String a = st.nextToken();
            String b = st.nextToken();
            int start = 0;
            for(int i=0;i<b.length();i++){
                if(a.charAt(start)==b.charAt(i))
                    start++;
                if(start==a.length())
                    break;
            }
            if(start==a.length())
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }
}


