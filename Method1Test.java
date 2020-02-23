import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class Method1Test {

    @RunWith(Parameterized.class)
    public static class Method1 {
        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new int[]{1, 7}, new int[]{1, 2, 1, 4, 2, 3, 4, 1, 7}},
                    {new int[]{5, 7}, new int[]{1, 4, 5, 7}},
                    {new int[]{5, 7}, new int[]{1,4 , 4, 7}},
                    {new int[]{5, 7}, new int[]{1,0 , 3, 7}},
            });
        }

        private Mainn tested;
        private int[] arrNew;
        private int[] arr;

        public Method1(int[] arrNew, int[] arr) {
            this.arrNew = arrNew;
            this.arr = arr;
        }

        @Before
        public void init() {
            tested = new Mainn();
        }

        @Test()
        public void massTest() {
            for (int i = 0; i < arrNew.length; i++) {
                Assert.assertEquals(arrNew[i], tested.method1(arr)[i]);
            }
        }

    }
}
