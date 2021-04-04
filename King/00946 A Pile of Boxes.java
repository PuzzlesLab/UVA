import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Main {
	
	private static class Box {
		int width;
		int occupiedHeight;
		LinkedList<Box> children;
		
		public Box(int w) {
			this.width=w;
			this.occupiedHeight=0;
			this.children=new LinkedList<>();
		}
	}
	
	private static Box findBox(Box root, Box b) {
		if (root.width<=b.width) return null;

		Box lastFeasibleBox=root;
		for (Box tryBox : root.children) {
			Box currFeasibleBox = findBox(tryBox, b);
			if (currFeasibleBox!=null && currFeasibleBox.width-currFeasibleBox.occupiedHeight>=b.width) lastFeasibleBox=currFeasibleBox;
			else break;
		}
		return lastFeasibleBox;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int NC=Integer.parseInt(s);
			Box root=new Box(Integer.MAX_VALUE);
			for (int nc=0;nc<NC;nc++) {
				int width=Integer.parseInt(br.readLine());
				Box currBox=new Box(width);
				Box putInBox=findBox(root, currBox);
				putInBox.occupiedHeight+=currBox.width;
				putInBox.children.addFirst(currBox);
			}
			System.out.println(root.occupiedHeight);
			br.readLine();
		}
	}
}