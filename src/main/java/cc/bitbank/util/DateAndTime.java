package cc.bitbank.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAndTime {

	/** 年　*/
	public static final int YEAR = 1;//Calendar.YEAR;
	/** 月　*/
	public static final int MONTH = 2;//Calendar.MONTH
	/** 日　*/
	public static final int DATE = 5;//Calendar.DAY_OF_MONTH


	public static String getYYYYMMDDHHMMSS(String strYYYYMMDDHHMMSS){
		String strDate = strYYYYMMDDHHMMSS.substring(0, 4) + "/" + strYYYYMMDDHHMMSS.substring(4,6) + "/" + strYYYYMMDDHHMMSS.substring(6,8) + " " + strYYYYMMDDHHMMSS.substring(8, 10) + ":" + strYYYYMMDDHHMMSS.substring(10,12) + ":" + strYYYYMMDDHHMMSS.substring(12,14);
		return strDate;
	}

	public static Date getYYYYMMDDHHMMSSDate(String strYYYYMMDDHHMMSS) throws Exception{
		String strDate = getYYYYMMDDHHMMSS(strYYYYMMDDHHMMSS);
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return sdFormat.parse(strDate);
	}

	public static String getYYYYMMDD(String strYYYYMMDD){
		String strDate = strYYYYMMDD.substring(0, 4) + "/" + strYYYYMMDD.substring(4,6) + "/" + strYYYYMMDD.substring(6,8);
		return strDate;
	}

	public static Date getYYYYMMDDDate(String strYYYYMMDD) throws Exception{
		String strDate = getYYYYMMDD(strYYYYMMDD);
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		return sdFormat.parse(strDate);
	}

	public static String getBaseDate() throws Exception{

		Date nowDate = new Date();
		if(getYYYYMMDDHHMMSSDate(formatYYYYMMDD(getNowDate()) + "090000").compareTo(nowDate)<=0){
			return formatYYYYMMDD(getNowDate());
		}

		return getAddDate(formatYYYYMMDD(getNowDate()),-1,DATE);

	}

	public static Date getNowDate() throws Exception {
	    Calendar cal1 = Calendar.getInstance();  //(1)オブジェクトの生成

	    int year = cal1.get(Calendar.YEAR);        //(2)現在の年を取得
	    int month = cal1.get(Calendar.MONTH) + 1;  //(3)現在の月を取得
	    int day = cal1.get(Calendar.DATE);         //(4)現在の日を取得

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        date = format.parse(year + "/" + month + "/" + day + "/");
        return date;
	}

	public static String formatYYYYMMDD(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String strDate = sdf.format(date);
        return strDate;
	}

	/**
	 * 指定日から加算後の日付を取得する
	 * @param  strDate 指定日(YYYYMMDD)
	 * @param  num     加算する数値
	 * @param  calType 加算対象
	 *                 例(MriDate.YEAR ： 年, MriDate.MONTH ： 月, Calendar.DATE ： 日, MriDate.WEEK : 週)
	 * @return 加算後の日付(yyyy/MM/dd)
	 */
	public static String getAddDate(String strYYYYMMDD, int num, int calType) throws Exception {

		// 入力情報が存在しない場合、
		if (strYYYYMMDD == null || strYYYYMMDD.equals("")) {
			return "";
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getYYYYMMDDDate(strYYYYMMDD));

		switch (calType) {
		case YEAR:
			calendar.add(Calendar.YEAR, num);
			break;
		case MONTH:
			calendar.add(Calendar.MONTH, num);
			break;
		case DATE:
			calendar.add(Calendar.DATE, num);
			break;
		default:
			throw new IllegalArgumentException("第2引数が不正です。日付の計算に失敗しました。");
		}
		return formatYYYYMMDD(calendar.getTime());
	}

}
