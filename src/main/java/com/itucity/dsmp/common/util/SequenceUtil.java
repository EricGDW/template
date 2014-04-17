package com.itucity.dsmp.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 获取序列号
 */
public class SequenceUtil {
	private static final int MAX_SEQ=0x2000000;
	private static final int MIN_SEQ=0x0000000;
	private static final Lock lock=new ReentrantLock();
	private static int count=MIN_SEQ;
	
	/**
	 * 7位时间(精确到秒)
	 * +5位序列号(支持最大序列3355,4432)
	 * @return 
	 */
	public static String getSequence() {
		return toString(System.currentTimeMillis()/1000, 7)+toString(getSimpleSequence(), 5);
	}
	
	/**
	 * 返回6位序列号
	 * @return
	 */
	private static int getSimpleSequence() {
		lock.lock();
		try {
			if(count>=MAX_SEQ) count=MIN_SEQ;
			return ++count;
		} finally {
			lock.unlock();
		}
	}
	
	private final static char[] digits = {
		'0' , '1' , '2' , '3' , '4' , '5' ,
		'6' , '7' , '8' , '9' , 'a' , 'b' ,
		'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
		'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
		'o' , 'p' , 'q' , 'r' , 's' , 't' ,
		'u' , 'v' , 'w' , 'x' , 'y' , 'z'
	};
	
	/**
	 * @param i 需转换的数字
	 * @param len 生成字符串最小长度
	 * @return
	 */
	private static String toString(long i, int len) {
		final int size=16;
		final int shift=5; //位移量5, 这里采用32进制
		char[] buf = new char[size];
		int charPos = size;
		int radix = 1 << shift;
		long mask = radix - 1;
		do {
		    buf[--charPos] = digits[(int)(i & mask)];
		    i >>>= shift;
		} while (i != 0 || len>size-charPos);
		return new String(buf, charPos, (size - charPos));
    }
	
	/**
	 * 格式化序列号字符串(yyyy-MM-dd HH:mm:ss | d)
	 * @param seq 序列号字符串
	 * @return
	 */
	public static String formatSeq(String seq) {
		long date=Long.valueOf(seq.substring(0, 7), 32)*1000;
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(date))+" | "+Long.valueOf(seq.substring(7), 32);
	}
	
	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
//		System.out.println(toString(Long.MAX_VALUE, 11));
		System.out.println(Long.toString((new SimpleDateFormat("yyyy-MM-dd")).parse("3012-01-01").getTime()/1000, 32));
		System.out.println(getSimpleSequence());
		System.out.println(getSequence());
		System.out.println(formatSeq(getSequence()));
	}
}
