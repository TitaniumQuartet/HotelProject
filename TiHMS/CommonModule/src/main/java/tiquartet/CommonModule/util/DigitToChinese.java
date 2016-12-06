package tiquartet.CommonModule.util;

public class DigitToChinese {
	private static String[] chineseDigit = {"零","一","二","三","四","五","六","七","八","九","十"};
	
	public static String toChinese(int digit){
		if(digit<0||digit>10) return null;
		else return chineseDigit[digit];
	}
}
