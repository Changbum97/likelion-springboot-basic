package com.likelion.springbootbasic.hospitalExercise.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ReadLineContext<T> {
    private Parser<T> parser;

    public ReadLineContext(Parser<T> parser) {
        this.parser = parser;
    }

    public List<T> readByLine(String filename) throws IOException {
        List<T> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        br.readLine();

        String str;
        while( (str = br.readLine()) != null) {
            try{
                result.add(parser.parse(str));
            } catch (Exception e) {
                System.out.println("파싱 중 문제가 생겨 이 라인은 넘어갑니다. 라인내용 : " + str);
            }
        }
        br.close();
        return result;
    }
}
