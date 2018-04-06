package com.tic.tac.util;

import static com.tic.tac.util.PositionEnum.*;

import java.util.ArrayList;
import java.util.List;

public enum WinnerEmun {

	ONE(TL,TM,TR),
	TWO(ML,MM,MR),
	THREE(BL,BM,BR),
	FOUR(TL,ML,BL),
	FIVE(TM,MM,BM),
	SIX(TR,MR,BR),
	SEVEN(TL,MM,BR),
	EIGHT(TR,MM,BL);
	
	private WinnerEmun(PositionEnum winningPosition1, PositionEnum winningPosition2, PositionEnum winningPosition3) {
		this.winningPosition1 = winningPosition1;
		this.winningPosition2 = winningPosition2;
		this.winningPosition3 = winningPosition3;
	}
	
	private PositionEnum winningPosition1;
	private PositionEnum winningPosition2;
	private PositionEnum winningPosition3;
	
	public List<PositionEnum> getAllPositions() {
		List<PositionEnum> list = new ArrayList<>();
		list.add(winningPosition1);
		list.add(winningPosition2);
		list.add(winningPosition3);
		return list;
	}
}
