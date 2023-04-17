package com.example.demo.timer;

import com.example.demo.timer.style1.SystemTimer;
import com.example.demo.timer.style1.TimerTask;
import org.junit.jupiter.api.Test;

class SystemTimerTest {

    @Test
    void add() {
        SystemTimer timer  = new SystemTimer(1000, 10);

        timer.add(new TimerTask("?????", 13000));
    }
}