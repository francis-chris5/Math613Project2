
package com.csfrancis555.chris_francis_math_613_project_2;

/**
 * A class to handle the SOR algorithm (w=1.2) with optional functionality to search out the optimal weight value for a given matrix
 * @author Chris Francis
 */
public class SuccessiveOverRelaxation {
    private Matrix A;
    private Vector b;

    
    /**
     * Constructor for the Ax = b equation to search for a solution of x with a SOR algorithm 
     * @param A the coefficient matrix
     * @param b the desired result matrix
     */
    public SuccessiveOverRelaxation(Matrix A, Vector b) {
        this.A = A;
        this.b = b;
    }//end 2-arg constructor
    
    
}//end SuccessiveOverRelaxation Class
