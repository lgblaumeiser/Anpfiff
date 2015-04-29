/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.model;

/**
 * The condition shape reduction of a game is represented by this enumeration
 *
 * @author Lars Geyer-Blaumeiser
 */
public enum ShapeReduction {
	LOW(1), MEDIUM(2), HIGH(3);

	private final int reductionValue;

	ShapeReduction(int reductionValue) {
		this.reductionValue = reductionValue;
	}

	int getShapeReductionValuePerPlayer() {
		return reductionValue;
	}

	int getShapeReductionValuePerTeam() {
		return 11 * reductionValue;
	}
}
