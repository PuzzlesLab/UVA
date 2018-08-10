import java.util.*;
import java.io.*;
public class Main {         
    public static String dict[];
    public static String word;
    public static void print(String rule,String hold,int current){
        if(current<rule.length()){
            if(rule.charAt(current)=='0'){
                for(int i=0;i<=9;i++){                    
                    print(rule,hold+i,current+1);
                }
            }else{
                for(int i=0;i<dict.length;i++){
                    print(rule,hold+dict[i],current+1);
                }
            }
        }else{
            System.out.println(hold);
        }
    }
    
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            int n = Integer.parseInt(s);
            dict = new String[n];
            for(int i=0;i<n;i++){
                dict[i] = br.readLine();
            }
            int m = Integer.parseInt(br.readLine());
            System.out.println("--");
            for(int i=0;i<m;i++){
                print(br.readLine(),"",0);
            }
        }
    }
}
