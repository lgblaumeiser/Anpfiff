/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

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
		checkArgument(gameTable.size() == 34);
		for (final List<Game> gameDay : gameTable) {
			checkArgument(gameDay.size() == 9);
		}
		this.gameTable = gameTable;
	}

	public List<Game> getGameDay(final int index) {
		checkArgument(index >= 0 && index < 34);
		return gameTable.get(index);
	}

}
