/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vut.data;

/**
 *
 * @author NGONGO 214046087
 */
public class Student
{

    private String studNo, name, gender, subject;
    private int test1, test2, test3;
    private double examMark;

    public Student()
    {
        this("", "", "", "", 0, 0, 0);
    }

    public Student(String studNo, String name, String gender, String subject, int test1, int test2, int test3)
    {
        this.studNo = studNo;
        this.name = name;
        this.gender = gender;
        this.subject = subject;
        this.test1 = test1;
        this.test2 = test2;
        this.test3 = test3;
      
    }

    public void setStudNo(String studNo)
    {
        this.studNo = studNo;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public void setTest1(int test1)
    {
        this.test1 = test1;
    }

    public void setTest2(int test2)
    {
        this.test2 = test2;
    }

    public void setTest3(int test3)
    {
        this.test3 = test3;
    }

    public String getStudNo()
    {
        return studNo;
    }

    public String getName()
    {
        return name;
    }

    public String getGender()
    {
        return gender;
    }

    public String getSubject()
    {
        return subject;
    }

    public int getTest1()
    {
        return test1;
    }

    public int getTest2()
    {
        return test2;
    }

    public int getTest3()
    {
        return test3;
    }

    public double getExamMark(double examMark){
    
        return examMark;
    
    }
   

    public double calcSemesteMark()
    {
        double semMar = 0;
        
        semMar = (test1 + test2 + test3) / 3;
        
        return semMar;
    }

    public double calcFinalmark(double examMark)
    {
        double finalMark = 0 ;
        finalMark = (calcSemesteMark()+ examMark) / 2;
        return finalMark;
    }

    @Override
    public String toString()
    {
        return "\n" + studNo + "\t" + name + "\t" + subject + "\t" + calcSemesteMark() + "\t" + getExamMark(examMark) + "\t" + calcFinalmark(examMark);
    }

}
