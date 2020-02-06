package com.chatelain.outbreakprediction.forecast;

import com.chatelain.outbreakprediction.forecast.params.Params;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class OutBreakCalculator {

    private int pIndex = 0;

    public int worldTime = 0;

    private Map<String, PeopleStatistics> dataForDate = new LinkedHashMap<>();

    public Map<String, Object> result = new HashMap<>();

    public Params params;

    public PersonPool personPool;

    public Hospital hospital;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Date beginTime = dateFormat.parse("2020-02-01 00:00");

    public OutBreakCalculator(Params params) throws ParseException {
        this.params = params;
        this.personPool = new PersonPool(params, this);
        this.hospital = new Hospital(params);
    }

    public void calculate() {
        List<Person> people = personPool.getPersonList();
        if (people == null) {
            return;
        }
        people.get(pIndex++).update();
        for (Person person : people) {
            person.update();
        }
        if (pIndex >= people.size()) {
            pIndex = 0;
        }
        PeopleStatistics peopleStatistics = new PeopleStatistics(personPool.getPeopleSize(Person.State.NORMAL),
                personPool.getPeopleSize(Person.State.SHADOW),
                personPool.getPeopleSize(Person.State.CONFIRMED),
                personPool.getPeopleSize(Person.State.FREEZE));

        Date thisDay = getDate();
        dataForDate.put(dateFormat.format(thisDay), peopleStatistics);
        if (personPool.getPeopleSize(Person.State.SHADOW) == 0 && personPool.getPeopleSize(Person.State.CONFIRMED) == 0) {
            result.put("lastTime", worldTime);
            result.put("endDate", dateFormat.format(getDate()));
            result.put("dataForDate", dataForDate);
        } else {
            worldTime++;
            calculate();
        }
    }

    public void initInfected() {
        List<Person> people = personPool.getPersonList();//获取所有的市民
        for (int i = 0; i < params.getORIGINAL_COUNT(); i++) {
            Person person;
            do {
                person = people.get(new Random().nextInt(people.size() - 1));//随机挑选一个市民
            } while (person.isInfected());//如果该市民已经被感染，重新挑选
            person.beInfected();//让这个幸运的市民成为感染者
        }
    }

    private Date getDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginTime);
        calendar.add(Calendar.HOUR, worldTime);
        return calendar.getTime();
    }


}
