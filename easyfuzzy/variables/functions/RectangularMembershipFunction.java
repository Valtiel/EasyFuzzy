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
 *
 * @author César Rosales
 * @version 0.1
 * 
 */
public class RectangularMembershipFunction implements MembershipFunction {

    /**
     * The a point of the rectangle  
     */
    private double a;
    /**
     * The b point of the rectangle 
     */
    private double b;
    /**
     * The y point of the rectangle 
     */
    private double y;

    /**
     * 
     * @param a
     * @param b
     * @param y
     * @throws FunctionException 
     */
    public RectangularMembershipFunction(double a, double b, double y) throws FunctionException {
        this.a = a;
        this.b = b;
        if (!(y > 1)) {
            this.y = y;
        } else {
            throw new FunctionException("The y value must be < 1");
        }
    }

    /**
     * 
     * @param a
     * @param b 
     */
    public RectangularMembershipFunction(double a, double b) {
        this.a = a;
        this.b = b;
        this.y = 1;
    }
    /**
     * @param input the value x of f(x). 
     * @return double the result of the function.
     */
    @Override
    public double getValue(double input) {
        if (input >= a && input <= b) {
            return y;
        } else {
            return 0;
        }
    }
    /**
     * 
     * @param input the value x of f(x). 
     * @param clip the value to clip: if result > clip then return clip.
     * @return double the result of the function if it is under clip,
     * else return the clip value
     */
    @Override
    public double getClippedValue(double input, double clip) {
        if (input >= a && input <= b) {
            if (input < clip) {
                return y;
            } else {
                return clip;
            }
        } else {
            return 0;
        }
    }
 /**
     * @return the maximum (not infinity) value of the function, 
     * warning
     */
    @Override
    public double getMax() {
        return b;
    }
   /**
     * @return the minimum (not infinity) value of the function, 
     * warning
     */
    @Override
    public double getMin() {
        return a;
    }
}
