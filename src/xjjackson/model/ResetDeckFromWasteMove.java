package xjjackson.model;

import ks.common.games.Solitaire;
import ks.common.model.Column;
import ks.common.model.Deck;
import ks.common.model.Pile;

public class ResetDeckFromWasteMove extends ks.common.model.Move{
	
	/** The Deck. */
	protected Deck deck;

	/** The Piles. */
	protected Column wastePile;
	
	/**
	 * ResetDeckMove constructor comment.
	 */
	public ResetDeckFromWasteMove(Deck d, Column p1) {
		super();

		this.deck = d;
		this.wastePile = p1;
		
	}
	/**
	 * Each move should knows how to execute itself.
	 * <p>
	 * Creation date: (10/21/01 3:33:39 PM)
	 * @param edu.wpi.cs.solitaire.game.Solitaire   the game being played.
	 * @return boolean
	 * @since V1.6.2
	 */
	public boolean doMove (Solitaire theGame) {

		// VALIDATE:
		if (valid (theGame) == false)	return false;

		// EXECUTE:
		int numAdded = 0;


		while (wastePile.count() > 0) {
			deck.add(wastePile.get());
			numAdded++;
		}
		

		// finally update the total number.
		theGame.updateNumberCardsLeft(numAdded);
		return true;
	}
	/**
	 * This move cannot be undone. To implement the undo, one would only have to keep
	 * track of the number of cards removed from each pile, and then restore them to
	 * their proper place.
	 * <p>
	 * For the sake of skillful playing, however, we have decided to make the act of
	 * reforming the deck a "point of no return".
	 */
	public boolean undo(ks.common.games.Solitaire game) {
		// announce no ability to undo.
		return false;
	}
	/**
	 * Validate ResetDeck Move.
	 * @param game edu.wpi.cs.soltaire.games.Solitaire
	 */
	public boolean valid (ks.common.games.Solitaire game) {
		// VALIDATION:
		boolean validation = false;

		// dealFour(d) : d.empty()
		if (deck.count() < 3)	validation = true;

		return validation;
	}

}
