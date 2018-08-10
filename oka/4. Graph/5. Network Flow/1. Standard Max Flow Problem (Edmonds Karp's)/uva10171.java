import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s=br.readLine())!=null){
            if(s.equals("0")){
               break; 
            }
            int N = Integer.parseInt(s);
            int Y[][] = new int[26][26];
            int M[][] = new int[26][26];
            for(int i=0;i<26;i++){
                Arrays.fill(Y[i],100000);
                Arrays.fill(M[i],100000);
            }
            StringTokenizer st;
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                String status = st.nextToken();
                boolean bidirection = st.nextToken().equals("B");
                int u = st.nextToken().charAt(0)-'A';
                int v = st.nextToken().charAt(0)-'A';
                int w = Integer.parseInt(st.nextToken());
                if(status.equals("Y")){
                    Y[u][v] = w;
                    if(bidirection)
                        Y[v][u] = w;
                }else{
                    M[u][v] = w;
                    if(bidirection)
                        M[v][u] = w;
                }
            }
            for(int i=0;i<26;i++){
                Y[i][i] = 0;
                M[i][i] = 0;
            }
            for(int k=0;k<26;k++){
                for(int i=0;i<26;i++){
                    for(int j=0;j<26;j++){
                        Y[i][j] = Math.min(Y[i][j],Y[i][k]+Y[k][j]);
                        M[i][j] = Math.min(M[i][j],M[i][k]+M[k][j]);
                    }
                }
            }
            st = new StringTokenizer(br.readLine());
            int start = st.nextToken().charAt(0)-'A';
            int end = st.nextToken().charAt(0)-'A';
            int min = 100000;
            ArrayList<Integer> arr = new ArrayList<>();
            for(int i=0;i<26;i++){
                if(Y[start][i]==100000||M[end][i]==100000)
                    continue;
                int sum = Y[start][i] + M[end][i];
                if(min>sum){
                    min = sum;
                    arr.clear();
                    arr.add(i);
                }else if(min==sum){
                    arr.add(i);
                }
            }
            if(arr.isEmpty()){
                System.out.println("You will never meet.");
            }else{
                System.out.print(min);
                Collections.sort(arr);
                for(int i=0;i<arr.size();i++){
                    System.out.print(" "+(char)(arr.get(i)+'A'));
                }
                System.out.println();
            }
        }
    }
}