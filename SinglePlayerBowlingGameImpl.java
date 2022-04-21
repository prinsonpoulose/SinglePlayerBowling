package com.test.bowling;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Implementation for single player Bowling game.
 * @author prinson
 *
 */
public class SinglePlayerBowlingGameImpl implements SinglePlayerBowlingGame {
	
	private final int[] rolls;
	private int currentRollIndex = 0;
	private int noOfStrikes = 0;;
	private int noOfSpares = 0;
	boolean isGameFinished = false;
	private int currentFrame = 0;
	private int noOfBallsRolled = 0;
	private int score = 0;
	private final int[] frameTotal;
	private final int[] pin1Score;
	private final int[] pin2Score;
	private final StringBuilder sb = new StringBuilder(512);
	
	public SinglePlayerBowlingGameImpl() {
		rolls = new int[21];
		Arrays.fill(rolls, -1);
		frameTotal = new int[10];
		pin1Score = new int[10];
		pin2Score = new int[12];
	}

	@Override
	public RESPONSE_CODE roll(int numberOfPinsDown) {
		if(!isGameFinished()) {
			RESPONSE_CODE code = validate(numberOfPinsDown);
			if(code != RESPONSE_CODE.SUCCESS) {
				return code;
			}
			rolls[currentRollIndex++]=numberOfPinsDown;
			try {
				score = calculate();
			} catch(Exception e) {
				currentRollIndex--;
				rolls[currentRollIndex]= -1;
				try {score = calculate();}catch(Exception e1) {}
				return RESPONSE_CODE.INVALID_INPUT;
			}
			return RESPONSE_CODE.SUCCESS;
		}
		return RESPONSE_CODE.GAME_ALREDY_FINISHED;
	}
	
	
	private RESPONSE_CODE validate(int numberOfPinsDown) {
		if(numberOfPinsDown<0 || numberOfPinsDown>10) {
			return RESPONSE_CODE.VALUE_SHOULD_BE_0_TO_10;
		}
		return RESPONSE_CODE.SUCCESS;
	}
	
	
	private int calculate() throws Exception {
		Arrays.fill(frameTotal, 0);
		Arrays.fill(pin1Score, 0);
		Arrays.fill(pin2Score, 0);
		int score = 0;
		int rollIndex = 0;
		boolean isFinished = true;
		int noOfStrikes = 0;
		int noOfSpares = 0;
		int frameIndex = 0;
		int pin1Index = 0;
		int pin2Index = 0;
		for(; frameIndex<10; frameIndex++) {
			if(rolls[rollIndex]==-1) {
				isFinished = false;
				break;
			}
			if(isStrike(rollIndex)) {
				noOfStrikes++;
				currentFrame = frameIndex+1;
				pin1Score[pin1Index++]= rolls[rollIndex];
				if(rolls[rollIndex+1]==-1) {
					score += rolls[rollIndex];
					rollIndex++;
					isFinished = false;
					frameTotal[frameIndex] = score;
					break;
				} else if(rolls[rollIndex+2]==-1) {
					score += rolls[rollIndex]+rolls[rollIndex+1];
					if(frameIndex==9) {
						if(rolls[rollIndex+1]==10) {
							noOfStrikes++;
						}
						pin2Score[pin2Index++] = rolls[rollIndex+1];
						rollIndex++;
					}
					rollIndex++;
					isFinished = false;
					frameTotal[frameIndex] = score;
					continue;
				}
				
				score+=getStrikeScore(rollIndex);
				frameTotal[frameIndex] = score;
				if(frameIndex==9) {
					if(rolls[rollIndex+1]==10) {
						noOfStrikes++;
					}
					if(rolls[rollIndex+2]==10) {
						noOfStrikes++;
					}
					pin2Score[pin2Index++] = rolls[rollIndex+1];
					pin2Score[pin2Index++] = rolls[rollIndex+2];
					rollIndex+=3;
					break;
				}
				rollIndex++;
				pin2Index++;
			} else if(isSpare(rollIndex)) {
				noOfSpares++;
				currentFrame = frameIndex+1;
				pin1Score[pin1Index++]= rolls[rollIndex];
				if(rolls[rollIndex+2]==-1) {
					score += rolls[rollIndex]+rolls[rollIndex+1];
					isFinished = false;
					frameTotal[frameIndex] = score;
					pin2Score[pin2Index++] = rolls[rollIndex+1];
					rollIndex +=2;
					break;
				}
				score += getSpareScore(rollIndex);
				frameTotal[frameIndex] = score;
				pin2Score[pin2Index++] = rolls[rollIndex+1];
				if(frameIndex==9) {
					pin2Score[pin2Index++] = rolls[rollIndex+2];
					rollIndex++;
				}
				rollIndex +=2;
			} else {
				currentFrame = frameIndex+1;
				pin1Score[pin1Index++]= rolls[rollIndex];
				if(rolls[rollIndex+1]==-1) {
					score += rolls[rollIndex];
					rollIndex++;
					isFinished = false;
					frameTotal[frameIndex] = score;
					pin2Index++;
					break;
				}
				score += getStandardScore(rollIndex);
				frameTotal[frameIndex] = score;
				pin2Score[pin2Index++] = rolls[rollIndex+1];
				rollIndex +=2;
			}
		}
		isGameFinished = isFinished;
		noOfBallsRolled = rollIndex;
		this.noOfStrikes = noOfStrikes;
		this.noOfSpares = noOfSpares;
		return score;
	}
	
	@Override
	public int score() {
		return score;
	}
	
	
	private boolean isStrike(int rollIndex) {
		return rolls[rollIndex] ==10;
	}
	
