/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package easyfuzzy.rules.comparator;

import easyfuzzy.rules.FuzzyTerm;

/**
 *
 * @author Root
 */
public class FuzzyNOT implements FuzzyTerm {

    FuzzyTerm term;

    public FuzzyNOT(FuzzyTerm term) {
        this.term = term;
    }

    @Override
    public double getDOM() {
        return 1 - term.getDOM();
    }

    @Override
    public void clearDOM() {
        term.clearDOM();
    }

    @Override
    public void orWithDOM(double val) {
        term.orWithDOM(val);
    }
}
