import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int K=Integer.parseInt(br.readLine());
			String [] courses=new String[K];
			HashMap<String,Integer> courseIdxMap=new HashMap<>();
			for (int k=0;k<K;k++) courses[k]=br.readLine();
			Arrays.sort(courses);
			for (int k=0;k<K;k++) courseIdxMap.put(courses[k],k);

			int [][] refCount=new int [K][K];
			int J=Integer.parseInt(br.readLine());
			for (int j=0;j<J;j++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int from=courseIdxMap.get(st.nextToken());
				int N=Integer.parseInt(st.nextToken());
				for (int n=0;n<N;n++) {
					int to=courseIdxMap.get(st.nextToken());
					if (from!=to) refCount[from][to]=1;
				}
			}
			for (int k=0;k<K;k++) for (int i=0;i<K;i++) for (int j=0;j<K;j++) {
				if (refCount[i][k]>0 && refCount[k][j]>0) refCount[i][j]=2;
			}

			StringBuilder sb=new StringBuilder();
			for (int i=0;i<K;i++) {
				ArrayList<String> deps=new ArrayList<>();
				for (int i2=0;i2<K;i2++) if (refCount[i][i2]==1) deps.add(courses[i2]);
				if (!deps.isEmpty()) {
					sb.append(courses[i]);
					sb.append(' ');
					sb.append(deps.size());
					for (int d=0;d<deps.size();d++) {
						sb.append(' ');
						sb.append(deps.get(d));
					}
					sb.append('\n');
				}
			}
			System.out.print(sb.toString());
		}
	}

}
