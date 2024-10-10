package org.apache.flink.learn.example.mailbox;

public interface MailBoxDefaultAction {

    void runDefaultAction(Controller controller) throws Exception;

    interface Controller {
    }
}
