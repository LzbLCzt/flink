package org.apache.flink.learn.example.mailbox;

public class MailBoxProcessors {
    protected final MailBoxDefaultAction action;

    public MailBoxProcessors(MailBoxDefaultAction action) {
        this.action = action;
    }
}
