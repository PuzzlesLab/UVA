import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {

	private static void addValues(int min, int max, ArrayList<Integer> values) {
		for (int i=min;i<=max;i+=2) values.add(i);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);

			ArrayList<Integer> values=new ArrayList<>();
			if (N>=4) { // Backtracking gives TLE for the N limit, use pattern instead.
				if (N%6!=2 && N%6!=3) {
					if (N%2==0) {
						addValues(2,N,values);
						addValues(1,N-1,values);
					} else {
						addValues(1,N,values);
						addValues(2,N-1,values);
					}
				} else {
					int start=(N%2==0) ? N/2 : (N-1)/2;
					if (start%2==0 && N%2==0) {
						addValues(start,N,values);
						addValues(2,start-2,values);
						addValues(start+3,N-1,values);
						addValues(1,start+1,values);
					} else if (start%2==0 && N%2==1) {
						addValues(start,N-1,values);
						addValues(2,start-2,values);
						addValues(start+3,N-2,values);
						addValues(1,start+1,values);
						values.add(N);
					} else if (start%2==1 && N%2==0) {
						addValues(start,N-1,values);
						addValues(1,start-2,values);
						addValues(start+3,N,values);
						addValues(2,start+1,values);
					} else {
						addValues(start,N-2,values);
						addValues(1,start-2,values);
						addValues(start+3,N-1,values);
						addValues(2,start+1,values);
						values.add(N);
					}
				}
			}
			
			StringBuilder ans=new StringBuilder();
			if (values.isEmpty()) ans.append("Impossible");
			else {
				for (int v: values) {
					ans.append(v);
					ans.append(' ');
				}
				ans.setLength(ans.length()-1);
			}
			System.out.println(ans);
		}
	}

}
