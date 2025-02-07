package org.apache.flink.learn.example.WatermarkStrategy;

import java.io.Serializable;

public interface TimestampAssignerSupplier<T> extends Serializable {

    TimestampAssigner<T> createTimestampAssigner(Context context);
    interface Context{}
}
