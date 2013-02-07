/**
 * César Rosales http://www.codigoerrante.web44.com This class is released under
 * the:
 *
 * GNU Lesser General Public License (LGPL)
 * http://www.gnu.org/copyleft/lesser.html
 */
package easyfuzzy.defuzzifyer;

import easyfuzzy.variables.LinguisticVariable;

/**
 * DefuzzyerMethod: Interface to the methods of defuzzying
 * @author César Rosales
 * @version 0.1
 */
public interface DefuzzyerMethod {

    public double getDefuzziedValue(LinguisticVariable lv);
}
