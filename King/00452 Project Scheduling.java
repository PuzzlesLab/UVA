import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	private static class Data {
		String task;
		int time;
		public Data(String t, int ti) {
			this.task=t;
			this.time=ti;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine().trim());
		br.readLine();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			HashMap<String, Integer> taskTime=new HashMap<>();
			HashMap<String, ArrayList<String>> adjList=new HashMap<>();
			String s;
			
			ArrayList<String> startTasks=new ArrayList<>();
			while ((s=br.readLine())!=null) {
				if (s.trim().length()==0) break;
				StringTokenizer st=new StringTokenizer(s);
				String taskA=st.nextToken();
				int time=Integer.parseInt(st.nextToken());
				taskTime.put(taskA, time);
				if (st.hasMoreTokens()) {
					String taskBs=st.nextToken();
					for (int i=0;i<taskBs.length();i++) {
						String taskB=taskBs.substring(i, i+1);
						if (!adjList.containsKey(taskB)) adjList.put(taskB, new ArrayList<>());
						adjList.get(taskB).add(taskA);	
					}
				} else startTasks.add(taskA);
			}
			
			HashMap<String, Integer> maxLength=new HashMap<>();
			for (String t : startTasks) maxLength.put(t,  taskTime.get(t));
			
			LinkedList<Data> q=new LinkedList<>();
			for (String t : startTasks) q.add(new Data(t, taskTime.get(t)));
			while (!q.isEmpty()) {
				Data dat=q.removeFirst();
				if (adjList.containsKey(dat.task)) {
					for (String neighbour : adjList.get(dat.task)) {
						int next=dat.time+taskTime.get(neighbour);
						if (next>maxLength.getOrDefault(neighbour, -1)) {
							maxLength.put(neighbour, next);
							q.addLast(new Data(neighbour, next));
						}
					}
				}
			}
			
			int max=0;
			for (int i : maxLength.values()) max=Math.max(i, max);
			
			if (testCase>0) System.out.println();
			System.out.println(max);
		}
	}

}