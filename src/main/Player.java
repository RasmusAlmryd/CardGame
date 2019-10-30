package main;

import java.util.ArrayList;

public class Player {

    //TODO crate Player TestClass

    private String name;
    private boolean playing = false;
    private Deck deck;
    public Hand hand;
    public ArrayList<Card> field;
    public ArrayList<Card> dead_zone;

    public Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
        this.field = new ArrayList<Card>();
        this.dead_zone = new ArrayList<Card>();
    }

    public ArrayList<Card> getDead_zone() {
        return dead_zone;
    }

    public void beginTurn(){
        playing = true;
    }

    public void endTurn(){
        playing = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean isPlaying() {
        return playing;
    }

    @Override
    public String toString() {
        return "main.Player{" +
                "name='" + name + '\'' +
                ", is_playing=" + playing +
                ", deck=" + deck +
                '}';
    }

    public void drawCard(int i){
        if (hand==null){
            this.hand = new Hand();
        }
        for (int j = 0; j < i; j++) {
            Card draw = deck.draw();
            hand.addCard(draw);
        }
    }

    public void useCard(String name){
        Card card = this.hand.removeCard(name);
        this.field.add(card);
    }

    public void discardCard(String name){
        Card card = this.hand.removeCard(name);
        this.dead_zone.add(card);
    }

    public void printField(){
        System.out.println("Field list by - main.Player="+name);
        for (int i = 0; i < this.field.size(); i++) {
            System.out.println(this.field.get(i));
        }
    }

}