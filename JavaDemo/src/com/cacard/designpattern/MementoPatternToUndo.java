/**
 * 备忘录模式实现 Undo/Redo
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

/* 编辑器 */
class Editor {
	private String content;
	private EditorMemento mem = new EditorMemento();

	// 保存状态
	public void saveState() {
		mem.addState(content);
	}

	// 撤销更改
	public void undo() {
		String c = mem.undo();
		if (c != null) {
			this.content = c;
		}
	}

	// 重做
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

/* 状态备忘录 */
class EditorMemento {
	private ArrayDeque<String> undoQueue = new ArrayDeque<String>(); // Undo 队列
	private ArrayDeque<String> redoQueue = new ArrayDeque<String>(); // Redo 队列

	// 添加状态
	public void addState(String content) {
		undoQueue.addLast(content);
		redoQueue.clear(); // 清除redoQueue
	}

	// undo
	// 从undoQueue中删除并放到redoQueue中
	public String undo() {

		String last = undoQueue.pollLast();
		if (last != null) {
			redoQueue.addLast(last);
		}

		return undoQueue.peekLast();
	}

	// redo
	// 从redo队头获取一个元素，移到undoQueue中
	public String redo() {
		if (redoQueue.isEmpty()) {
			return null;
		}

		String str = redoQueue.pollFirst();
		redoQueue.addLast(str);

		return str;
	}
}