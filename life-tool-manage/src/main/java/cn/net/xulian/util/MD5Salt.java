package cn.net.xulian.util;

import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Salt
{
	public static final int SALT_LENGTH = 16;
	public static final String DEFAULT_SALT = "0123456789abcdef";
	public static final String NO_SALT = "";
	private static char saltArray[] =
		{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	/**
	 * 生成含有随机盐的密码
	 */
	private static String randomSalt()
	{
		Random random = new Random();
		StringBuilder sb = new StringBuilder(SALT_LENGTH);
		for(int i = 0; i < SALT_LENGTH; i++)
		{
			sb.append(saltArray[random.nextInt(16)]);
		}
		
		return sb.toString();
	}
	
	/**
	 * 计算MD5值，不加盐
	 * @param password
	 * @return
	 */
	public static String md5(String strToMd5)// MD5不加盐
	{
		return md5Salt(strToMd5, false);
	}
	
	/**
	 * 加盐MD5值
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String md5Salt(String strToMd5, boolean hasSalt)
	{
		try
		{
			String salt = DEFAULT_SALT;
			if(hasSalt)
			{
				salt = randomSalt();
			}
				
			strToMd5 = md5Hex(strToMd5 + salt);
//			System.out.println("salt:" + salt);
//			System.out.println("strToMd5" + strToMd5);
			char[] cs = new char[48];
			for(int i = 0; i < 48; i += 3)
			{
				cs[i] = strToMd5.charAt(i / 3 * 2);
				char c = salt.charAt(i / 3);
				cs[i + 1] = c;
				cs[i + 2] = strToMd5.charAt(i / 3 * 2 + 1);
			}
			
			return new String(cs);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 校验密码是否正确
	 */
	public static boolean verify(String strToMd5, String md5)
	{
		char[] cs1 = new char[32];
		char[] cs2 = new char[16];
		for (int i = 0; i < 48; i += 3)
		{
			cs1[i / 3 * 2] = md5.charAt(i);
			cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
			cs2[i / 3] = md5.charAt(i + 1);
		}
		String salt = new String(cs2);
		return md5Hex(strToMd5 + salt).equals(new String(cs1));
	}

	/**
	 * 获取十六进制字符串形式的MD5摘要
	 */
	public static String md5Hex(String src)
	{
		return DigestUtils.md5Hex(src);
	}
}
