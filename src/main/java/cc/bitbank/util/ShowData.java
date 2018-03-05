<<<<<<< HEAD
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

	public String showTicker(Ticker ticker) {
		StringBuffer sb = new StringBuffer();
        sb.append("最新取引価格：" + ticker.last + "\n");
        //sb.append("歩み値の買い注文の最高値：" + ticker.buy + "\n");
        //sb.append("歩み値の売り注文の最安値：" + ticker.sell + "\n");
        sb.append("安値：" + ticker.low + "\n");
        sb.append("高値：" + ticker.high + "\n");
        //sb.append("取得日：" + ticker.timestamp + "\n");
        //sb.append("出来高：" + ticker.vol + "\n");
        return sb.toString();
	}

	public String showDepth(Depth depth) {
		StringBuffer sb = new StringBuffer();
		//　多分、注文に表示されている部分だと思う。
        for(int i=0;i<depth.getAsks().length;i++) {
        	sb.append("売り板 i:" + i + "  :" + depth.getAsks()[i][0] + " " + depth.getAsks()[i][1] + "\n");
        }

        for(int i=0;i<depth.getBids().length;i++) {
        	sb.append("買い板 i:" + i + "  :" + depth.getBids()[i][0] + " " + depth.getBids()[i][1] + "\n");
        }
        return sb.toString();
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

	public String showTransaction(Transactions.Transaction[] ts) {
		StringBuffer sb = new StringBuffer();
        for(int i=0;i<ts.length;i++) {
    			sb.append("直近の歩み値：" + ts[i] + "\n");
        }
        return sb.toString();
	}

	public String showTransaction(Transactions.Transaction[] ts,String strYYYYMMDD) {
		StringBuffer sb = new StringBuffer();
        for(int i=0;i<ts.length;i++) {
    			sb.append(strYYYYMMDD + "歩み値：" + ts[i] + "\n");
        }
        return sb.toString();
	}





}
=======
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

	public String showTicker(Ticker ticker) {
		StringBuffer sb = new StringBuffer();
        sb.append("最新取引価格：" + ticker.last + "\n");
        //sb.append("歩み値の買い注文の最高値：" + ticker.buy + "\n");
        //sb.append("歩み値の売り注文の最安値：" + ticker.sell + "\n");
        sb.append("安値：" + ticker.low + "\n");
        sb.append("高値：" + ticker.high + "\n");
        //sb.append("取得日：" + ticker.timestamp + "\n");
        //sb.append("出来高：" + ticker.vol + "\n");
        return sb.toString();
	}

	public String showDepth(Depth depth) {
		StringBuffer sb = new StringBuffer();
		//　多分、注文に表示されている部分だと思う。
        for(int i=0;i<depth.getAsks().length;i++) {
        	sb.append("売り板 i:" + i + "  :" + depth.getAsks()[i][0] + " " + depth.getAsks()[i][1] + "\n");
        }

        for(int i=0;i<depth.getBids().length;i++) {
        	sb.append("買い板 i:" + i + "  :" + depth.getBids()[i][0] + " " + depth.getBids()[i][1] + "\n");
        }
        return sb.toString();
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

	public String showTransaction(Transactions.Transaction[] ts) {
		StringBuffer sb = new StringBuffer();
        for(int i=0;i<ts.length;i++) {
    			sb.append("直近の歩み値：" + ts[i] + "\n");
        }
        return sb.toString();
	}

	public String showTransaction(Transactions.Transaction[] ts,String strYYYYMMDD) {
		StringBuffer sb = new StringBuffer();
        for(int i=0;i<ts.length;i++) {
    			sb.append(strYYYYMMDD + "歩み値：" + ts[i] + "\n");
        }
        return sb.toString();
	}





}
>>>>>>> branch 'master' of https://github.com/sinoharamotoki/bitbank.git
