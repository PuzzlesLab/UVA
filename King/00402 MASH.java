import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int X=Integer.parseInt(st.nextToken());
			int [] cards=new int [20];
			for (int i=0;i<cards.length;i++) cards[i]=Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> peoples=new ArrayList<>();
			for (int n=1;n<=N;n++) peoples.add(n);
			
			int currCard=0;
			while (peoples.size()>X && currCard<cards.length) {
				int currPos=0;
				while (peoples.size()>X) {
					currPos=currPos+(cards[currCard]-1);
					if (currPos<peoples.size()) peoples.remove(currPos);
					else break;
				}
				currCard++;
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Selection #");
			sb.append(testCase++);
			sb.append('\n');
			for (int people: peoples) {
				sb.append(people);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			sb.append('\n');
			System.out.println(sb.toString());
		}
	}

}
