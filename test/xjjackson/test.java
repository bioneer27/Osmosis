package xjjackson;

import java.awt.event.MouseEvent;

import heineman.klondike.DealCardMove;
import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.launcher.Main;
import ks.tests.KSTestCase;
import xjjackson.model.*;
import xjjackson.controller.*;
import xjjackson.view.*;

public class test extends KSTestCase {
Osmosis game;

	
	// window for game.
	GameWindow gw;
	
	protected void setUp() {
		game = new Osmosis();
		
		// Because solitaire variations are expected to run within a container, we need to 
		// do this, even though the Container will never be made visible. Note that here
		// we select the "random seed" by which the deck will be shuffled. We use the 
		// special constant Deck.OrderBySuit (-2) which orders the deck from Ace of clubs
		// right to King of spades.
		gw = Main.generateWindow(game, Deck.OrderBySuit); 
		
	}
	
	// clean up properly
	protected void tearDown() {
		gw.setVisible(false);
		gw.dispose();
	}
	
	public void testDealOne() {
		DealCards dfm = new DealCards(game.deck, game.wastePile);

		assertTrue (game.wastePile.empty());
		assertTrue (dfm.valid(game));
		assertTrue (dfm.doMove(game));
		assertEquals (new Card(Card.SEVEN, Card.HEARTS), game.wastePile.peek());
		assertTrue (dfm.undo(game));
		
		
		// fix things so they stay broke
		game.deck.removeAll();
		
		assertFalse (dfm.valid(game));
		assertFalse (dfm.doMove(game));
		
	}
	
	public void testDeckController() {

		// first create a mouse event
		MouseEvent pr = createPressed (game, game.deckView, 0, 0);
		game.deckView.getMouseManager().handleMouseEvent(pr);
		
		assertEquals (new Card(Card.SEVEN, Card.HEARTS), game.wastePile.peek());
		assertTrue (game.undoMove());
		assertTrue (game.wastePile.count() == 0);
		
	}
	
	public void testPileController() {

		// first create a mouse event
		MouseEvent pr = createPressed (game, game.pileView4, 200, 350);
		game.pileView4.getMouseManager().handleMouseEvent(pr);

		// drop on the first column
		MouseEvent rel = createReleased (game, game.rowView1, 300, 50);
		game.rowView1.getMouseManager().handleMouseEvent(rel);

		assertEquals (2, game.column1.count());
		
		assertTrue (game.column1.peek().isFaceUp());

		// go ahead and flip card by re-executing move
		game.rowView1.getMouseManager().handleMouseEvent(pr);
		assertTrue (game.column1.peek().isFaceUp());

		// undo twice.
		assertTrue (game.undoMove());

		assertEquals (1, game.column1.count());
		
	}
	
	public void testPileController2() {

		// first create a mouse event
		MouseEvent pr = createPressed (game, game.pileView1, 200, 50);
		game.pileView1.getMouseManager().handleMouseEvent(pr);

		// drop on the first column
		MouseEvent rel = createReleased (game, game.rowView2, 300, 150);
		game.rowView2.getMouseManager().handleMouseEvent(rel);

		assertEquals (1, game.column2.count());
		
		assertTrue (game.column1.peek().isFaceUp());

		// go ahead and flip card by re-executing move
		game.rowView1.getMouseManager().handleMouseEvent(pr);
		assertTrue (game.column1.peek().isFaceUp());

		// undo twice.
		assertTrue (game.undoMove());

		assertEquals (1, game.column1.count());
		
	}
	
