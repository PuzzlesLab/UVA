import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main {

	private static class Street {
		int n1, n2, dist;
		
		public Street(char c1, char c2, int dist) {
			this.n1=c1-'a';
			this.n2=c2-'a';
			this.dist=dist;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		ArrayList<Street> streets=new ArrayList<>();
		while ((s=br.readLine())!=null) {
			if (s.equals("deadend")) {
				int [] degree=new int [26];
				int [][] minDist=new int [26][26];
				for (int i=0;i<minDist.length;i++) Arrays.fill(minDist[i],100000);

				for (int i=0;i<streets.size();i++) {
					Street street=streets.get(i);
					degree[street.n1]++;
					degree[street.n2]++;
					
					minDist[street.n1][street.n2]=Math.min(minDist[street.n1][street.n2],street.dist);
					minDist[street.n2][street.n1]=Math.min(minDist[street.n2][street.n1],street.dist);
				}

				int sum=0;
				for (int i=0;i<streets.size();i++) sum+=streets.get(i).dist;
				
				boolean allEven=true;
				for (int i=0;i<degree.length;i++) allEven&=degree[i]%2==0;

				if (!allEven) {
					int first=-1;
					int second=-1;
					for (int i=0;i<degree.length;i++) if (degree[i]%2==1) {
						if (first==-1) first=i;
						second=i;
					}

					for (int k=0;k<minDist.length;k++) for (int i=0;i<minDist.length;i++) for (int j=0;j<minDist.length;j++) {
						minDist[i][j]=Math.min(minDist[i][j],minDist[i][k]+minDist[k][j]);
					}
					sum+=minDist[first][second];
				}

				System.out.println(sum);
				
				streets.clear();
				continue;
			}
			streets.add(new Street(s.charAt(0),s.charAt(s.length()-1),s.length()));
		}
	}

}
