package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import entity.Student;

public class StudentDaoFromFile implements StudentDao {
	private File file;// �����file ��Ϊ "student.dat"

	public StudentDaoFromFile(File f) {
		this.file = f;
	}

	public Student getStudent(String id, String passwd) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			// ��ȡ�����ļ�
			String str = null;
			while ((str = br.readLine()) != null) {
				String s[] = str.split(":");
				String i = s[0]; // ѧ���ı��id
				String p = s[2]; // ѧ��������
				// �������ı�ź��������ļ����ҵõ�
				if (i.equals(id + "") && p.equals(passwd)) {
					Student stu = new Student(s[1], Integer.parseInt(s[0]),
							s[2], Integer.parseInt(s[3]));

					// �����˵�ǰѧ�����󣬲�return
					return stu;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// û���ҵ���Ӧ���˻������룬�򷵻ؿ�
		return null;
	}

	public boolean updateScore(String id, int score) {
		// Code12 ��ɲ�updateScore������ʵ�� (15%)
		List<String> list = new ArrayList<String>();

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;

		try {
			// �޸ķ�����һ������
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			String str = null;
			while ((str = br.readLine()) != null) {
				String s[] = str.split(":");
				if (s[0].equals(id + "")) {
					// ��Ҫ�޸ĵ���һ���������޸�
					list.add(s[0] + ":" + s[1] + ":" + s[2] + ":" + score);
				} else {
					list.add(str);
				}
			}

			// �Ӽ��Ͻ����ݶ�����
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);

			for (int i = 0; i < list.size(); i++) {
				bw.write(list.get(i));
				bw.newLine();
				bw.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		// �޸ĳɹ���true
		return true;
	}

}
