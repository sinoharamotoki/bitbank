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
import cc.bitbank.util.Bb;
import cc.bitbank.util.CandlestickLogic;
import cc.bitbank.util.DateAndTime;
import cc.bitbank.util.Rsi;
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
            Bitbankcc bitbankcc = new Bitbankcc();

            // 表示するためのロジック
            ShowData showData = new ShowData();
            CandlestickLogic csLogic = new CandlestickLogic();

            // 処理の当日日付を取得
            String strYYYYMMDD = DateAndTime.getBaseDate();

            // Decimalの値の表示のフォーマット定義
            DecimalFormat format = new DecimalFormat("#.#");
            // 小数点以下の最小値
            format.setMinimumFractionDigits(2);


            // 購入する際には、必要なキーみたい。。。
            //bb.setKey(rb.getString("key"), rb.getString("secret"));
            bitbankcc.setKey("key", "secret");

            // ティッカー情報を返す（トップページの大事な情報）
            Ticker ticker = bitbankcc.getTicker(cp);
            showData.showTicker(ticker);

            // 毎日の終値を取得するために、ローソクを設定
            List<Candlestick.Ohlcvs.Ohlcv>  cs = csLogic.getCs(bitbankcc, cp, CandleType._1DAY, strYYYYMMDD,2);
            BigDecimal diffNum = ticker.last.subtract(cs.get(0).close);
            BigDecimal diffPer = diffNum.divide(cs.get(0).close, 5, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
            System.out.println("前日比：" + diffNum);
            System.out.println("前日比%：" + format.format(diffPer));

            // RSIを取得する
//            Rsi rsi = new Rsi();
//            System.out.println("RSI_1DAY:" + format.format(rsi.getRsi_1DAY(bitbankcc, cp)));
//            System.out.println("RSI_1HOUR:" + format.format(rsi.getRsi_1HOUR(bitbankcc, cp)));
//            System.out.println("RSI_15MIN:" + format.format(rsi.getRsi_15MIN(bitbankcc, cp)));
//            System.out.println("RSI_5MIN:" + format.format(rsi.getRsi_5MIN(bitbankcc, cp)));
//            System.out.println("RSI_1MIN:" + format.format(rsi.getRsi_1MIN(bitbankcc, cp)));

            // BBを取得する
            Bb bb = new Bb();
            BigDecimal bbVal[] = bb.getBb_1DAY(bitbankcc, cp , 9);
            System.out.println("BB+1:" + bbVal[0]);
            System.out.println("BB-1:" + bbVal[1]);
            System.out.println("BB+2:" + bbVal[2]);
            System.out.println("BB-2:" + bbVal[3]);
            System.out.println("BB+3:" + bbVal[4]);
            System.out.println("BB-3:" + bbVal[5]);




            // 板情報を返す（注文に出てくる数字の一覧だと思う）
            Depth depth = bitbankcc.getDepth(cp);
            // showData.showDepth(depth);

            // 最新の全約定履歴を返す(歩み値だと思う)
            Transactions.Transaction[] ts = bitbankcc.getTransaction(cp).transactions;
            // showData.showTransaction(ts);

            // 過去分の歩み値(件数多すぎて表示はさせないほうが無難かも)
            String strYYYYMMDDOLD = DateAndTime.getAddDate(DateAndTime.getBaseDate(), -1, DateAndTime.DATE); // 過去の日付しか指定できない
            ts = bitbankcc.getTransaction(cp, strYYYYMMDDOLD).transactions;
            // showData.showTransaction(ts,strYYYYMMDD);


            // ローソクの情報を取得
//            cs = csLogic.getCs(bb, cp, CandleType._1DAY, strYYYYMMDD,5);
//            csLogic.showCandlestick(cs);
//
//            cs = csLogic.getCs(bb, cp, CandleType._1MIN, strYYYYMMDD);
//            csLogic.showCandlestick(cs, 5);

//            csLogic.showCandlestick(cs);

            //showData.showCandlestick(cs);

            //showData.showCandlestick(showData.getCandlestickToday(cs, strYYYYMMDD));

            //showData.showCandlestick(showData.getCandlestickNewData(cs, 5));

            //csLogic.showCandlestick(cs);


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
