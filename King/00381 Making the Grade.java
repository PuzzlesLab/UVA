import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int classes=Integer.parseInt(br.readLine());
		System.out.println("MAKING THE GRADE OUTPUT");
		for (int cls=0;cls<classes;cls++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int S=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			
			double [] avg=new double[S];
			int [] bonus=new int [S];
			int [] noa=new int [S];
			for (int s=0;s<S;s++) {
				st=new StringTokenizer(br.readLine());
				LinkedList<Double> score=new LinkedList<>();
				for (int t=0;t<T;t++) score.add(Double.parseDouble(st.nextToken()));
				if (T>2) {
					Collections.sort(score);
					score.removeFirst();
				}
				bonus[s]=Integer.parseInt(st.nextToken());
				noa[s]=Integer.parseInt(st.nextToken());
				
				for (double d : score) avg[s]+=d;
				avg[s]/=score.size();
			}
			
			double classAvg=0.0;
			for (double d : avg) classAvg+=d;
			classAvg/=S;
			
			double sd=0.0;
			for (double d : avg) sd+=Math.pow(d-classAvg, 2.0);
			sd=Math.sqrt(sd/S);
			
			for (int s=0;s<S;s++) avg[s]+=3*(bonus[s]/2);
			
			char [] possibleGrades= {'A','B','C','D','F'};
			int [] gradeIndex=new int [S];
			for (int s=0;s<S;s++) {
				int result=(int)Math.round(avg[s]);
				if (result>=classAvg+sd) gradeIndex[s]=0;
				else if (result>=classAvg) gradeIndex[s]=1;
				else if (result>=classAvg-sd) gradeIndex[s]=2;
				else if (result<classAvg-sd) gradeIndex[s]=3;
				
				if (noa[s]==0) gradeIndex[s]=Math.max(gradeIndex[s]-1, 0);
				else gradeIndex[s]=Math.min(gradeIndex[s]+noa[s]/4, possibleGrades.length-1);
			}
			
			int [] gradePoints = {4,3,2,1,0};
			double agp=0.0;
			for (int i : gradeIndex) agp+=gradePoints[i];
			agp/=S;
			
			System.out.printf("%.1f\n", agp);
		}
		System.out.println("END OF OUTPUT");
	}

}
