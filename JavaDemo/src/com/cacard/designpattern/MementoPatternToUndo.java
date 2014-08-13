/**
 * ����¼ģʽʵ�� Undo/Redo
 */

package com.cacard.designpattern;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class MementoPatternToUndo {

	public static void main(String[] args) {
		Editor editor = new Editor();
		editor.setContent("1");
		editor.saveState();
		editor.setContent("2");
		editor.saveState();
		editor.setContent("3");
		editor.saveState();

		editor.undo();
		System.out.println(editor.getContent());
		editor.redo();
		System.out.println(editor.getContent());
	}

}

/* �༭�� */
class Editor {
	private String content;
	private EditorMemento mem = new EditorMemento();

	// ����״̬
	public void saveState() {
		mem.addState(content);
	}

	// ��������
	public void undo() {
		String c = mem.undo();
		if (c != null) {
			this.content = c;
		}
	}

	// ����
	public void redo() {
		String c = mem.redo();
		if (c != null) {
			this.content = c;
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

/* ״̬����¼ */
class EditorMemento {
	private ArrayDeque<String> undoQueue = new ArrayDeque<String>(); // Undo ����
	private ArrayDeque<String> redoQueue = new ArrayDeque<String>(); // Redo ����

	// ���״̬
	public void addState(String content) {
		undoQueue.addLast(content);
		redoQueue.clear(); // ���redoQueue
	}

	// undo
	// ��undoQueue��ɾ�����ŵ�redoQueue��
	public String undo() {

		String last = undoQueue.pollLast();
		if (last != null) {
			redoQueue.addLast(last);
		}

		return undoQueue.peekLast();
	}

	// redo
	// ��redo��ͷ��ȡһ��Ԫ�أ��Ƶ�undoQueue��
	public String redo() {
		if (redoQueue.isEmpty()) {
			return null;
		}

		String str = redoQueue.pollFirst();
		redoQueue.addLast(str);

		return str;
	}
}