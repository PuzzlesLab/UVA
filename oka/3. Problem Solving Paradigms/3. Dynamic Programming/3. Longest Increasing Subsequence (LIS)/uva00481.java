import java.util.*;
import java.io.*;
public class Main {                  
    public static int search(int arr1[],int T[],int len,int target){
        int low = 0;
        int high = len;
        int mid = -1;
        while(low<=high){
            mid = (low+high)/2;
            // mid < len to prevent mid+1 out of the max index: len
            if(mid<len&&target>arr1[T[mid]]&&target<=arr1[T[mid+1]]){
                return mid+1;
            }else if(target<arr1[T[mid]]){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        // condition come here when target=arr1[T[mid]] && target<arr1[T[mid+1]];
        if(target>arr1[T[mid]]){
            return mid+1;
        }else{
            return mid;
        }
    }
    
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        String s;
        
        ArrayList<Integer> arr = new ArrayList<>();
        while((s=br.readLine())!=null&&!s.isEmpty()){
            arr.add(Integer.parseInt(s));
        }
      
        int size = arr.size();
        int path[] = new int[size];
        int arr1[] = new int[size];
        
        for(int i=0;i<size;i++){
            arr1[i] = arr.get(i);
            path[i] = -1;
        }
        
        int len = 0;
        int T[] = new int[size];
        T[0] = 0;
        
        for(int i=1;i<size;i++){
            if(arr1[i]<arr1[T[0]]){
                T[0] = i; 
            }else if(arr1[i]>arr1[T[len]]){
                len++;
                T[len] = i;
                path[T[len]] = T[len-1]; 
            }else{
                int index = search(arr1,T,len,arr1[i]);
                T[index] = i;
                path[T[index]] = T[index-1];
            }
        }
        
        int max = len+1; //len is index-based 
        
        Stack<Integer> stack = new Stack<>();
        int index = T[len];
        
        while(index!=-1){
            stack.push(arr1[index]);
            index = path[index];
        }
        
        pr.println(max);
        pr.println("-");
        while(stack.size()>0)
            pr.println(stack.pop());// sb contain \n already
        pr.close();
    }
}
