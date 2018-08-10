import java.util.*;
import java.io.*;

public class Main { 
    public static Map<String,Integer> m = new HashMap<>();
    public static int dp(String s){
        if(m.containsKey(s))
            return m.get(s);
        else{
            int r = 0;
            int i = 0;
            while(i<s.length()){
                int j = i+1;
                while(j<s.length()&&s.charAt(i)==s.charAt(j))
                    j++;
                if(j-i>1){
                    r = dp(s.substring(0,i)+s.substring(j));
                    if(r==1){
                        break;
                    }
                }
                i=j;
            }
            m.put(s,r);
            return r;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        m.put("",1);
        for(int i=0;i<T;i++){
            String s = br.readLine();
            System.out.println(dp(s));
        }
    }
}

