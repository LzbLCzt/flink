package org.apache.flink.learn.example.WatermarkStrategy;

import org.apache.flink.api.common.eventtime.TimestampAssigner;

interface WatermarkGeneratorSupplier<T> {

    WatermarkGenerator<T> createWatermarkGenerator(Context context);

    interface Context {

    }
}
