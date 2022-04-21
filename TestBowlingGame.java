package com.test.bowling;

import org.junit.Test;

import com.test.bowling.SinglePlayerBowlingGame.RESPONSE_CODE;

import static org.junit.Assert.assertEquals;

public class TestBowlingGame {
	
	@Test
	public void testFullScore() {
		SinglePlayerBowlingGame game = new SinglePlayerBowlingGameImpl();
		assertEquals(game.isGameStarted(),false);
		for(int i=0;i<12;i++) {
			game.roll(10);
		}
		assertEquals(game.isGameStarted(),true);
		assertEquals(game.score(),300);
		assertEquals(game.isGameFinished(),true);
		assertEquals(game.getCurrentFrame(),10);
		assertEquals(game.getNumberOfBallsRolled(),12);
		assertEquals(game.getNumberOfStrikes(),12);
		assertEquals(game.getNumberOfSpares(),0);
	}
	
	@Test
	public void testZeroScore() {
		SinglePlayerBowlingGame game = new SinglePlayerBowlingGameImpl();
		assertEquals(game.isGameStarted(),false);
		for(int i=0;i<12;i++) {
			game.roll(0);
		}
		assertEquals(game.isGameStarted(),true);
		assertEquals(game.isGameFinished(),false);
		for(int i=12;i<20;i++) {
			game.roll(0);
		}
		assertEquals(game.score(),0);
		assertEquals(game.isGameFinished(),true);
		assertEquals(game.getCurrentFrame(),10);
		assertEquals(game.getNumberOfBallsRolled(),20);
		assertEquals(game.getNumberOfStrikes(),0);
		assertEquals(game.getNumberOfSpares(),0);
	}
	
	@Test
	public void testAllOnesScore() {
		SinglePlayerBowlingGame game = new SinglePlayerBowlingGameImpl();
		assertEquals(game.isGameStarted(),false);
		for(int i=0;i<12;i++) {
			game.roll(1);
		}
		assertEquals(game.isGameStarted(),true);
		assertEquals(game.isGameFinished(),false);
		for(int i=12;i<20;i++) {
			game.roll(1);
		}
		assertEquals(game.score(),20);
		assertEquals(game.isGameFinished(),true);
		assertEquals(game.getCurrentFrame(),10);
		assertEquals(game.getNumberOfBallsRolled(),20);
		assertEquals(game.getNumberOfStrikes(),0);
		assertEquals(game.getNumberOfSpares(),0);
	}
	
	@Test
	public void testRandomScore1() {
		SinglePlayerBowlingGame game = new SinglePlayerBowlingGameImpl();
		assertEquals(game.isGameStarted(),false);
		game.roll(10);
		game.roll(5);
		game.roll(4);
		assertEquals(game.score(),28);
		assertEquals(game.isGameFinished(),false);
		assertEquals(game.getCurrentFrame(),2);
		assertEquals(game.getNumberOfBallsRolled(),3);
		assertEquals(game.getNumberOfStrikes(),1);
		assertEquals(game.getNumberOfSpares(),0);
	}
	
	@Test
	public void testRandomScore2() {
		SinglePlayerBowlingGame game = new SinglePlayerBowlingGameImpl();
		assertEquals(game.isGameStarted(),false);
		game.roll(10);
		game.roll(5);
		game.roll(5);
		assertEquals(game.score(),30);
		assertEquals(game.isGameFinished(),false);
		assertEquals(game.getCurrentFrame(),2);
		assertEquals(game.getNumberOfBallsRolled(),3);
		assertEquals(game.getNumberOfStrikes(),1);
		assertEquals(game.getNumberOfSpares(),1);
	}
	
	@Test
	public void testRandomScore3() {
		SinglePlayerBowlingGame game = new SinglePlayerBowlingGameImpl();
		assertEquals(game.isGameStarted(),false);
		game.roll(10);
		game.roll(5);
		game.roll(5);
		game.roll(5);
		game.roll(4);
		assertEquals(game.score(),44);
		assertEquals(game.isGameFinished(),false);
		assertEquals(game.getCurrentFrame(),3);
		assertEquals(game.getNumberOfBallsRolled(),5);
		assertEquals(game.getNumberOfStrikes(),1);
		assertEquals(game.getNumberOfSpares(),1);
	}
	
	@Test
	public void testRandomScore4() {
		SinglePlayerBowlingGame game = new SinglePlayerBowlingGameImpl();
		assertEquals(game.isGameStarted(),false);
		game.roll(5);
		game.roll(4);
		game.roll(0);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		assertEquals(game.score(),23);
		assertEquals(game.isGameFinished(),false);
		assertEquals(game.getCurrentFrame(),3);
		assertEquals(game.getNumberOfBallsRolled(),6);
		assertEquals(game.getNumberOfStrikes(),0);
		assertEquals(game.getNumberOfSpares(),0);
	}
	
