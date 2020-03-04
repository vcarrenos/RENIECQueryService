package pe.gob.midis.sisfoh.connection;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQSimpleConnectionManager;

public class MQPoolManager
{

    public static MQSimpleConnectionManager myConnMan = null;

    public synchronized static void init()
    {
        if (myConnMan == null) {
            myConnMan = new MQSimpleConnectionManager();
            myConnMan.setActive(MQSimpleConnectionManager.MODE_AUTO);
            myConnMan.setTimeout(60000); // 1 min
            myConnMan.setMaxConnections(100);// setMaxConnections(arg0)setHighThreshold(50);
            myConnMan.setMaxUnusedConnections(10);// setMaxConnections(arg0)setHighThreshold(50);
            MQEnvironment.addConnectionPoolToken();
        }
    }
}
