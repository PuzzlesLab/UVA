import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Answer {
		int x, y;
		boolean yReliable;
	}

	private static int search(boolean [] reliable, int currSize, int reliableCount, Answer[] answers) {
		if (currSize==reliable.length) {
			for (int i=0;i<answers.length;i++) if (reliable[answers[i].x]) {
				if (reliable[answers[i].y]!=answers[i].yReliable) return 0;
			}
			return reliableCount;
		} else {
			int ans=0;
			reliable[currSize]=true;
			ans=Math.max(ans,search(reliable,currSize+1,reliableCount+1,answers));
			reliable[currSize]=false;
			ans=Math.max(ans,search(reliable,currSize+1,reliableCount,answers));
			return ans;
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int I=Integer.parseInt(st.nextToken());
			int A=Integer.parseInt(st.nextToken());
			
			Answer [] answers=new Answer[A];
			for (int a=0;a<A;a++) {
				st=new StringTokenizer(br.readLine());
				answers[a]=new Answer();
				answers[a].x=Integer.parseInt(st.nextToken())-1;
				int temp=Integer.parseInt(st.nextToken());
				answers[a].y=Math.abs(temp)-1;
				answers[a].yReliable=temp>0;
			}

			System.out.println(search(new boolean [I], 0, 0, answers));
		}
	}

}
