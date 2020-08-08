import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Main {
	
	/*
	 * Lame question, doesn't seem to follow the output from Java Random object.
	 * This is the first 1K random()/141%2 numbers generated from cpp code.
	 */
	public static char [] rdn="0011000101010111110011110111011010110111000010101011001010110100011100001010000001100001011101010100001100100110110100000111000010010000010011001111001011111010011000100111001001111110001100110110000101111010100011011110011001010011100001010011100011100001111100110111110110010001011011110000011110100100101101000001110011010100010010000110000000011110110010000011011100000000000001101111100011010010010011101100110001000001011110111010000001001100110001000101101010101101011001011001101110001001111010101010011110010001100100011010101110010000111111001011110011011000011010001001110010001001010010011111100001110011110100100111100101011011000001010000100110010100111110010000110111001110110011010101000010101100001110001000110101000001111001100001001001110000000110001101000000110001111101111101100011010001110110111100000110110110011110001010011010000011010001000011010101000100111101000010111100101101000011011011100101011001101111011001100000010010001110010001000110100101110011111001011010111111".toCharArray();
	public static int rdnCount=0;
	
	private static String solve(String jane, String john) {
		LinkedList<Character> janeFaceDown=new LinkedList<>();
		LinkedList<Character> johnFaceDown=new LinkedList<>();
		for (char c : jane.toCharArray()) janeFaceDown.addLast(c);
		for (char c : john.toCharArray()) johnFaceDown.addLast(c);
		
		int winner=-1;
		StringBuilder sb=new StringBuilder();
		LinkedList<Character> janeFaceUp=new LinkedList<>();
		LinkedList<Character> johnFaceUp=new LinkedList<>();
		for (int lp=0;lp<1000;lp++) {
			if (janeFaceUp.size()+janeFaceDown.size()==0) winner=1;
			else if (johnFaceUp.size()+johnFaceDown.size()==0) winner=0;
			if (winner!=-1) break;
			
			janeFaceUp.addFirst(janeFaceDown.removeFirst());
			johnFaceUp.addFirst(johnFaceDown.removeFirst());
			
			if (janeFaceUp.getFirst() == johnFaceUp.getFirst()) {
				int r=rdn[rdnCount++]-'0';
				if (r==0) {
					while (!johnFaceUp.isEmpty()) janeFaceUp.addFirst(johnFaceUp.removeLast());
					sb.append("Snap! for Jane: ");
					for (char c : janeFaceUp) sb.append(c);
				} else {
					while (!janeFaceUp.isEmpty()) johnFaceUp.addFirst(janeFaceUp.removeLast());
					sb.append("Snap! for John: ");
					for (char c : johnFaceUp) sb.append(c);
				}
				sb.append('\n');
			}
			if (janeFaceDown.isEmpty()) {
				while (!janeFaceUp.isEmpty()) janeFaceDown.addFirst(janeFaceUp.removeFirst());
			}
			
			if (johnFaceDown.isEmpty()) {
				while (!johnFaceUp.isEmpty()) johnFaceDown.addFirst(johnFaceUp.removeFirst());
			}
		}
		if (winner==0) sb.append("Jane wins.");
		else if (winner==1) sb.append("John wins.");
		else sb.append("Keeps going and going ...");

		return sb.toString();
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			String jane=br.readLine();
			String john=br.readLine();
			String ans=solve(jane, john);
			if (testCase>0) System.out.println();
			System.out.println(ans);
		}
	}

}