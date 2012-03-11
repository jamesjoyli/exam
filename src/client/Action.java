package client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import entity.Request;
import entity.Response;

public class Action {
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Response res;
	
	private BufferedReader br;
	private String ip,port;//ip��ַ�Ͷ˿�
	
	public Action(){
		try {
			//�������ļ���ȡip��ַ�Ͷ˿ڣ�����ֱ��д��
			br=new BufferedReader(new InputStreamReader(new FileInputStream("client.cfg")));
			ip=br.readLine();
			port=br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//ÿ����Ҫ����������ʵ�ʱ�򣬵��ø÷���
	public Response doAction(Request req) {
			try {
				//1.����һ��Socket
				s = new Socket(ip,Integer.parseInt(port));
				
				//2.�Ȱ����ݷ���ȥ�����������
				oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(req);
				oos.flush();
				
				//3.��������Ϣ��������
				ois = new ObjectInputStream(s.getInputStream());
				res = (Response) ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally{
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			}
		//4.Ҫ���������Ļظ� ����
		return res;
	}

}
