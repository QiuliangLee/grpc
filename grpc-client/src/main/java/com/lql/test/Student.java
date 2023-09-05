package com.lql.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Observable;
import java.util.Observer;

/**
 * @author liqiuliang
 * @create 2023-09-03 19:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Observer {
    private String name;

    @Override
    public void update(Observable o, Object arg) {
        Teacher teacher = (Teacher) o;
        System.out.printf("学生%s观察%s,被告知%s\n", this.name, teacher.getName(), arg);
    }
}
