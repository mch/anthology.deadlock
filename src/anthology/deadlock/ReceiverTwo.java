package anthology.deadlock;

public class ReceiverTwo
{
    private int numReceived;
    private ReceiverOne other;

    public ReceiverTwo(ReceiverOne one)
    {
        numReceived = 0;
        other = one;
    }

    public synchronized void handleMessage()
    {
        // do other stuff
        numReceived++;
        other.callback();
    }

    public synchronized int getNumReceived()
    {
        return numReceived;
    }

}
