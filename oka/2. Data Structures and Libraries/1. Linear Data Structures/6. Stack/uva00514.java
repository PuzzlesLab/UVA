import java.util.*;
import java.io.*;

public class Main {
    public static void main(String arg[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            int T = Integer.parseInt(s);
            if(T==0)
                break;
            while(!(s=br.readLine()).equals("0")){
                int cur = 1;
                Stack<Integer> stack = new Stack<>();
                StringTokenizer st = new StringTokenizer(s);
                
                outer:
                for(int i=0;i<T;i++){
                    int now = Integer.parseInt(st.nextToken());
                    if(stack.isEmpty()){
                        stack.add(cur);
                        cur++;
                    }
                    while(stack.peek()!=now){
                        stack.add(cur);
                        cur++;
                        if(cur==T+2){
                            break outer;
                        }
                    }
                    if(stack.peek()==now){
                        stack.pop();
                    }
                }
                if(stack.isEmpty()){
                    System.out.println("Yes");
                }else{
                    System.out.println("No");
                }
            }
            System.out.println();
        }
    }
    
}
