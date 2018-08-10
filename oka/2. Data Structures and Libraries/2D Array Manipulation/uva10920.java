import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(!(s=br.readLine()).equals("0 0")){
            StringTokenizer st = new StringTokenizer(s);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            int r = (int)Math.sqrt(b);
            boolean finish = false;
            
            int line=r, col=r;
            
            if(r*r==b&&r%2==1){
               finish = true;
            }else if(r%2==0){
                line--;
                col--;
                r--;
            }
            
            if(!finish){
                int period = r+1;
                int remainder = b-r*r;
                int group = (remainder-1)/period;
                int gr = (remainder-1)%period;
                switch(group){
                    case 0: line++;
                            col-=gr;
                            break;
                    case 1: line-=gr;
                            col-=r;
                            break;
                    case 2: line-=r;
                            col-=r-1-gr;
                            break;
                    case 3: line-=r-1-gr;
                            col++;
                            break;
                }
                
            }
            
            System.out.printf("Line = %d, column = %d.\n",line+(a-r)/2,col+(a-r)/2);
        }
    }
   
}
