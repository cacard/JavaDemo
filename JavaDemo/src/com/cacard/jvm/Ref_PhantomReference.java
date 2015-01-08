/**
 * 		��������/�������
 * 
 * 		- �������ٶ����ʱ���״��ڴ��б������
 * 		- ׼ȷ��˵�����ReferenceQueue��PhantomReference����ʹ�������塣
 * 
 * 		ע��
 * 		����������Լ���finalize()����ö���Ļ��չ��̱Ƚ����⡣��һ��GC�������Ϊ�ɻ��գ���Ϊ��finalize()�����Խ���F-Queue���ȴ�Finalizer�߳�ִ��finalize()����Ϻ���GCһ�Ρ�
 * 
 */

package com.cacard.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class Ref_PhantomReference {

	// �����ڲ������
	private static void test() {
		ReferenceQueue<CanFinalize> q = new ReferenceQueue<CanFinalize>();
		PhantomReference<CanFinalize> pr = new PhantomReference<CanFinalize>(new Ref_PhantomReference().new CanFinalize(), q); // ���´����ò���pr
		
		if(q.poll()==null){
			System.out.println("��δ�ͷš�");
		}else{
			System.out.println("�Ѿ������������Ѿ������ͷ�");
			return;
		}
		
		System.gc(); // ������GC�ͷŶ���
		
		try {
			System.out.println("->before");
			System.gc();
			Thread.sleep(1000);
			System.gc(); 
			Reference<? extends CanFinalize> r = q.remove(); //�����ȴ����ȵ��󣬴�queue�Ƴ������ء�
			//Reference<? extends CanFinalize> r = q.poll(); // ����������ѯ���ޣ������Ƴ������ء����򷵻�null��
			System.out.println("->after, and r:" + r); // �����֮�󣬿�ȷ�Ŷ����Ѿ���ȫ���ڴ������
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		test();
	}

	class CanFinalize {

		private int _1M = 1024 * 1024;
		private byte[] b = new byte[_1M * 100]; // ������GC��ʱ���

		@Override
		public void finalize() throws Throwable {
			super.finalize(); // ������룬��Ȼ�ͷ�ʧ��
			System.out.println("~()");
		}
	}

}
