
package com.csfrancis555.chris_francis_math_613_project_2;

import java.util.HashMap;


/**
 * Class to handle LDR Decomposition for the Jacobi Method
 * @author Chris Francis
 */
public class LDRDecomposition {
    
    ///////////////////////////  DATA FIELDS  //////////////////////////
    private int m;
    private int n;
    private Matrix L;
    private Matrix D;
    private Matrix R;
    private Matrix inverseD;
    private HashMap<String, Matrix> matrices = new HashMap<>();
    
    
    //////////////////////////  CONSTRUCTORS  //////////////////////////

    /**
     * Decomposes a matrix into the L, D, R, and inverse D matrices and creates a key-value mapping collection
     * @param A the matrix to be decomposed
     */
    public LDRDecomposition(Matrix A) {
        double[][] zeros = new double[A.getM()][A.getN()];
        for(int i=0; i<A.getMatrix().length; i++){
            for(int j=0; j<A.getMatrix()[i].length; j++){
                zeros[i][j] = 0;
            }
        }
        L = new Matrix(zeros);
        D = new Matrix(zeros);
        R = new Matrix(zeros);
        inverseD = new Matrix(zeros);
        for(int i=0; i<A.getMatrix().length; i++){
            for(int j=0; j<A.getMatrix()[i].length; j++){
                if(i < j){
                    L.setValue(i, j, A.getValue(i, j));
                }
                else if(i > j){
                    R.setValue(i, j, A.getValue(i, j));
                }
                else if(i == j){
                    D.setValue(i, j, A.getValue(i, j));
                }
            }
        }
        for(int i=0; i<D.getMatrix().length; i++){
            for(int j=0; j<D.getMatrix()[i].length; j++){
                if(D.getValue(i, j) != 0){
                    D.setValue(i, j, 1.0/D.getValue(i,j));
                }
            }
        }
        matrices.put("L", L);
        matrices.put("D", D);
        matrices.put("R", R);
        matrices.put("inverseD", inverseD);
    }//end 1-arg constructor
    
    
    
    /////////////////////////////////////////  GETTERS & SETTERS  /////////////////

    /**
     * Returns a key-value mapping of the "L", "D", "R", and "inverseD" matrices
     * @return 
     */
    public HashMap<String, Matrix> getMatrices() {
        return matrices;
    }//end getMatrices()
    
}//end LDRDecomposition class
