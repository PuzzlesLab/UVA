import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int num=Integer.parseInt(st.nextToken());
			int den=Integer.parseInt(st.nextToken());
			
			int carry=num/den;
			num%=den;
			
			int [] posRem=new int [den];
			Arrays.fill(posRem,-1);
			int cPos=0;
			int cycleStart=-1;
			ArrayList<Integer> quotient=new ArrayList<>();
			int rem=num;
			while (rem>0) {
				int q=(rem*10)/den;
				if (posRem[rem]!=-1) {
					cycleStart=posRem[rem];
					break;
				}
				quotient.add(q);
				posRem[rem]=cPos;
				cPos++;

				rem=(rem*10)%den;
			}

			StringBuilder sb=new StringBuilder();
			sb.append(carry);
			sb.append('.');
			if (quotient.isEmpty()) sb.append('0');
			else {
				for (int i=0;i<quotient.size();i++) {
					if (i==cycleStart) sb.append('(');
					sb.append(quotient.get(i));
				}
				if (cycleStart!=-1) sb.append(')');
			}
			System.out.println(sb);
		}
	}

}
