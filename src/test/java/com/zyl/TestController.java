package com.zyl;

import java.io.*;

/**
 * Created by z1761 on 2018/10/22.
 */
public class TestController {

    public static void main(String[] args) {
        test outTest = new test(1,2,3,"11","22","33");

        String pathFile = "D://test.txt";
        FileOutputStream file;
        ObjectOutputStream out;
        try {

            file = new FileOutputStream(pathFile);

            out = new ObjectOutputStream(file);
            out.writeObject(outTest);
            out.close();
            file.close();
            System.out.println("序列化存入file");
           outTest.bb="2000";

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        outTest= null;
        FileInputStream fileinput;
        try {

            fileinput = new FileInputStream(pathFile);
            ObjectInputStream object = new ObjectInputStream(fileinput);
           test  inputTest= (test)object.readObject();
           object.close();
           fileinput.close();
            System.out.println(inputTest.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
class test implements Serializable{

    private int a;
    private int b;
    private int c;
    private String aa;
     String bb;
    private String cc;

    public test() {
       // super();
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public test(int a, int b, int c, String aa, String bb, String cc) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.aa = aa;
        this.bb = bb;
        this.cc = cc;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
