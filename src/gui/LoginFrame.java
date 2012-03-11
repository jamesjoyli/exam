package gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.Action;
import entity.Request;
import entity.Response;
import entity.Student;

public class LoginFrame extends JFrame implements ActionListener{
	private JLabel title;
	private JLabel idLabel,passwdLabel;
	private JTextField idField;
	private JPasswordField passwdField;
	private JButton ok,cancel;
	private Action action ;
	
	public LoginFrame(){
		super("������¼");
		
		action=new Action();
		title=new JLabel("������¼");
		idLabel=new JLabel("ѧ��:");
		passwdLabel=new JLabel("����:");
		idField=new JTextField(15);
		passwdField=new JPasswordField(15);
		ok=new JButton("��¼");
		cancel=new JButton("ȡ��");
		
		init();
		addEventHandler();
	}
	
	/**
	 * �÷������ý��沼��
	 *
	 */
	private void init(){
		JPanel northPanel=new JPanel();
		northPanel.add(title);
		JPanel centerPanel=new JPanel();
		centerPanel.add(idLabel);
		centerPanel.add(idField);
		centerPanel.add(passwdLabel);
		centerPanel.add(passwdField);
		JPanel southPanel=new JPanel();
		southPanel.add(ok);
		southPanel.add(cancel);
		
		this.add(southPanel,BorderLayout.SOUTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.add(northPanel,BorderLayout.NORTH);
	}
	
	/**
	 * �÷���Ϊͼ���������¼�����
	 *
	 */
	private void addEventHandler(){
		ok.addActionListener(this);
		cancel.addActionListener(this);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("��¼")){
			login();
			
		}else if(e.getActionCommand().equals("ȡ��")){
			System.exit(0);
		}
		
	}
	
	private void login(){
		//		�ж��������ID�������Ƿ�Ϊ�գ���Ϊ���򷵻ء�
		if(idField.getText().trim().equals("") || new String(passwdField.getPassword()).trim().equals("")){
			JOptionPane.showMessageDialog(this,"ID �����벻��Ϊ�գ�");
			return;
		}
		String id=idField.getText().trim();
		String passwd=new String(passwdField.getPassword()).trim();
		
		//code1:��ɿͻ��˵�¼���� (16%)
		Request req = new Request(Request.LOGIN_REQ);
		req.addParameter("studentId",id);
		req.addParameter("studentPasswd",passwd);
		Response res = action.doAction(req);
		//�õ�����
		Student s =(Student)res.getParameter("stu");
		
		if(s==null){
			JOptionPane.showMessageDialog
			(LoginFrame.this,"�˻����������");
			
		}else{
			new WelcomeFrame(s).showMe();
			LoginFrame.this.dispose();
			
		}
	}
	
	/**
	 * �÷������ý����С���ɼ��Լ�Ĭ�Ϲرղ�����
	 *
	 */
	public void showMe(){
		this.setSize(240,180);
		this.setResizable(false);
		this.setLocation(400,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
}
