package com.test.bowling;

/**
 * 
 * A bowling game meant for single player
 * @author prinson
 *
 */
public interface SinglePlayerBowlingGame {
	
	enum RESPONSE_CODE {SUCCESS, GAME_ALREDY_FINISHED, VALUE_SHOULD_BE_0_TO_10, INVALID_INPUT};
	
	/**
	 * Update the score with the number of pins scored in the current roll
	 * @param numberOfPinsDown
	 * @return returns true when successfully able to update the score. false when the system occurred unexpected error. One possibility is when user tries to update the score after the game is finished.
	 */
	RESPONSE_CODE roll(int numberOfPinsDown);
	
	/**
	 * 
	 * @return returns the current score of the game
	 */
	int score();
	
	/**
	 * 
	 * @return returns total number of strikes achieved in the game.
	 */
	int getNumberOfStrikes();
	
	/**
	 * 
	 * @return returns total number of spares achieved in the game.
	 */
	int getNumberOfSpares();
	
	/**
	 * 
	 * @return returns the current active frame number. Frame number can be between 1-10.
	 */
	int getCurrentFrame();
	
	/**
	 * 
	 * @return returns total number of balls  palyed in the game so far.
	 */
	int getNumberOfBallsRolled();
	
	/**
	 * 
	 * @return returns true if the game started already.
	 */
	boolean isGameStarted();
	
	/**
	 * 
	 * @return returns true if the game is finished
	 */
	boolean isGameFinished();
	
	/**
	 * 
	 * @return returns detailed status of the game including the score achieved.
	 */
	CharSequence getStatus();
	
	/**
	 * An option to restart the game from the beginning.
	 */
	void restartGame();
}