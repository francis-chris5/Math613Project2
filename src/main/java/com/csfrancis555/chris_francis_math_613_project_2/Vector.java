
package com.csfrancis555.chris_francis_math_613_project_2;

import java.io.File;
import java.util.Random;
import java.util.Scanner;


/**
 * The vector objects to be used in this project
 * @author Chris Francis
 */
public class Vector {
    
    ///////////////////////////////////////////////  DATAFIELDS  ////////////////////
    private int n;
    private double[] vector;

    
    
    
    /////////////////////////////////////////////  CONSTRUCTORS  //////////////////
    
    /**
     * Constructor for this vector object
     * @param n the number of dimensions in the vector
     */
    public Vector(int n) {
        this.n = n;
        this.vector = new double[this.n];
    }//end 1-arg constructor
    
    
    
    
    
    
    //////////////////////////////////  GETTERS & SETTERS  ///////////////////////
    
    /**
     * retrieves a particular value from the vector
     * @param i the position of the value to be retrieved 
     * @return the value at the desired position
     */
    public double getValue(int i){
        return this.vector[i];
    }//end getValue()
    
    
    /**
     * changes an individual value in the vector
     * @param i the position to be changed
     * @param value the value to change it to
     */
    public void setValue(int i, double value){
        this.vector[i] = value;
    }//end setValue()
    
    
    /**
     * the entire vector as a matrix
     * @return 
     */
    public double[] getVector(){
        return this.vector;
    }//end getVector()

    
    /**
     * the number of dimensions in the vector
     * @return 
     */
    public int getN() {
        return n;
    }//end getN()
    
    
    
    
    
    
    
    //////////////////////////////////////////  OPERATIONS  //////////////////////////////
    
    /**
     * multiply this vector by a scalar multiple
     * @param s the scalar multiple
     * @return the scaled vector
     */
    public Vector scalarMultiply(double s){
        Vector y = new Vector(this.getN());
        for(int i=0; i<this.getVector().length; i++){
            y.setValue(i, this.getValue(i) * s);
        }
        return y;
    }//end scalarMultiply()
    
    
    /**
     * adds this to another vector if they have the same number of dimensions
     * @param y the other vector to be added
     * @return the vector with the sum of each dimension if they're the same size, null otherwise
     */
    public Vector sum(Vector y){
        Vector z;
        if(this.getN() == y.getN()){
            z = new Vector(this.getN());
            for(int i=0; i<this.getVector().length; i++){
                z.setValue(i, this.getValue(i) + y.getValue(i));
            }
        }
        else{
            z = null;
        }
        return z;
    }//end sum()
    
    
    
    /**
     * Finds the difference between this vector and another one
     * @param y the other vector of the same size to find the difference between
     * @return a vector with the discrepancies for each dimension in the two vectors, or null if the two vectors are not the same size
     */
    public Vector difference(Vector y){
        Vector z;
        if(this.getN() == y.getN()){
            z = new Vector(this.getN());
            for(int i=0; i<this.getVector().length; i++){
                z.setValue(i, this.getValue(i) - y.getValue(i));
            }
        }
        else{
            z = null;
        }
        return z;
    }//end sum()
    
    
    /**
     * multiply this vector by a matrix on its left
     * @param A the matrix to be multiplied by
     * @return the resultant vector if they are compatible size, null otherwise
     */
    public Vector matrixMultiply(Matrix A){
        Vector b;
        if(this.getN() == A.getN()){
            b = new Vector(this.getN());
            for(int i=0; i<b.getVector().length; i++){
                b.setValue(i, 0);
            }
            for(int i=0; i<A.getMatrix().length; i++){
                for(int j=0; j<A.getMatrix()[i].length; j++){
                    b.setValue(i, b.getValue(i) + A.getValue(i, j) * this.getValue(j));
                }
            }
        }
        else{
            b = null;
        }
        return b;
    }//end matrixMultiply()
    
    
    
    
    
    
    ////////////////////////////////////////  FILL VECTOR VALUES  ///////////////////////
    
    
    /**
     * Fills this vector with random values between 0 and 1
     */
    public void randomFill(){
        Random rand = new Random();
        for(int i=0; i<this.getVector().length; i++){
            this.setValue(i, rand.nextDouble());
        }
    }//end randomFill()
    
    
    
    /**
     * In a realistic situation sensors would collect the massive amounts of data and store it in a way that could typically be easily exported as a .csv file, for test cases in class creating the .csv file in Excel seems reasonable.
     * @param filepath the filepath or URL where the .csv file is to be found
     */
    public void fillFromCSV(String filepath){
        File csv = new File(filepath);
        try{
            Scanner fromFile = new Scanner(csv);
            int i=0;
            while(fromFile.hasNextDouble()){
                this.setValue(i, fromFile.nextDouble());
                i++;
            }
        }
        catch(Exception e){
            this.randomFill();
        }
    }//end fillFromCSV()
    
    
    
    /**
     * Creates a zero vector in the desired size
     * @param n the number of dimensions for this zero vector
     * @return an n-dimensional zero vector
     */
    public static Vector zero(int n){
        Vector zero = new Vector(n);
        for(int i=0; i<n; i++){
            zero.setValue(i, 0);
        }
        return zero;
    }//end Zero()
    
    
    
    /**
     * Finds the specified norm type for this vector
     * @param type the type of norm to find, e.q. 1, 2, or infinite
     * @return 
     */
    public double getNorm(Norm type){
        switch(type){
            case ONE:
                double sum = 0;
                for(int i=0; i<n; i++){
                    sum += Math.abs(getValue(i));
                }
                return sum;
            case TWO:
                double squares = 0;
                for(int i=0; i<n; i++){
                    squares += Math.pow(getValue(i), 2);
                }
                return Math.sqrt(squares);
            case INFINITE:
                double max = getValue(0);
                for(int i=0; i<n; i++){
                    if(getValue(i) > max){
                        max = Math.abs(getValue(i));
                    }
                }
                return max;
        }
        return 0; //only reachable upon a not possible error, the compiler doesn't know this
    }//end getNorm()
    
    
    
    
    
    
    //////////////////////////////////////////////  OVERRIDES  ////////////////////////

    /**
     * a console/terminal friendly textual representation of this vector
     * @return 
     */
    @Override
    public String toString() {
        String representation = new String("[");
        for(int i=0; i<this.getVector().length; i++){
            representation += this.getValue(i) + " ";
        }
        representation += "\b]^T";
        return representation;
    }//end toString()

    
    
    /**
     * compares to see if two vectors are equal
     * @param obj the other matrix to compare to
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        try{
            Vector y = (Vector)obj;
            if(this.getN() != y.getN()){
                return false;
            }
            boolean checking = true;
            for(int i=0; i<this.getVector().length; i++){
                if(this.getValue(i) != y.getValue(i)){
                    checking = false;
                }
            }
            return checking;
        }
        catch(Exception e){
            return false;
        }
    }//end equals()
    
    
}//end Vector class
