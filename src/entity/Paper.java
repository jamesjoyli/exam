package entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Paper��Ķ������һ���Ծ���������Question�����⣩
 */
public class Paper implements java.io.Serializable{
	private String subject;//���Կ�Ŀ����
	private int limitTime;//��������ʱ�䣨��λ:���ӣ�
	private List<Question> allQuestions=new ArrayList<Question>();//�����������
	private char[] answers = new char[10];//ѧ����ѡ��,����Ĭ�ϴ�С10
	public static final long serialVersionUID=56701230908070210L;
	
	public Paper(){
		
	}
	
	public Paper(String subject ,int limitTime,List<Question> allQuestions){
		this.subject=subject;
		this.limitTime=limitTime;
		this.allQuestions=allQuestions;
		answers=new char[getQuestionNumber()];
	}
	
	public Paper(String subject,int limitTime){
		this(subject,limitTime,new ArrayList<Question>());
	}
	
	public boolean  addQuestion(Question q){
		return allQuestions.add(q);
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<Question> getAllQuestions() {
		return allQuestions;
	}

	public void setAllQuestions(List<Question> allQuestions) {
		this.allQuestions = allQuestions;
	}

	public char[] getAnswers() {
		return answers;
	}

	public void setAnswers(char[] answers) {
		this.answers = answers;
	}
	
	/**
	 * ���������Ծ������������
	 * @return
	 */
	public int getQuestionNumber(){
		return allQuestions.size();
	}
	
	/**
	 * ���õ�index������Ŀ���ѡ��Ĵ𰸡�
	 * @param index
	 * @param answer
	 */
	public void setAnswerAt(int index,char answer){
		answers[index]=answer;
	}
	
	
	
	public int getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(int limitTime) {
		this.limitTime = limitTime;
	}

	//�俼�����ʱ��,����toString�������ͻᰴ�ո��Ǻ�ķ������
	public String toString(){
		StringBuffer sb=new StringBuffer(subject+"��������\n");
		for(int i=0;i<allQuestions.size();i++){
			sb.append((i+1)+","+allQuestions.get(i));
		}
		return sb.toString();
	}
	
	/**
	 * �÷������ݿ���ѡ��Ĵ𰸣�����ÿ����ĵ÷֣������ĸ�������
	 * @return
	 */
	public int getScore(){
		int score = 0;
		for(int i=0;i<getAllQuestions().size();i++){
			Question q = getAllQuestions().get(i);
			//�Ƚ��Ծ������ȷ�𰸺�ѧ��ѡ��Ĵ�
			if(q.getAnswer()==getAnswers()[i]){
				score=score+10;
			}
		}
		
		return score;
	}

}
