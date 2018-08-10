import java.util.*;
import java.io.*;
public class Main {                  
    
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int line = 0;
        
        while(!(s=br.readLine()).equals("0")){
            line++;
            int t = Integer.parseInt(s);
            block b[] = new block[t*3];
            for(int i=0;i<t;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int L = Integer.parseInt(st.nextToken());
                int W = Integer.parseInt(st.nextToken());
                int H = Integer.parseInt(st.nextToken());
             
                b[i*3] = new block(L,W,H);
                b[i*3+1] = new block(L,H,W);
                b[i*3+2] = new block(H,W,L);
            }
            Arrays.sort(b);
            int lis[] = new int[3*t];
            lis[0] = b[0].height;
            for(int i=1;i<3*t;i++){
                lis[i] = Math.max(b[i].height,lis[i]);
                for(int j=0;j<i;j++){
                    if(b[j].width<b[i].width&&b[j].length<b[i].length){
                        lis[i] = Math.max(lis[i],lis[j]+b[i].height);
                    }
                }
                
            }
            int max = 0;
            for(int i=0;i<3*t;i++){
                max = Math.max(lis[i],max);
            }
            System.out.printf("Case %d: maximum height = %d\n",line,max);
        }
    }
}

class block implements Comparable<block>{
    public int length;
    public int width;
    public int height;
    
    public block(int L,int W,int H){
        length = Math.min(L,W);
        width = Math.max(L,W);
        height = H;
    }
    
    @Override
    public int compareTo(block c){
        if(c.length>this.length){
            return -1;
        }else if(c.length<this.length){
            return 1;
        }else if(c.width>this.width){
            return -1;
        }else{
            return 1;
        }
    }
    
}