package xjjackson.model;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;

public class StartFoundationFromWaste extends ks.common.model.Move{
	
	protected Column from;
	protected Column to;

	/** The card being moved (if any). */
	protected Card drag;
	
	public StartFoundationFromWaste(Column from, Column to, Card drag){
		super();
		this.from = from;
		this.to = to;
		this.drag = drag;
	}

	@Override
	public boolean doMove(Solitaire game) {
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
		
		if (to.count() == 0) {
			validation = true;
		}

		return validation;
		// TODO Auto-generated method stub
	}

}
