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
	protected Column[] columnArray;
	protected int colIndex;
	
	public StartFoundationFromWaste(Column from, Column to, Card drag, Column[] columnArray, int colIndex){
		super();
		this.from = from;
		this.to = to;
		this.drag = drag;
		this.columnArray = columnArray;
		this.colIndex = colIndex;
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
		game.updateScore(-1);
		return true;
	}

	@Override
	public boolean valid(Solitaire game) {
		
		boolean validFoundation = false;
		boolean validation = false;
		
		if (!columnArray[colIndex - 1].empty() && columnArray[colIndex].empty()) validFoundation = true;
		
		
		/*You need to have the base card first to add */
		int base = columnArray[0].peek(0).getRank();
		int draggedRank = drag.getRank();
		
		boolean validBase = (base == draggedRank);
		
		if (to.count() == 0 && validBase && validFoundation) {
			validation = true;
		}

		return validation;
		// TODO Auto-generated method stub
	}

}
