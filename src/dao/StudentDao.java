package dao;
import entity.Student;

public interface StudentDao {
	/**
	 * �÷������ݸ�����ѧ�ź�����ӿ��������л��ĳ����������
	 * @param id ѧ��
	 * @param passwd ����
	 * @return ����ײ���������ƥ��Ŀ����򷵻ظÿ����������û��ƥ��Ŀ����򷵻�null��
	 */
	public Student getStudent(String id,String passwd);
	
	/**
	 * �÷������µײ������п����ĳɼ�
	 * @param id �������
	 * @param score �µĳɼ�
	 * @return ������³ɹ�����true�����򷵻�false
	 */
	public boolean updateScore(String id,int score);
}
