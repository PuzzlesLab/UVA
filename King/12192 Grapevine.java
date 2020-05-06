import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static int equalOrGreaterIndex(int [] data, int target) {
		if (data[0]>=target) return 0;
		else if (data[data.length-1]<target) return -1;
		
		int min=0, max=data.length-1;
		while (min<max) {
			int mid=(min+max)/2;
			if (data[mid]==target) {
				while (mid>0 && data[mid-1]==target) mid--;
				return mid;
			} else if (data[mid]>target) max=mid-1;
			else min=mid+1;
		}
		if (target>data[min] && min+1<data.length && data[min+1]>=target) min++;
		return min;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int row=Integer.parseInt(st.nextToken());
			int col=Integer.parseInt(st.nextToken());
			int [][] map=new int [row][col];
			
			for (int r=0;r<row;r++) {
				st=new StringTokenizer(br.readLine());
				for (int c=0;c<col;c++) map[r][c]=Integer.parseInt(st.nextToken());
			}
			
			int Q=Integer.parseInt(br.readLine());
			for (int q=0;q<Q;q++) {
				st=new StringTokenizer(br.readLine());
				int qMin=Integer.parseInt(st.nextToken());
				int qMax=Integer.parseInt(st.nextToken());
				int ans=0;
				
				int [] start=new int [row]; Arrays.fill(start, -1);
				for (int r=0;r<row;r++) start[r]=equalOrGreaterIndex(map[r],qMin);
				
				for (int r=0;r<row;r++) if (start[r]>=0) {
					int size=ans;
					while (r+size<row && start[r]+size<col && map[r+size][start[r]+size]<=qMax) size++;
					ans=Math.max(size,ans);
				}
				System.out.println(ans);
			}
			
			System.out.println('-');
		}
	}

}