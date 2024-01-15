import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int times;
		char [] text;
		
		public Tuple(int t, char [] s) {
			this.times=t;
			this.text=s;
		}
	}

	private static String pad(String s, int len) {
		StringBuilder sb=new StringBuilder(s);
		while (sb.length()<len) sb.append(" ");
		return sb.toString();
	}

	private static int[] getCycle(int [] nums, int digit) {
		ArrayList<Integer> list=new ArrayList<>();
		int temp=digit;
		do {
			list.add(temp);
			temp=nums[temp];
		} while (temp!=digit);

		int [] result=new int[list.size()];
		for (int i=0;i<result.length;i++) result[i]=list.get(i);
		return result;
	};

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String s=br.readLine().trim();
			int N=Integer.parseInt(s);
			if (N==0) break;

			StringTokenizer st=new StringTokenizer(br.readLine());
			int [] nums=new int [N];
			for (int i=0;i<nums.length;i++) nums[Integer.parseInt(st.nextToken())-1]=i;

			ArrayList<Tuple> queries=new ArrayList<>();
			while (true) {
				s=br.readLine();
				if (s.equals("0")) break;
				int K=0;
				int strip=0;
				for (int i=0;i<s.length();i++) {
					if (Character.isDigit(s.charAt(i))) {
						K=K*10+(s.charAt(i)-'0');
						strip++;
					} else break;
				}
				s=pad(s.substring(strip+1),N);
				
				char [] table=new char [N];
				for (int i=0;i<N;i++) table[i]=s.charAt(i);
				queries.add(new Tuple(K,table));
			}

			int [][] cycleTable=new int [N][];
			for (int n=0;n<N;n++) cycleTable[n]=getCycle(nums,n);

			for (int q=0;q<queries.size();q++) {
				Tuple t=queries.get(q);

				StringBuilder sb=new StringBuilder();
				for (int n=0;n<N;n++) sb.append(t.text[cycleTable[n][t.times%cycleTable[n].length]]);
				System.out.println(sb);
			}
			System.out.println();
		}
	}

}
