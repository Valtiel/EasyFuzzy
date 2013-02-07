/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package easyfuzzy;

import easyfuzzy.controller.BasicFuzzyController;
import easyfuzzy.controller.FuzzyOp;
import easyfuzzy.defuzzifyer.CentroidMethod;
import easyfuzzy.rules.FuzzyTerm;
import easyfuzzy.rules.comparator.FuzzyAND;
import easyfuzzy.rules.modifier.FzSet;
import easyfuzzy.variables.FuzzySet;
import easyfuzzy.variables.IllegalSetException;
import easyfuzzy.variables.LinguisticVariable;
import easyfuzzy.variables.functions.FunctionException;
import easyfuzzy.variables.functions.TrapezoidalMembershipFunction;
import easyfuzzy.variables.functions.TriangularMembershipFunction;
import javax.swing.plaf.basic.BasicArrowButton;

/**
 *
 * @author clases
 */
public class EasyFuzzy {

    private FzSet targetclose;
    private FzSet targetmedium;
    private FzSet targetfar;
    private FzSet desirable;
    private FzSet undesirable;
    private FzSet verydesirable;
    private FzSet loads;
    private FzSet okey;
    private FzSet low;

    public void createVariables(BasicFuzzyController bfc) throws FunctionException {
        try {

            LinguisticVariable lv = new LinguisticVariable("DISTANCE");
            targetclose = lv.addSet("targetclose", new TrapezoidalMembershipFunction(0, 0, 25, 50));
            targetmedium = lv.addSet("targetmedium", new TriangularMembershipFunction(25, 300, 150));
            targetfar = lv.addSet("targetfar", new TrapezoidalMembershipFunction(150, 300, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));
            bfc.addVariable(lv);

            LinguisticVariable am = new LinguisticVariable("AMMOSTATUS");
            low = am.addSet("low", new TriangularMembershipFunction(0, 10, 0));
            okey = am.addSet("okey", new TriangularMembershipFunction(0, 30, 10));
            loads = am.addSet("loads", new TrapezoidalMembershipFunction(10, 30, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));
            bfc.addVariable(am);

            LinguisticVariable ds = new LinguisticVariable("DESIRABILITY");
            undesirable = ds.addSet("undesirable", new TrapezoidalMembershipFunction(0, 0, 25, 50));
            desirable = ds.addSet("desirable", new TriangularMembershipFunction(25, 75, 50));
            verydesirable = ds.addSet("verydesirable", new TrapezoidalMembershipFunction(50, 75, 100, Double.POSITIVE_INFINITY));
            bfc.addVariable(ds);

            System.out.println("VARIABLES CREATED!");
        } catch (IllegalSetException ex) {
            ex.printStackTrace();
        }
    }

    public void createRules(BasicFuzzyController bfc) {
        bfc.addRule(FuzzyOp.and(targetfar, loads), desirable);
        bfc.addRule(FuzzyOp.and(targetfar, okey), undesirable);
        bfc.addRule(FuzzyOp.and(targetfar, low), undesirable);
        bfc.addRule(FuzzyOp.and(targetmedium, loads), verydesirable);
        bfc.addRule(FuzzyOp.and(targetmedium, okey), verydesirable);
        bfc.addRule(FuzzyOp.and(targetmedium, low), desirable);
        bfc.addRule(FuzzyOp.and(targetclose, loads), undesirable);
        bfc.addRule(FuzzyOp.and(targetclose, okey), undesirable);
        bfc.addRule(FuzzyOp.and(targetclose, low), undesirable);
        // bfc.addRule(FuzzyOp.or(average, clever), desirable);
        System.out.println("RULES CREATED!");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FunctionException {
        BasicFuzzyController bfc = new BasicFuzzyController();
        EasyFuzzy ef = new EasyFuzzy();
        ef.createVariables(bfc);
        ef.createRules(bfc);
        bfc.fuzzify("DISTANCE", 150);
        bfc.fuzzify("AMMOSTATUS", 80);
        CentroidMethod cm=new CentroidMethod();
        cm.setSamplesPoints(10);
        bfc.setDefuzzifyerMethod(cm);
        //System.out.println("TEST: "+ef..getDOM());
        System.out.println("DEFUZZIFYED VALUE: " + bfc.defuzzify("DESIRABILITY"));
    }
}
