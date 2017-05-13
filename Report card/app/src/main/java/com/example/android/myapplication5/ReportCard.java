package com.example.android.myapplication5;


public class ReportCard {

    //initialize constants for grades
    private static final char A_GRADE = 'A';
    private static final char B_GRADE = 'B';
    private static final char C_GRADE = 'C';
    private static final char D_GRADE = 'D';
    private static final char F_GRADE = 'F';

    //declare variables for grade values
    private double mGradeVal1;
    private double mGradeVal2;
    private double mGradeVal3;

    //declare variables for grades
    private char mGrade1;
    private char mGrade2;
    private char mGrade3;

    private double GPA = 0.0;

    //identify the year to which the grades belong
    private static int year;

    public double getmGradeVal2() {
        return mGradeVal2;
    }

    public void setmGradeVal2(double mGradeVal2) {
        this.mGradeVal2 = mGradeVal2;
    }

    public char getmGrade1() {
        return mGrade1;
    }

    public void setmGrade1(char mGrade1) {
        this.mGrade1 = mGrade1;
    }

    public char getmGrade2() {
        return mGrade2;
    }

    public void setmGrade2(char mGrade2) {
        this.mGrade2 = mGrade2;
    }

    public char getmGrade3() {
        return mGrade3;
    }

    public void setmGrade3(char mGrade3) {
        this.mGrade3 = mGrade3;
    }

    public double getmGradeVal1() {
        return mGradeVal1;
    }

    public void setmGradeVal1(double mGradeVal1) {
        this.mGradeVal1 = mGradeVal1;
    }

    public double getmGradeVal3() {
        return mGradeVal3;
    }

    public void setmGradeVal3(double mGradeVal3) {
        this.mGradeVal3 = mGradeVal3;
    }


    public static int getYear() {
        return year;
    }

    public static void setYear(int year) {
        ReportCard.year = year;
    }

    //create a constructor that takes three grades as input
    public ReportCard(char grade1, char grade2, char grade3) {

        mGrade1 = grade1;
        mGrade2 = grade2;
        mGrade3 = grade3;

        //check the grades and assign values
        if (mGrade1 == A_GRADE) {
            mGradeVal1 = 4.0;
        } else if (mGrade1 == B_GRADE) {
            mGradeVal1 = 3.0;
        } else if (mGrade1 == C_GRADE) {
            mGradeVal1 = 2.0;
        } else if (mGrade1 == D_GRADE) {
            mGradeVal1 = 1.0;
        } else {
            mGradeVal1 = 0.0;
        }

        if (mGrade2 == A_GRADE) {
            mGradeVal2 = 4.0;
        } else if (mGrade2 == B_GRADE) {
            mGradeVal2 = 3.0;
        } else if (mGrade2 == C_GRADE) {
            mGradeVal2 = 2.0;
        } else if (mGrade2 == D_GRADE) {
            mGradeVal2 = 1.0;
        } else {
            mGradeVal2 = 0.0;
        }

        if (mGrade3 == A_GRADE) {
            mGradeVal3 = 4.0;
        } else if (mGrade3 == B_GRADE) {
            mGradeVal3 = 3.0;
        } else if (mGrade3 == C_GRADE) {
            mGradeVal3 = 2.0;
        } else if (mGrade3 == D_GRADE) {
            mGradeVal3 = 1.0;
        } else {
            mGradeVal3 = 0.0;
        }


    }


    //function to check if a student has failed
    public boolean hasFailed() {
        if (mGrade1 == F_GRADE || mGradeVal2 == F_GRADE || mGradeVal3 == F_GRADE) {
            return true;
        }
        return false;
    }

    //function to calculate GPA
    public double getGPA() {
        double gpa = (mGradeVal1 + mGradeVal2 + mGradeVal3) / 3;
        return gpa;
    }

    //the toString method returns contents in string format
    @Override
    public String toString() {
        return "The student has the following grades : " + mGrade1 + " " + mGrade2 + " " + mGrade3 + ". The GPA is " + getGPA();
    }

}
