import main.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

//TODO REALLY IMPORTANT Check these tests

public class PlayerTest {

    ArrayList<Card> cards_list = new ArrayList<Card>();
    Deck deck;
    Deck deck2;
    Player play0;
    Player play1;
    Player play2;
    Card card;
    Game game;

    @Before
    public void setUp() throws Exception {

        //creating sample card List
        for (int i = 0; i < 30; i++) {
            if (i % 2 == 0) {
                cards_list.add(new WarriorCard("War" + Integer.toString(i + 1), "red", 2, 2, "any"));
            } else {
                cards_list.add(new LandCard("Land" + Integer.toString(i + 1), "blue", "any"));
            }
        }

        deck = new Deck("player_deck", this.cards_list);
        this.play1 = new Player("P1", deck);
        deck2 = new Deck("player_deck", this.cards_list);
        this.play2 = new Player("P2", deck2);
        card = null;
        game = new Game(play1,play2);

        this.play0 = new Player("P1", deck);

        try{
            play1.drawChosenCard("War3");
        } catch (CardNotFoundException e) {
            //pass
        }

    }


    @Test
    public void testPlayerConstructor() {
        /*Hand must not be created when new() used
        must only be instantiated when the Game begins
        because the player can have a infinite number of games
        the hand is created by game
        * */
        Assert.assertNull(this.play0.hand);
        Assert.assertEquals(0,play0.dead_zone.size());
    }

    @Test
    public void testDrawIntoHand() {
        this.play1.drawCard(1);
        Assert.assertEquals(7,this.play1.hand.getSize());
    }

    @Test
    public void testCardNotFound() {
        try {
            this.play1.drawChosenCard("abcd");
        } catch (CardNotFoundException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testUseCard() {

        try {
            System.out.println(play1.hand);
            this.play1.useCard("War3");
        } catch (CardNotFoundException | TurnException e) {
            e.printStackTrace();
        }
        //TODO create assert that checks if the card is in arena
        // also, maybe that should be in the integration tests
        Assert.fail("Not implemented");
    }

    @Test
    public void testDiscardIntoDeadZone() {
        try {
            this.play1.discardCard("War3");
        } catch (CardNotFoundException e) {
            Assert.fail("e");
        }
        //should not be done getting a specific card because of the shuffle
        Assert.assertEquals(1,this.play1.dead_zone.size());
    }

    @Test
    public void testBeginTurn() {
        this.play1.beginTurn();
        Assert.assertEquals(true,play1.isPlaying());
    }

    @Test
    public void testEndTurn() {
        this.play1.beginTurn();
        this.play1.endTurn();
        Assert.assertEquals(false,play1.isPlaying());
    }
}
