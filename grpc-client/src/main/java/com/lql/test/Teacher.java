package com.lql.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Observable;

/**
 * @author liqiuliang
 * @create 2023-09-03 19:49
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends Observable {
    private String name;

    public void setHomework(String homework) {
        System.out.printf("老师%s布置了作业%s\n", this.name, homework);
        setChanged();
        notifyObservers();
    }
}
