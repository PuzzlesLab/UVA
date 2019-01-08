import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int num=Integer.parseInt(st.nextToken());
			int den=Integer.parseInt(st.nextToken());
			
			int tempN=num;
			int [] quotient=new int [den];
			int [] remainderPosition=new int [den];
			Arrays.fill(remainderPosition, -1);
			int cyclePosition=-1;
			int position=0;
			
			do {
				quotient[position]=tempN/den;
				
				tempN=tempN%den;
				position++;
				if (remainderPosition[tempN]==-1) remainderPosition[tempN]=position-1;
				else {
					cyclePosition=remainderPosition[tempN]+1;
					break;
				}
				
				tempN*=10;
			} while (true);
			
			StringBuilder sb=new StringBuilder();
			sb.append(num);
			sb.append('/');
			sb.append(den);
			sb.append(" = ");
			for (int i=0;i<Math.min(position,51);i++) {
				if (i==1) sb.append('.');
				if (cyclePosition==i) sb.append('(');
				sb.append(quotient[i]);
			}
			
			if (position>50) sb.append("...");
			sb.append(")\n   ");
			sb.append(position-cyclePosition);
			sb.append(" = number of digits in repeating cycle\n");
			System.out.println(sb.toString());
		}
	}
	
}