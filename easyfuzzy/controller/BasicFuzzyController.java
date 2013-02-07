/**
 * César Rosales http://www.codigoerrante.web44.com This class is released under
 * the:
 *
 * GNU Lesser General Public License (LGPL)
 * http://www.gnu.org/copyleft/lesser.html
 */
package easyfuzzy.controller;

import easyfuzzy.defuzzifyer.CentroidMethod;
import easyfuzzy.defuzzifyer.DefuzzyerMethod;
import easyfuzzy.rules.FuzzyTerm;
import easyfuzzy.rules.Rule;
import easyfuzzy.variables.LinguisticVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * BasicFuzzyController: Controller with the list of Variables and the methos to
 * fuzzify and defuzzify
 *
 * @author César Rosales
 * @version 0.1
 */
public class BasicFuzzyController extends FuzzyController {

    /*
     * DefuzzyerMethod
     */
    DefuzzyerMethod defMethod;
    /*
     * List of Variables
     */
    List<LinguisticVariable> variablesList;
    /*
     * list of rules.
     */
    List<Rule> rulesList;

    /**
     * Create a new instance of the BasicFuzzyController
     */
    public BasicFuzzyController() {
        variablesList = new ArrayList<>();
        rulesList = new ArrayList<>();
        defMethod = new CentroidMethod();
    }

    /**
     * add a new Variable to the Controller.
     *
     * @param lv the Variable to add.
     */
    public void addVariable(LinguisticVariable lv) {
        variablesList.add(lv);
    }

    /**
     * Set the DefuzzifyerMethod to the Controller. By default, this references
     * to an instance of CentroidMethod
     *
     * @param defMethod the metod to set.
     */
    public void setDefuzzifyerMethod(DefuzzyerMethod defMethod) {
        this.defMethod = defMethod;
    }

    /**
     * create and add a new Rule to the Controller.
     *
     * @param ant Antecedent of the Rule
     * @param con Consequent of the Rule
     */
    public void addRule(FuzzyTerm ant, FuzzyTerm con) {
        rulesList.add(new Rule(ant, con));
    }

    /**
     * fuzzify the variable desgined by the label with the given value
     *
     * @param label the label of the variable to fuzzify
     * @param val the input
     */
    public void fuzzify(String label, double val) {
        //System.out.println("FUZZIFYING...");
        for (LinguisticVariable lv : variablesList) {
            if (label.equalsIgnoreCase(lv.getLabel())) {
                lv.fuzziffy(val);
            }
        }
    }

     /**
     * defuzzify the variable desgined by the label
     *
     * @param label the label of the variable to fuzzify
     * @return the puntual value.
     */
    public double defuzzify(String label) {
        //System.out.println("DEFUZZIFYING...");
        setConfidencesOfConsequentsToZero();
        LinguisticVariable lv = null;

        for (LinguisticVariable l : variablesList) {
            if (l.getLabel().equalsIgnoreCase(label)) {
                lv = l;
            }
        }

        if (lv == null) {
            throw new UnsupportedOperationException("HACER NUEVA EXCEPCION PARA ESTO");
        } else {
            this.setConfidencesOfConsequentsToZero();
            for (Rule r : rulesList) {
                r.calculate();
            }
            //System.out.println(lv.getBestLabel());
            return defMethod.getDefuzziedValue(lv);
        }
    }
/**
 * Private method: used to defuzzify
 */
    private void setConfidencesOfConsequentsToZero() {
        for (Rule r : rulesList) {
            r.getConsequent().clearDOM();
        }
    }
}