package org.apache.flink.learn.example.WatermarkStrategy;

public final class RecordTimestampAssigner<E> implements TimestampAssigner<E>{
    @Override
    public long extractTimestamp(E element, long recordTimestamp) {
        return recordTimestamp;
    }
}