	private boolean isSpare(int rollIndex) throws Exception {
		if(rolls[rollIndex]+rolls[rollIndex+1]>10) {
			throw new Exception("Input score recieved");
		}
		return rolls[rollIndex]+rolls[rollIndex+1]==10;
	}
	
	private int getStrikeScore(int rollIndex) {
		return rolls[rollIndex]+rolls[rollIndex+1]+rolls[rollIndex+2];
	}
	
	private int getSpareScore(int rollIndex) {
		return rolls[rollIndex]+rolls[rollIndex+1]+rolls[rollIndex+2];
	}
	
	private int getStandardScore(int rollIndex) throws Exception {
		if(rolls[rollIndex]+rolls[rollIndex+1]>10) {
			throw new Exception("Input score recieved");
		}
		return rolls[rollIndex]+rolls[rollIndex+1];
	}

	@Override
	public int getNumberOfStrikes() {
		return noOfStrikes;
	}

	@Override
	public int getNumberOfSpares() {
		return noOfSpares;
	}

	@Override
	public int getCurrentFrame() {
		return currentFrame;
	}

	@Override
	public int getNumberOfBallsRolled() {
		return noOfBallsRolled;
	}

	@Override
	public boolean isGameStarted() {
		return currentRollIndex>0;
	}

	@Override
	public boolean isGameFinished() {
		return isGameFinished;
	}

	@Override
	public CharSequence getStatus() {
		sb.setLength(0);
		sb.append("--------------------------------------------------------------------------------------------\n");
		sb.append(String.format("%12s|%17s|%16s|%15s|%12s|%15s|", "GameProgress","NoOfStrikesScored","NoOfSparesScored","NoOfBallsRolled","CurrentFrame","TotalScoreSoFar" ));
		sb.append("\n--------------------------------------------------------------------------------------------\n");
		sb.append(String.format("\n%12s|%17d|%16d|%15d|%12d|%15d|", isGameStarted()?(isGameFinished()?"Completed":"InProgress"):"Not Started",getNumberOfStrikes(),getNumberOfSpares(),getNumberOfBallsRolled(),getCurrentFrame(),score() ));
		sb.append("\n--------------------------------------------------------------------------------------------\n");
		sb.append("\n");
		sb.append("-----------------------------------------------------------------------\n");
		sb.append(String.format("%10s|%5d|%5d|%5d|%5d|%5d|%5d|%5d|%5d|%5d|%5d|\n", "Frame  ",1,2,3,4,5,6,7,8,9,10));
		sb.append("-----------------------------------------------------------------------\n");
		sb.append(String.format("%10s|%5d|%5d|%5d|%5d|%5d|%5d|%5d|%5d|%5d|%5d|\n", "Pin 1  ",pin1Score[0],pin1Score[1],pin1Score[2],pin1Score[3],pin1Score[4],pin1Score[5],pin1Score[6],pin1Score[7],pin1Score[8],pin1Score[9]));
		sb.append("-----------------------------------------------------------------------\n");
		sb.append(String.format("%10s|%5d|%5d|%5d|%5d|%5d|%5d|%5d|%5d|%5d|%5s|\n", "Pin 2  ",pin2Score[0],pin2Score[1],pin2Score[2],pin2Score[3],pin2Score[4],pin2Score[5],pin2Score[6],pin2Score[7],pin2Score[8],formatLastPin2Score()));
		sb.append("-----------------------------------------------------------------------\n");
		sb.append(String.format("%10s|%5d|%5d|%5d|%5d|%5d|%5d|%5d|%5d|%5d|%5d|\n", "Total  ",frameTotal[0],frameTotal[1],frameTotal[2],frameTotal[3],frameTotal[4],frameTotal[5],frameTotal[6],frameTotal[7],frameTotal[8],frameTotal[9]));
		sb.append("-----------------------------------------------------------------------\n");
		return sb.toString();
	}
	
	private String formatLastPin2Score() {
		if(getCurrentFrame()<10) {
			return ""+0;
		}
		if(pin2Score[9]==0 && pin2Score[10]==0) {
			if(isGameFinished()) {
				return "0+0";
			}else {
				return ""+0;
			}
		}
		if(pin2Score[9]==0 && pin2Score[10]>0) {
			return "0+"+pin2Score[10];
		} else {
			return pin2Score[9]+"+"+pin2Score[10];
		}
	}

	@Override
	public void restartGame() {
		Arrays.fill(rolls, -1);
		currentRollIndex = 0;
		noOfStrikes = 0;;
		noOfSpares = 0;
		isGameFinished = false;
		currentFrame = 0;
		noOfBallsRolled = 0;
		score = 0;
		Arrays.fill(frameTotal, 0);
		Arrays.fill(pin1Score, 0);
		Arrays.fill(pin2Score, 0);
	}
	
	public static void main(String[] args) {
		SinglePlayerBowlingGame game = new SinglePlayerBowlingGameImpl();
		
		Scanner sc = new Scanner(System.in);
		while(!game.isGameFinished()) {
			System.out.println("Enter the value between [0 to 10] to be rolled or \"exit\" to come out of the game or \"restart\" to restart from the begining:");
			String val = sc.nextLine();
			if("exit".equalsIgnoreCase(val)) {
				break;
			} if("restart".equalsIgnoreCase(val)) {
				game.restartGame();
			} else {
				try {
					int rollVaue = Integer.parseInt(val);
					RESPONSE_CODE code = game.roll(rollVaue);
					if(code!=RESPONSE_CODE.SUCCESS) {
						System.out.println ("Invalid value entered:"+val);
					} else {
						System.out.println(game.getStatus());
					}
				}catch(Exception e) {
					System.out.println ("Invalid value entered:"+val);
				}
			}
		}
		System.out.println(game.getStatus());
		sc.close();
	}

}
