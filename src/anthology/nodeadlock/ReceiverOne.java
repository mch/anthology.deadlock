package anthology.nodeadlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ReceiverOne
{
    private int numExpected;
    private int numReceived;
    private Lock lock;
    private Condition condition;

    public ReceiverOne(int numExpected, Lock lock, Condition condition)
    {
        numReceived = 0;
        this.numExpected = numExpected;
        this.lock = lock;
        this.condition = condition;
    }

    public synchronized boolean isComplete()
    {
        return numExpected == numReceived;
    }

    public synchronized void handleMessage()
    {
        // do other stuff
        numReceived++;

        lock.lock();
        try
        {
            condition.signal();
        }
        finally
        {
            lock.unlock();
        }
    }

    public synchronized int getNumReceived()
    {
        return numReceived;
    }
}
