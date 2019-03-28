package com.assignment.rsupport.support.test;

import com.assignment.rsupport.support.fixture.Fixture;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;

public class BaseTest extends Fixture {
    // Rule은 하나의 테스트 클래스 내에서 동작 방식을 재정의, 추가
    // SoftAssertions를 사용할 경우 하나의 단위테스트에서 여러 테스트를 진행할 때 실패하는 조건이 있어도 다음 테스트를 실행할 수 있음
    @Rule
    public JUnitSoftAssertions softly = new JUnitSoftAssertions();
}
