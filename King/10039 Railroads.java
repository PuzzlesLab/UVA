import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static class Edge implements Comparable<Edge> {
		int fromTime;
		int from;
		int toTime;
		int to;
		
		public int compareTo(Edge e) {
			if (this.fromTime!=e.fromTime) return this.fromTime-e.fromTime;
			if (this.toTime!=e.toTime) return this.toTime-e.toTime;
			return this.from-e.from;
		}
	}

	private static ArrayList<Edge> [] Edges;
	private static int End;
	private static int [][] Dp;

	private static int compute(int city, int time) {
		if (city==End) return time;
		
		if (Dp[city][time]==2401) {
			int earliest=2400;
			for (int i=0;i<Edges[city].size();i++) {
				Edge edge=Edges[city].get(i);
				if (edge.fromTime>=time) earliest=Math.min(earliest,compute(edge.to,edge.toTime));
			}
			Dp[city][time]=earliest;
		}
		return Dp[city][time];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			HashMap<String,Integer> cityIdx=new HashMap<>();
			int C=Integer.parseInt(br.readLine());
			String [] cities=new String[C];
			Edges=new ArrayList [C];
			for (int c=0;c<C;c++) {
				cities[c]=br.readLine();
				cityIdx.put(cities[c],c);
				Edges[c]=new ArrayList<>();
			}

			int T=Integer.parseInt(br.readLine());
			for (int t=0;t<T;t++) {
				int N=Integer.parseInt(br.readLine());
				int tempCIdx=-1;
				int tempT=0;
				for (int n=0;n<N;n++) {
					StringTokenizer st=new StringTokenizer(br.readLine());
					if (tempCIdx==-1) {
						tempT=Integer.parseInt(st.nextToken());
						tempCIdx=cityIdx.get(st.nextToken());
					} else {
						Edge e=new Edge();
						e.fromTime=tempT;
						e.from=tempCIdx;
						e.toTime=Integer.parseInt(st.nextToken());
						e.to=cityIdx.get(st.nextToken());
						tempT=e.toTime;
						tempCIdx=e.to;
						Edges[e.from].add(e);
					}
				}
			}
			for (int c=0;c<C;c++) Collections.sort(Edges[c]);
			
			int startAfter=Integer.parseInt(br.readLine());
			int start=cityIdx.get(br.readLine());
			End=cityIdx.get(br.readLine());
			
			int earliestReach=2401;
			int latestStart=-1;
			Dp=new int [C+1][2400];
			for (int c=0;c<C;c++) Arrays.fill(Dp[c],2401);
			for (int i=0;i<Edges[start].size();i++) {
				Edge edge=Edges[start].get(i);
				if (edge.fromTime>=startAfter) {
					int result=compute(edge.to,edge.toTime);
					if (result<=earliestReach) {
						earliestReach=result;
						latestStart=Math.max(latestStart,edge.fromTime);
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Scenario ");
			sb.append(tc);
			sb.append('\n');
			if (earliestReach>=2400) sb.append("No connection\n");
			else {
				sb.append("Departure ");
				sb.append(String.format("%04d",latestStart));
				sb.append(' ');
				sb.append(cities[start]);
				sb.append('\n');
				sb.append("Arrival   ");
				sb.append(String.format("%04d",earliestReach));
				sb.append(' ');
				sb.append(cities[End]);
				sb.append('\n');
			}
			System.out.println(sb.toString());
		}
	}

}
