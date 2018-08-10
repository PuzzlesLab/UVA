import java.util.*;
import java.io.*;

public class Main { 

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            br.readLine();
            int N = Integer.parseInt(br.readLine());
            Task t[] = new Task[N];
            StringTokenizer st;
            for(int j=0;j<N;j++){
                st = new StringTokenizer(br.readLine());
                int day = Integer.parseInt(st.nextToken());
                int fine = Integer.parseInt(st.nextToken());
                t[j] = new Task(day,fine,j+1);
            }
            if(i>0)
                System.out.println();
            Arrays.sort(t);
            for(int j=0;j<N-1;j++){
                System.out.print(t[j].id+" ");
            }
            System.out.println(t[N-1].id);
        }
    }
}

class Task implements Comparable<Task>{
    int day;
    int fine;
    int id;
    
    Task(int day,int fine,int id){
        this.day = day;
        this.fine = fine;
        this.id = id;
    }
       
    // (x1,y1) , (x2,y2)
    // By induction, neighbour swap does not affect other fine in the group.
    // Hence, fine for neighbour 
    // 1,2 = x1*y2 , 2,1=x2*y1
    // Since minimize, so if x1*y2>x2*y1 , we use 1,2 vice versa.  
    @Override
    public int compareTo(Task t){
        //if(day!=t.day){
            int diff = day*t.fine-t.day*fine;
            if(diff!=0)
                return diff;
            else
                return id-t.id;
    }
}
