package com.chen.algorithm.day12;

import java.util.Arrays;
import java.util.Comparator;

/**
 * code01比较器
 *
 * @author chenzihan
 * @date 2022/06/08
 */
public class Code01Comparator {
    public static class Student {
        public int id;
        public int age;
        public String name;

        public Student(int id, int age, String name) {
            this.id = id;
            this.age = age;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        Student student = new Student(1, 10, "name2");
        Student student2 = new Student(2, 7, "name3");
        Student student3 = new Student(3, 17, "name1");
        Student[] students = new Student[]{student, student2, student3};
        Arrays.sort(students, Comparator.comparingInt(Student::getAge));
        for (Student s : students) {
            System.out.println(s);
        }
    }

}
