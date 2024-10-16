/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.runtime.asyncprocessing;

import javax.annotation.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * A context that preserves the necessary variables required by each operation, all operations for
 * one record will share the same element context.
 *
 * <p>Reference counting mechanism, please refer to {@link ContextStateFutureImpl}.
 *
 * @param <K> The type of the key inside the record.
 */
public class RecordContext<K> extends ReferenceCounted<RecordContext.DisposerRunner> {
    /** The empty record for timer and non-record input usage. */
    static final Object EMPTY_RECORD = new Object();

    /** The record to be processed. */
    private final Object record;

    /** The key inside the record. */
    private final K key;

    /** Whether this Record(Context) has occupied the corresponding key. */
    private volatile boolean keyOccupied;

    /**
     * The disposer for disposing this context. This should be invoked in {@link
     * #referenceCountReachedZero()}, which may be called once the ref count reaches zero in any
     * thread.
     */
    private final Consumer<RecordContext<K>> disposer;

    RecordContext(Object record, K key, Consumer<RecordContext<K>> disposer) {
        super(0);
        this.record = record;
        this.key = key;
        this.keyOccupied = false;
        this.disposer = disposer;
    }

    public Object getRecord() {
        return record;
    }

    public K getKey() {
        return this.key;
    }

    /** Check if this context has occupied the key. */
    boolean isKeyOccupied() {
        return keyOccupied;
    }

    /** Set the flag that marks this context has occupied the corresponding key. */
    void setKeyOccupied() {
        keyOccupied = true;
    }

    @Override
    protected void referenceCountReachedZero(@Nullable DisposerRunner disposerRunner) {
        if (keyOccupied) {
            keyOccupied = false;
            if (disposerRunner != null) {
                disposerRunner.runDisposer(() -> disposer.accept(this));
            } else {
                disposer.accept(this);
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(record, key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecordContext<?> that = (RecordContext<?>) o;
        if (!Objects.equals(record, that.record)) {
            return false;
        }
        return Objects.equals(key, that.key);
    }

    @Override
    public String toString() {
        return "RecordContext{"
                + "record="
                + record
                + ", key="
                + key
                + ", occupied="
                + keyOccupied
                + ", ref="
                + getReferenceCount()
                + "}";
    }

    public interface DisposerRunner {
        void runDisposer(Runnable task);
    }
}
