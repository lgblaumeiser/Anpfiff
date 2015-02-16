/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.model;

import static com.google.common.base.Objects.equal;
import static java.util.Objects.hash;

/**
 * An entry in the table with all necessary information
 *
 * @author Lars Geyer-Blaumeiser
 */
public class TableEntry {
	private final FootballTeam team;
	private final int goalsShot;
	private final int goalsReceived;
	private final int won;
	private final int draw;
	private final int lost;;

	/**
	 * Constructor
	 *
	 * @param team
	 *            The team represented by this entry
	 * @param goalsShot
	 *            The number of goals shot so far in the season
	 * @param goalsReceived
	 *            The number of goals received so far in the season
	 * @param won
	 *            The number of games won so far in the season
	 * @param draw
	 *            The number of games which were a draw so far in the season
	 * @param lost
	 *            The number of games lost so far in the season
	 */
	public TableEntry(FootballTeam team, int goalsShot, int goalsReceived, int won, int draw, int lost) {
		this.team = team;
		this.goalsShot = goalsShot;
		this.goalsReceived = goalsReceived;
		this.won = won;
		this.draw = draw;
		this.lost = lost;
	}

	/**
	 * @return The team which fills the place in the table represented by this
	 *         table entry
	 */
	public FootballTeam getTeam() {
		return team;
	}

	/**
	 * @return The points won so far by the team on the place in the table
	 *         represented by this table entry
	 */
	public int getPoints() {
		return won * 3 + draw;
	}

	/**
	 * @return The goals shot during the season so far by this team
	 */
	public int getGoalsShot() {
		return goalsShot;
	}

	/**
	 * @return The goals received during the season so far by this team
	 */
	public int getGoalsReceived() {
		return goalsReceived;
	}

	/**
	 * @return Returns the goals difference between goals shot and received
	 */
	public int getGoalDifference() {
		return goalsShot - goalsReceived;
	}

	/**
	 * @return the won
	 */
	public final int getWon() {
		return won;
	}

	/**
	 * @return the draw
	 */
	public final int getDraw() {
		return draw;
	}

	/**
	 * @return the lost
	 */
	public final int getLost() {
		return lost;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return hash(team, goalsShot, goalsReceived, won, draw, lost);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TableEntry) {
			final TableEntry other = (TableEntry) obj;
			return equal(team, other.team) && equal(goalsShot, other.goalsShot)
					&& equal(goalsReceived, other.goalsReceived) && equal(won, other.won) && equal(draw, other.draw)
					&& equal(lost, other.lost);
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
		final StringBuilder entryString = new StringBuilder();
		entryString.append(team.getName());
		entryString.append(" ");
		entryString.append(getPoints());
		entryString.append(" ");
		entryString.append(getGoalDifference());
		entryString.append(" ");
		entryString.append(goalsShot);
		entryString.append(":");
		entryString.append(goalsReceived);
		entryString.append(" ");
		entryString.append(won);
		entryString.append(" ");
		entryString.append(draw);
		entryString.append(" ");
		entryString.append(lost);
		entryString.append("\n");
		return entryString.toString();
	}
}
