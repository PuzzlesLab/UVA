import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	private static class Turtle implements Comparable<Turtle> {
		int weight;
		int strength;
		
		public Turtle(int weight, int strength) {
			this.weight=weight;
			this.strength=strength;
		}
		
		public int compareTo(Turtle t) {
			if (this.strength!=t.strength) return this.strength-t.strength;
			return this.weight-t.weight;
		}
	}

	public static void main (String [] args) throws Exception {	
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Turtle> turtles=new ArrayList<>();
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			if (st.countTokens()==0) break;
			int w=Integer.parseInt(st.nextToken());
			int str=Integer.parseInt(st.nextToken());
			turtles.add(new Turtle(w,str));
		}
		Collections.sort(turtles);

		int holder=Integer.MAX_VALUE/2;
		int size=turtles.size();
		int [] dp=new int [size+1];
		Arrays.fill(dp, holder);
		dp[0]=0;

		for (int i=0;i<size;i++) {
			Turtle curr=turtles.get(i);
			for (int i2=size;i2>=0;i2--) {
				if (dp[i2]+curr.weight<=curr.strength) dp[i2+1]=Math.min(dp[i2+1], curr.weight+dp[i2]);
			}
		}
		
		int ans=0;
		for (int i=dp.length-1;i>=0;i--) if (dp[i]!=holder) {
			ans=i;
			break;
		}
		System.out.println(ans);
	}
}
