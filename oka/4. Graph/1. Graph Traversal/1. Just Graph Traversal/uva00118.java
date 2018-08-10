import java.util.*;
import java.io.*;
public class Main {                
    public static char[] dir = {'N','E','S','W'};
    public static int states; // 0 -> N, 1 -> E,...
    public static int recX,recY ;
    public static int x,y ;
    
    public static void forward(){
        switch(states){
            case 0: y++;  break;
            case 1: x++;  break;
            case 2: y--;  break;
            case 3: x--;  break;
        }
    }
    
    public static void back(){
        switch(states){
            case 0: y--;  break;
            case 1: x--;  break;
            case 2: y++;  break;
            case 3: x++;  break;
        }
    }
    
    public static void left(){
        states = (states+3)%4;
    }
    
    public static void right(){
        states = (states+1)%4;
    }    
    
    public static int state(String s){
        switch(s){
            case "N": return 0;
            case "E": return 1;
            case "S": return 2;
            case "W": return 3;
        }
        return -1;
    }
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        recX = Integer.parseInt(st.nextToken());
        recY = Integer.parseInt(st.nextToken());
        boolean protect[][] = new boolean[51][51];
        String s;
        while((s=br.readLine())!=null&&!s.isEmpty()){
            StringTokenizer st2 = new StringTokenizer(s);
            x = Integer.parseInt(st2.nextToken());
            y = Integer.parseInt(st2.nextToken());
            states = state(st2.nextToken());
            String op = br.readLine();
            boolean lost = false;

            for(int i=0;i<op.length();i++){
                if(op.charAt(i)=='L'){
                    left();
                }else if(op.charAt(i)=='R'){
                    right();
                }else if(op.charAt(i)=='F'){
                    forward();
                    if(x<0||x>recX||y<0||y>recY){
                        back();
                        if(!protect[x][y]){
                            protect[x][y] = true;
                            lost = true;
                            break;
                        }
                    }
                }
            }
            if(lost)
                System.out.println(x+" "+y+" "+dir[states]+" LOST");
            else
                System.out.println(x+" "+y+" "+dir[states]);
        }
    }
}