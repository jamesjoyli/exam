package entity;

import java.util.ArrayList;
import java.util.List;
/**
 * Question�������һ�����⡣������ɺ��ĸ�ѡ���Լ���ȷ��
 * @author new
 *
 */
public class Question implements java.io.Serializable{
	public static final long serialVersionUID=9080302019871213L;
	
	private String title;//���
	private int score;//��ֵ
	private List<String> items=new ArrayList<String>();//����ѡ��
	private char answer;//��ȷ��
	
	public Question(){
		
	}

	
	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public char getAnswer() {
		return answer;
	}

	public List<String> getItems() {
		return items;
	}

	public String getTitle() {
		return title;
	}
	
	public void setAnswer(char answer) {
		this.answer = answer;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	//Ҫ�õ���Ŀ����,ֱ�������������󼴿���ʾ����,��Ϊ�Ѿ�������toString����
	public String toString(){
		StringBuffer sb=new StringBuffer(title+"\n");
		for(int i=0;i<items.size();i++){
			sb.append((char)(i+'A')+","+items.get(i)+"\n");
		}
		return sb.toString();
	}
}
