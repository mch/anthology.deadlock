package anthology.deadlock;

public class ReceiverOne
{
    private int numExpected;
    private int numReceived;
    private ReceiverTwo otherReceiver;

    public ReceiverOne(int numExpected)
    {
        this.numExpected = numExpected;
        numReceived = 0;
        otherReceiver = new ReceiverTwo(this);
    }

    public synchronized void handleMessage()
    {
        // do other stuff
        numReceived++;
        notifyAll();
    }

    public synchronized void callback()
    {
        // do other stuff
        notifyAll();
    }

    public synchronized boolean isComplete()
    {
        return numExpected == numReceived && numExpected == otherReceiver.getNumReceived();
    }

    public synchronized int getNumReceived1()
    {
        return numReceived;
    }

    public synchronized int getNumReceived2()
    {
        return otherReceiver.getNumReceived();
    }
}
