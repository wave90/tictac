package com.tic.tac;

import com.tic.tac.bot.TeacherBot;


public class GameRunner {

	public static void main(String[] args) {
		TeacherBot teacher = new TeacherBot();
		teacher.teachBots(1000, 10, 5);
	}
	
}
