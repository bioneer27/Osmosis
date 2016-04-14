package xjjackson.model;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;
import ks.common.model.Pile;

public class MoveWasteToFoundation extends ks.common.model.Move{
	
	/** From & To piles. */
	protected Column from;
	protected Column to;

	/** The card being moved (if any). */
	protected Card drag;
	
	public MoveWasteToFoundation(Column from, Column to, Card drag){
		super();
		this.from = from;
		this.to = to;
		this.drag = drag;
	}

	@Override
	public boolean doMove(Solitaire game) {
		
		// TODO Auto-generated method stub
		if (valid (game) == false) {
			return false;
		}

		// EXECUTE:
		to.add (drag);

		game.updateScore(1);
		
		return true;
	}

	@Override
	public boolean undo(Solitaire game) {
		
		// TODO Auto-generated method stub

		
		if (to.empty()) return false;

		// UNDO: move back
		from.add (to.get());
		return true;
	}

	@Override
	public boolean valid(Solitaire game) {
		boolean validation = false;
		
		if (to.suit() == drag.getSuit()) {
			validation = true;
		}

		return validation;
		// TODO Auto-generated method stub

	}

}