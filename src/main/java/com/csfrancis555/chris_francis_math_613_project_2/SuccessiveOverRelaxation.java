
package com.csfrancis555.chris_francis_math_613_project_2;

import java.util.Arrays;
import java.util.Collections;

/**
 * A class to handle the SOR algorithm (w=1.2) with optional functionality to search out the optimal weight value for a given matrix
 * @author Chris Francis
 */
public class SuccessiveOverRelaxation {
    private Matrix A;
    private Vector b;
    private double[] weights = new double[5];
    private double optimal = 1.2;
    private Integer[] iterates = new Integer[5];
    private Vector v;
    private double e = 0.001;

    
    /**
     * Constructor for the Ax = b equation to search for a solution of x with a SOR algorithm 
     * @param A the coefficient matrix
     * @param b the desired result matrix
     */
    public SuccessiveOverRelaxation(Matrix A, Vector b) {
        this.A = A;
        this.b = b;
        this.v = new Vector(b.getN());
        updateSpread(1, 2);
        resetVector();
        
    }//end 2-arg constructor

    
    
    /**
     * resets the vector for the next pass at the SOR algorithm while searching for the optimal weight
     */
    private void resetVector(){
        for(int i=0; i<v.getN(); i++){
            v.setValue(i, i+1);
        }
    }//end resetVector()
    
    
    
    /**
     * method to reset the weights to be checking while looking for the optimal one
     * @param min the lower bound of the range the optimal weight falls in
     * @param max the upper bound of the range the optimal weight falls in
     */
    private void updateSpread(double min, double max){
        double spread = (max - min)/5;
        for(int i=0; i<weights.length; i++){
            weights[i] = min + i * spread;
        }
        for(int i=0; i<iterates.length; i++){
            iterates[i] = 0;
        }
    }//end updateSpread()
    
    
    /**
     * repeatedly calls the SOR algorithm method with 5 options for weights and counts which one takes the fewest iterates, and then squeezes the range to check five more weights, eventually arriving at an optimal weight, by default it squeezes the range five times
     */
    public void optimize(){
        for(int n=0; n<5; n++){
            resetVector();
            for(int i=0; i<weights.length; i++){
                iterates[i] = approximate(v, weights[i], 0);
            }
            int index = Collections.min(Arrays.asList(iterates));
            optimal = weights[index];
            if(optimal == weights[0]){
                updateSpread(weights[0], weights[1]);
            }
            else if(optimal == weights[weights.length-1]){
                updateSpread(weights[weights.length-2], weights[weights.length-1]);
            }
            else{
                updateSpread(weights[index-1], weights[index+1]);
            }
        }
    }//end optimize()
    
    
    
    
    /**
     * Method to calculate a single iterate of the SOR process
     * @param x the current iterate approximate solution
     * @return the next iterate approximate solution
     */
    private int approximate(Vector x, double w, int counter){
        double metric = Double.MAX_VALUE;
        while(metric > e){
            for(int i=0; i<x.getN(); i++){
                x.setValue(i, b.getValue(i));
                for(int j=0; j<x.getN(); j++){
                    x.setValue(i, x.getValue(i) - A.getValue(i, j)*x.getValue(i));
                }
                x.setValue(i, x.getValue(i) + w * x.getValue(i) / A.getValue(i, i));
            }
            metric = norm(x);
            counter++;
        }
        return counter;
    }//end approximate()
    
    
    
    /**
     * Calculates the 1-norm of the approximate solution ||b - Ax'||
     * @return the 1-norm
     */
    public double norm(Vector x){
        Vector Ax = x.matrixMultiply(A);
        Ax = Ax.scalarMultiply(-1);
        Vector bMinusAx = b.sum(Ax);
        double sum = 0;
        for(int i=0; i<bMinusAx.getN(); i++){
            sum += bMinusAx.getValue(i);
        }
        return sum;
    }//end norm()

    @Override
    public String toString() {
        return "The approximate solution is \n " + v + "\n and the optimal weight appears to be " + optimal;
    }
    
    
    
    
}//end SuccessiveOverRelaxation Class
