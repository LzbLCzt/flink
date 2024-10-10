package org.apache.flink.learn.example.WatermarkStrategy;

public class BoundedOutOfOrdernessWatermarks<T> implements WatermarkGenerator<T>{

    private long maxTimestamp;

    public BoundedOutOfOrdernessWatermarks(long maxOutOfOrderness) {
        this.maxTimestamp = maxOutOfOrderness;
    }
}
