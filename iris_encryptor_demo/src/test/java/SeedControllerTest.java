import org.encryptor.seed.KISA_SEED_ECB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;


public class SeedControllerTest {

    @Test
    public void  test() throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        //given
        String originStr = "{ \"memId\" : \"742221\" , \"memNm\" : \"홍길동\" , \"birthYmd \" : \"19000101\" , \"mpNo\" : \"01012345678\" , \"emailAddr\" : \"mkson@test.com\" }";
        String key = "SONOITTEST000000";

        //when
        String encStr = KISA_SEED_ECB.Common.EncSeedEcb(originStr, key);
        String decStr = KISA_SEED_ECB.Common.DecSeedEcb(encStr, key);

        //then
        Assertions.assertEquals(originStr, decStr); //암호화 하기 전의 평문과 복호화한 후의 평문을 비교.

        //log
        System.out.println("----------------------------------");
        System.out.println("==> [originStr]     : " + originStr);
        System.out.println("==> [key]           : " + key);
        System.out.println("==> [type]          : " + "SEED");
        System.out.println("==> [encStr]        : " + encStr);
        System.out.println("==> [decStr]        : " + decStr);
        System.out.println("----------------------------------");
    }
}
