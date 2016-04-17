package xjjackson.model;


import ks.common.model.Deck;
import ks.common.model.Pile;
import ks.common.model.Column;

/**
 * Represents the moving of a card from the deck to the waste pile.
 * <p>
 * There are two parameters for this move:
 * <p>
 * deck = Deck which deals the cards<br>
 * wastePile = Pile to which the card is dealt.<br>
 * <p>
 * Creation date: (10/28/01 8:50:54 AM)
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class DealCards extends ks.common.model.Move {
	/** The deck. */
	protected Deck deck;

	/** The wastePile. */
	protected Column wastePile;
	
	protected int cards;
	
	public DealCards(Deck deck, Column wastePile){
		super();
		this.deck = deck;
		this.wastePile = wastePile;
		this.cards = 32;
	}
/**
 * DealCardMove constructor.
 * @param Deck deck
 * @param Pile wastePile
 * 
 * 
 */
	public DealCards (Deck deck, Column wastePile, int cards) {
		super();
	
		this.deck = deck;
		this.wastePile = wastePile;
		this.cards = cards;
	}
/**
 * Do Move
 * @param theGame ks.games.Solitaire 
 */
public boolean doMove (ks.common.games.Solitaire game) {
	// VALIDATE
	if (valid(game) == false)
		return false;
	
	int boundary;
	if (cards < 3){
		boundary = cards;
	}
	else {
		boundary = 3;
	}
	
	int i;
	
	for (i = 0; i < boundary; i++ ){
		wastePile.add(deck.get());
	}
		
	game.updateNumberCardsLeft(-i);
	return true;
	/*
	if (cards >= 3){
		
		System.out.println("YES");
	// EXECUTE:
	// Get card from deck
		wastePile.add (deck.get());
		wastePile.add (deck.get());
		wastePile.add (deck.get());
	
		game.updateNumberCardsLeft (-3);
		return true;
	}
	
	if (cards == 2){
		wastePile.add (deck.get());
		wastePile.add (deck.get());
		game.updateNumberCardsLeft(-2);
		return true;
		
	}
	
	if (cards == 1){
		wastePile.add (deck.get());
		game.updateNumberCardsLeft(-1);
		return true;
	}
	return true;*/
	
}
/**
 * To undo this move, we move the cards from the wastePile back to the Deck.
 * @param theGame ks.games.Solitaire 
 */
public boolean undo(ks.common.games.Solitaire game) {

	// VALIDATE:
	if (wastePile.empty()) return false;
	
	int wasteSize = wastePile.count();
	
	if (wasteSize >= 3 ){
	// UNDO:
		deck.add (wastePile.get());
		deck.add (wastePile.get());
		deck.add (wastePile.get());
		game.updateNumberCardsLeft(+3);
	}
	else {
		int i;
		for (i = 0; i < wasteSize; i++){
			deck.add (wastePile.get());
		}
		game.updateNumberCardsLeft(+(i));
	}

	// update the number of cards to go.
	return true;
}
/**
 * Action for Klondike: Deal card from deck to wastePile.
 * @param d ks.common.games.Solitaire
 */
public boolean valid(ks.common.games.Solitaire game) {
	// VALIDATION:
	boolean validation = false;

	//    dealCard(deck,wastePile) : not deck.empty()
	if (!deck.empty()) 
		validation = true;

	return validation;
}
}