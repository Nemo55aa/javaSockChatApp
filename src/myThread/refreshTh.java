package myThread;

public class refreshTh extends Thread {
    private int intValue;

    public refreshTh(){
        this.intValue = 0;
    }
    public refreshTh(int intval_in){
        this.intValue = intval_in;
    }
    public void setIntval(int val_in){
        this.intValue = val_in;
    }

    @Override
    public void run(){
        
        for(int i = 1; i <= 50; i++){
            System.out.print("myThread.intValue is: ");
            System.out.println(this.intValue);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
    
}