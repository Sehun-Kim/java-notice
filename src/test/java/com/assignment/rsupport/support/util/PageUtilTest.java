package com.assignment.rsupport.support.util;

import com.assignment.rsupport.support.test.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageUtilTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(PageUtilTest.class);

    private int curNum;
    private int totalNum;

    @Test
    public void getNextFirstPageNum_exist() {
        curNum = 9;
        totalNum = 12;

        PageUtil pageUtil = new PageUtil(curNum, totalNum);
        softly.assertThat(pageUtil.getNextFirstPageNum()).isNotNull();
        softly.assertThat(pageUtil.getNextFirstPageNum()).isEqualTo(11);
    }

    @Test
    public void getNextFirstPageNum_null() {
        PageUtil pageUtil = new PageUtil(6, 6);
        softly.assertThat(pageUtil.getNextFirstPageNum()).isNull();
    }

    @Test
    public void getBeforeFirstPageNum_exist() {
        curNum = 5;
        totalNum = 6;

        PageUtil pageUtil = new PageUtil(curNum, totalNum);
        softly.assertThat(pageUtil.getBeforeFirstPageNum()).isNotNull();
        softly.assertThat(pageUtil.getBeforeFirstPageNum()).isEqualTo(1);
    }

    @Test
    public void getBeforeFirstPageNum_null() {
        PageUtil pageUtil = new PageUtil(0, 6);
        softly.assertThat(pageUtil.getBeforeFirstPageNum()).isNull();
    }

    @Test
    public void getPageNums() {
        curNum = 10;
        totalNum = 11;

        PageUtil pageUtil = new PageUtil(curNum, totalNum);
        for (Integer num : pageUtil.getPageNums()) {
            logger.debug("num : {}", num);
        }
    }
}