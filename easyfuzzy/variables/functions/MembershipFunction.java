/**
 * César Rosales
 * http://www.codigoerrante.web44.com
 *   
 * This class is released under the:
 * GNU Lesser General Public License (LGPL)
 * http://www.gnu.org/copyleft/lesser.html
 */
package easyfuzzy.variables.functions;

/**
 * MembershipFunction: This interface must be implemented
 * by the class to be part of a FuzzySet Object as his 
 * membership function.
 * @author César Rosales 
 * @version 0.1
 */
public interface MembershipFunction {

    /**
     * @param input the value x of f(x). 
     * @return double the result of the function.
     */
    double getValue(double input);

    /**
     * 
     * @param input the value x of f(x). 
     * @param clip the value to clip: if result > clip then return clip.
     * @return double the result of the function if it is under clip,
     * else return the clip value
     */
    double getClippedValue(double input, double clip);

    /**
     * 
     * @return the maximum (not infinity) value of the function. 
     */
    public double getMax();

    /**
     * 
     * @return the minimum (not infinity) value of the function. 
     */
    public double getMin();
}
