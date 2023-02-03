package me.mykim.javatest.junit5.extensionpack;

import me.mykim.javatest.junit5.FindSlowTestExtension;
import me.mykim.javatest.junit5.SlowTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

/**
 * @ExtendWith(FindSlowTestExtension.class)
 * 선언적 방법 등록 : 이 객체(FindSlowTestExtension)를 커스터마이징 할 수 없다.
 * 만약 테스트마다 FindSlowTestExtension의 THRESHOLD값을 다르게 설정하고 싶다면 불가하다.
 *
 *  private long THRESHOLD;
 *
 *  public FindSlowTestExtension(long THRESHOLD) {
 *      this.THRESHOLD = THRESHOLD;
 *  }
 *
 */
//@ExtendWith(FindSlowTestExtension.class)
class ExtensionPackTest {

    @RegisterExtension // 프로그래밍 등록
    static FindSlowTestExtension findSlowTestExtension
            = new FindSlowTestExtension(1000L); // THRESHOLD값을 테스트마다 설정할 수 있다.


    @Test
    @DisplayName("확장팩 등록 테스트 : FindSlowTestExtension")
    void testCode() throws Exception {
        Thread.sleep(3000);
        System.out.println("test method");
    }

    @SlowTest
    @DisplayName("확장팩 등록 테스트 : FindSlowTestExtension : @SlowTest가 있을때")
    void testCode2() throws Exception {
        Thread.sleep(3000);
        System.out.println("test method");
    }




}
