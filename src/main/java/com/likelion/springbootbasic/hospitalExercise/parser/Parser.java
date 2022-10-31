package com.likelion.springbootbasic.hospitalExercise.parser;

public interface Parser<T> {
    T parse(String str);
}
