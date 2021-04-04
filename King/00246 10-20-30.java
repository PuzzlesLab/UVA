import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

class Main {
	
	public static void main (String [] args) throws Exception {
		HashSet<Integer> condValue=new HashSet<>();
		condValue.add(10);
		condValue.add(20);
		condValue.add(30);
		
		Scanner sc=new Scanner(System.in);
		while (true) {
			int first=sc.nextInt();
			if (first==0) break;

			ArrayList<LinkedList<Integer>> decks=new ArrayList<>();
			for (int i=0;i<7;i++) decks.add(new LinkedList<>());
			
			LinkedList<Integer> cards=new LinkedList<>();
			cards.add(first);
			for (int i=1;i<52;i++) cards.add(sc.nextInt());
			
			for (int i=0;i<7;i++) decks.get(i).addLast(cards.removeFirst());
			int deckIdx=0;
			String condition="";
			int dealtCount=7;
			HashSet<String> states=new HashSet<>();
			while (cards.size()>0 && condition.length()==0) {
				LinkedList<Integer> currDeck=decks.get(deckIdx);
				currDeck.add(cards.removeFirst());
				dealtCount++;
				//cardDealOnCurrentStack++;
				
				while (currDeck.size()>=3) {
					int deckSize=currDeck.size();
					boolean cardsRemoved=false;
					if (condValue.contains(currDeck.getFirst()+currDeck.get(1)+currDeck.getLast())) {
						cards.addLast(currDeck.removeFirst());
						cards.addLast(currDeck.removeFirst());
						cards.addLast(currDeck.removeLast());
						cardsRemoved=true;
					} else if (condValue.contains(currDeck.getFirst()+currDeck.get(deckSize-2)+currDeck.getLast())) {
						cards.addLast(currDeck.removeFirst());
						cards.addLast(currDeck.remove(currDeck.size()-2));
						cards.addLast(currDeck.removeLast());
						cardsRemoved=true;
					} else if (condValue.contains(currDeck.get(deckSize-3)+currDeck.get(deckSize-2)+currDeck.getLast())) {
						cards.addLast(currDeck.remove(currDeck.size()-3));
						cards.addLast(currDeck.remove(currDeck.size()-2));
						cards.addLast(currDeck.removeLast());
						cardsRemoved=true;
					}
					if (!cardsRemoved) break;
				}
				
				if (currDeck.isEmpty()) {
					decks.remove(deckIdx);
					states.clear();
					if (decks.size()==0) {
						condition="Win ";
						break;
					} else if (deckIdx==decks.size()) deckIdx=0;
				} else deckIdx=(deckIdx+1)%decks.size();
				
				
				StringBuilder sb=new StringBuilder(cards.toString());
				for (LinkedList<Integer> deck : decks) sb.append(deck.toString());
				String stateText=sb.toString();
				if (states.contains(stateText)) {
					condition="Draw";
					break;
				} else states.add(stateText);
			}
			if (condition.length()==0) condition="Loss";
			
			System.out.printf("%s: %d\n", condition, dealtCount);
		}
	}
}