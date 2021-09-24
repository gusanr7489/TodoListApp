package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}

	public void addItem(TodoItem t) {
		list.add(t);
	}

	public void deleteItem(TodoItem t) {
		list.remove(t);
	}

	public void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	public void listAll() {
		for (TodoItem myitem : list) {
			System.out.println(myitem.toString());
		}
	}
	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}
	
	public TodoItem getItem(int index) {
		return list.get(index);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
	
	public void find(String target) {
		int count=0;
		for(TodoItem myitem : list) {
			if(myitem.getTitle().contains(target) || myitem.getDesc().contains(target)) {
				System.out.println( (list.indexOf(myitem) + 1) + ". " + myitem.toString());
				count++;
			}
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public void findCategory(String target) {
		int count=0;
		for(TodoItem myitem : list) {
			if(myitem.getCategory().contains(target)) {
				System.out.println( (list.indexOf(myitem)+1) + ". " + myitem.toString());
				count++;
			}
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public void listCategory() {
		String st="";
		int count=0;
		for(TodoItem myitem: list) {
			if(!st.contains(myitem.getCategory())) {
				if(count==0)
					st = myitem.getCategory();
				else 
					st = st + " / " +myitem.getCategory();
				count++;
			}
		}
		System.out.println(st + "\n" 
				+ "총 " + count + "개의 카테고리가 등록되어 있습니다.");
	}
}
