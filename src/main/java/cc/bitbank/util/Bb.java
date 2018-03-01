package cc.bitbank.util;

import java.math.BigDecimal;
import java.util.List;

import cc.bitbank.Bitbankcc;
import cc.bitbank.entity.Candlestick;
import cc.bitbank.entity.enums.CandleType;
import cc.bitbank.entity.enums.CurrencyPair;

public class Bb {
	CandlestickLogic candlestickLogic;


	public Bb() {
		candlestickLogic = new CandlestickLogic();
	}

	public BigDecimal[] getBb_1DAY(Bitbankcc bb,CurrencyPair cp, int num) throws Exception{
		String strYYYYMMDD = DateAndTime.getBaseDate();
		List<Candlestick.Ohlcvs.Ohlcv>  cs = candlestickLogic.getCs(bb,cp,CandleType._1DAY,strYYYYMMDD.substring(0, 4),num);
		return getBb(cs);
	}

	public BigDecimal[] getBb_1HOUR(Bitbankcc bb,CurrencyPair cp, int num) throws Exception{
		String strYYYYMMDD = DateAndTime.getBaseDate();
		List<Candlestick.Ohlcvs.Ohlcv>  cs = candlestickLogic.getCs(bb, cp, CandleType._1HOUR, strYYYYMMDD,num);
		return getBb(cs);
	}

	public BigDecimal[] getBb_15MIN(Bitbankcc bb,CurrencyPair cp, int num) throws Exception{
		String strYYYYMMDD = DateAndTime.getBaseDate();
		List<Candlestick.Ohlcvs.Ohlcv>  cs = candlestickLogic.getCs(bb, cp, CandleType._15MIN, strYYYYMMDD,num);
		return getBb(cs);
	}

	public BigDecimal[] getBb_5MIN(Bitbankcc bb,CurrencyPair cp, int num) throws Exception{
		String strYYYYMMDD = DateAndTime.getBaseDate();
		List<Candlestick.Ohlcvs.Ohlcv>  cs = candlestickLogic.getCs(bb, cp, CandleType._5MIN, strYYYYMMDD,num);
		return getBb(cs);
	}

	public BigDecimal[] getBb_1MIN(Bitbankcc bb,CurrencyPair cp, int num) throws Exception{
		String strYYYYMMDD = DateAndTime.getBaseDate();
		List<Candlestick.Ohlcvs.Ohlcv>  cs = candlestickLogic.getCs(bb, cp, CandleType._1MIN, strYYYYMMDD,num);
		return getBb(cs);
	}

	public BigDecimal[] getBb(List<Candlestick.Ohlcvs.Ohlcv>  cs ){
        int count = 0;
        BigDecimal bcDay[] = new BigDecimal[cs.size()];
         // 金額の取得
        for(int i=-cs.size()+1;i<=0;i++) {
            	bcDay[count]=cs.get(cs.size()+i-1).close;
            	//System.out.println(cs.get(cs.size()+i-1).date + " " + bc15Day[count]);
            	count++;

        }


        //HP検証用
//
//        bcDay[0]=BigDecimal.valueOf(460000);
//        bcDay[1]=BigDecimal.valueOf(457000);
//        bcDay[2]=BigDecimal.valueOf(452000);
//        bcDay[3]=BigDecimal.valueOf(470000);
//        bcDay[4]=BigDecimal.valueOf(461000);
//        bcDay[5]=BigDecimal.valueOf(456000);
//        bcDay[6]=BigDecimal.valueOf(456000);
//        bcDay[7]=BigDecimal.valueOf(441000);
//        bcDay[8]=BigDecimal.valueOf(413000);

        BigDecimal average = BigDecimal.valueOf(0);
        for(int i=0;i<bcDay.length;i++){
        	average = average.add(bcDay[i]);
        }
        average = average.divide(BigDecimal.valueOf(bcDay.length), 3 ,BigDecimal.ROUND_HALF_UP);

        BigDecimal val = BigDecimal.valueOf(0);

        // 標準偏差の算出
        for(int i=0;i<bcDay.length;i++){
        	BigDecimal temp = bcDay[i].subtract(average);
        	temp = temp.multiply(temp);
        	val = val.add( temp );
        }
        double stdev = 0.0;
        stdev = Math.sqrt(val.doubleValue()/bcDay.length);

        BigDecimal BB[] = {
        		(average.add(BigDecimal.valueOf(stdev))).divide(BigDecimal.valueOf(1), 3 ,BigDecimal.ROUND_HALF_UP),
        		(average.subtract(BigDecimal.valueOf(stdev))).divide(BigDecimal.valueOf(1), 3 ,BigDecimal.ROUND_HALF_UP),
        		(average.add(BigDecimal.valueOf(stdev).multiply(BigDecimal.valueOf(2)))).divide(BigDecimal.valueOf(1), 3 ,BigDecimal.ROUND_HALF_UP),
        		(average.subtract(BigDecimal.valueOf(stdev).multiply(BigDecimal.valueOf(2)))).divide(BigDecimal.valueOf(1), 3 ,BigDecimal.ROUND_HALF_UP),
        		(average.add(BigDecimal.valueOf(stdev).multiply(BigDecimal.valueOf(3)))).divide(BigDecimal.valueOf(1), 3 ,BigDecimal.ROUND_HALF_UP),
        		(average.subtract(BigDecimal.valueOf(stdev).multiply(BigDecimal.valueOf(3)))).divide(BigDecimal.valueOf(1), 3 ,BigDecimal.ROUND_HALF_UP)

        		};
       return BB;


	}

	public BigDecimal getLine(BigDecimal[] bb,int line){
		BigDecimal returnBb = BigDecimal.valueOf(0);
		switch (line) {
		case 1:
			returnBb = bb[0];
			break;
		case -1:
			returnBb = bb[1];
			break;
		case 2:
			returnBb = bb[2];
			break;
		case -2:
			returnBb = bb[3];
			break;
		case 3:
			returnBb = bb[4];
			break;
		case -3:
			returnBb = bb[5];
			break;
		default:
			break;
		}

		return returnBb;
	}

}
