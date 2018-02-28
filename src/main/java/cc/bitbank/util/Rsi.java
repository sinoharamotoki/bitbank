package cc.bitbank.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cc.bitbank.Bitbankcc;
import cc.bitbank.entity.Candlestick;
import cc.bitbank.entity.enums.CandleType;
import cc.bitbank.entity.enums.CurrencyPair;

public class Rsi {

	CandlestickLogic candlestickLogic;



	public Rsi() {
		candlestickLogic = new CandlestickLogic();
	}

	public BigDecimal getRsi_1DAY(Bitbankcc bb,CurrencyPair cp) throws Exception{
		String strYYYYMMDD = DateAndTime.getBaseDate();
		List<Candlestick.Ohlcvs.Ohlcv>  cs = candlestickLogic.getCs(bb,cp,CandleType._1DAY,strYYYYMMDD.substring(0, 4),15);
		return getRsi(cs);
	}

	public BigDecimal getRsi_1HOUR(Bitbankcc bb,CurrencyPair cp) throws Exception{
		String strYYYYMMDD = DateAndTime.getBaseDate();
		List<Candlestick.Ohlcvs.Ohlcv>  cs = candlestickLogic.getCs(bb, cp, CandleType._1HOUR, strYYYYMMDD,15);
		return getRsi(cs);
	}

	public BigDecimal getRsi_15MIN(Bitbankcc bb,CurrencyPair cp) throws Exception{
		String strYYYYMMDD = DateAndTime.getBaseDate();
		List<Candlestick.Ohlcvs.Ohlcv>  cs = candlestickLogic.getCs(bb, cp, CandleType._15MIN, strYYYYMMDD,15);
		return getRsi(cs);
	}

	public BigDecimal getRsi_5MIN(Bitbankcc bb,CurrencyPair cp) throws Exception{
		String strYYYYMMDD = DateAndTime.getBaseDate();
		List<Candlestick.Ohlcvs.Ohlcv>  cs = candlestickLogic.getCs(bb, cp, CandleType._5MIN, strYYYYMMDD,15);
		return getRsi(cs);
	}

	public BigDecimal getRsi_1MIN(Bitbankcc bb,CurrencyPair cp) throws Exception{
		String strYYYYMMDD = DateAndTime.getBaseDate();
		List<Candlestick.Ohlcvs.Ohlcv>  cs = candlestickLogic.getCs(bb, cp, CandleType._1MIN, strYYYYMMDD,15);
		return getRsi(cs);
	}

	public BigDecimal getRsi(List<Candlestick.Ohlcvs.Ohlcv>  cs ){
        // 15日間の値を取得して、配列に格納
        int count = 0;
        BigDecimal bc15Day[] = new BigDecimal[15];
         // 金額の取得
        for(int i=-14;i<=0;i++) {
            	bc15Day[count]=cs.get(cs.size()+i-1).close;
            	//System.out.println(cs.get(cs.size()+i-1).date + " " + bc15Day[count]);
            	count++;

        }

        // RSIを求めたい
        // 一日目はdiffNumにて算出済み
        BigDecimal plus = BigDecimal.valueOf(0);
        BigDecimal minus = BigDecimal.valueOf(0);

        //HP検証用

//        bc15Day[0]=BigDecimal.valueOf(1000);
//        bc15Day[1]=BigDecimal.valueOf(1020);
//        bc15Day[2]=BigDecimal.valueOf(1010);
//        bc15Day[3]=BigDecimal.valueOf(1030);
//        bc15Day[4]=BigDecimal.valueOf(1040);
//        bc15Day[5]=BigDecimal.valueOf(1050);
//        bc15Day[6]=BigDecimal.valueOf(1080);
//        bc15Day[7]=BigDecimal.valueOf(1070);
//        bc15Day[8]=BigDecimal.valueOf(1050);
//        bc15Day[9]=BigDecimal.valueOf(1090);
//        bc15Day[10]=BigDecimal.valueOf(1100);
//        bc15Day[11]=BigDecimal.valueOf(1120);
//        bc15Day[12]=BigDecimal.valueOf(1110);
//        bc15Day[13]=BigDecimal.valueOf(1120);
//        bc15Day[14]=BigDecimal.valueOf(1100);




        for(int i=0;i<14;i++) {
        	BigDecimal diffNumOld = bc15Day[i+1].subtract(bc15Day[i]);
        	//System.out.println(bc15Day[i+1] + "-" + bc15Day[i] + "=" + diffNumOld);
        	if(diffNumOld.compareTo(BigDecimal.valueOf(0))>0){
            	plus = plus.add(diffNumOld);
            }else{
            	minus = minus.add(diffNumOld);
            }
            //System.out.println(i + " plus:" + plus + " minus:" + minus);
        }

        plus = plus.divide(BigDecimal.valueOf(14), 4 ,BigDecimal.ROUND_HALF_UP);
        minus = minus.divide(BigDecimal.valueOf(14), 3 ,BigDecimal.ROUND_HALF_UP);
        //System.out.println("plus:" + plus + " minus:" + minus);
        minus = plus.add(minus.abs());
        //System.out.println("plus:" + plus + " minus:" + minus);

        BigDecimal RSI = plus.divide(minus, 4 ,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
       return RSI;


	}

}
