import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int tc=1;
		while (true) {
			int NC=sc.nextInt();
			if (NC==0) break;

			boolean [][] sequence=new boolean [201][201];
			HashMap<String,Integer> eventIdxMap=new HashMap<>();
			ArrayList<String> events=new ArrayList<>();
			for (int nc=0;nc<NC;nc++) {
				int NE=sc.nextInt();
				int lastIdx=-1;
				for (int ne=0;ne<NE;ne++) {
					String event=sc.next();
					if (!eventIdxMap.containsKey(event)) {
						eventIdxMap.put(event,events.size());
						events.add(event);
					}
					int currIdx=eventIdxMap.get(event);
					if (lastIdx!=-1) sequence[lastIdx][currIdx]=true;
					lastIdx=currIdx;
				}
			}
			int NM=sc.nextInt();
			for (int nm=0;nm<NM;nm++) sequence[eventIdxMap.get(sc.next())][eventIdxMap.get(sc.next())]=true;

			int E=events.size();
			for (int k=0;k<E;k++) for (int i=0;i<E;i++) for (int j=0;j<E;j++) sequence[i][j]|=(sequence[i][k]&&sequence[k][j]);

			int count=0;
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<E;i++) for (int i2=i+1;i2<E;i2++) if (!sequence[i2][i] && !sequence[i][i2]) {
				if (count<2) {
					sb.append('(');
					sb.append(events.get(i));
					sb.append(',');
					sb.append(events.get(i2));
					sb.append(')');
					sb.append(' ');
				}
				count++;
			}

			StringBuilder ans=new StringBuilder();
			ans.append("Case ");
			ans.append(tc++);
			ans.append(", ");
			ans.append(count==0?"no":count);
			ans.append(" concurrent events");
			ans.append(count==0?".":":\n");
			ans.append(sb.toString());
			System.out.println(ans.toString());
		}
	}

}
