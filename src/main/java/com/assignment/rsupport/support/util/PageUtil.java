package com.assignment.rsupport.support.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {
    public static final int PAGE_SIZE = 2;
    public static final int PAGE_COUNT = 5;

    private int currentPageBundleNum;
    private int totalNumberOfPages;

    public PageUtil(int pageNumber, int totalNumberOfPages) {
        this.currentPageBundleNum = pageNumber / PAGE_COUNT;
        this.totalNumberOfPages = totalNumberOfPages;
    }

    public Integer getNextFirstPageNum() {
        if (currentPageBundleNum < this.totalNumberOfPages / PAGE_COUNT)
            return PAGE_COUNT * (currentPageBundleNum + 1) + 1;
        return null;
    }

    public Integer getBeforeFirstPageNum() {
        if (currentPageBundleNum > 0)
            return PAGE_COUNT * (currentPageBundleNum - 1) + 1;
        return null;
    }

    public List<Integer> getPageNums() {
        List<Integer> pageNums = new ArrayList<>();
        int start = PAGE_COUNT * this.currentPageBundleNum;
        int last = start + PAGE_COUNT < totalNumberOfPages ? start + PAGE_COUNT : totalNumberOfPages + 1;

        for (int i = start; i < last; i++)
            pageNums.add(i + 1);

        return pageNums;
    }
}
