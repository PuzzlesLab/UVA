import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			
			int levelA=(int)(Math.floor(Math.log(A)/Math.log(2)))+1;
			int levelB=(int)(Math.floor(Math.log(B)/Math.log(2)))+1;
			
			int lowerLevel=Math.max(levelA,levelB);
			int toRemove=(1<<(N-lowerLevel+1))-1;
			
			int total=(1<<N);
			System.out.println(total-toRemove);
		}
	}

}
