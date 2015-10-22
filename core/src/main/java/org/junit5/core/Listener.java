package org.junit5.core;

public interface Listener {

    void onTestStart(String id);

    void onTestComplete(String id);

    void onTestFailure(String id);

}
