import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

class Main {
	
	private static class Component implements Comparable<Component> {
		int price, quality;
		
		public int compareTo(Component c) {
			return this.price-c.price;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			long B=Integer.parseInt(st.nextToken());

			HashMap<String, ArrayList<Component>> components = new HashMap<>();
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				String compType=st.nextToken();
				Component c=new Component();
				st.nextToken(); // Ignore model.
				c.price=Integer.parseInt(st.nextToken());
				c.quality=Integer.parseInt(st.nextToken());
				if (!components.containsKey(compType)) components.put(compType, new ArrayList<>());
				components.get(compType).add(c);
			}
			
			for (ArrayList<Component> list: components.values()) Collections.sort(list);
			
			HashMap<String, Component> selected=new HashMap<>();
			long currPrice=0;
			for (String key: components.keySet()) {
				Component cheapest=null;
				for (Component comp: components.get(key)) {
					if (cheapest==null || comp.price<=cheapest.price) {
						cheapest=comp;
					}
				}
				selected.put(key, cheapest); // Put cheapest
				currPrice+=cheapest.price;
			}

			while (true) {
				int currQuality=Integer.MAX_VALUE;
				for (Component c : selected.values()) {
					currQuality=Math.min(currQuality, c.quality);
				}

				boolean flag=false;
				for (Entry<String, Component> entry: selected.entrySet()) if (entry.getValue().quality==currQuality) {
					String compType=entry.getKey();
					Component lowestQComp=entry.getValue();
					long remaining=B-(currPrice-lowestQComp.price);

					for (Component candidate: components.get(compType)) if (candidate.quality>currQuality && remaining>=candidate.price) { // Put next higher quality
						selected.put(compType, candidate);
						currPrice=currPrice-lowestQComp.price+candidate.price;
						flag=true;
						break;
					}
					
					if (flag) break;
				}
				
				if (!flag) break;
			}
			
			int quality=Integer.MAX_VALUE;
			for (String key: selected.keySet()) quality=Math.min(quality, selected.get(key).quality);
			System.out.println(quality);
		}
	}

}
