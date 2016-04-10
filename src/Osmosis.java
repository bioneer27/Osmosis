



import ks.client.gamefactory.GameWindow;
import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.games.Solitaire;
import ks.common.model.*;
import ks.common.view.*;
import ks.launcher.Main;
import xjjackson.controller.DeckController;
import xjjackson.controller.PileController;
import xjjackson.controller.ColumnController;


/**
 * Sample Solitaire Plug-in to show how this works.
 * <p>
 * This sample is part of a tutorial showing how to develop a solitaire plug-in.
 * <p>
 * There are three steps to creating a plug-in:
 * <p>
 * 1. Design the Model.<br>
 * 2. Design the View.<br>
 * 3. Design the Controller.<br>
 * <p>
 * NAME OF SOLITAIRE VARIATION: Narcotic
 * <p>
 * DESCRIPTION:
 * Deal four cards face up in a row. If two or more are of the same rank, put 
 * duplicates on the leftmost of them. Then deal four more across, pause, and 
 * do the same. Keep going until you run out of cards. Gather the piles up from
 * the right to left, turn the whole pile upside down and start again. Whenever
 * the four cards you deal are of the same rank, eliminate them. Win if you 
 * eventually succeed in eliminating all fifty-two. This may take years.
 * <p>
 * MODEL:
 * <p>
 *   deck: one 52-card deck of playing cards<br>
 *   pile1: a pile showing the leftmost pile of cards<br>
 *   pile2: the second pile from the left<br>
 *   pile3: the third pile from the left<br>
 *   pile4: the fourth pile from the left<br>
 *   score: an integer representing the score of the game<br>
 *   numLeft: an integer representing the number of cards left in the deck<br>
 * <p>
 * VIEW:
 * <p>
 *   DeckView for the deck. PileView for the piles. IntegerView for the
 *   MutableIntegers.
 * <p>
 * CONTROLLERS:
 * <p>
 * Deck controller to deal cards to piles. Include move for showing the
 * drag action.
 * 
 * <p>
 * INITIALIZATION:
 * <p>
 *   shuffle deck<br>
 *   deal one card face up to pile1, pile2, pile3, pile4<br>
 * <p>
 * EXECUTION:
 * <p>
 * Launch this class as an application.
 * 
 * @author George Heineman 
 */
public class Osmosis extends Solitaire {

	/** Each Game has a Deck. */
	protected Deck deck;

	/** And four Piles  */
	protected Pile pile1, pile2, pile3, pile4;
	
	protected Column column1, column2, column3, column4;

	/** The view of the deck */
	protected DeckView deckView;

	/** The columns */
	protected PileView pileView1, pileView2, pileView3, pileView4;
	protected ColumnView columnView1, columnView2, columnView3, columnView4;

	/** The display for the score. */
	protected IntegerView scoreView;

	/** View for the number of cards left in the deck. */
	protected IntegerView numLeftView;

	/** To select a new deck type uncomment this method */
//	@Override
//	public String getDeckType() {
//		return "tiny";
//	}
	
	/**
	 * Prepare the controllers.
	 */
	private void initializeController() {
		// Initialize Controllers for DeckView
		deckView.setMouseAdapter(new DeckController (this));
		
		// add trivial controllers for all piles.
		pileView1.setMouseAdapter(new PileController (this, pileView1));
		pileView2.setMouseAdapter(new PileController (this, pileView2));
		pileView3.setMouseAdapter(new PileController (this, pileView3));
		pileView4.setMouseAdapter(new PileController (this, pileView4));
		columnView1.setMouseAdapter(new ColumnController (this, columnView1));
		columnView2.setMouseAdapter(new ColumnController (this, columnView2));
		columnView3.setMouseAdapter(new ColumnController (this, columnView3));
		columnView4.setMouseAdapter(new ColumnController (this, columnView4));
		
		// to complete the behavior, you must register the default 'released adapter'
		// with each widget that isn't expecting anything! Note that widgets
		// that are expecting things must handle mouseReleased whenever there
		// is any dragging going on. Check out NarcoticDeckController
		scoreView.setMouseAdapter (new SolitaireReleasedAdapter(this));
		numLeftView.setMouseAdapter (new SolitaireReleasedAdapter(this));
	}

	
	/** Return the name of this solitaire variation. */
	public String getName() {
		return "Osmosis";
	}

