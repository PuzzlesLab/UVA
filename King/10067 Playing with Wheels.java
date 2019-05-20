import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

class Main {

	private static class Data {
		int [] values;
		int move;
		public Data (int a, int b, int c, int d, int move) {
			this.values=new int [] {a,b,c,d};
			this.move=move; 
		}
		public Data(Data d) {
			this.values=Arrays.copyOf(d.values, d.values.length);
			this.move=d.move;
		}
		public int toNum() {return values[0]*1000+values[1]*100+values[2]*10+values[3];}

	}
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCaseCount=sc.nextInt();
		int [] deltas=new int [] {-1,1};
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			Data start=new Data(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),0);
			Data end=new Data(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),0);
			
			int [] flag=new int [10000];
			//0 = unvisited, 1=visited, 2=forbidden.
			int F=sc.nextInt();
			for (int f=0;f<F;f++) flag[new Data(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),0).toNum()]=2;
			
			LinkedList<Data> queue=new LinkedList<>();
			queue.add(start);
			flag[start.toNum()]=1;
			int step=-1;
			while (!queue.isEmpty()) {
				Data curr=queue.removeFirst();
				if (curr.toNum()==end.toNum()) {
					step=curr.move;
					break;
				} else {
					for (int i=0;i<4;i++) for (int delta : deltas) {
						Data next=new Data(curr);
						next.values[i]=Math.floorMod(next.values[i]+delta,10);
						next.move++;
						if (flag[next.toNum()]==0) {
							queue.addLast(next);
							flag[next.toNum()]=1;
						}
					}
				}
			}
			
			System.out.println(step);
		}
	}

}
