package org.apache.flink.learn.example.WatermarkStrategy;

final class WatermarkStrategyWithTimestampAssigner<T> implements WatermarkStrategy<T> {

    private final WatermarkStrategy<T> baseStrategy;
    private final TimestampAssignerSupplier<T> timestampAssigner;

    WatermarkStrategyWithTimestampAssigner(WatermarkStrategy<T> baseStrategy, TimestampAssignerSupplier<T> timestampAssigner) {
        this.baseStrategy = baseStrategy;
        this.timestampAssigner = timestampAssigner;
    }

    @Override
    public WatermarkGenerator<T> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
        return baseStrategy.createWatermarkGenerator(context);
    }

    @Override
    public TimestampAssigner<T> createTimestampAssigner(TimestampAssignerSupplier.Context context) {
        return timestampAssigner.createTimestampAssigner(context);
    }
}
