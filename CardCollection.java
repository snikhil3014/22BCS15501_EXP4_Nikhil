import java.util.*;

class Card {
    String symbol;
    String value;

    Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String toString() {
        return value + " of " + symbol;
    }
}

public class CardCollection {
    static Collection<Card> cards = new ArrayList<>();

    static void addCard(String symbol, String value) {
        cards.add(new Card(symbol, value));
    }

    static List<Card> getCardsBySymbol(String symbol) {
        List<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.symbol.equalsIgnoreCase(symbol)) {
                result.add(card);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Card\n2. Find Cards by Symbol\n3. Exit");
            int choice = scanner.nextInt();
            if (choice == 3) break;
            switch (choice) {
                case 1:
                    System.out.println("Enter Symbol and Value:");
                    addCard(scanner.next(), scanner.next());
                    break;
                case 2:
                    System.out.println("Enter Symbol to Search:");
                    List<Card> foundCards = getCardsBySymbol(scanner.next());
                    System.out.println(foundCards.isEmpty() ? "No cards found" : foundCards);
                    break;
            }
        }
        scanner.close();
    }
}