package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import dao.PaperDao;
import dao.PaperDaoFromFile;
import dao.StudentDao;
import dao.StudentDaoFromFile;
import entity.Paper;
import entity.Question;
import entity.Request;
import entity.Response;
import entity.Student;

public class Controller {
	private StudentDao stuDao;
	private PaperDao papDao;
	private BufferedReader br;
	private String stuFile,papFile;
	
	public Controller(){
		try {
			br=new BufferedReader(new InputStreamReader(new FileInputStream("server.cfg")));
			stuFile=br.readLine();//�õ�ѧ����Ϣ�洢������
			papFile=br.readLine();//�õ�������Ϣ�洢������
		} catch (Exception e) {
			e.printStackTrace();
		}
		//�õ���ѧ����Ϣ��ŵĵ�ַ,��ʵ���˽ӿ�
		stuDao=new StudentDaoFromFile(new File(stuFile.split("=")[1]));
		papDao=new PaperDaoFromFile();
	}
	
	//����ȫ�������жϺ󽻸���ͬ�ķ���ȥִ��
	public Response execute(Request req){
		Response res=null;
		//���ж����������,������ͬ�ķ���ȥִ��
		switch (req.getType()) {
		case Request.LOGIN_REQ: res=login(req); break;
		case Request.STARTEXAM_REQ:res=startExam(req);break;
		case Request.FINISH_REQ:res=finish(req);break;
		default: break;
		}
		
		return res;
	}
	
	//�������÷�д�뵽ѧ����Ϣ��
	private Response finish(Request req){
		int score=(Integer)req.getParameter("score");
		String id=(String)req.getParameter("stuId");
	
		////�ʽӿ�������-�Ƿ񽫵÷�д�뵽�����ļ�
		boolean flag = stuDao.updateScore(id, score);
		
		Response res = new Response(Response.FINISH_RES);
		res.addParameter("do",flag);
		
		return res;
	}
	
	//��ʼ���ԣ��õ��Ծ�ķ���
	private Response startExam(Request req){
		//���Ծ������
		String subjectname=(String)req.getParameter("subjectname");
		
		String subjectPath=null;//���Ծ�ĵ�ַ
		BufferedReader br=null;
		try {
			br=new BufferedReader(new InputStreamReader(new FileInputStream("subject.cfg")));
			String str=null;
			while((str=br.readLine())!=null){
				//�����Ŀ��һ����������Ӧ���Ծ�
				if(str.split("=")[0].equals(subjectname)){
					subjectPath = str.split("=")[1];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		//�ʽӿ�������-�Ծ�
		Paper p = papDao.getPaper(subjectname,subjectPath);
		
		Response res = new Response(Response.STARTEXAM_RES);
		res.addParameter("examPaper",p);
		
		
		return res;
	}
	
	//��¼��һЩ����
	private Response login(Request req){
		String id=(String)req.getParameter("studentId");
		String passwd=(String)req.getParameter("studentPasswd");
		
		//�ʽӿ�������-ѧ��
		Student s = stuDao.getStudent(id, passwd);
		
		Response res=new Response(Response.LOGIN_RES);
		res.addParameter("stu",s);
		
		return res;
	}
	
	

}

