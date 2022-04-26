/**
 *
 */
package primitives;

import static primitives.Util.*;

/**
 * This class will serve all primitive classes based on three numbers
 *
 * @author Dan Zilberstein
 */
public class Double3 {
	final double _d1;
	final double _d2;
	final double _d3;

	/**
	 * Zero triad (0,0,0)
	 */
	public static final Double3 ZERO = new Double3(0, 0, 0);

	/**
	 * Ones triad (1,1,1)
	 */
	public static final Double3 ONE = new Double3(1, 1, 1);

	/**
	 * Constructor to initialize Double3 based object with its three number values
	 *
	 * @param d1 first number value
	 * @param d2 second number value
	 * @param d3 third number value
	 */
	public Double3(double d1, double d2, double d3) {
		this._d1 = d1;
		this._d2 = d2;
		this._d3 = d3;
	}

	/**
	 * Constructor to initialize Double3 based object the same number values
	 *
	 * @param value number value for all 3 numbers
	 */
	public Double3(double value) {
		this._d1 = value;
		this._d2 = value;
		this._d3 = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Double3 other)
			return isZero(_d1 - other._d1) && isZero(_d2 - other._d2) && isZero(_d3 - other._d3);
		return false;
	}

	@Override
	public int hashCode() {
		return (int) Math.round(_d1 + _d2 + _d3);
	}

	@Override
	public String toString() {
		return "(" + _d1 + "," + _d2 + "," + _d3 + ")";
	}

	/**
	 * Sum two floating point triads into a new triad where each couple of numbers
	 * is summarized
	 *
	 * @param rhs right handle side operand for addition
	 * @return result of add
	 */
	public Double3 add(Double3 rhs) {
		return new Double3(_d1 + rhs._d1, _d2 + rhs._d2, _d3 + rhs._d3);
	}

	/**
	 * Subtract two floating point triads into a new triad where each couple of
	 * numbers is subtracted
	 *
	 * @param rhs right handle side operand for addition
	 * @return result of add
	 */
	public Double3 subtract(Double3 rhs) {
		return new Double3(_d1 - rhs._d1, _d2 - rhs._d2, _d3 - rhs._d3);
	}

	/**
	 * Scale (multiply) floating point triad by a number into a new triad where each
	 * number is multiplied by the number
	 *
	 * @param rhs right handle side operand for scaling
	 * @return result of scale
	 */
	public Double3 scale(double rhs) {
		return new Double3(_d1 * rhs, _d2 * rhs, _d3 * rhs);
	}

	/**
	 * Reduce (divide) floating point triad by a number into a new triad where each
	 * number is divided by the number
	 *
	 * @param rhs right handle side operand for reducing
	 * @return result of scale
	 */
	public Double3 reduce(double rhs) {
		return new Double3(_d1 / rhs, _d2 / rhs, _d3 / rhs);
	}

	/**
	 * Product two floating point triads into a new triad where each couple of
	 * numbers is multiplied
	 *
	 * @param rhs right handle side operand for product
	 * @return result of product
	 */
	public Double3 product(Double3 rhs) {
		return new Double3(_d1 * rhs._d1, _d2 * rhs._d2, _d3 * rhs._d3);
	}

	/**
	 * Checks whether all the numbers are lower than a test number
	 * @param k the test number
	 * @return true if all the numbers are less than k, false otherwise
	 */

	public boolean lowerThan(double k) {
		return _d1 < k && _d2 < k && _d3 < k;
	}

	/**
	 * checks if all the numbers in the double3 are greater than a given double
	 * @param k number to compare to
	 * @return true for greater than, false for not
	 */
	public boolean greaterThan(double k) {
		return _d1 > k && _d2 > k && _d3 > k;
	}

}
