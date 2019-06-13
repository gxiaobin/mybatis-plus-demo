/**
 * 
 */
package com.zm.util;

import java.util.Random;

/**
 * 
 * <随机生成字母或数字生成器,可做订单号，编号等>
 * @author Administrator
 *
 */
public class RandomUtil {
	public static final String   LETTERNUMBER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final String   LETTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String  NUMBERONLY = "0123456789";
	//按范围随机生成数字，长度不定
	public static int nextInt(final int min, final int max) {
	    Random rand= new Random();
        int tmp = Math.abs(rand.nextInt());
    	return tmp % (max - min + 1) + min;
 }
	//随机生成固定长度字符串
	public static String getStrRandom(int length,String base) { //length表示生成字符串的长度   
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 } 
	 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println("Sequence===="+nextInt(10,30));
		System.out.println("getStrRandom===="+"M13025669630"+getStrRandom(6,LETTERNUMBER));
		System.out.println("getStrRandom===="+"M13025669630"+getStrRandom(6,LETTER));
		System.out.println("getStrRandom===="+getStrRandom(6,NUMBERONLY));
	}
}
