package com.tic.tac.util;

public enum PositionEnum {

	TL(1),
	TM(2),
	TR(3),
	ML(4),
	MM(5),
	MR(6),
	BL(7),
	BM(8),
	BR(9);
	
	
	private PositionEnum(int pos) {
		this.pos = pos;
	}

	private int pos;

	public int getPos() {
		return pos;
	}

	public static PositionEnum getPosFromInt(int pos) {
		if(pos > 0 && pos < 10) {
			for(PositionEnum posEnum:PositionEnum.values()) {
				if(posEnum.getPos() == pos) {
					return posEnum;
				}
			}
		}
		throw new IllegalStateException("Error while getting Position");
	}
	
}
