package com.zm.util;

import java.util.UUID;

/**
 * <主键生成器>
 * @author Administrator
 *
 */
public class PrimaryKeyUtil {
    /**
     * 获得一个UUID(非同步）
     * @return String UUID
     */
    public static String getUUID() {
        String strResult = "";
        try {
            String uuid = UUID.randomUUID().toString();
            strResult = uuid.replaceAll("-", "");           //去掉“-”符号
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strResult;
    }

    /**
     * 获得一个UUID (同步）
     * @return String UUID
     */
    public static synchronized String getSyncUUID() {
        String strResult = "";
        try {
            String uuid = UUID.randomUUID().toString();
            strResult = uuid.replaceAll("-", "");           //去掉“-”符号
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strResult;
    }

    /**
     * 按照手机获得会员ID
     * @return String UUID
     */
    public static String getMemberID(String mobile) {
        String strResult = "";
        try {
            long l = Long.parseLong(mobile);
            strResult = "M" + Long.toString(l * 5 - 1000000) + RandomUtil.getStrRandom(4, RandomUtil.LETTER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strResult;
    }

    /**
     * 获得会员ID
     * @return String
     */
    public static String getMemberID() {
        String strResult = "";
        try {
            String strDate = JodaTimeUtils.getStrCurrentDate(JodaTimeUtils.DF_SHORT);
            long l = Long.parseLong(strDate);
            strResult = "M" + Long.toString(l * 5 - 1000000000) + RandomUtil.getStrRandom(4, RandomUtil.LETTERNUMBER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strResult;
    }

    /**
     * 获得课程ID
     * @return String UUID
     */
    public static String getCourseID() {
        String strResult = "";
        try {
            String strDate = JodaTimeUtils.getStrCurrentDate(JodaTimeUtils.DF_SHORT_YMD);
            long l = Long.parseLong(strDate);
            strResult = "C" + Long.toString(l) + RandomUtil.getStrRandom(5, RandomUtil.LETTERNUMBER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strResult;
    }

    /**
     * 获得贷款ID或者合同ID
     * @return String UUID
     */
    public static String getbusinessID(String flag) {
        String strResult = "";
        try {
            String strDate = JodaTimeUtils.getStrCurrentDate(JodaTimeUtils.DF_YMD);
            strResult = flag + strDate + RandomUtil.getStrRandom(6, RandomUtil.LETTERNUMBER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strResult;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("getUUID    = " + getUUID());
        System.out.println("getMemberID= " + getMemberID("18999999999"));
        System.out.println("getbusinessID=" + getbusinessID("L"));
        System.out.println("getbusinessID=" + getbusinessID("C"));
    }
}
