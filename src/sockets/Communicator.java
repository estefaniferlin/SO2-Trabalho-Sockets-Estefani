package sockets;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author 20202PF.CC0011
 */
public class Communicator extends Thread{
    
    public static final byte EXTRATO = 1;
    public static final byte SAQUE = 2;
    public static final byte DEPOSITO = 3;
    public static final byte CONNECTION_PORT = 4;
    private SocketChannel canalCliente = null;
    BlockingQueue<ByteBuffer> incoming = new LinkedBlockingQueue<ByteBuffer>();
    
    public void connectServer(String hostDescription) {
        try {
            String vet[] = hostDescription.split(":");
            String hostname = vet[0];
            int port = Integer.parseInt(vet[1].trim());
            canalCliente = SocketChannel.open(new InetSocketAddress(hostname, port));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        //rodaListener();
           
    }
    
    public SocketChannel getSocket() {
        // TODO Auto-generated method stub
        try {
            return canalCliente;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String clientRemoteChannelDesc() {
        try {
            String hostAddress = canalCliente.socket().getInetAddress().getHostAddress();
            String portAddress = Integer.toString(canalCliente.socket().getPort());
            return hostAddress + ":" + portAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ByteBuffer recebeMensagem() {
        try{
            return incoming.take();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
    
}
