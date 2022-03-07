package test.demo;

import java.util.concurrent.Callable;

/**
 * @author
 * @date 2022 年 02 月 28 日
 */
public class ThreadC implements Callable {


    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Object call() throws Exception {
        return 1;
    }
}
