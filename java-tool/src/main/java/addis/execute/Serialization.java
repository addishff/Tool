package addis.execute;

import java.io.*;

/**
 * Created by huangfeifeng on 8/19/16.
 */
public class Serialization {
    public static void main(String[] args) {
        Student st = new Student("Tom", 'M', 20, 3.6);
        File file = new File("/Users/huangfeifeng/Desktop/student.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //Student对象序列化过程
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(st);
            oos.flush();
            oos.close();
            fos.close();

            //Student对象反序列化过程
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Student st1 = (Student) ois.readObject();
            System.out.println("name = " + st1.getName());
            System.out.println("sex = " + st1.getSex());
            System.out.println("year = " + st1.getYear());
            System.out.println("gpa = " + st1.getGpa());
            System.out.println("array = " + st1.arrayBean);
            ois.close();
            fis.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Student implements Serializable {
    private String name;
    private char sex;
    private int year;
    private double gpa;
    ArrayBean arrayBean;

    public Student() {

    }

    public Student(String name, char sex, int year, double gpa) {
        this.name = name;
        this.sex = sex;
        this.year = year;
        this.gpa = gpa;
        arrayBean = new ArrayBean();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getName() {
        return this.name;
    }

    public char getSex() {
        return this.sex;
    }

    public int getYear() {
        return this.year;
    }

    public double getGpa() {
        return this.gpa;
    }
}