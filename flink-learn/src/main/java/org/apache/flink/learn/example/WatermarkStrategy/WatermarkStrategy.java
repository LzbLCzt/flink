package org.apache.flink.learn.example.WatermarkStrategy;

interface WatermarkStrategy<T> extends WatermarkGeneratorSupplier<T> {
    WatermarkGenerator<T> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context);

    static <T> WatermarkStrategy<T> forBoundedOutOfOrderness(long maxOutOfOrderness) {
        return (ctx) -> new BoundedOutOfOrdernessWatermarks<T>(maxOutOfOrderness);
    }
}
