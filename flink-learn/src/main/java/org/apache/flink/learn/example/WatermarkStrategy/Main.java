package org.apache.flink.learn.example.WatermarkStrategy;

import org.apache.flink.learn.example.core.CloudInstanceMetric;

public class Main {
    public static void main(String[] args) {
        WatermarkStrategy<CloudInstanceMetric> watermarkStrategy = WatermarkStrategy.<CloudInstanceMetric>forBoundedOutOfOrderness(
                100L);
    }
}
