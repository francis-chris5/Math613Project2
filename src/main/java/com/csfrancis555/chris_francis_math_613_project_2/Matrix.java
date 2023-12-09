
package com.csfrancis555.chris_francis_math_613_project_2;

import java.io.File;
import java.util.Random;
import java.util.Scanner;


/**
 * The matrix objects to be used in this project
 * @author Chris Francis
 */
public class Matrix {
    
    //////////////////////////////////////////  DATAFIELDS  /////////////////////////
    private int m;
    private int n;
    private double[][] matrix;

    
    
    
    ////////////////////////////////////////  CONSTRUCTORS  /////////////////////
    
    /**
     * Creates an empty matrix object to be filled later with either random values between 1 and 0 or loaded in from a .csv file (standard for sensor data collection)
     * @param m the number of rows in the matrix
     * @param n the number of columns in the matrix
     */
    public Matrix(int m, int n) {
        this.m = m;
        this.n = n;
        matrix = new double[m][n];
    }//end 2-arg constructor

    
    
    /**
     * creates a matrix object from a 2D array
     * @param matrix the 2D array to turn into a matrix object
     */
    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }//end 1-arg constructor
    
    

    
    
    
    
    //////////////////////////////////////////  GETTERS & SETTERS  //////////////////////
    
    /**
     * the number of rows in the matrix
     * @return 
     */
    public int getM() {
        return m;
    }//end getM()

    
    /**
     * the number of columns in the matrix
     * @return 
     */
    public int getN() {
        return n;
    }//end getN()
    
    
    /**
     * Request a particular value from the matrix based on its position
     * @param i the row number of the desired value in the matrix
     * @param j the column number of the desired value in the matrix
     * @return the desired value from the matrix
     */
    public double getValue(int i, int j){
        return matrix[i][j];
    }//end getValue()
    
    
    /**
     * Alters a particular value in the matrix based on its position
     * @param i the row number of the value to change
     * @param j the column number of the value to change
     * @param value the desired value to put in the matrix
     */
    public void setValue(int i, int j, double value){
        this.matrix[i][j] = value;
    }//end setValue()

    
    /**
     * the entire matrix as a 2D array
     * @return 
     */
    public double[][] getMatrix() {
        return matrix;
    }//end getMatrix()
    
    
    
    
    
    
    
    ///////////////////////////////////////////////////////  OPERATIONS  ////////////////
    
    /**
     * Multiply by placing another matrix to the right
     * @param B the other matrix
     * @return the product of this matrix with the other one on the right if they're compatible, null otherwise
     */
    public Matrix rightMultiply(Matrix B){
        Matrix C;
        if(this.getN() == B.getM()){
            C = new Matrix(this.getM(), this.getN());
            for(int i=0; i<C.getMatrix().length; i++){
                for(int j=0; j<C.getMatrix()[i].length; j++){
                    C.setValue(i, j, 0);
                }
            }
            for(int i=0; i<this.getMatrix().length; i++){
                for(int j=0; j<this.getMatrix()[i].length; j++){
                    for(int k=0; k<this.getMatrix()[i].length; k++){
                        C.setValue(i, j, C.getValue(i, j) + this.getValue(i, k) * B.getValue(k, j));
                    }
                }
            }
        }
        else{
            C = null;
        }
        return C;
    }//end rightMultiply()
    
    
    /**
     * Multiply by placing another matrix to the left
     * @param B the other matrix
     * @return the product of this matrix with the other one on the left if they're compatible, null otherwise
     */
    public Matrix leftMultiply(Matrix B){
        Matrix C;
        if(B.getN() == this.getM()){
            C = new Matrix(this.getM(), this.getN());
            for(int i=0; i<C.getMatrix().length; i++){
                for(int j=0; j<C.getMatrix()[i].length; j++){
                    C.setValue(i, j, 0);
                }
            }
            for(int i=0; i<this.getMatrix().length; i++){
                for(int j=0; j<this.getMatrix()[i].length; j++){
                    for(int k=0; k<this.getMatrix()[i].length; k++){
                        C.setValue(i, j, C.getValue(i, j) + B.getValue(i, k) * this.getValue(k, j));
                    }
                }
            }
        }
        else{
            C = null;
        }
        return C;
    }//end leftMultiply()
    
    
    /**
     * multiply this matrix by a scalar value
     * @param s the scalar to multiply by
     * @return the scaled matrix
     */
    public Matrix scalarMultiply(double s){
        Matrix C = new Matrix(this.getM(), this.getN());
        for(int i=0; i<this.getMatrix().length; i++){
            for(int j=0; j<this.getMatrix()[i].length; j++){
                C.setValue(i, j, this.getMatrix()[i][j] * s);
            }
        }
        return C;
    }//end scalarMultiply()
    
    
    /**
     * find the sum of this matrix with another appropriately sized matrix
     * @param B the other matrix to add to this one
     * @return a new matrix with the summed values if they're the same size, null otherwise
     */
    public Matrix sum(Matrix B){
        Matrix C;
        if(this.getN() == B.getN() && this.getM() == B.getM()){
            C = new Matrix(this.getM(), this.getN());
            for(int i=0; i<this.getMatrix().length; i++){
                for(int j=0; j<this.getMatrix()[i].length; j++){
                    C.setValue(i, j, this.getValue(i, j) + B.getValue(i, j));
                }
            }
        }
        else{
            C = null;
        }
        return C;
    }//end sum()
    
    
    
    /**
     * finds the transpose of the Matrix
     * @return the transpose of the matrix
     */
    public Matrix transpose(){
        Matrix T = new Matrix(this.getM(), this.getN());
        for(int i=0; i<this.getM(); i++){
            for(int j=0; j<this.getN(); j++){
                T.setValue(j , i, this.getValue(i, j));
            }
        }
        return T;
    }//end transpose()
    
    
    
    
    
    
    
    /////////////////////////////////////////////////  FILL MATRIX VALUES  ////////////////////
    
    
    /**
     * Fills this matrix with random values between 0 and 1
     */
    public void randomFill(){
        Random rand = new Random();
        for(int i=0; i<this.getMatrix().length; i++){
            for(int j=0; j<this.getMatrix()[i].length; j++){
                this.setValue(i, j, rand.nextDouble());
            }
        }
    }//end randomFill()
    
    
    /**
     * In a realistic situation sensors would collect the massive amounts of data and store it in a way that could typically be easily exported as a .csv file, for test cases in class creating the .csv file in Excel seems reasonable.
     * In the event of a file reading error it will call the randomFill() method instead
     * @param filepath the filepath or URL where the .csv file is to be found
     */
    public void fillFromCSV(String filepath){
        File csv = new File(filepath);
        try{
            Scanner fromFile = new Scanner(csv);
            int i=0, j=0;
            while(fromFile.hasNextLine()){
                j=0;
                String line = fromFile.nextLine();
                Scanner lineReader = new Scanner(line);
                lineReader.useDelimiter(",");
                while(lineReader.hasNextDouble()){
                    this.setValue(i, j, lineReader.nextDouble());
                    j++;
                }
                i++;
            }
        }
        catch(Exception e){
            this.randomFill();
        }
    }//end fillFromCSV()
    
    
    
    /**
     * Class method to return an identity matrix of a desired size
     * @param m the number of rows
     * @param n the number of columns
     * @return 
     */
    public static Matrix identity(int m, int n){
        double[][] temp = new double[m][n];
        for(int i=0; i<temp.length; i++){
            for(int j=0; j<temp.length; j++){
                if(i == j){
                    temp[i][j] = 1;
                }
                else{
                    temp[i][j] = 0;
                }
            }
        }
        return new Matrix(temp);
    }//end identity()
    
    
    
    
    
    
    
    ///////////////////////////////////////////////  OVERRIDES  ////////////////////////

    /**
     * a console/terminal friendly textual representation of this matrix
     * @return 
     */
    @Override
    public String toString() {
        String representation = new String("\n");
        for(int i=0; i<this.getMatrix().length; i++){
            for(int j=0; j<this.getMatrix()[i].length; j++){
                representation += this.getValue(i, j) + " ";
            }
            representation += "\n";
        }
        return representation;
    }//end toString()

    
    
    /**
     * compares to see if two matrices are equal
     * @param obj the other matrix to compare to
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        try{
            Matrix B = (Matrix)obj;
            if(this.getN() != B.getN() || this.getM() != B.getM()){
                return false;
            }
            boolean checking = true;
            for(int i=0; i<this.getMatrix().length; i++){
                for(int j=0; j<this.getMatrix()[i].length; j++){
                    if(this.getValue(i, j) != B.getValue(i, j)){
                        checking = false;
                    }
                }
            }
            return checking;
        }
        catch(Exception e){
            return false;
        }
    }//end equals()
    
    
    
}//end Matrix class
