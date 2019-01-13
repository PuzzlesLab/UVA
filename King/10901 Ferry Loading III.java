import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	private static class Car {
		int id, waitStart, reachTime, location;
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int maxLoad=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			int cars=Integer.parseInt(st.nextToken());
			
			ArrayList<Car> list=new ArrayList<>();
			for (int car=0;car<cars;car++) {
				st=new StringTokenizer(br.readLine());
				Car c=new Car();
				c.id=car;
				c.waitStart=Integer.parseInt(st.nextToken());
				c.location=st.nextToken().equals("left") ? 0 : 1;
				list.add(c);
			}
			list.sort((a,b) -> {
				int [] delta=new int [] {a.waitStart-b.waitStart, a.location-b.location, a.id-b.id};
				for (int i=0;i<delta.length;i++) if (delta[i]!=0) return delta[i];
				return 0;
			});
			
			ArrayList<Car> temp=new ArrayList<>();
			temp.addAll(list);
			boolean ferryAtLeft=true;
			int currTime=0;
			while (temp.size()>0) {
				int loadCount=0;
				int arrivalTime=currTime+T;
				if (ferryAtLeft) {
					//Load possible number of cars that arrived earlier than now.
					for (int i=0;i<temp.size() && loadCount<maxLoad;i++) if (temp.get(i).location==0 && temp.get(i).waitStart<=currTime) {
						temp.remove(i).reachTime=arrivalTime;
						loadCount++;
						i--;
					}

					if (loadCount>0) {
						//Now go to right.
						ferryAtLeft=false;
						currTime=arrivalTime;
					} else {
						//Not loaded, then wait for the first next car. We are at left now, priority goes to left car.
						int nextCarTime=temp.get(0).waitStart;
						boolean hasLeftNextCar=false;
						for (int i=0;i<temp.size() && temp.get(i).waitStart==nextCarTime;i++) hasLeftNextCar=temp.get(i).location==0;
						//If next car is at left, wait for it.
						if (hasLeftNextCar) currTime=nextCarTime;
						else {
						//If next car is at right, move to right side once it arrives.
							ferryAtLeft=false;
							currTime=Math.max(arrivalTime, nextCarTime+T);
						}
					}
				} else if (!ferryAtLeft) {
					//Same with left side, invert everything!
					for (int i=0;i<temp.size() && loadCount<maxLoad;i++)  if (temp.get(i).location==1 && temp.get(i).waitStart<=currTime) {
						temp.remove(i).reachTime=arrivalTime;
						loadCount++;
						i--;
					}

					if (loadCount>0) {
						ferryAtLeft=true;
						currTime=arrivalTime;
					} else  {
						int nextCarTime=temp.get(0).waitStart;
						boolean hasLeftNextCar=false;
						for (int i=0;i<temp.size() && temp.get(i).waitStart==nextCarTime;i++) hasLeftNextCar=temp.get(i).location==1;
						if (hasLeftNextCar) currTime=nextCarTime;
						else {
							ferryAtLeft=true;
							currTime=Math.max(arrivalTime, nextCarTime+T);
						}
					}
				}
				
			}
			
			list.sort((a,b) -> {return a.id-b.id;});
			if (testCase>0) System.out.println();
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<list.size();i++) {
				sb.append(list.get(i).reachTime);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}