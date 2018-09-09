import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main {
	
	private static class Data implements Comparable<Data> {
		String timestamp;
		String carplate;
		String action;
		int location;
		
		public Data(String s) {
			StringTokenizer st=new StringTokenizer(s);
			this.carplate=st.nextToken();
			this.timestamp=st.nextToken();
			this.action=st.nextToken();
			this.location=Integer.parseInt(st.nextToken());
		}
		
		public int getHour() {
			return Integer.parseInt(this.timestamp.split(":")[2]);
		}
		
		@Override
		public int compareTo(Data arg0) {
			return this.timestamp.compareTo(arg0.timestamp);
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine(); //empty!
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int [] fare=new int [24];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<fare.length;i++) fare[i]=Integer.parseInt(st.nextToken());
			
			TreeMap<String, Integer> bill=new TreeMap<>();
			HashMap<String, Data> enter=new HashMap<>();
			
			PriorityQueue<Data> queue=new PriorityQueue<>();
			
			String s;
			while ((s=br.readLine())!=null) {
				if (s.length()==0) break;
				queue.offer(new Data(s));
			}
				
				
			while (!queue.isEmpty()) {
				Data d=queue.poll();
				
				if (d.action.equals("enter")) enter.put(d.carplate, d);
				else if (enter.containsKey(d.carplate)) {
					Data e=enter.remove(d.carplate);
					bill.put(d.carplate, bill.getOrDefault(d.carplate,0)+(Math.abs(d.location-e.location)*fare[e.getHour()]+100));
				}
			}
			
			StringBuilder sb=new StringBuilder();
			for (String key : bill.keySet()) {
				sb.append(key);
				sb.append(" $");
				sb.append(String.format("%.2f",bill.get(key)/100.0+2));
				sb.append("\n");
			}
			System.out.print(sb.toString());
			if (testCase<testCaseCount-1) System.out.println();
		}
	}

}