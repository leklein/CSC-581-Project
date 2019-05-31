import java.util.ArrayList;
import java.util.List;

public class Player
{
    private String name;
    private List<String> cards;

    public Player(String name)
    {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public String getName()
    {
        return this.name;
    }

    public List<String> getCards()
    {
        return this.cards;
    }

    public void addCard(String card)
    {
        cards.add(card);
    }
}