package org.apache.flink.learn.example.WatermarkStrategy;

public interface TimestampAssigner<T> {
    long NO_TIMESTAMP = Long.MIN_VALUE;

    long extractTimestamp(T element, long recordTimestamp);
}