	@Test
	public void testRandomScore5() {
		SinglePlayerBowlingGame game = new SinglePlayerBowlingGameImpl();
		assertEquals(game.isGameStarted(),false);
		game.roll(5);
		game.roll(4);
		game.roll(0);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		game.roll(5);
		game.roll(4);
		game.roll(0);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		game.roll(5);
		game.roll(4);
		game.roll(0);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		assertEquals(game.score(),78);
		assertEquals(game.isGameFinished(),true);
		assertEquals(game.getCurrentFrame(),10);
		assertEquals(game.getNumberOfBallsRolled(),20);
		assertEquals(game.getNumberOfStrikes(),0);
		assertEquals(game.getNumberOfSpares(),0);
	}
	
	@Test
	public void testRandomScore6() {
		SinglePlayerBowlingGame game = new SinglePlayerBowlingGameImpl();
		assertEquals(game.isGameStarted(),false);
		game.roll(5);
		game.roll(4);
		game.roll(0);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		game.roll(5);
		game.roll(4);
		game.roll(0);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		game.roll(5);
		game.roll(4);
		game.roll(0);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		game.roll(10);
		game.roll(0);
		assertEquals(game.score(),79);
		assertEquals(game.isGameFinished(),false);
		assertEquals(game.getNumberOfStrikes(),1);
		assertEquals(game.getNumberOfBallsRolled(),20);
		game.roll(10);
		assertEquals(game.score(),89);
		assertEquals(game.isGameFinished(),true);
		assertEquals(game.getCurrentFrame(),10);
		assertEquals(game.getNumberOfBallsRolled(),21);
		assertEquals(game.getNumberOfStrikes(),2);
		assertEquals(game.getNumberOfSpares(),0);
	}
	
	@Test
	public void testRandomScore7() {
		SinglePlayerBowlingGame game = new SinglePlayerBowlingGameImpl();
		assertEquals(game.isGameStarted(),false);
		game.roll(5);
		game.roll(4);
		game.roll(0);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		game.roll(5);
		game.roll(4);
		game.roll(0);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		game.roll(5);
		game.roll(4);
		game.roll(0);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		game.roll(10);
		game.roll(0);
		assertEquals(game.score(),79);
		assertEquals(game.isGameFinished(),false);
		assertEquals(game.getNumberOfStrikes(),1);
		assertEquals(game.getNumberOfBallsRolled(),20);
		game.roll(10);
		assertEquals(game.score(),89);
		assertEquals(game.isGameFinished(),true);
		assertEquals(game.getCurrentFrame(),10);
		assertEquals(game.getNumberOfBallsRolled(),21);
		assertEquals(game.getNumberOfStrikes(),2);
		assertEquals(game.getNumberOfSpares(),0);
	}
	
	@Test
	public void testInputs() {
		SinglePlayerBowlingGame game = new SinglePlayerBowlingGameImpl();
		assertEquals(game.roll(-1),RESPONSE_CODE.VALUE_SHOULD_BE_0_TO_10);
		assertEquals(game.roll(11),RESPONSE_CODE.VALUE_SHOULD_BE_0_TO_10);
		assertEquals(game.roll(5),RESPONSE_CODE.SUCCESS);
		assertEquals(game.roll(6),RESPONSE_CODE.INVALID_INPUT);
		game.roll(4);
		game.roll(0);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		game.roll(5);
		game.roll(4);
		game.roll(0);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		game.roll(5);
		game.roll(4);
		game.roll(0);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		game.roll(10);
		game.roll(0);
		game.roll(0);
		assertEquals(game.roll(5),RESPONSE_CODE.GAME_ALREDY_FINISHED);
	}
	
	@Test
	public void testRestartGame() {
		SinglePlayerBowlingGame game = new SinglePlayerBowlingGameImpl();
		assertEquals(game.isGameStarted(),false);
		game.roll(5);
		game.roll(4);
		game.roll(0);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		assertEquals(game.score(),23);
		assertEquals(game.isGameStarted(),true);
		assertEquals(game.isGameFinished(),false);
		assertEquals(game.getCurrentFrame(),3);
		assertEquals(game.getNumberOfBallsRolled(),6);
		assertEquals(game.getNumberOfStrikes(),0);
		assertEquals(game.getNumberOfSpares(),0);
		game.restartGame();
		assertEquals(game.isGameStarted(),false);
		assertEquals(game.isGameFinished(),false);
		assertEquals(game.score(),0);
		assertEquals(game.getCurrentFrame(),0);
		assertEquals(game.getNumberOfBallsRolled(),0);
		assertEquals(game.getNumberOfStrikes(),0);
		assertEquals(game.getNumberOfSpares(),0);
		
		game.roll(5);
		game.roll(4);
		game.roll(0);
		game.roll(5);
		game.roll(4);
		game.roll(5);
		assertEquals(game.score(),23);
		assertEquals(game.isGameStarted(),true);
		assertEquals(game.isGameFinished(),false);
		assertEquals(game.getCurrentFrame(),3);
		assertEquals(game.getNumberOfBallsRolled(),6);
		assertEquals(game.getNumberOfStrikes(),0);
		assertEquals(game.getNumberOfSpares(),0);
	}
}
