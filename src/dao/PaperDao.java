package dao;
import entity.Paper;

public interface PaperDao {
	/**
	 * ���ݸ����Ŀ�Ŀ�����ظÿ�Ŀ��һ���Ծ�
	 * @param subject ��Ŀ
	 * @return ������ʳɹ��򷵻�Paper���󣬷��򷵻�null��
	 */
	public Paper getPaper(String subjectname,String subjectpath);
}
