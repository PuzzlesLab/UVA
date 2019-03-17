import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	public static class Node {
		int x, y, line;
		String name;
		ArrayList<Edge> edgeList;
		
		public Node (int x, int y, int line, String name) {
			this.x=x;
			this.y=y;
			this.edgeList=new ArrayList<>();
			this.line=line;
			this.name=name;
		}
		
		public String toString() {
			return this.name;
		}
	}
	
	public static class Edge implements Comparable<Edge>{
		Node next;
		double time;
		
		public Edge(Node n, double time) {
			this.next=n;
			this.time=time;
		}
		
		public int compareTo(Edge e) {
			return (this.time>=e.time) ? 1 : -1;
		}
	}
	
	public static void addEdge(Node start, Node end, double factor) {
		double dx=start.x-end.x, dy=start.y-end.y;
		double dist=Math.sqrt(dx*dx+dy*dy)*factor;
		start.edgeList.add(new Edge(end, dist));
		end.edgeList.add(new Edge(start, dist));
		//System.out.println(start+" <> "+end+" - "+dist/1000.0);
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			Node home=new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),0, "home");
			Node school=new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),1, "school");
			ArrayList<Node> nodes=new ArrayList<>();
			nodes.add(home);
			nodes.add(school);

			String s;
			int lineCount=2;
			while ((s=br.readLine())!=null) {
				if (s.length()==0) break;
				ArrayList<Node> currLineNodes=new ArrayList<>();
				st=new StringTokenizer(s);
				while (st.hasMoreTokens()) {
					int x=Integer.parseInt(st.nextToken());
					int y=Integer.parseInt(st.nextToken());
					if (x==-1 && y==-1) break;
					else {
						Node n=new Node(x, y, lineCount, "Line "+lineCount+" Node "+currLineNodes.size());
						nodes.add(n);
						currLineNodes.add(n);
					}
				}
				for (int i=1;i<currLineNodes.size();i++) addEdge(currLineNodes.get(i-1), currLineNodes.get(i), 1.5);
				lineCount++;
			}

			for (int i=0;i<nodes.size();i++) for (int i2=i+1;i2<nodes.size();i2++)  {
				Node start=nodes.get(i);
				Node end=nodes.get(i2);
				addEdge(start, end, 6.0);
			}
			
			HashMap<Node, Double> nearest=new HashMap<>();
			nearest.put(home, 0.0);
			PriorityQueue<Edge> queue=new PriorityQueue<>();
			queue.offer(new Edge(home, 0.0));
			while (!queue.isEmpty()) {
				Edge e=queue.poll();
				if (e.next == school) break;

				for (Edge nextEdge : e.next.edgeList) {
					double time=e.time+nextEdge.time;
					if (time<nearest.getOrDefault(nextEdge.next, Double.MAX_VALUE)) {
						nearest.put(nextEdge.next,time);
						queue.offer(new Edge(nextEdge.next, time));
					}

				}
			}
			if (testCase>0) System.out.println();
			System.out.println(Math.round(nearest.get(school)/1000.0));
		}
	}

}
