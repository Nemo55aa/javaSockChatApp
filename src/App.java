// Kinda GUI thing (swingLib) imports here
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// for Socket com. importing here
import java.net.*;
import java.io.*;

// timer
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

// ownLib
import myThread.refreshTh;
import sockThread.*;

public class App {
    public static void main(String[] args) throws IOException   {
        MyFrame f = new MyFrame();
        
        f.l1.setText("waiting Connection from Client...");
        f.setVisible(true);
        

        
        Timer time = new Timer();


        time.scheduleAtFixedRate(new myTimerTsk(), 0, 5000);

        //System.out.println("Hello, World!");
    }
}

// ================== swing ================== 
class MyFrame extends JFrame implements ActionListener{
    mypanel panel;
    JButton readbutton, sendButton;
    JLabel l1;
    JTextField tf1;
    int width, height;
    
    srvSockTh srv;
    
    public MyFrame() {
        // ==================== STYLING WINDOW ====================
        {
        Container contentPane = getContentPane();
        readbutton = new JButton("read server's receiving Buffer");
        sendButton = new JButton("send text to Client");
        l1 = new JLabel("label");
        tf1 = new JTextField("input Text here!");
        panel = new mypanel();

        width = 640; height = 480;
        setSize(width, height);
        setTitle("My Window Application");
        
        contentPane.setLayout(new BorderLayout());
        contentPane.add("North", panel);
        contentPane.add("West",readbutton);
        contentPane.add("East",sendButton);
        contentPane.add("Center",l1);
        contentPane.add("South",tf1);

        readbutton.addActionListener(this);
        sendButton.addActionListener(this);
        addWindowListener(new MyWindowAdapter());
        }

        // ==================== STARTING SERVER ====================
        srv = new srvSockTh();
        srv.start();
        
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == readbutton) {
            String tmpString = srv.readLineStr();
            System.out.print("I read :");
            System.out.println(tmpString);
            //l1.setText("I read:" + tmpString);
            l1.setText(tmpString);
        }
        if (ae.getSource() == sendButton) {
            String tmpString = tf1.getText(); 
            srv.sendLineStr(tmpString);
            System.out.print("I sent :");
            System.out.println(tmpString);
        }
            
    }
}
/**
 * MyWindowAdapter extend
 */
class MyWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
class mypanel extends JPanel{
    public void paintComponent(Graphics g) {
    g.drawLine(0,0,200,200);
    }
}

class myTimerTsk extends TimerTask {

    // 実行回数
    static int i = 1;
  
    public void run() {
      try {
  
        System.out.println("-----------------------------------------");
        System.out.println(i + "回目タスク開始" + new Date());
  
        //3秒間停止する
        Thread.sleep(3000);
  
        System.out.println(i + "回目タスク終了" + new Date());
  
        i = i + 1;
  
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  
// ================== socket ================== 
/*
class GreetServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    //private acceptTh acTh;\
    String greeting;

    public String start(int port) {
        try{
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            greeting = in.readLine();
            if ("hello server".equals(greeting)) {
                System.out.println("hello client");
            }
            else {
                System.out.println("unrecognised greeting");
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        return greeting;
    }

    public void stop() {
        try{
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

 */