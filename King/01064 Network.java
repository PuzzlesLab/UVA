import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Packet implements Comparable<Packet> {
		int msgId, startByte, endByte;
		
		public int size() {
			return endByte-startByte+1;
		}
		
		public int compareTo(Packet p) {
			return this.startByte-p.startByte;
		}
	}

	private static class Buffer {
		int [] nextByte;
		PriorityQueue<Packet> [] packets;
		int currSize, maxSize;
		
		public Buffer(int count) {
			this.nextByte=new int [count];
			this.packets=new PriorityQueue [count];
			for (int i=0;i<count;i++) this.packets[i]=new PriorityQueue<>();
		}

	}

	public static int simulate(Packet [] packets, int [] msgOrder, int [] msgSize) {
		Buffer buffer=new Buffer(msgSize.length);
		int outputMsgIdx=0;
		for (Packet incomingP: packets) {
			int msg=incomingP.msgId;
			buffer.currSize+=incomingP.size();
			buffer.packets[msg].offer(incomingP);
			while (outputMsgIdx<msgOrder.length && msg==msgOrder[outputMsgIdx] && !buffer.packets[msg].isEmpty() && buffer.nextByte[msg]+1==buffer.packets[msg].peek().startByte) {
				Packet removedP = buffer.packets[msg].poll();
				buffer.currSize-=removedP.size();
				buffer.nextByte[msg]=removedP.endByte;
				if (buffer.nextByte[msg]==msgSize[msg]) {
					outputMsgIdx++;
				}
			}
			buffer.maxSize=Math.max(buffer.maxSize, buffer.currSize);
		}
		return buffer.maxSize;
	}

	public static int search(Packet [] packets, int [] scenario, int size, boolean [] exists, int [] msgSize) {
		if (size==scenario.length) return simulate(packets, scenario, msgSize);
		else {
			int ans=Integer.MAX_VALUE;
			for (int i=0;i<scenario.length;i++) if (!exists[i]) {
				exists[i]=true;
				scenario[size]=i;
				ans=Math.min(ans, search(packets,scenario,size+1,exists,msgSize));
				exists[i]=false;
			}
			return ans;
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] msgSize=new int [N];
			for (int n=0;n<N;n++) msgSize[n]=Integer.parseInt(st.nextToken());
			
			Packet [] packets=new Packet[M];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				packets[m]=new Packet();
				packets[m].msgId=Integer.parseInt(st.nextToken())-1;
				packets[m].startByte=Integer.parseInt(st.nextToken());
				packets[m].endByte=Integer.parseInt(st.nextToken());
			}

			int ans=search(packets, new int [N], 0, new boolean [N], msgSize); //Permute msg sequence
			System.out.printf("Case %d: %d\n\n", testCase++, ans);
		}
	}

}
