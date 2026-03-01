import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static class BusGroup {
		int id, line, seqNoOnLine;
		
		public BusGroup(int i, int l, int s) {
			this.id=i;
			this.line=l;
			this.seqNoOnLine=s;
		}
	}

	private static int getParent(int [] parent, int n) {
		if (parent[n]==n) return n;
		return parent[n]=getParent(parent,parent[n]);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			st.nextToken(); // We don't need.
			st.nextToken(); // We don't need.

			ArrayList<Integer> [] lineStops=new ArrayList [N];
			ArrayList<BusGroup> [] lineBusGroups=new ArrayList [N];
			ArrayList<BusGroup> allBusGroups=new ArrayList<>();
			int busGroupId=0;
			int maxLineStopLength=0;
			for (int n=0;n<N;n++) {
				lineStops[n]=new ArrayList<>();
				st=new StringTokenizer(br.readLine());
				HashMap<Integer,Integer> stopToSeqMap=new HashMap<>();
				while (st.hasMoreTokens()) {
					int stop=Integer.parseInt(st.nextToken());
					lineStops[n].add(stop);
					stopToSeqMap.put(stop,lineStops[n].size()-1);
				}
				maxLineStopLength=Math.max(maxLineStopLength,lineStops[n].size());
				st=new StringTokenizer(br.readLine());
				lineBusGroups[n]=new ArrayList<>();
				while (st.hasMoreTokens()) {
					int seqNo=stopToSeqMap.get(Integer.parseInt(st.nextToken()));
					Integer.parseInt(st.nextToken()); // We don't need to count number of buses anyway.
					BusGroup b=new BusGroup(busGroupId++,n,seqNo);
					lineBusGroups[n].add(b);
					allBusGroups.add(b);
				}
			}

			int [] parent=new int [busGroupId];
			for (int i=0;i<parent.length;i++) parent[i]=i;
			for (int epoch=0;epoch<=maxLineStopLength*2;epoch++) {
				// Link current state.
				HashMap<Integer,ArrayList<BusGroup>> stopBusGroupsMap=new HashMap<>();
				for (int i=0;i<allBusGroups.size();i++) {
					BusGroup bg=allBusGroups.get(i);
					int stop=lineStops[bg.line].get(bg.seqNoOnLine);
					if (!stopBusGroupsMap.containsKey(stop)) stopBusGroupsMap.put(stop,new ArrayList<>());
					stopBusGroupsMap.get(stop).add(bg);
				}
				for (ArrayList<BusGroup> stopBusGroups: stopBusGroupsMap.values()) {
					int rootP=1000000;
					for (int i=0;i<stopBusGroups.size();i++) rootP=Math.min(rootP,getParent(parent,stopBusGroups.get(i).id));
					for (int i=0;i<stopBusGroups.size();i++) parent[getParent(parent,stopBusGroups.get(i).id)]=rootP;
				}
				// Move buses.
				for (int i=0;i<allBusGroups.size();i++) {
					BusGroup bg=allBusGroups.get(i);
					bg.seqNoOnLine=(bg.seqNoOnLine+1)%lineStops[bg.line].size();
				}
			}

			boolean ans=true;
			for (int i=0;i<busGroupId;i++) ans&=getParent(parent,i)==0;
			System.out.println(ans?"Yes":"No");
		}
	}

}