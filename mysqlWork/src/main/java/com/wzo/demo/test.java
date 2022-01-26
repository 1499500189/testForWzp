package com.wzo.demo;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author
 * @date 2022 年 01 月 07 日
 */
public class test {
    public static void main(String[] args) {
      int[]   nums1 = {1,2,3,0,0,0};
      int  m = 3;
      int[] nums2 = {2,5,6};
      int n = 3;
      shortMy(nums1,m,nums2,n);
        for (int i : nums1) {
            System.out.println(i);
        }

    }
    public  static void shortMy (int[] nums1, int m, int[] nums2, int n){
        for (int i = m; i < nums1.length; i++) {
           nums1[i] =     nums2[i-m];
        }
        Arrays.sort(nums1);
    }










}
