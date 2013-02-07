/**
 * César Rosales http://www.codigoerrante.web44.com This class is released under
 * the:
 *
 * GNU Lesser General Public License (LGPL)
 * http://www.gnu.org/copyleft/lesser.html
 */
package easyfuzzy.controller;

import easyfuzzy.rules.FuzzyTerm;
import easyfuzzy.rules.comparator.FuzzyAND;
import easyfuzzy.rules.comparator.FuzzyNOT;
import easyfuzzy.rules.comparator.FuzzyOR;

/**
 * FuzzyOp: Class that contains static methods with the Fuzzy Operators
 * @author César Rosales
 * @version 0.1
 */
public class FuzzyOp {

    public static double and(double a, double b) {
        return Math.min(a, b);
    }

    public static FuzzyTerm and(FuzzyTerm a, FuzzyTerm b) {
        return new FuzzyAND(a, b);
    }

    public static FuzzyTerm or(FuzzyTerm a, FuzzyTerm b) {
        return new FuzzyOR(a, b);
    }

    public static FuzzyTerm not(FuzzyTerm a) {
        return new FuzzyNOT(a);
    }

    public static double or(double a, double b) {
        return Math.max(a, b);
    }

    public static double not(double a) {
        return 1 - a;
    }
}
