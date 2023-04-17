package com.example.demo.hutool;

import lombok.Data;

/**
 * @date: 2022/6/30
 */
@Data
public class LongParent {

    private Long target;

    public void setTarget(Long target) {
        this.target = target;
    }
}
