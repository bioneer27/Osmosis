package xjjackson.controller;

import java.awt.event.MouseEvent;


import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.model.Column;
import ks.common.model.Element;
import ks.common.view.*;

import xjjackson.model.*;

/**
 * Final Pile controller.
 * 
 * @author George T. Heineman (heineman@cs.wpi.edu)
 */
public class ColumnController extends java.awt.event.MouseAdapter {
	/** The narcotic instance. */
	protected Solitaire theGame = null;

	/** The PileView widget being controlled. */
	RowView columnview;

	/** NarcoticDeckController constructor comment. */
	public ColumnController(Solitaire game, RowView pileview) {
		super();

		theGame = game;
		this.columnview = pileview;
	}

	/**
	 * Respond to mouse click events.
	 */
	public void mouseClicked(MouseEvent me) {
		return;
//        if (me.getClickCount() > 1) {
//            Pile p1 = (Pile) theGame.getModelElement ("pile1");
//            Pile p2 = (Pile) theGame.getModelElement ("pile2");
//            Pile p3 = (Pile) theGame.getModelElement ("pile3");
//            Pile p4 = (Pile) theGame.getModelElement ("pile4");
//
//            // check to see if we can remove all cards.
//            Move m = null;// new RemoveAllMove (p1, p2, p3, p4);
//            if (m.doMove(theGame)) {
//                // SUCCESS
//                theGame.pushMove (m);
//
//                // redraw all piles
//                theGame.refreshWidgets();
//            }
//        }
	}
	
	/**
	 * On mouse released, get card being dragged and properly form the
	 * desired move
	 */
	public void mouseReleased(java.awt.event.MouseEvent me) {
		
		Column p1 = (Column) theGame.getModelElement ("column1");
		Column p2 = (Column) theGame.getModelElement ("column2");
		Column p3 = (Column) theGame.getModelElement ("column3");
		Column p4 = (Column) theGame.getModelElement ("column4");
		Column[] columnArray= new Column[4];
		columnArray[0] = p1;
		columnArray[1] = p2;
		columnArray[2] = p3;
		columnArray[3] = p4;
		
		Column foundation = (Column) columnview.getModelElement();
		int columnSize = foundation.count();
		
		
		Container c = theGame.getContainer();

		/** Return if there is no card being dragged chosen. */
		Widget w = c.getActiveDraggingObject();
		if (w == Container.getNothingBeingDragged()) return;

		/** Must be the CardView widget. */
		CardView cardView = (CardView) w;
		Card theCard = (Card) cardView.getModelElement();

		/** Recover the From Pile */
		
		if (columnSize == 0){

			StartFoundationFromWasteHelper(theCard, cardView, c, columnArray);
			return;
			
		}
		
		Widget fromPileView = c.getDragSource();
		
		Element fromPile =  fromPileView.getModelElement();
		

		// Determine the To Pile
		Column toPile = (Column) columnview.getModelElement();
		
		int colIndex = moveValidatorHelper(toPile, columnArray);

		// Try to make the move
		Move m;
		try {
		
			m = new MoveReserveToFoundation ((Pile)fromPile, toPile, theCard, columnArray, colIndex);
		} catch(Exception e) {
			/*You need this card to move, move to valid for move*/


			m = new MoveWasteToFoundation((Column)fromPile, toPile, theCard, columnArray, colIndex);
		}
		if (m.doMove (theGame)) {
			// SUCCESS
			theGame.pushMove (m);
			
		} else {
			// invalid move! Return to the pile from whence it came.
			// Rely on the ability of each Widget to support this method.
			fromPileView.returnWidget (cardView);
		}

		// Since we could be released over a widget, or over the container, 
		// we must repaintAll() clipped to the region we are concerned about.
		// first refresh the last known position of the card being dragged.
		// then the widgets.
		theGame.refreshWidgets(); 

		// release the dragging object since the move is now complete (this 
		// will reset container's dragSource).
		c.releaseDraggingObject();
		c.repaint();
	}

	/**
	 * On mouse Press for now, we simply delete top card and move to be
	 * the dragging card of the container.
	 * 
	 * @param me     low-level mouse event
	 */
	public void mousePressed(java.awt.event.MouseEvent me) {
		// Ask PileView to retrieve the top card as a CardView Widget
		/*You do not need a mouse press in the column controller*/
		return;
		/*CardView cardView = columnview.getCardViewForTopCard(me);

		// no card present!
		if (cardView == null) { return; }
		
		// Have container track this object now. Record where it came from
		Container c = theGame.getContainer();
		c.setActiveDraggingObject (cardView, me);
		c.setDragSource(columnview);

		// we simply redraw our source pile to avoid flicker,
		// rather than refreshing all widgets...
		columnview.redraw();*/
	}
	
	private void StartFoundationFromWasteHelper(Card theCard, CardView cardView, Container c, Column[] columnArray){
		
		
		Widget fromPileView = c.getDragSource();
		
		Element fromPile =  fromPileView.getModelElement();
		

		// Determine the To Pile
		Column toPile = (Column) columnview.getModelElement();
		
		int colIndex = moveValidatorHelper(toPile, columnArray);

		// Try to make the moves
		Move m;
		try {

			m = new StartFoundationFromReserve ((Pile)fromPile, toPile, theCard, columnArray, colIndex);
		} catch(Exception e) {

			m = new StartFoundationFromWaste((Column)fromPile, toPile, theCard, columnArray, colIndex);
		}
		if (m.doMove (theGame)) {
			// SUCCESS
			theGame.pushMove (m);
			
		} else {
			// invalid move! Return to the pile from whence it came.
			// Rely on the ability of each Widget to support this method.
			fromPileView.returnWidget (cardView);
		}

		// Since we could be released over a widget, or over the container, 
		// we must repaintAll() clipped to the region we are concerned about.
		// first refresh the last known position of the card being dragged.
		// then the widgets.
		theGame.refreshWidgets(); 

		// release the dragging object since the move is now complete (this 
		// will reset container's dragSource).
		c.releaseDraggingObject();
		c.repaint();
		
	}
	

	
	private int moveValidatorHelper(Column foundation, Column[] columnArray){
		int foundationNumber = 0;
		int i;
		for (i = 0; i < 4; i++){
			if (foundation == columnArray[i])
				foundationNumber = i;
		}
		return foundationNumber;
	}
	
	
}