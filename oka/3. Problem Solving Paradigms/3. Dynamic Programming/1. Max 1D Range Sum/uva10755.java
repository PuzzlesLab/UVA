import java.io.*;
import java.util.*;
public class Main { 
    public static void main(String[] args)throws IOException{      
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i=1;i<=t;i++){
            if(i>1){
                System.out.println();
            }
            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.nextInt();
            long g[][][] = new long[x][y][z];
            for(int a=0;a<x;a++){
                for(int b=0;b<y;b++){
                    for(int c=0;c<z;c++){
                        g[a][b][c] = in.nextLong();
                    }
                }
            }
            long s[][][][][] = new long[x][y][y][z][z]; 
            for(int a=0;a<x;a++){
                for(int b1=0;b1<y;b1++){
                    for(int b2=b1;b2<y;b2++){
                        for(int c1=0;c1<z;c1++){
                            for(int c2=c1;c2<z;c2++){
                                if(b2-b1==0){
                                    s[a][b1][b2][c1][c2] += g[a][b2][c2];
                                    if(c2-c1>0){
                                        s[a][b1][b2][c1][c2] += s[a][b1][b2][c1][c2-1];
                                    }
                                }else{
                                    s[a][b1][b2][c1][c2] += s[a][b1][b2-1][c1][c2]+g[a][b2][c2];
                                    if(c2-c1>0){
                                        s[a][b1][b2][c1][c2] += s[a][b1][b2][c1][c2-1]-s[a][b1][b2-1][c1][c2-1];
                                    }
                                }
                            }
                        }
                    }
                }
            }
            long max = 0;
            long tmax = s[0][0][0][0][0];
            for(int b1=0;b1<y;b1++){
                for(int b2=b1;b2<y;b2++){
                    for(int c1=0;c1<z;c1++){
                        for(int c2=c1;c2<z;c2++){
                            max = s[0][b1][b2][c1][c2];
                            tmax = Math.max(tmax,max);
                            for(int a=1;a<x;a++){
                                max = Math.max(s[a][b1][b2][c1][c2],max+s[a][b1][b2][c1][c2]);
                                tmax = Math.max(tmax,max);
                            }
                        }
                    }
                }
            }
            System.out.println(tmax);
        }      
    }
}