	/**
	 * Initialize the Model for Narcotic.
	 *
	 * Don't forget to create the standard Score and numberCardsLeft model elements.
	 *
	 * @param seed    used to shuffle identical decks.
	 */
	private void initializeModel(int seed) {

		// initial score is set to ZERO (every Solitaire game by default has a score) 
		// and there are 52 cards left.
		numLeft = getNumLeft();
		numLeft.setValue(52);
		score = getScore();
		score.setValue(0);
		
		// add to our model a deck 
		deck = new Deck("deck");
		deck.create(seed);
		addModelElement(deck);

		pile1 = new Pile("pile1");
		pile2 = new Pile("pile2");
		pile3 = new Pile("pile3");
		pile4 = new Pile("pile4");
		column1 = new Column("column1");
		column2 = new Column("column2");
		column3 = new Column("column3");
		column4 = new Column("column4");
		// add to our model a set of four piles
		addModelElement(pile1);
		addModelElement(pile2);
		addModelElement(pile3);
		addModelElement(pile4);
		addModelElement(column1);
		addModelElement(column2);
		addModelElement(column3);
		addModelElement(column4);
	}

	/**
	 * Prepare the views
	 */
	private void initializeView() {
		// Get the card artwork to be used. This is needed for the dimensions.
		CardImages ci = getCardImages();

		// add to our view (as defined within our superclass). Similar for other widgets
		deckView = new DeckView(deck);
		deckView.setBounds(20, 400, ci.getWidth(), ci.getHeight());
		addViewWidget(deckView);

		pileView1 = new PileView(pile1);
		pileView1.setBounds(40 + ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
		addViewWidget(pileView1);

		pileView2 = new PileView(pile2);
		pileView2.setBounds(40 + ci.getWidth(), 120, ci.getWidth(), ci.getHeight());
		addViewWidget(pileView2);

		pileView3 = new PileView(pile3);
		pileView3.setBounds(40 +  ci.getWidth(), 220, ci.getWidth(), ci.getHeight());
		addViewWidget(pileView3);

		pileView4 = new PileView(pile4);
		pileView4.setBounds(40 + ci.getWidth(), 320, ci.getWidth(), ci.getHeight());
		addViewWidget(pileView4);
		
		columnView1 = new ColumnView(column1);
		columnView1.setBounds(80 + 2 * ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
		addViewWidget(columnView1);
		
		columnView2 = new ColumnView(column2);
		columnView2.setBounds(80 + 2 * ci.getWidth(), 120, ci.getWidth(), ci.getHeight());
		addViewWidget(columnView2);
		
		columnView3 = new ColumnView(column3);
		columnView3.setBounds(80 + 2 * ci.getWidth(), 220, ci.getWidth(), ci.getHeight());
		addViewWidget(columnView3);
		
		columnView4 = new ColumnView(column4);
		columnView4.setBounds(80 + 2 * ci.getWidth(), 320, ci.getWidth(), ci.getHeight());
		addViewWidget(columnView4);

		scoreView = new IntegerView(getScore());
		scoreView.setBounds(100 + 5 * ci.getWidth(), 20, 100, 60);
		addViewWidget(scoreView);

		numLeftView = new IntegerView(getNumLeft());
		numLeftView.setBounds(200 + 5* ci.getWidth(), 20, 100, 60);
		addViewWidget(numLeftView);
	}

	/** Determine whether game has been won. */
	public boolean hasWon() {
		return deck.empty() &&
			   pile1.empty() && pile2.empty() && pile3.empty() && pile4.empty();
	}

	/** Initialize solitaire variation. */
	public void initialize() {
		// Initialize model, view, and controllers.
		initializeModel(getSeed());
		initializeView();
		initializeController();

		// Prepare game AFTER all controllers are set up.
		// each column gets a card from the deck.
		pile1.add (deck.get());
		pile2.add (deck.get());
		pile3.add (deck.get());
		pile4.add (deck.get());
		column1.add (deck.get());
		column2.add (deck.get());
		column3.add (deck.get());
		column4.add (deck.get());

		// we have dealt four cards.
		updateNumberCardsLeft (-4);	
	}
	
	/** Code to launch solitaire variation. */
	public static void main (String []args) {
		// Seed is to ensure we get the same initial cards every time.
		GameWindow gw = Main.generateWindow(new Osmosis(), Deck.OrderByRank);
		gw.setVisible(true);
	}
}
