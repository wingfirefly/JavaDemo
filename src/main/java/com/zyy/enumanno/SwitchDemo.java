package com.zyy.enumanno;

import org.junit.Test;
/**
 * 枚举的应用
 * @author Shinelon
 *
 */
public class SwitchDemo {

    @Test
    public void testSwitch() {
        MessageWrapper wrapper = MessageWrapper.SUCCESS;
        switch (wrapper) {
        case SUCCESS:
            break;
        case FAILURE:
            break;

        default:
            break;
        }
    }
}
