package org.apache.flink.learn.example.WatermarkStrategy;


interface WatermarkStrategy<T> extends WatermarkGeneratorSupplier<T>, TimestampAssignerSupplier<T>{


    static <T> WatermarkStrategy<T> forBoundedOutOfOrderness(long duration) {
        return (ctx) -> new BoundedOutOfOrdernessWatermarks<>(duration);
    }

    static <T> WatermarkStrategy<T> forGenerator(WatermarkGeneratorSupplier<T> generatorSupplier) {
        return generatorSupplier::createWatermarkGenerator;
    }

    @Override
    default TimestampAssigner<T> createTimestampAssigner(TimestampAssignerSupplier.Context context) {
        return new RecordTimestampAssigner<>();
    }

}
