
package com.csfrancis555.chris_francis_math_613_project_2;

import java.util.HashMap;

/**
 * A class to handle the SOR algorithm (w=1.2) with optional functionality to search out the optimal weight value for a given matrix
 * @author Chris Francis
 */
public class SuccessiveOverRelaxation {
    private Matrix A;
    private Vector b;
    private double weights = 1.2;
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
        for(int i=0; i<v.getN(); i++){
            v.setValue(i, i+1);
        }
        v = approximate(v, weights);
    }//end 2-arg constructor

    
    
    public Vector getV() {
        return v;
    }//end getV()
    
    
    
    
    
    
    /**
     * Method to calculate a single iterate of the SOR process
     * @param x the current iterate approximate solution
     * @return the next iterate approximate solution
     */
    public Vector approximate(Vector x, double w){
        double metric = Double.MAX_VALUE;
        while(metric > e){
            for(int i=0; i<x.getN(); i++){
                x.setValue(i, b.getValue(i));
                for(int j=0; j<x.getN(); j++){
                    x.setValue(i, x.getValue(i) - A.getValue(i, j)*x.getValue(j));
                }
                x.setValue(i, x.getValue(i) + w * x.getValue(i) / A.getValue(i, i));
            }
            metric = norm(x);
        }
        return x;
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
    
}//end SuccessiveOverRelaxation Class
