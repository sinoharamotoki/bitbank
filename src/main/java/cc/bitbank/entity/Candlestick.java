package cc.bitbank.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by tanaka on 2017/04/12.
 */
public class Candlestick extends Data {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Ohlcvs {
        public BigDecimal[][] ohlcv;
        public String type;

        public class Ohlcv {
            @JsonIgnore
            public BigDecimal open;
            @JsonIgnore
            public BigDecimal high;
            @JsonIgnore
            public BigDecimal low;
            @JsonIgnore
            public BigDecimal close;
            @JsonIgnore
            public BigDecimal volume;
            @JsonIgnore
            public Date date;

            public Ohlcv(BigDecimal o, BigDecimal h, BigDecimal l, BigDecimal c, String v, long d) {
                this.open = o;
                this.high = h;
                this.low = l;
                this.close = c;
                this.volume = new BigDecimal(v);
                this.date = new Date(d);
            }

            public String toString() {
                return "[Ohlcv] open " + open + ", high " + high + ", low " + low + ", close " + close +
                        ", volume " + volume + ", date " + date;
             }
        }

        public List<Ohlcv> getOhlcvList() {
			List<Ohlcv> list = new ArrayList<Ohlcv>();
            for(BigDecimal[] i : this.ohlcv) {
                list.add(
                    new Ohlcv(i[0], i[1], i[2], i[3],
                        i[4].toString(), i[5].longValue())
                );
            }
            return list;
        }

    }

    public Ohlcvs[] candlestick;
    public Date timestamp;

    Candlestick() {}
    Candlestick(Ohlcvs[] o) {
        this.candlestick = o;
    }
}
