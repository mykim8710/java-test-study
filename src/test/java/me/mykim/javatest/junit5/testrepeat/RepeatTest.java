package me.mykim.javatest.junit5.testrepeat;

import me.mykim.javatest.domain.Study;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

class RepeatTest {
    /**
     * 테스트를 반복하고 싶다면....
     */

    @DisplayName("테스트반복 @RepeatedTest")
    @RepeatedTest(value = 5, name = "{displayName}, {currentRepetition} / {totalRepetitions}") // (반복횟수, "display")
    void testCode1(RepetitionInfo repetitionInfo) throws Exception {
       System.out.println("테스트를 반복하고 싶다. " +repetitionInfo.getCurrentRepetition() +" / " +repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("다른값들을 가지고 테스트반복 @ParameterizedTest")
    @ParameterizedTest(name = "{index} {displayName}, source = {0}")
    @ValueSource(strings = {"mykim", "khjang", "jskim"})
    void testCode2(String source) throws Exception {
        System.out.println("솔로지옥 = " + source +"는 솔로입니다.");
    }

    @DisplayName("다른값들을 가지고 테스트반복 @ParameterizedTest2")
    @ParameterizedTest(name = "{index} {displayName}, source = {0}")
    @ValueSource(strings = {"mykim", "khjang", "jskim"})
    //@EmptySource      // 빈 문자열을 하나더 추가
    //@NullSource       // null case 추가
    @NullAndEmptySource // @EmptySource + @NullSource
    void testCode3(String source) throws Exception {
        System.out.println("솔로지옥 = " + source +"는 솔로입니다.");
    }

    @DisplayName("다른값들을 가지고 테스트반복 @ParameterizedTest3 : 인자 값 타입 변환")
    @ParameterizedTest(name = "{index} {displayName}, source = {0}")
    @ValueSource(ints = {10, 20, 30})
    void testCode4(@ConvertWith(StudyConverter.class) Study study) throws Exception {
        System.out.println("getLimit = " +study.getLimit());
    }

    // SimpleArgumentConverter 하나의 인자만 가능
    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            Assertions.assertEquals(Study.class, targetType, "Study class 타입으로만 변경이 가능합니다.");
            return new Study(Integer.parseInt(source.toString()));
        }
    }


    //
    @DisplayName("다른값들을 가지고 테스트반복 @ParameterizedTest4 : @CsvSource")
    @ParameterizedTest(name = "{index}, {displayName}, source = {0}, {1}")
    @CsvSource({"10, 'java'", "5, 'python'", "3, 'kotlin'"})
    void testCode5(Integer limit, String name) throws Exception {
        Study study = new Study(limit, name);
        System.out.println("study = " + study);
    }

    @DisplayName("다른값들을 가지고 테스트반복 @ParameterizedTest4 : @CsvSource2")
    @ParameterizedTest(name = "{index}, {displayName}, source = {0}, {1}")
    @CsvSource({"10, 'java'", "5, 'python'", "3, 'kotlin'"})
    void testCode6(ArgumentsAccessor argumentsAccessor) throws Exception {
        Study study = new Study(argumentsAccessor.getInteger(0),argumentsAccessor.getString(1));
        System.out.println("study = " + study);
    }

    @DisplayName("다른값들을 가지고 테스트반복 @ParameterizedTest4 : @CsvSource3")
    @ParameterizedTest(name = "{index}, {displayName}, source = {0}, {1}")
    @CsvSource({"10, 'java'", "5, 'python'", "3, 'kotlin'"})
    void testCode7(@AggregateWith(CustomAggregator.class) Study study) throws Exception {
        System.out.println("study = " + study);
    }


    // ArgumentsAggregator를 구현하는 클래스는 static inner class or public class
    static class CustomAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(0),accessor.getString(1));
        }
    }

}