	public void testDeckPress() {

		// first create a mouse event
		MouseEvent pr = createPressed (game, game.deckView, 50, 450);
		game.deckView.getMouseManager().handleMouseEvent(pr);
		
		createPressed (game, game.deckView, 50, 450);
		game.deckView.getMouseManager().handleMouseEvent(pr);
		
		createPressed (game, game.deckView, 50, 450);
		game.deckView.getMouseManager().handleMouseEvent(pr);
		
		createPressed (game, game.deckView, 50, 450);
		game.deckView.getMouseManager().handleMouseEvent(pr);
		
		createPressed (game, game.deckView, 50, 450);
		game.deckView.getMouseManager().handleMouseEvent(pr);
		
		createPressed (game, game.deckView, 50, 450);
		game.deckView.getMouseManager().handleMouseEvent(pr);
		
		createPressed (game, game.deckView, 50, 450);
		game.deckView.getMouseManager().handleMouseEvent(pr);
		
		createPressed (game, game.deckView, 50, 450);
		game.deckView.getMouseManager().handleMouseEvent(pr);
		
		createPressed (game, game.deckView, 50, 450);
		game.deckView.getMouseManager().handleMouseEvent(pr);
		
		createPressed (game, game.deckView, 50, 450);
		game.deckView.getMouseManager().handleMouseEvent(pr);
		
		createPressed (game, game.deckView, 50, 450);
		game.deckView.getMouseManager().handleMouseEvent(pr);
		
		createPressed (game, game.deckView, 50, 450);
		game.deckView.getMouseManager().handleMouseEvent(pr);
		
		createPressed (game, game.deckView, 50, 450);
		game.deckView.getMouseManager().handleMouseEvent(pr);
		
		

		assertEquals (0, game.wastePile.count());
		
		assertTrue (game.column1.peek().isFaceUp());

		// go ahead and flip card by re-executing move
		game.rowView1.getMouseManager().handleMouseEvent(pr);
		assertTrue (game.column1.peek().isFaceUp());

		// undo twice.
		assertFalse (game.undoMove());

		assertEquals (1, game.column1.count());
		
	}
	
	public void testMoveToFoundation() {

		// first create a mouse event
		MouseEvent pr = createPressed (game, game.pileView4, 200, 350);
		game.pileView4.getMouseManager().handleMouseEvent(pr);
		// drop on the first column
		MouseEvent rel = createReleased (game, game.rowView1, 300, 50);
		game.rowView1.getMouseManager().handleMouseEvent(rel);
		
		// first create a mouse event
		MouseEvent pr1 =  createPressed (game, game.pileView1, 200, 50);
		game.pileView1.getMouseManager().handleMouseEvent(pr1);
		// drop on the first column
		MouseEvent rel1 = createReleased (game, game.rowView2, 300, 150);
		game.rowView2.getMouseManager().handleMouseEvent(rel1);

		MouseEvent pr2 =  createPressed (game, game.pileView1, 200, 50);
		game.pileView1.getMouseManager().handleMouseEvent(pr2);
		// drop on the first column
		MouseEvent rel2 = createReleased (game, game.rowView2, 300, 150);
		game.rowView2.getMouseManager().handleMouseEvent(rel2);


		

		

		assertEquals (0, game.wastePile.count());
		
		assertTrue (game.column1.peek().isFaceUp());

		// go ahead and flip card by re-executing move
		game.rowView1.getMouseManager().handleMouseEvent(pr);
		assertTrue (game.column1.peek().isFaceUp());



		assertEquals (2, game.column2.count());
		
	}
	
	public void testPileToPileControllerError(){
		
		// first create a mouse event
		MouseEvent pr =createPressed (game, game.pileView1, 200, 50);
		game.pileView1.getMouseManager().handleMouseEvent(pr);
		// drop on the first column
		MouseEvent rel =createReleased (game, game.pileView2, 200, 150);
		game.pileView2.getMouseManager().handleMouseEvent(rel);
		
	}
	
	public void testWasteToFoundation(){
		
		MouseEvent pr1 = createPressed (game, game.deckView, 50, 450);
		game.deckView.getMouseManager().handleMouseEvent(pr1);
		
		// first create a mouse event
		MouseEvent pr =createPressed (game, game.fpv, 85, 73);
		game.fpv.getMouseManager().handleMouseEvent(pr);
		// drop on the first column
		MouseEvent rel =createReleased (game, game.rowView1, 300, 50);
		game.rowView1.getMouseManager().handleMouseEvent(rel);
		
		assertEquals (2, game.column1.count());
	}
	
public void testWasteToWaste(){
		
		MouseEvent pr1 = createPressed (game, game.deckView, 50, 450);
		game.deckView.getMouseManager().handleMouseEvent(pr1);
		
		// first create a mouse event
		MouseEvent pr =createPressed (game, game.fpv, 85, 73);
		game.fpv.getMouseManager().handleMouseEvent(pr);
		// drop on the first column
		MouseEvent rel =createReleased (game, game.fpv, 85, 73);
		game.fpv.getMouseManager().handleMouseEvent(rel);
		
		assertEquals (1, game.column1.count());
	}

}
