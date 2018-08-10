import java.util.*;
import java.io.*;
public class Main {     
    public static int row[];
    public static int max;
    public static int score[][];
    
    public static boolean place(int r,int c){
        for(int prev=0;prev<c;prev++){
            if(row[prev]==r||Math.abs(row[prev]-r)==Math.abs(prev-c)){
                return false;
            }
        }
        return true;
    }
    
    public static void backtrack(int c){
        if(c==8){
            int sum = 0;
            for(int i=0;i<8;i++){
                sum += score[row[i]][i];
            }
            max = Math.max(max,sum);
        }else{
            for(int r=0;r<8;r++){
                if(place(r,c)){
                    row[c] = r;
                    backtrack(c+1);
                }
            }
        }
    }
    
    public static void main(String[] args){   
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        
        for(int i=0;i<k;i++){
            row = new int[8];
            max = 0;
            score = new int[8][8];
            for(int j=0;j<8;j++){
                for(int m=0;m<8;m++){
                    score[j][m] = in.nextInt();
                }
            }
            backtrack(0);
            System.out.printf("%5d\n",max);
        }
    }
}