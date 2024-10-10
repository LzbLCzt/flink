package org.apache.flink.learn.example.WatermarkStrategy;

public class AscendingTimestampsWatermarks<T> extends BoundedOutOfOrdernessWatermarks<T> {

    public AscendingTimestampsWatermarks() {
        super(0L);
    }
}
