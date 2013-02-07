/**
 * César Rosales http://www.codigoerrante.web44.com This class is released under
 * the:
 *
 * GNU Lesser General Public License (LGPL)
 * http://www.gnu.org/copyleft/lesser.html
 */
package easyfuzzy.variables;

import easyfuzzy.rules.modifier.FzSet;
import easyfuzzy.variables.functions.FunctionException;
import easyfuzzy.variables.functions.MembershipFunction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import easyfuzzy.variables.functions.TriangularMembershipFunction;

/**
 * LinguisticVariable:
 *
 * @author César Rosales
 * @version 0.1
 */
public class LinguisticVariable {

    /**
     * List with all the Sets of the Variable
     */
    private List<FuzzySet> setList;
    /**
     * Label or the name of the variable
     */
    private String label;

    /**
     * Creates a new instance of
     * <code>LiguisticVariable</code> class
     *
     * @param label the label of the Variable
     */
    public LinguisticVariable(String label) {
        this.label = label;
        setList = new ArrayList<>();
    }

    /**
     *
     * Add a FuzzySet to the LinguisticVariable Object, also return a FzSet
     * reference FzSetReferences is used to create the Rules.
     *
     * @param set
     * @return FzSet reference, act as a proxy of the FuzzySet instance, used to
     * create the Rules
     * @throws IllegalSetException when the set is already in the Variable
     */
    public FzSet addSet(FuzzySet set) throws IllegalSetException {
        for (FuzzySet fs : setList) {
            if (set.equals(fs)) {
                throw new IllegalSetException("The label " + set.getLabel() + ""
                        + " is already registered");
            }
        }
        setList.add(set);
        return new FzSet(set);
    }

    /**
     * Remove a set of the Variable given the label
     *
     * @param label the label of the set to be removed
     * @return true if the operation is successfull, false otherwise
     * @throws IllegalSetException
     */
    public boolean removeSet(String label) throws IllegalSetException {
        for (FuzzySet fs : setList) {
            if (fs.getLabel().equalsIgnoreCase(label)) {
                setList.remove(fs);
                return true;
            }
        }
        return false;
    }

    /**
     * Fuzzify the sets of the Variable given an input.
     *
     * @param input the input
     */
    public void fuzziffy(double input) {
        for (FuzzySet fs : setList) {
            fs.calculateDOM(input);
        }
    }

    /**
     * @return the label of the set with the highest degree of membership
     */
    public String getBestLabel() {
        double flag = 0;
        String labelFlag = "";
        for (FuzzySet fs : setList) {
            double value = fs.getDOM();
            if (value > flag) {
                labelFlag = fs.getLabel();
            }
        }
        return labelFlag;
    }

    /**
     * @return the setList
     */
    public List<FuzzySet> getSetList() {
        return setList;
    }

    /**
     * @param setList the setList to set
     */
    public void setSetList(List<FuzzySet> setList) {
        this.setList = setList;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     *
     * @param input
     * @return the Label of the Set with higher results.
     */
    public String getBestFuzzyValue(double input) {
        double flag = 0;
        String labelFlag = "";
        for (FuzzySet fs : setList) {
            double value = fs.getNormalValue(input);
            if (value > flag) {
                labelFlag = fs.getLabel();
            }
        }
        return labelFlag;
    }

    public Map<String, Double> getNormalValues(double input) {
        HashMap<String, Double> values = new HashMap<>();
        for (FuzzySet fs : setList) {
            values.put(fs.getLabel(), fs.getNormalValue(input));
        }
        return values;
    }

    public Map<String, Double> getFairlyValues(double input) {
        HashMap<String, Double> values = new HashMap<>();
        for (FuzzySet fs : setList) {
            values.put(fs.getLabel(), fs.getFairlyValue(input));
        }
        return values;
    }

    public Map<String, Double> getVeryValues(double input) {
        HashMap<String, Double> values = new HashMap<>();
        for (FuzzySet fs : setList) {
            values.put(fs.getLabel(), fs.getVeryValue(input));
        }
        return values;
    }

    /**
     *
     * Add a FuzzySet to the LinguisticVariable Object, also return a FzSet
     * reference FzSetReferences is used to create the Rules.
     *
     * @param label the label of the set
     * @param MembershipFunction the function of the set
     * @return FzSet reference, act as a proxy of the FuzzySet instance, used to
     * create the Rules
     * @throws IllegalSetException when the set is already in the Variable
     */
    public FzSet addSet(String setLabel, MembershipFunction MembershipFunction) throws IllegalSetException {
        FuzzySet set = new FuzzySet(setLabel, MembershipFunction);
        for (FuzzySet fs : setList) {
            if (set.equals(fs)) {
                throw new IllegalSetException("The label " + set.getLabel() + ""
                        + " is already registered");
            }
        }

        setList.add(set);
        return new FzSet(set);

    }

    /**
     * @return The maximum (non infinity) value. Used to defuzzify proccess
     */
    public double getMax() {
        double flag = 0;
        double max = 0;
        for (FuzzySet fs : setList) {
            max = fs.getMembershipFunction().getMax();
            if (flag < max) {
                flag = max;
            }
        }
        return flag;
    }

    /**
     *
     * @return The minimum (non infinity) value. Used to defuzzify proccess
     */
    public double getMin() {
        double flag = 0;
        double min = 0;
        for (FuzzySet fs : setList) {
            min = fs.getMembershipFunction().getMin();
            if (flag > min) {
                flag = min;
            }
        }
        return flag;
    }
}
