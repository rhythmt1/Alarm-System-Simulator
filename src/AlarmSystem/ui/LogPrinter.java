package AlarmSystem.ui;

import AlarmSystem.model.EventLog;
import AlarmSystem.model.exception.LogException;

public interface LogPrinter {
    void printLog(EventLog el) throws LogException;

}
