package org.apache.flink.learn.example.WatermarkStrategy;

interface WatermarkGeneratorSupplier<T> {
    WatermarkGenerator<T> createWatermarkGenerator(Context context);

    interface Context {
    }
}
