import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t>0){
            t--;
            br.readLine();
            int M = Integer.parseInt(br.readLine());
            int start = 0;
            int end = 0;
            String s;
            ArrayList<Range> r = new ArrayList<>();
            while(!(s=br.readLine()).equals("0 0")){
                StringTokenizer st = new StringTokenizer(s);
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                r.add(new Range(left,right));
            }
            Collections.sort(r);
            ArrayList<Range> arr = new ArrayList<>();
            boolean first = true;
            int count = 0;
            for (Range rn : r) {
                if(end>=M){
                    break;
                }      
                int left = rn.left;
                if(left<=start){
                    left = start;
                    if(left>=rn.right){
                        continue;
                    }
                    if(end<rn.right){
                        if(first){
                            first = false;
                            arr.add(new Range(rn.left,rn.right));
                            count++;
                        }else{
                            int index = arr.size()-1;
                            arr.set(index,new Range(rn.left,rn.right));
                        }
                        end = rn.right;
                    }
                }else{
                    if(end<left){
                        // no solution
                        break;
                    }
                    start = end;
                    end = rn.right;
                    arr.add(new Range(rn.left,rn.right));
                    count++;
                }                
            }
            if(end<M){
                count = 0;
            }
            System.out.println(count);
            if(count>0){
                for (Range rn : arr) {
                    System.out.println(rn.left+" "+rn.right);
                }
            }
            if(t>0){
                System.out.println();
            }
        }
    }
}

class Range implements Comparable<Range>{
    int left;
    int right;
    Range(int left,int right){
        this.left = left;
        this.right = right;
    }
    @Override
    public int compareTo(Range r){
        if(this.left>r.left){
            return 1;
        }else if(this.left<r.left){
            return -1;
        }
        if(this.right>r.right){
            return 1;
        }else{
            return -1;
        }
    }
}
