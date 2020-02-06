package com.chatelain.outbreakprediction.forecast;

import com.chatelain.outbreakprediction.forecast.params.Constants;
import com.chatelain.outbreakprediction.forecast.params.Params;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: PersonPool
 * @Description: TODO
 * @author: Bruce Young
 * @date: 2020年02月02日 17:21
 */
public class PersonPool {
    private static PersonPool personPool;
    
    private Params params;


    List<Person> personList = new ArrayList<Person>();

    public List<Person> getPersonList() {
        return personList;
    }


    /**
     * @param state 市民类型 Person.State的值
     * @return 获取指定人群数量
     */
    public int getPeopleSize(int state) {
        if (state == -1) {
            return params.getCITY_PERSON_SIZE();
        }
        int i = 0;
        for (Person person : personList) {
            if (person.getState() == state) {
                i++;
            }
        }
        return i;
    }

    public PersonPool(Params params, OutBreakCalculator calculator) {
        City city = new City(400, 400);
        this.params = params;
        for (int i = 0; i < params.getCITY_PERSON_SIZE(); i++) {
            Random random = new Random();
            int x = (int) (100 * random.nextGaussian() + city.getCenterX());
            int y = (int) (100 * random.nextGaussian() + city.getCenterY());
            if (x > 700) {
                x = 700;
            }
            personList.add(new Person(city, x, y, calculator));
        }
    }
}
