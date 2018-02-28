package cc.bitbank.util;

import java.util.ArrayList;
import java.util.List;

import cc.bitbank.Bitbankcc;
import cc.bitbank.entity.Candlestick;
import cc.bitbank.entity.enums.CandleType;
import cc.bitbank.entity.enums.CurrencyPair;

public class CandlestickLogic {

	/**
	 * ローソクのsize分のデータをListにつめて返す
	 * @param bb
	 * @param cp			種類（CurrencyPair.BTC_JPY）
	 * @param ct			キャンドルタイプ(CandleType._1DAY)
	 * @param strYYYYMMDD	日付
	 * @param size			配列のMAX数
	 * @return
	 * @throws Exception
	 */
	public List<Candlestick.Ohlcvs.Ohlcv> getCs(Bitbankcc bb,CurrencyPair cp,CandleType ct,String strYYYYMMDD,int size) throws Exception{
		List<Candlestick.Ohlcvs.Ohlcv>  returnRs = new ArrayList<Candlestick.Ohlcvs.Ohlcv>();
		if (ct == CandleType._1DAY){
			strYYYYMMDD = strYYYYMMDD.substring(0,4);
		}
		List<Candlestick.Ohlcvs.Ohlcv>  cs = bb.getCandlestick(cp, ct, strYYYYMMDD).candlestick[0].getOhlcvList();
		int count = cs.size();
		if(count<size){
			List<Candlestick.Ohlcvs.Ohlcv>  csOld = new ArrayList<Candlestick.Ohlcvs.Ohlcv>();
			if (ct == CandleType._1DAY){
				csOld = bb.getCandlestick(cp, ct, (Integer.parseInt(strYYYYMMDD) -1) + "").candlestick[0].getOhlcvList();
			}else{
				csOld = bb.getCandlestick(cp, ct, DateAndTime.getAddDate(strYYYYMMDD, -1, DateAndTime.DATE) ).candlestick[0].getOhlcvList();
			}
			// 過去の日付から取得する
			for(int i=csOld.size()-(size-count);i<csOld.size();i++){
				returnRs.add(csOld.get(i));
			}
			// 今日日付の値から取得する
			for(int i=0;i<count;i++){
				returnRs.add(cs.get(i));
			}
		}else{
			for(int i=count-size;i<count;i++){
				returnRs.add(cs.get(i));
			}
		}

		return returnRs;
	}

	/**
	 * ローソクのデータをListにつめて返す
	 * @param bb
	 * @param cp			種類（CurrencyPair.BTC_JPY）
	 * @param ct			キャンドルタイプ(CandleType._1DAY)
	 * @param strYYYYMMDD	日付
	 * @return
	 * @throws Exception
	 */
	public List<Candlestick.Ohlcvs.Ohlcv> getCs(Bitbankcc bb,CurrencyPair cp,CandleType ct,String strYYYYMMDD) throws Exception{
		if( ct == CandleType._1DAY ){
			strYYYYMMDD = strYYYYMMDD.substring(0,4);
		}
		List<Candlestick.Ohlcvs.Ohlcv>  cs = bb.getCandlestick(cp, ct, strYYYYMMDD).candlestick[0].getOhlcvList();
		return cs;
	}

	/**
	 * Candlestickの内容を全て表示する
	 * @param cs
	 */
	public void showCandlestick(List<Candlestick.Ohlcvs.Ohlcv> cs) {
        for (int i=0;i<cs.size();i++) {
	    		System.out.println(cs.get(i).toString());
	    }
	}

	/**
	 * Candlestickの内容を最新のcount分表示する
	 * @param cs
	 * @param count	表示したい最新の行数
	 */
	public void showCandlestick(List<Candlestick.Ohlcvs.Ohlcv> cs, int count) {
		if(count > cs.size()){
			count = cs.size();
		}
        for (int i=cs.size()-count;i<cs.size();i++) {
	    		System.out.println(cs.get(i).toString());
	    }
	}

	/**
	 * 対象の時間をピンポイントで取得してローソクで返す
	 * @param cs
	 * @param strYYYYMMDDHHMMSS
	 * @return　ローソクのリスト
	 * @throws Exception
	 */
	public List<Candlestick.Ohlcvs.Ohlcv> getCandlestickYYYYMMDDHHMMSS(List<Candlestick.Ohlcvs.Ohlcv> cs,String strYYYYMMDDHHMMSS) throws Exception {
		List<Candlestick.Ohlcvs.Ohlcv> rcs = new ArrayList<>();
        for(int i=0;i<cs.size();i++){
        	if(cs.get(i).date.compareTo(DateAndTime.getYYYYMMDDHHMMSSDate(strYYYYMMDDHHMMSS))==0){
        		rcs.add(cs.get(i));
        	}
        }
        return rcs;
	}

}
