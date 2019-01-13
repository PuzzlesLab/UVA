import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int maxLength=Integer.parseInt(st.nextToken())*100;
			int cars=Integer.parseInt(st.nextToken());
			
			LinkedList<Integer> left=new LinkedList<>();
			LinkedList<Integer> right=new LinkedList<>();
			for (int car=0;car<cars;car++) {
				st=new StringTokenizer(br.readLine());
				int length=Integer.parseInt(st.nextToken());
				if (st.nextToken().equals("left")) left.add(length);
				else right.add(length);
			}
			
			int ans=0;
			boolean ferryAtLeft=true;
			while (!left.isEmpty() || !right.isEmpty()) {
				int currLength=0;
				if (ferryAtLeft) {
					while (!left.isEmpty() && currLength+left.peek()<=maxLength) {
						currLength+=left.peek();
						left.removeFirst();
					}

					ferryAtLeft=false;
					ans++;
				} else if (!ferryAtLeft) {
					while (!right.isEmpty() && currLength+right.peek()<=maxLength) {
						currLength+=right.peek();
						right.removeFirst();
					}
					
					ferryAtLeft=true;
					ans++;
				}
			}
			System.out.println(ans);
		}
	}

}
