package dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import entity.Paper;
import entity.Question;

public class PaperDaoFromFile implements PaperDao{
	
	public Paper getPaper(String subjectname,String subjectpath) {
		BufferedReader br1=null;
		
		Paper paper = new Paper();//����һ�ݿ���
		paper.setSubject(subjectname);//�����Ծ������
		paper.setLimitTime(3600);//���Ծ�����ʱ�䣺һСʱ
		
		try {
			//��ȡ�ı����ɿ��Ծ�  subjectpath��Ϊ ��corejava.exm��
			br1=new BufferedReader(new InputStreamReader(new FileInputStream(subjectpath)));
			String str = null;
			
			while(true){
				//�ȶ�һ�У�����ǿյģ���ʾ��Ŀû��,��ôֱ������ѭ��
				if((str=br1.readLine())==null){
					break;
				}
				
				//������ǿյģ���ô����һ��������
				Question question = new Question();
				//���ø���Ŀ�ı���
				question.setTitle(str);
				
				//ѭ��   ������Ŀ������
				for(int i=0;i<4;i++){
					str=br1.readLine();
					if(str.indexOf("<T>")==0){
						str=str.substring(3);//�׵�ǰ���<T>
						question.setAnswer((char)('A'+i));//����ȷ��д�뵽��Ŀ�� 
					}
					//�����ݼӵ�����Ŀ�ļ�����ȥ�洢
					question.getItems().add(str);
				}
				question.setScore(10);//�Լ����ⶨ���˸�����
				//����Ŀ���뵽�Ծ���
				paper.getAllQuestions().add(question);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
		
		//�����Ծ� return
		return paper;
	}
	

}
