package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.Action;
import entity.Paper;
import entity.Request;
import entity.Response;
import entity.Student;

/**
 * ��ӭ����
 */
public class WelcomeFrame extends JFrame implements ActionListener {
	private JLabel title;
	private JLabel text;
	private JButton ok, cancel;
	private Student stu;

	private JComboBox box;
	private Action action;

	/**
	 * ���췽��
	 * 
	 * @param stu
	 *            �ɹ���¼�Ŀ���
	 */
	public WelcomeFrame(Student stu) {
		super("��ӭ�μӿ���");

		action = new Action();
		this.stu = stu;

		title = new JLabel(stu.getName() + ",��ӭ����JAVA����ϵͳ");
		// �������δ����,��ô�ɼ�Ϊ-1
		if (stu.getScore() == -1) {
			text = new JLabel("��ѡ���Կ�Ŀ��");
			box = new JComboBox(new String[] { "corejava", "c++", "JavaWeb",
					"EJB" });

		} else {
			text = new JLabel("���Ѿ�����˿��ԣ������ظ�����!\n���ĵ÷��ǣ�" + stu.getScore());
		}

		ok = new JButton("��ʼ����");
		cancel = new JButton("�˳�");
		init();
		addEventHandler();
	}

	private void init() {
		JPanel northPanel = new JPanel();
		northPanel.add(title);
		JPanel centerPanel = new JPanel();
		centerPanel.add(text);
		JPanel southPanel = new JPanel();

		// û���ԵĻ�������Ҫ���ؿ�ʼ ���԰�ť �� ����ѡ���
		if (stu.getScore() == -1) {
			southPanel.add(ok);
			centerPanel.add(box);
		}

		southPanel.add(cancel);

		this.add(southPanel, BorderLayout.SOUTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(northPanel, BorderLayout.NORTH);
	}

	private void addEventHandler() {
		ok.addActionListener(this);
		cancel.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("��ʼ����")) {
			startExam();

		} else if (e.getActionCommand().equals("�˳�")) {
			System.exit(0);
		}
	}

	/**
	 * ��ɿͻ��˿�ʼ���Թ���
	 * 
	 */
	private void startExam() {
		// code3:��ɿͻ��˿�ʼ���ԵĹ��� (18%)

		// �õ�ѡ�����������ݵ����� --���Ծ������
		String s = (String) box.getSelectedItem();

		Request req = new Request(Request.STARTEXAM_REQ);
		req.addParameter("subjectname", s);

		Response res = action.doAction(req);
		Paper p = (Paper) res.getParameter("examPaper");

		// �յ�һ���Ծ��ж��Ƿ�Ϊ��
		if (p == null) {
			JOptionPane.showMessageDialog(WelcomeFrame.this, "�Ծ���ش������Ժ����ԣ�");
		} else {
			// �� ѧ�� �� �Ծ� һ�𴫵����Խ���
			new ExamMainFrame(stu, p).showMe();
			WelcomeFrame.this.dispose();
		}
	}

	public void showMe() {
		this.setSize(400, 300);
		this.setResizable(false);
		this.setLocation(400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

}
