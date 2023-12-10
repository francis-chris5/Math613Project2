
package com.csfrancis555.chris_francis_math_613_project_2;

/**
 * Main class to start the program which calculates a solution to Ax=b with the Successive Over-Relaxation Method
 * @author Chris Francis
 */
public class Chris_Francis_Math_613_Project_2 {

    /**
     * Main method
     * @param args command line arguments for starting the program
     */
    public static void main(String[] args) {
        Matrix A = new Matrix(4, 4);
        A.fillFromCSV("matrix.csv");
        Vector b = new Vector(4);
        b.fillFromCSV("vector.csv");
        
        SuccessiveOverRelaxation SOR = new SuccessiveOverRelaxation(A, b);
        
        System.out.println(SOR.getV());
        
    }//end main()
}//end Main Class
