package org.apache.flink.learn.example.WatermarkStrategy;

import org.apache.flink.api.common.eventtime.WatermarkOutput;

interface WatermarkGenerator<T> {

    void onEvent(T event, long eventTimestamp, WatermarkOutput output);

    void onPeriodicEmit(WatermarkOutput output);

}
