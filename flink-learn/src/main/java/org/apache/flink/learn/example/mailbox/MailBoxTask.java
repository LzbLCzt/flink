package org.apache.flink.learn.example.mailbox;

public class MailBoxTask {

    protected final MailBoxProcessors processors;

    protected void processInput(MailBoxDefaultAction.Controller controller) {
        System.out.println(controller);
    }

    protected MailBoxTask() {
        this.processors = new MailBoxProcessors(this::processInput);
    }
}
