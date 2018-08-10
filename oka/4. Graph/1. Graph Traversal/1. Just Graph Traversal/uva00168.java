import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        String s;
        while(!(s=br.readLine()).equals("#")){
            StringTokenizer st = new StringTokenizer(s,";");
            int count = st.countTokens();
            Map<Integer,ArrayList<Integer>> g = new HashMap<>();
            boolean candle[] = new boolean[26];
            for(int i=1;i<count;i++){
                String path = st.nextToken();
                int start = path.charAt(0)-'A';
                ArrayList<Integer> arr = new ArrayList<>();
                for(int j=2;j<path.length();j++){
                    int end = path.charAt(j)-'A';
                    arr.add(end);
                }
                g.put(start,arr);
            }
            StringTokenizer st2 = new StringTokenizer(st.nextToken(),".");
            String path = st2.nextToken();
            ArrayList<Integer> arr = new ArrayList<>();
            int start = path.charAt(0)-'A';
            for(int i=2;i<path.length();i++){
                int end = path.charAt(i)-'A';
                arr.add(end);
            }
            g.put(start,arr);

            StringTokenizer st3 = new StringTokenizer(st2.nextToken());

            int minotaur = st3.nextToken().charAt(0)-'A';
            int theseus = st3.nextToken().charAt(0)-'A';
            int k = Integer.parseInt(st3.nextToken());
            
            int counter = 0;
            StringBuilder sb = new StringBuilder();
            while(true){
                boolean escape = false;
                int initial = minotaur;
                if(g.get(minotaur)!=null){
                    for(int i=0;i<g.get(minotaur).size();i++){
                        int target = g.get(minotaur).get(i);
                        if(target!=theseus&&!candle[target]){
                            escape = true;
                            minotaur = target;
                            break;
                        }
                    }
                }
                if(!escape){
                    sb.append("/"+(char)(minotaur+'A')+"\n");
                    break;
                }
                theseus = initial;
                counter = (counter+1)%k;
                if(counter==0){
                    candle[theseus] = true;
                    sb.append((char)(theseus+'A')+" ");
                }
            }
            pr.print(sb);
        }
        pr.close();
    }  
}
