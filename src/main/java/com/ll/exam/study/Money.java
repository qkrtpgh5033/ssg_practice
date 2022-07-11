package com.ll.exam.study;

import java.util.Objects;

public class Money {
    private final int value;

    public Money(int value) {
        this.value = value;
    }

    /**
     * DTO와 달리
     * VO는 로직포함이 가능!
     */
    public int getHalfValue() {
        return value/2;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }

        if( !(o instanceof Money)){
            return false;
        }
        Money money = (Money) o;
        return value == money.value;

    }


    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}


