import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static class Item implements Comparable<Item> {
		String name;
		double cost, sellPrice, profit;
		int count;
		
		public Item(String n, double c, double s) {
			this.name=n;
			this.cost=c;
			this.sellPrice=s;
			this.profit=this.sellPrice-this.cost;
		}
		
		public int compareTo(Item i) {
			return this.name.compareTo(i.name);
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		HashMap<String,Item> itemMap=new HashMap<>();
		double currNett=0;
		while (!(s=br.readLine()).equals("*")) {
			StringTokenizer st=new StringTokenizer(s);
			String action=st.nextToken();
			
			if (action.equals("new")) {
				String n=st.nextToken();
				itemMap.put(n, new Item(n,Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())));
			} else if (action.equals("delete")) {
				String n=st.nextToken();
				Item item=itemMap.get(n);
				currNett-=item.count*item.cost;
				itemMap.remove(n);
			} else if (action.equals("buy")) {
				String n=st.nextToken();
				int amount=Integer.parseInt(st.nextToken());
				itemMap.get(n).count+=amount;
			} else if (action.equals("sell")) {
				String n=st.nextToken();
				int amount=Integer.parseInt(st.nextToken());
				itemMap.get(n).count-=amount;
				currNett+=itemMap.get(n).profit*amount;
			} else if (action.equals("report")) {
				StringBuilder sb=new StringBuilder();
				sb.append("                  INVENTORY REPORT\n");
				sb.append("Item Name     Buy At      Sell At      On Hand        Value\n");
				sb.append("---------     ------      -------      -------        -----\n");
				ArrayList<Item> list=new ArrayList<>(itemMap.values());
				Collections.sort(list);
				double stockValue=0.0;
				for (int i=0;i<list.size();i++) {
					Item item=list.get(i);
					double cStockV=item.count*item.cost;
					
					String buyAt=String.format("%.2f",item.cost);
					sb.append(item.name);
					for (int i2=0;i2+item.name.length()+buyAt.length()<20;i2++) sb.append(' ');
					sb.append(buyAt);
					
					sb.append(String.format("%13.2f%13d%13.2f\n",item.sellPrice,item.count,cStockV));
					stockValue+=cStockV;
				}
				sb.append("------------------------\n");
				sb.append(String.format("Total value of inventory %34.2f\nProfit since last report %34.2f\n\n",stockValue,currNett));
				System.out.print(sb);
				currNett=0.0;
			}
		}
	}

}
