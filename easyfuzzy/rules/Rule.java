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
 * Rule: This class contain references of FuzzyTerms interfaces to create the 
 * rules. 
 * @author César Rosales
 * @version 0.1
 */
public class Rule implements Serializable {

    private FuzzyTerm antecedent;
    private FuzzyTerm consequent;
/**
 * 
 * Construct a Rule. 
 * @param antecedent the antecedent of the Rule 
 * @param consequent the consequent of the Rule
 */
    public Rule(FuzzyTerm antecedent, FuzzyTerm consequent) {
        this.antecedent = antecedent;
        this.consequent = consequent;
    }
/**
 * Calculate the consequent of the rule, based on the degrees of the antecedent.
 */
    public void calculate() {
        //System.out.println("RULE "+antecedent.getDOM());
        consequent.orWithDOM(getAntecedent().getDOM());
    }

    /**
     * @return the antecedent
     */
    public FuzzyTerm getAntecedent() {
        return antecedent;
    }

    /**
     * @param antecedent the antecedent to set
     */
    public void setAntecedent(FuzzyTerm antecedent) {
        this.antecedent = antecedent;
    }

    /**
     * @return the consequent
     */
    public FuzzyTerm getConsequent() {
        return consequent;
    }

    /**
     * @param consequent the consequent to set
     */
    public void setConsequent(FuzzyTerm consequent) {
        this.consequent = consequent;
    }
}
