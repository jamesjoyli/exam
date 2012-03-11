package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import client.Action;
import entity.Paper;
import entity.Question;
import entity.Request;
import entity.Response;
import entity.Student;

public class ExamMainFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel studentName = null;//��ʾѧ��������Label
	private JLabel studentID = null;//��ʾѧ��ID��Label
	private JLabel subject = null;//��ʾ��Ŀ���Ƶ�Label
	private JTextArea jTextArea = null;//��ʾ������ı���
	private JRadioButton aRadio = null;//Aѡ�ť
	private JRadioButton bRadio = null;//Bѡ�ť
	private JRadioButton cRadio = null;//Cѡ�ť
	private JRadioButton dRadio = null;//Dѡ�ť
	private JButton previous = null;//��һ�ⰴť
	private JButton next = null;//��һ�ⰴť
	private JButton handIn = null;//����ť
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JLabel remainQuestions = null;//��ʾʣ����������Label
	private JLabel remainTime = null;//��ʾʣ��ʱ���Label
	private Student student;//�μӿ��ԵĿ���
	private Paper paper;//�����Ծ� 
	
	private Action action;
	/**
	 * This is the default constructor
	 */
	public ExamMainFrame(Student stu,Paper paper) {
		super();
		action = new Action();
		this.student=stu;
		this.paper=paper;
		initialize();
		addEventHandler();
	}
	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setBounds(new Rectangle(14, 75, 738, 269));
		}
		return jTextArea;
	}

	/**
	 * This method initializes aRadio	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getARadio() {
		if (aRadio == null) {
			aRadio = new JRadioButton();
			aRadio.setPreferredSize(new Dimension(35, 30));
			aRadio.setSize(new Dimension(50, 25));
			aRadio.setLocation(new Point(262, 367));
			aRadio.setText("A");
		}
		return aRadio;
	}

	/**
	 * This method initializes bRadio	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getBRadio() {
		if (bRadio == null) {
			bRadio = new JRadioButton();
			bRadio.setPreferredSize(new Dimension(35, 30));
			bRadio.setSize(new Dimension(50, 25));
			bRadio.setLocation(new Point(322, 367));
			bRadio.setText("B");
		}
		return bRadio;
	}

	/**
	 * This method initializes cRadio	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getCRadio() {
		if (cRadio == null) {
			cRadio = new JRadioButton();
			cRadio.setText("C");
			cRadio.setLocation(new Point(382, 367));
			cRadio.setSize(new Dimension(50, 25));
		}
		return cRadio;
	}

	/**
	 * This method initializes dRadio	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getDRadio() {
		if (dRadio == null) {
			dRadio = new JRadioButton();
			dRadio.setText("D");
			dRadio.setLocation(new Point(442, 367));
			dRadio.setSize(new Dimension(50, 25));
		}
		return dRadio;
	}

	/**
	 * This method initializes previous	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPrevious() {
		if (previous == null) {
			previous = new JButton();
			previous.setText("<<��һ��");
			previous.setLocation(new Point(220, 412));
			previous.setSize(new Dimension(90, 35));
		}
		return previous;
	}

	/**
	 * This method initializes next	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNext() {
		if (next == null) {
			next = new JButton();
			next.setText("��һ��>>");
			next.setLocation(new Point(350, 412));
			next.setSize(new Dimension(90, 35));
		}
		return next;
	}

	/**
	 * This method initializes handIn	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getHandIn() {
		if (handIn == null) {
			handIn = new JButton();
			handIn.setText("����");
			handIn.setSize(new Dimension(90, 35));
			handIn.setLocation(new Point(470, 412));
		}
		return handIn;
	}
	
	/**
	 * Ϊͼ���������¼��ķ���
	 */
	public void addEventHandler(){
		handIn.addActionListener(this);
		previous.addActionListener(this);
		next.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) {
				int op=JOptionPane.showConfirmDialog(
					ExamMainFrame.this,
					"ȷ��Ҫ�˳�������?","ȷ���˳�",JOptionPane.YES_NO_OPTION);
				if(op==JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
		
		aRadio.addActionListener(this);
		bRadio.addActionListener(this);
		cRadio.addActionListener(this);
		dRadio.addActionListener(this);
	}
	
	public void showMe(){
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
		//����һ���µ��߳�--����ʱ
		new TimeRefreshThread().start();
	}

	/**
	 * ����ʵ��ActionListener��ʵ��actionPerformed����
	 */
	
	int count=0; //�������� ���
	
	public void actionPerformed(ActionEvent e) {
		String str=e.getActionCommand();
		if(str.equals("����")){
			submitPaper();

		}else if(str.equals("<<��һ��")){
			//code5:�����һ���л����� (5%)
			if(count==0){
				//����ǵ�һ�⣬��ôҪ��ʾ��
				JOptionPane.showMessageDialog(this,"�Ѿ��ǵ�һ��");
			}else{
				count--;
				//�ı������ĿҪ�ı�,ˢ�µ�
				getJTextArea().setText(
						(count+1)+"."+paper.getAllQuestions().get(count).toString()
				);
				//ʣ�����Ŀ��
				remainQuestions.setText(paper.getQuestionNumber()-count+"");
				
				//ʹÿ����ť�ÿ�,����ѡ���Ĵ�������
				aRadio.setSelected(false);
				bRadio.setSelected(false);
				cRadio.setSelected(false);
				dRadio.setSelected(false);
				if(paper.getAnswers()[count]=='A'){
					aRadio.setSelected(true);
				} else if(paper.getAnswers()[count]=='B'){
					bRadio.setSelected(true);
				} else if(paper.getAnswers()[count]=='C'){
					cRadio.setSelected(true);
				} else if(paper.getAnswers()[count]=='D'){
					dRadio.setSelected(true);
				}
			}
			
		}else if(str.equals("��һ��>>")){
			//code6:�����һ���л����� (5%)
			if(count==9){
				JOptionPane.showMessageDialog(this,"�Ѿ������һ��");
			}else{
				count++;
				getJTextArea().setText(
						(count+1)+"."+paper.getAllQuestions().get(count).toString()
				);
				
				remainQuestions.setText(paper.getQuestionNumber()-count+"");
			
				//ʹÿ����ť�ÿ�,����ѡ���Ĵ�������
				aRadio.setSelected(false);
				bRadio.setSelected(false);
				cRadio.setSelected(false);
				dRadio.setSelected(false);
				if(paper.getAnswers()[count]=='A'){
					aRadio.setSelected(true);
				} else if(paper.getAnswers()[count]=='B'){
					bRadio.setSelected(true);
				} else if(paper.getAnswers()[count]=='C'){
					cRadio.setSelected(true);
				} else if(paper.getAnswers()[count]=='D'){
					dRadio.setSelected(true);
				}
			}
			
			
		}else if(str.equals("A")){
			//ÿ�ε����ť,Ҫ��������ÿ�,��Ϊֻ��ѡȡһ����
			paper.getAnswers()[count]='A';
			bRadio.setSelected(false);
			cRadio.setSelected(false);
			dRadio.setSelected(false);
		}else if(str.equals("B")){
			paper.getAnswers()[count]='B';
			aRadio.setSelected(false);
			cRadio.setSelected(false);
			dRadio.setSelected(false);
		}else if(str.equals("C")){
			paper.getAnswers()[count]='C';
			aRadio.setSelected(false);
			bRadio.setSelected(false);
			dRadio.setSelected(false);
		}else if(str.equals("D")){
			paper.getAnswers()[count]='D';
			aRadio.setSelected(false);
			bRadio.setSelected(false);
			cRadio.setSelected(false);
		}
	}
	
	/**
	 * ��ɽ�����
	 *
	 */
	private void submitPaper(){
		//code 4:��ɿͻ��˽�����
		
		int score = paper.getScore();//���ÿ�������ַ���,�õ����÷�
		
		Request req = new Request(Request.FINISH_REQ);
		req.addParameter("score",score);
		req.addParameter("stuId",student.getId());
		Response res = action.doAction(req);
		
		boolean flag =(Boolean)res.getParameter("do");
		
		if(flag){
			//�ڿͻ�����ʾ�³ɼ�
			JOptionPane.showMessageDialog(ExamMainFrame.this,"�����յĳɼ�Ϊ:"+score);
			System.exit(0);
				
		}else{
			JOptionPane.showMessageDialog(ExamMainFrame.this,"�ύʧ�ܣ����Ժ����ύ");
		}
	}
	
	
	/**
	 * �ڲ��࣬����ˢ�¿���ʣ��ʱ�䣬ÿ��ˢ��һ�Ρ�
	 */
	class TimeRefreshThread extends Thread{
		public void run() {
			long allTime = paper.getLimitTime();//��
			while (allTime >= 0) {
				try {
					allTime--;
					Thread.sleep(1000);
					remainTime.setText(allTime/60/60+":"+allTime/60
							%60+":"+allTime%60);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	


	/**
	 * ��ʼ���������÷�����ɶԽ���ĳ�ʼ�������������е���ʾ�Ĳ������ݳ�ʼ����
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(775, 515);
		this.setContentPane(getJContentPane());
		this.setTitle("���ڿƼ���׼������ϵͳ������--"+student.getName()+"--"+paper.getSubject());
		
		studentName.setText(student.getName());
		studentID.setText(student.getId()+"");
		subject.setText(paper.getSubject());
		
		//����ʣ����������ʼֵ
		remainQuestions.setText(paper.getQuestionNumber()+"");
		
		//����ʣ��ʱ���ʼֵ �Լ� ��ɫ
		remainTime.setText(paper.getLimitTime()/60/60+":"+paper.getLimitTime()/60
				%60+":"+paper.getLimitTime()%60);
		remainTime.setForeground(Color.red);
		
		
		//���ó�ʼ�ĵ�һ��������Ŀ �Լ� ��Ŀ���������ɫ
		jTextArea.setText(
			"1."+paper.getAllQuestions().get(0).toString()
		);
		jTextArea.setFont(new Font("����",Font.BOLD,20));
		jTextArea.setForeground(Color.pink);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			remainTime = new JLabel();
			remainTime.setBounds(new Rectangle(644, 393, 106, 36));
			remainTime.setText("");
			remainQuestions = new JLabel();
			remainQuestions.setBounds(new Rectangle(25, 396, 107, 35));
			remainQuestions.setText("");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(643, 372, 98, 21));
			jLabel8.setText("ʣ��ʱ�䣺");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(24, 373, 94, 23));
			jLabel7.setText("ʣ�����⣺");
			subject = new JLabel();
			subject.setBounds(new Rectangle(531, 46, 85, 19));
			subject.setText("");
			studentID = new JLabel();
			studentID.setBounds(new Rectangle(378, 47, 79, 18));
			studentID.setText("");
			studentName = new JLabel();
			studentName.setBounds(new Rectangle(226, 45, 80, 19));
			studentName.setText("");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(150, 45, 77, 19));
			jLabel3.setText("����������");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(314, 46, 65, 19));
			jLabel2.setText("������ţ�");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(468, 46, 64, 19));
			jLabel1.setText("���Կ�Ŀ:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(172, 4, 415, 33));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 24));
			jLabel.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel.setText("welcome to tarena exam center");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(studentName, null);
			jContentPane.add(studentID, null);
			jContentPane.add(subject, null);
			jContentPane.add(getJTextArea(), null);
			jContentPane.add(getARadio(), null);
			jContentPane.add(getBRadio(), null);
			jContentPane.add(getCRadio(), null);
			jContentPane.add(getDRadio(), null);
			jContentPane.add(getPrevious(), null);
			jContentPane.add(getNext(), null);
			jContentPane.add(getHandIn(), null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(remainQuestions, null);
			jContentPane.add(remainTime, null);
		}
		return jContentPane;
	}

}  
