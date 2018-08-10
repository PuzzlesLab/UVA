import java.util.*;
import java.io.*;
public class Main {         
    
    public static int time(String t){
        int sum = 0;
        for(int i=0;i<4;i++){
            sum += (t.charAt(i)-'0')*Math.pow(10,3-i);
        }
        return sum;
    }
    
    public static void main(String[] args)throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int N = Integer.parseInt(br.readLine());
        System.out.println("CALL FORWARDING OUTPUT");
        for(int i=1;i<=N;i++){
            System.out.println("SYSTEM "+i);
            Map<String,ArrayList<Call>> data = new HashMap<>();
            while(!(s=br.readLine()).equals("0000")){
                StringTokenizer st = new StringTokenizer(s);
                
                String source = st.nextToken();
                int time = time(st.nextToken());
                int duration = time(st.nextToken());
                String target = st.nextToken();
                if(!data.containsKey(source)){
                    ArrayList<Call> arr = new ArrayList<>();
                    arr.add(new Call(time,duration,target));
                    data.put(source,arr);
                }else{
                    data.get(source).add(new Call(time,duration,target));                   
                }
            }
            
            while(!(s=br.readLine()).equals("9000")){
                StringTokenizer st = new StringTokenizer(s);
                String call = st.nextToken();
                int time = time(call);
                String initial = st.nextToken();
                String target = initial;
                boolean loop = false;
                ArrayList<String> history = new ArrayList<>();
                int start = 0;
                while(data.containsKey(target)&&start<data.get(target).size()){
                    if(data.get(target).get(start).forward(time)){
                        target = data.get(target).get(start).getForward();
                        if(history.contains(target)){
                            loop = true;
                            break;
                        }else{
                            history.add(target);
                        }
                        start = 0;
                    }else{
                        start++;
                    }
                }
                
                String result;
                if(loop){
                    result = "9999";
                }else{
                    result = target;
                }
                
                System.out.printf("AT %s CALL TO %s RINGS %s\n",call,initial,result);
            }
            
        }
        System.out.println("END OF OUTPUT");
    }
}

class Call{
    private int time;
    private int duration;
    private String target;
    
    Call(int start,int d,String t){

        time = start;
        duration = d;
        target = t;
    }
    
    boolean forward(int t){
        if(t>=time&&t<=time+duration){
            return true;
        }    
        else
            return false;
    }
    
    String getForward(){
        return target;
    }
    
}