import java.util.*;
import java.io.*;
public class Main {            
    
    public static void main(String[] args){   
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            int rec[][] = new int[n][n];

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    rec[i][j] = in.nextInt();
                }
            }

            int max_so_far = rec[0][0];
            int max_ending;

            for(int i=0;i<n;i++){
                int tmp[] = new int[n];
                for(int i1=i; i1<n; i1++){
                    for(int j=0;j<n;j++){
                        tmp[j] += rec[i1][j];
                    }
                    // Kadene's Algo
                    max_ending = tmp[0];
                    max_so_far = Math.max(max_so_far,max_ending);

                    for(int j=1;j<n;j++){
                        max_ending = Math.max(tmp[j],max_ending+tmp[j]);
                        max_so_far = Math.max(max_so_far,max_ending);
                    }
                }
            }
            System.out.println(max_so_far);
        }
    }
}
