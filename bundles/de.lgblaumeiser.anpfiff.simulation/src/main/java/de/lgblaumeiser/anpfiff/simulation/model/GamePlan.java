/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.model;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.hash;

import java.util.List;

/**
 * This class contains foreach game days the list of games identifying the teams
 * which play agains each other.
 *
 * @author Lars Geyer-Blaumeiser
 *
 */
public class GamePlan {
	final List<List<Game>> gameTable;

	public GamePlan(final List<List<Game>> gameTable) {
		checkNotNull(gameTable);
		checkArgument(gameTable.size() == SeasonConstants.NUMBER_OF_GAME_DAYS);
		for (final List<Game> gameDay : gameTable) {
			checkArgument(gameDay.size() == SeasonConstants.NUMBER_OF_GAMES_PER_DAY);
		}
		this.gameTable = gameTable;
	}

	public List<Game> getGameDay(final int index) {
		checkArgument(index > 0 && index <= SeasonConstants.NUMBER_OF_GAME_DAYS);
		return gameTable.get(index - 1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return hash(gameTable);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GamePlan) {
			return equal(gameTable, ((GamePlan) obj).gameTable);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder gameTableString = new StringBuilder(10000);
		gameTableString.append("Game Plan\n");
		int day = 1;
		for (final List<Game> currentGameDay : gameTable) {
			gameTableString.append("Day ");
			gameTableString.append(day);
			gameTableString.append(":\n");
			for (final Game currentGame : currentGameDay) {
				gameTableString.append(currentGame);
				gameTableString.append("\n");
			}
			day++;
		}
		return gameTableString.toString();
	}

}
