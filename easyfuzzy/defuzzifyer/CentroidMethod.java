/**
 * César Rosales http://www.codigoerrante.web44.com This class is released under
 * the:
 *
 * GNU Lesser General Public License (LGPL)
 * http://www.gnu.org/copyleft/lesser.html
 */
package easyfuzzy.defuzzifyer;

import easyfuzzy.variables.FuzzySet;
import easyfuzzy.variables.LinguisticVariable;
import java.util.List;

/**
 * DefuzzyerMethod: Interface to the methods of defuzzying
 * @author César Rosales
 * @version 0.1
 */
public class CentroidMethod implements DefuzzyerMethod {
    /**
     * The list of sets to deffuzify
     */
    List<FuzzySet> setList;
     /**
     * The min value
     */
    double min;
    /**
     * The max value 
     */
    double max;
    /**
     * The number of samples
     */
    int s;
    /**
     * Create an instance of the Centroid Method
     */
    public CentroidMethod(){
        setList=null;
        min=0;
        max=0;
        s=10;
    }
    /**
     * Deffuzify the Value of the LinguisticVariable and return it.
     * @param lv The Linguistic Variable to defuzzify
     * @return the puntual value or crisp.
     */
    @Override
    public double getDefuzziedValue(LinguisticVariable lv) {
        setList=lv.getSetList();
        max=lv.getMax();
        min=lv.getMin();
        
        double jump=(max-min)/s;
        double sumAreas=0;
        double sumCont=0;
        
        double[] areas=new double[s];
        double[] values=new double[s];
        double flag=min+jump;
        for(int i=0; i<s; i++){
            for(FuzzySet fs:setList){
                areas[i]+=(fs.getClippedValue(flag));
            }
            values[i]+=flag;
            flag+=jump;
        }
        for(double d:areas){
            sumAreas+=d;
        }
        for(int i=0;i<s;i++){
            sumCont+=(values[i]*areas[i]);
        }
        return sumCont/sumAreas;
    }
    /**
     * Set the sample points to the Centroid Method
     * @param s 
     */
    public void setSamplesPoints(int s){
        this.s=s;
    }
    
}
