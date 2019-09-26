package org.zzq.serverfeign.component;

import org.springframework.stereotype.Component;
import org.zzq.serverfeign.inteface.ScheduleServiceHi;

@Component
public class SchedualServiceHiHystric implements ScheduleServiceHi {

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
