package xjjackson.model;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;
import ks.common.model.Pile;

public class MoveReserveToFoundation extends ks.common.model.Move{
	
	/** From & To piles. */
	protected Pile from;
	protected Column to;

	/** The card being moved (if any). */
	protected Card drag;
	
	protected Column[] columnArray;
	
	protected int colIndex; 
//	
//	public MoveReserveToFoundation(Pile from, Column to, Card drag){
//		super();
//		this.from = from;
//		this.to = to;
//		this.drag = drag;
//		this.columnArray = columnArray;
//		this.colIndex = colIndex;
//	}
//	
	public MoveReserveToFoundation(Pile from, Column to, Card drag, Column[] columnArray, int colIndex){
		super();
		this.from = from;
		this.to = to;
		this.drag = drag;
		this.columnArray = columnArray;
		this.colIndex = colIndex;
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
		game.updateScore(-1);
		return true;
	}

	@Override
	public boolean valid(Solitaire game) {

		boolean validation = false;
		boolean subsetValidator = false;		
		int dragRank = drag.getRank();
		if (colIndex == 0){
			subsetValidator = true;
		}
		else {
			int i;
			Column column = columnArray[colIndex - 1];
			int colSize = column.count();
			for (i = 0; i < colSize; i++){
				if (column.peek(i).getRank() == drag.getRank()) 	subsetValidator = true;
				
			}
				
		}
		
		if (to.suit() == drag.getSuit() && subsetValidator) {
			validation = true;
		}

		return validation;
		// TODO Auto-generated method stub

	}

}
