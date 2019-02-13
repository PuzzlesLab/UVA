import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans=new StringBuilder();
		
		TreeSet<Integer> scores=new TreeSet<>();
		for (int i=1;i<=20;i++) {
			scores.add(i);
			scores.add(i*2);
			scores.add(i*3);
		}
		scores.add(0);
		scores.add(50);
		
		int [] P=new int [181];
		int [] C=new int [181];
		for (int p1 : scores) for (int p2 : scores) for (int p3 : scores) {
			int sum=p1+p2+p3;
			P[sum]++;
			if (p1>=p2 && p2>=p3) C[sum]++;
		}

		while (true) {
			int targetScore=Integer.parseInt(br.readLine());
			if (targetScore<=0) break;
			int p=0, c=0;
			
			if (targetScore<=180) {
				p=P[targetScore];
				c=C[targetScore];
			}
			
			if (p==0) {
				ans.append("THE SCORE OF ");
				ans.append(targetScore);
				ans.append(" CANNOT BE MADE WITH THREE DARTS.\n");
			}
			else {
				ans.append("NUMBER OF COMBINATIONS THAT SCORES ");
				ans.append(targetScore);
				ans.append(" IS ");
				ans.append(c);
				ans.append(".\n");
				
				ans.append("NUMBER OF PERMUTATIONS THAT SCORES ");
				ans.append(targetScore);
				ans.append(" IS ");
				ans.append(p);
				ans.append(".\n");
			}
			ans.append("**********************************************************************\n");
		}
		ans.append("END OF OUTPUT");
		System.out.println(ans.toString());
	}

}