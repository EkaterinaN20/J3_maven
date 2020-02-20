import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class Method2 {

    @RunWith(Parameterized.class)
    public static class Method3 {
        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {true, new int[]{1, 2, 1, 4, 2, 3, 4, 1, 7}},
                    {true, new int[]{1, 5, 7}},
                    {false, new int[]{2,0 , 3, 7}},
                    {false, null}
            });
        }

        private Mainn tested3;
        private boolean x;
        private int[] arr;

        public Method3(boolean x, int[] arr) {
            this.x = x;
            this.arr = arr;
        }

        @Before
        public void init() {
            tested3 = new Mainn();
        }

        @Test()
        public void massTest() {
                Assert.assertEquals(x, tested3.method2(arr));

        }

    }
}