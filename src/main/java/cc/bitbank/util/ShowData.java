package cc.bitbank.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cc.bitbank.entity.Candlestick;
import cc.bitbank.entity.Depth;
import cc.bitbank.entity.Ticker;
import cc.bitbank.entity.Transactions;

public class ShowData {

	public void showTicker(Ticker ticker) {
        System.out.println("最新取引価格：" + ticker.last);
        System.out.println("歩み値の買い注文の最高値：" + ticker.buy);
        System.out.println("歩み値の売り注文の最安値：" + ticker.sell);
        System.out.println("安値：" + ticker.low);
        System.out.println("高値：" + ticker.high);
        System.out.println("取得日：" + ticker.timestamp);
        System.out.println("出来高：" + ticker.vol);
	}

	public void showDepth(Depth depth) {
		//　多分、注文に表示されている部分だと思う。
        for(int i=0;i<depth.getAsks().length;i++) {
            	System.out.println("売り板 i:" + i + "  :" + depth.getAsks()[i][0] + " " + depth.getAsks()[i][1]);
        }

        for(int i=0;i<depth.getBids().length;i++) {
            	System.out.println("買い板 i:" + i + "  :" + depth.getBids()[i][0] + " " + depth.getBids()[i][1]);
        }
        /*
        ↑の正式なループ
        for(int i=0;i<depth.getAsks().length;i++) {
        		for(int j=0;j<depth.getAsks()[i].length;j++)
            		// jは0,1しか入らないらしい。。。　固定で出すか
            		System.out.println("売り板 i:" + i + " j:" + j + "  :" + depth.getAsks()[i][j]);
        }

        for(int i=0;i<depth.getBids().length;i++) {
        		for(int j=0;j<depth.getAsks()[i].length;j++)
        			// jは0,1しか入らないらしい。。。　固定で出すか
            		System.out.println("買い板 i:" + i + " j:" + j + "  :" + depth.getBids()[i][j]);
        }*/
	}

	public void showTransaction(Transactions.Transaction[] ts) {
        for(int i=0;i<ts.length;i++) {
    			System.out.println("直近の歩み値：" + ts[i]);
        }
	}

	public void showTransaction(Transactions.Transaction[] ts,String strYYYYMMDD) {
        for(int i=0;i<ts.length;i++) {
    			System.out.println(strYYYYMMDD + "歩み値：" + ts[i]);
        }
	}

	public void showCandlestick(List<Candlestick.Ohlcvs.Ohlcv> cs) {
        for (int i=0;i<cs.size();i++) {
	    		System.out.println(cs.get(i).toString());
	    }
	}

	public List<Candlestick.Ohlcvs.Ohlcv> getCandlestickNewData(List<Candlestick.Ohlcvs.Ohlcv> cs,int intCount){
		List<Candlestick.Ohlcvs.Ohlcv> rcs = new ArrayList<>();
		int intLoop = 1;
		if( cs.size()>intCount){
			intLoop = cs.size()-intCount;
		}
			for(int i=cs.size()-1;i>=intLoop;i--){
				rcs.add(cs.get(i));
			}
		return rcs;
	}

	public List<Candlestick.Ohlcvs.Ohlcv> getCandlestickYYYYMMDDHHMMSS(List<Candlestick.Ohlcvs.Ohlcv> cs,String strYYYYMMDDHHMMSS) throws Exception {
		List<Candlestick.Ohlcvs.Ohlcv> rcs = new ArrayList<>();
        for(int i=0;i<cs.size();i++){
        	if(cs.get(i).date.compareTo(DateAndTime.getYYYYMMDDHHMMSSDate(strYYYYMMDDHHMMSS))==0){
        		rcs.add(cs.get(i));
        	}
        }
        return rcs;
	}

	public List<Candlestick.Ohlcvs.Ohlcv> getCandlestickToday(List<Candlestick.Ohlcvs.Ohlcv> cs,String strYYYYMMDD) throws Exception {
		List<Candlestick.Ohlcvs.Ohlcv> rcs = new ArrayList<>();
        for(int i=0;i<cs.size();i++){
        	if(cs.get(i).date.compareTo(DateAndTime.getYYYYMMDDHHMMSSDate(strYYYYMMDD + "090000"))==0){
        		rcs.add(cs.get(i));
        	}
        }
        return rcs;
	}



}
