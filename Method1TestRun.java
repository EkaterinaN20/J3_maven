import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class Method1TestRun {

    @RunWith(Parameterized.class)
    public static class Method1 {
        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new int[]{1, 7}, new int[]{1, 2, 1, 4, 2, 3, 4, 1, 7}},
                    {new int[]{5, 7}, new int[]{1,0 , 3, 7}},
                    {null, new int[]{1,0 , 3, 7}},
            });
        }

        private Mainn tested1;
        private int[] arrNew;
        private int[] arr;

        public Method1(int[] arrNew, int[] arr) {
            this.arrNew = arrNew;
            this.arr = arr;
        }

        @Before
        public void init() {
            tested1 = new Mainn();
        }

        @Test(expected = RuntimeException.class)
        public void massTest() {
            throw new RuntimeException();
            }
        }

    }

