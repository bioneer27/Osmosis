package xjjackson.controller;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.games.Solitaire;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.model.Column;
import xjjackson.model.*;

/**
 * Controller for the DeckView widgets used in Narcotic.
 * 
 * By extending 'SolitaireReleasedAdapter' we take advantage of the 
 * default behavior 
 *
 * @author George T. Heineman (heineman@cs.wpi.edu)
 */
public class DeckController extends SolitaireReleasedAdapter {
    /** NarcoticDeckController constructor comment. */
    public DeckController(Solitaire game, Deck d, Column wastePile) {
        super(game);
    }
    
    /**
     * A MousePressed event on the DeckView is a signal to deal the next
     * set of cards (if the deck is nonempty).
     *
     * @param me     low-level mouse event
     */
    public void mousePressed(java.awt.event.MouseEvent me) {

        // Find the deck from our model
        Deck d = (Deck) theGame.getModelElement("deck");
        Column wp = (Column) theGame.getModelElement("wastePile");
        

        if (!d.empty()) {
            // Deal three cards
        	
        	
            Move m = new DealCards(d, wp, d.count());
            if (m.doMove(theGame)) {
                // SUCCESS: have solitaire game store this move
            	theGame.pushMove(m);

                // have solitaire game refresh widgets that were affected 
            	theGame.refreshWidgets();
            }
        } else {
        	// Reset Deck
        	if(d.count() == 0){

        		Move m = new ResetDeckFromWasteMove(d, wp);
        		if (m.doMove(theGame)){
        			theGame.pushMove(m);
        			theGame.refreshWidgets();
        			return;
        		}
        	}
        	
        	
        }
    }
}
