/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.model;

import static java.util.Objects.hash;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Class that stores the table of a season after a game day subsuming the
 * results so far
 *
 * @author Lars Geyer-Blaumeiser
 */
public class Table {
	List<TableEntry> table = Lists.newArrayListWithCapacity(SeasonConstants.NUMBER_OF_TEAMS);

	/**
	 * Constructor, creates an initial table based on the sequence of the teams
	 *
	 * @param teams
	 *            The list of teams for the season
	 */
	public Table(List<FootballTeam> teams) {
		for (final FootballTeam team : teams) {
			table.add(new TableEntry(team));
		}
	}

	/**
	 * @return Return the table as an iterable object starting from the table
	 *         leader to the last in the table
	 */
	public Iterable<TableEntry> getTeamIterator() {
		return Collections.unmodifiableList(table);
	}

	/**
	 * Sets the results of a game day so that the table is updated
	 *
	 * @param results
	 *            List of game results
	 */
	public void setResults(List<GameResult> results) {
		final Map<FootballTeam, Integer[]> nameToResultMapping = Maps
				.newHashMapWithExpectedSize(SeasonConstants.NUMBER_OF_TEAMS);
		for (final GameResult result : results) {
			nameToResultMapping.put(result.getHometeam(),
					new Integer[] { result.getHometeamgoals(), result.getGuestteamgoals() });
			nameToResultMapping.put(result.getGuestteam(),
					new Integer[] { result.getGuestteamgoals(), result.getHometeamgoals() });
		}
		for (final TableEntry currentEntry : table) {
			final Integer[] result = nameToResultMapping.get(currentEntry.getTeam());
			currentEntry.setResult(result[0], result[1]);
		}
		table = table.stream().sorted((e1, e2) -> {
			final int comparison = Integer.compare(e1.getPoints(), e2.getPoints());
			if (comparison != 0) {
				return -1 * comparison;
			}
			return -1 * Integer.compare(e1.getGoalDifference(), e2.getGoalDifference());
		}).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		return hash(table);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(final Object obj) {
		if (obj instanceof Table) {
			final Table other = (Table) obj;
			return table.equals(other.table);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		final StringBuilder tableString = new StringBuilder(10000);
		tableString.append("Table:\n");
		int position = 1;
		for (final TableEntry currentEntry : table) {
			tableString.append(position);
			tableString.append(". ");
			tableString.append(currentEntry.getTeam().getName());
			tableString.append(" ");
			tableString.append(currentEntry.getPoints());
			tableString.append(" ");
			tableString.append(currentEntry.getWon());
			tableString.append(" ");
			tableString.append(currentEntry.getDraw());
			tableString.append(" ");
			tableString.append(currentEntry.getLost());
			tableString.append(" ");
			tableString.append(currentEntry.getGoalsShot());
			tableString.append(":");
			tableString.append(currentEntry.getGoalsReceived());
			tableString.append("\n");
			position++;
		}
		return tableString.toString();
	}
}
