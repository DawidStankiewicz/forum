/**
 * Created by Dawid Stankiewicz on 28.07.2016
 */
package com.github.szczypioreg.forum.controller.form;

public class NewTopicForm {
	
	private String title;
	
	private String content;
	
	private int sectionId;

	public NewTopicForm() {}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	
}
