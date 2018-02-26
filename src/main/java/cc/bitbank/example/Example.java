package cc.bitbank.example;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import cc.bitbank.Bitbankcc;
import cc.bitbank.entity.Candlestick;
import cc.bitbank.entity.Depth;
import cc.bitbank.entity.Ticker;
import cc.bitbank.entity.Transactions;
import cc.bitbank.entity.enums.CandleType;
import cc.bitbank.entity.enums.CurrencyPair;
import cc.bitbank.exception.BitbankException;
import cc.bitbank.util.DateAndTime;
import cc.bitbank.util.ShowData;


/**
 * Created by shinohara on 2018/02/24.
 * 本システムは日付のやりとりはYYYYMMDD（20180224）で必ず行ってください。
 */
public class Example {
    public static void main(String args[]) {

    	//propertiesファイル配置ディレクトリ
        //String dir = "C:/Users/K060404/Documents/GitHub/bitbank/src/resource";
        //propertiesファイル名(.propertiesは不要)
        //String source = "example";

        try {

        		// どのコインを取得するか
            CurrencyPair cp = CurrencyPair.BTC_JPY;

            // 取得処理（購入する必要がなければいらいない！）
            //URLClassLoader urlLoader = new URLClassLoader(new URL[]{new File(dir).toURI().toURL()});
            //ResourceBundle rb = ResourceBundle.getBundle(source, Locale.getDefault(), urlLoader);
            Bitbankcc bb = new Bitbankcc();

            // 表示するためのロジック
            ShowData showData = new ShowData();

            // 処理の当日日付を取得
            String strYYYYMMDD = DateAndTime.getBaseDate();

            // Decimalの値の表示のフォーマット定義
            DecimalFormat format = new DecimalFormat("#.#");
            // 小数点以下の最小値
            format.setMinimumFractionDigits(2);


            // 購入する際には、必要なキーみたい。。。
            //bb.setKey(rb.getString("key"), rb.getString("secret"));
            bb.setKey("key", "secret");

            // ティッカー情報を返す（トップページの大事な情報）
            Ticker ticker = bb.getTicker(cp);
            showData.showTicker(ticker);

            // 15日間の値を取得して、配列に格納
            int count = 0;
            BigDecimal bc15Day[] = new BigDecimal[16];
	         // 金額の取得
            List<Candlestick.Ohlcvs.Ohlcv>  cs = bb.getCandlestick(cp, CandleType._1DAY, strYYYYMMDD.substring(0, 4)).candlestick[0].getOhlcvList();
	        for(int i=-1;i>=-15;i--) {
	            	bc15Day[count]=cs.get(cs.size()+i-1).close;
	            	System.out.println(bc15Day[count]);
	            	count++;

	        }
	        BigDecimal diffNum = ticker.last.subtract(bc15Day[0] );
            BigDecimal diffPer = diffNum.divide(bc15Day[0], 5, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
            System.out.println("前日比：" + diffNum);
            System.out.println("前日比%：" + format.format(diffPer));

            // RSIを求めたい
            // 一日目はdiffNumにて算出済み
            BigDecimal plus = BigDecimal.valueOf(0);
            BigDecimal minus = BigDecimal.valueOf(0);
            for(int i=0;i<14;i++) {
            		BigDecimal diffNumOld = bc15Day[i].subtract(bc15Day[i+1]);
                if(diffNumOld.compareTo(BigDecimal.valueOf(0))>0){
                	plus = plus.add(diffNumOld);
                }else{
                	minus = minus.add(diffNumOld);
                }
                System.out.println(i + " plus:" + plus + " diffTwo:" + minus);
            }

            if(diffNum.compareTo(BigDecimal.valueOf(0))>0){
            	plus = plus.add(diffNum);
            }else{
            	minus = minus.add(diffNum);
            }
            System.out.println("plus:" + plus + " diffTwo:" + minus);
            //plus = plus.divide(BigDecimal.valueOf(14), 4 ,BigDecimal.ROUND_HALF_UP);
            //minus = minus.divide(BigDecimal.valueOf(14), 4 ,BigDecimal.ROUND_HALF_UP);
            minus = plus.add(minus.abs());
            System.out.println("plus:" + plus + " diffTwo:" + minus);

            BigDecimal RSI = plus.divide(minus, 4 ,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
            System.out.println("RSI:" + format.format(RSI));



            // 板情報を返す（注文に出てくる数字の一覧だと思う）
            Depth depth = bb.getDepth(cp);
            // showData.showDepth(depth);

            // 最新の全約定履歴を返す(歩み値だと思う)
            Transactions.Transaction[] ts = bb.getTransaction(cp).transactions;
            // showData.showTransaction(ts);

            // 過去分の歩み値(件数多すぎて表示はさせないほうが無難かも)
            String strYYYYMMDDOLD = DateAndTime.getAddDate(DateAndTime.getBaseDate(), -1, DateAndTime.DATE); // 過去の日付しか指定できない
            ts = bb.getTransaction(cp, strYYYYMMDDOLD).transactions;
            // showData.showTransaction(ts,strYYYYMMDD);


            // ローソクの情報を取得
            cs = bb.getCandlestick(cp, CandleType._1DAY, "2018").candlestick[0].getOhlcvList();

            cs = bb.getCandlestick(cp, CandleType._1MIN, strYYYYMMDD).candlestick[0].getOhlcvList();
            //showData.showCandlestick(cs);

            showData.showCandlestick(showData.getCandlestickToday(cs, strYYYYMMDD));

            //showData.showCandlestick(showData.getCandlestickNewData(cs, 5));

            cs = bb.getCandlestick(cp, CandleType._1MIN, strYYYYMMDDOLD).candlestick[0].getOhlcvList();
            showData.showCandlestick(showData.getCandlestickNewData(cs, 5));


            /*
            Assets as = bb.getAsset();
            System.out.println(as.assets[0]);
            System.out.println(as.assets[1]);
            System.out.println(as.assets[2]);
            System.out.println(as.assets[3]);

            Order order = bb.getOrder(CurrencyPair.BTC_JPY, 90956209);
            System.out.println(order);

            Order order2 = bb.sendOrder(CurrencyPair.BTC_JPY, BigDecimal.valueOf(10000), BigDecimal.valueOf(0.01), OrderSide.BUY, OrderType.LIMIT);
            System.out.println(order2);

            Order order3 = bb.cancelOrder(CurrencyPair.BTC_JPY, 129781978);
            System.out.println(order3);

            long[] ids = {129830841, 129830734};
            Orders orders = bb.cancelOrders(CurrencyPair.BTC_JPY, ids);
            System.out.println(orders.orders[0]);
            System.out.println(orders.orders[1]);

            long[] ids2 = {90956209, 90951996};
            Orders orders2 = bb.getOrders(CurrencyPair.BTC_JPY, ids2);
            System.out.println(orders2.orders[0]);
            System.out.println(orders2.orders[1]);

			Map<String, Long> option = new HashMap<String, Long>();
            option.put("count", 1L);
            option.put("since", 1490348550380L);
            // Option's parameter can be seen https://docs.bitbank.cc/#!/Order/active_orders
            Orders orders3 = bb.getActiveOrders(CurrencyPair.BTC_JPY, option);
            for(Order o : orders3.orders) {
                System.out.println(o);
            }

            Accounts accounts = bb.getWithdrawalAccounts("btc");
            for(Accounts.Account a : accounts.accounts) {
                System.out.println(a);
            }

            Withdraw w = bb.requestWithdraw("btc", "XXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXX",
                    BigDecimal.valueOf(0.005), "867005", "");
            System.out.println(w);
*/
        } catch (BitbankException e) {
            System.out.println(e.code);
        } catch (Exception e) {
            System.out.println("エラー " + e.getMessage());
            e.printStackTrace();
        }
    }
}
