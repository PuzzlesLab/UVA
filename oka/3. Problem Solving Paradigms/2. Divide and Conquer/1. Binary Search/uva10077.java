import java.util.*;
import java.io.*;

public class Main {
   
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(!(s=br.readLine()).equals("1 1")){
            StringTokenizer st = new StringTokenizer(s);
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            //current
            int a = 1;
            int b = 1;
            
            //left parent
            int la = 0;
            int lb = 1;
            
            //right parent
            int ra = 1;
            int rb = 0;
            
            // the tree pattern is same as binary tree
            // the left branches of subtree will smaller than current node
            // the right branches of subtree will bigger than current node
            // we nid compute common denominator to compare the numerator
            
            while(A!=a||B!=b){
                // if current node greater than the request node
                if(a*B > A*b){
                    System.out.print("L");
                    // update right parent
                    // right parent become current node
                    ra = a;
                    rb = b;
                }else{
                    System.out.print("R");
                    // update left parent
                    // left parent become current node
                    la = a; 
                    lb = b;
                }
                // update current node
                a = la + ra;
                b = lb + rb;
            }
            System.out.println();
        }
    }
       
}

