/**
 * César Rosales http://www.codigoerrante.web44.com This class is released under
 * the:
 *
 * GNU Lesser General Public License (LGPL)
 * http://www.gnu.org/copyleft/lesser.html
 */
package easyfuzzy.rules;

import java.io.Serializable;

/**
 * FuzzyTerm: Interface to create a Composite Pattern, a convenient method
 * to create the rules
 * @author César Rosales
 * @version 0.1
 */
public interface FuzzyTerm extends Serializable {

    double getDOM();

    void clearDOM();

    void orWithDOM(double val);
    
}
