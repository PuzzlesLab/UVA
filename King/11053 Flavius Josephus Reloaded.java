import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			long N=Long.parseLong(st.nextToken());
			long a=Long.parseLong(st.nextToken());
			long b=Long.parseLong(st.nextToken());
			a%=N;

			HashMap<Long,Integer> visitCount=new HashMap<>();
			long curr=0;
			long length=N;
			while (true) {
				curr%=N;
				curr=(curr*curr)%N;
				curr=(a*curr)%N;
				curr=(curr+b)%N;
				
				int currCount=visitCount.getOrDefault(curr,0)+1;
				if (currCount==2) length--;
				else if (currCount==3) break;
				visitCount.put(curr,currCount);
			}
			
			System.out.println(length);
		}
	}

}
