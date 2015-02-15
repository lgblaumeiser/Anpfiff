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
	private int points;
	private int goalsShot;
	private int goalsReceived;
	private int won = 0;
	private int draw = 0;
	private int lost = 0;

	/**
	 * Constructor, sets the team and initializes the statistics members
	 *
	 * @param team
	 *            The team represented by this table entry
	 */
	public TableEntry(FootballTeam team) {
		this.team = team;
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
		return points;
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

	/**
	 * Adds a new result for the team
	 *
	 * @param goalsShot
	 *            Goals shot in the game
	 * @param goalsReceived
	 *            Goals received in the game
	 */
	public void setResult(Integer goalsShot, Integer goalsReceived) {
		this.goalsShot += goalsShot;
		this.goalsReceived += goalsReceived;
		final int currentpoints = computePoints(goalsShot, goalsReceived);
		switch (currentpoints) {
		case 3:
			won++;
			break;
		case 1:
			draw++;
			break;
		case 0:
			lost++;
			break;
		default:
			break;
		}
		points += currentpoints;
	}

	private Integer computePoints(Integer goalsShot, Integer goalsReceived) {
		final int comparison = goalsShot.compareTo(goalsReceived);
		if (comparison < 0) {
			return 0;
		}
		if (comparison > 0) {
			return 3;
		}
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return hash(team, points, goalsShot, goalsReceived);
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
			return equal(team, other.team) && equal(points, other.points) && equal(goalsShot, other.goalsShot)
					&& equal(goalsReceived, other.goalsReceived);
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
		entryString.append(points);
		entryString.append(" ");
		entryString.append(goalsShot);
		entryString.append(":");
		entryString.append(goalsReceived);
		entryString.append("\n");
		return entryString.toString();
	}
}
