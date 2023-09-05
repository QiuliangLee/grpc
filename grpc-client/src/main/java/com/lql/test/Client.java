package com.lql.test;

/**
 * @author liqiuliang
 * @create 2023-09-03 19:54
 */
public class Client {
    public static void main(String[] args) {
        Teacher teacher = new Teacher("Lucas");
        Student student1 = new Student("stu1");
        Student student2 = new Student("stu2");

        teacher.addObserver(student1);
        teacher.addObserver(student2);
        teacher.setHomework("数据结构超一本");
    }
}
