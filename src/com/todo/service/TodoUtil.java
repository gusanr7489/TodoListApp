package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("<�׸� �߰�>\n"
				+ "ī�װ� -> ");
		
		category = sc.next();
		
		sc.nextLine();
		
		System.out.print("���� -> ");
		title = sc.next().trim();
		
		if (list.isDuplicate(title)) {
			System.out.println("�׸� ������ �ߺ��˴ϴ�!");
			return;
		}
		sc.nextLine();
		
		System.out.print("���� -> ");
		//trim = �� �� ���� ���� �޼ҵ�
		desc = sc.nextLine().trim();
		
		System.out.print("������ -> ");
		due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<�׸� ����>\n"
				+ "������ �׸��� ��ȣ�� �Է��ϼ��� -> ");
		
		TodoItem target = l.getItem(sc.nextInt()-1);
		
		if(target==null) {
			System.out.println("�׸��� ã�� ���� �����ϴ�.");
			return;
		}
		
		System.out.println(target.toString());
		System.out.print("������ �����Ͻðڽ��ϱ�? (y/n) -> ");
		String do_delete = sc.next();
		
		if(do_delete.equals("y")) {
			l.deleteItem(target);
			System.out.println("�����Ǿ����ϴ�.");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("<�׸� ����>\n"
				+ "������ �׸��� ��ȣ�� �Է��ϼ��� -> ");
		
		TodoItem target = l.getItem(sc.nextInt()-1);
		
		System.out.print("�� ī�װ�? -> ");
		String new_category = sc.next().trim();
		
		System.out.print("�� ����? -> ");
		String new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title) && !target.getTitle().contains(new_title)) {
			System.out.println("�׸� ������ �ߺ��˴ϴ�!");
			return;
		}
		sc.nextLine();
		
		System.out.print("�� ����? -> ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("�� ������? -> ");
		String new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
		l.editItem(target, t);
		System.out.println("�׸��� �����Ǿ����ϴ�.");
		
	}
	
	public static void find(String target, TodoList l) {
		int count=0;
		for(TodoItem myitem : l.getList()) {
			if(myitem.getTitle().contains(target) || myitem.getDesc().contains(target)) {
				System.out.println( (l.indexOf(myitem) + 1) + ". " + myitem.toString());
				count++;
			}
		}
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
	}
	
	public static void findCategory(String target, TodoList l) {
		int count=0;
		for(TodoItem myitem : l.getList()) {
			if(myitem.getCategory().contains(target)) {
				System.out.println( (l.indexOf(myitem)+1) + ". " + myitem.toString());
				count++;
			}
		}
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
	}
	
	public static void listCategory(TodoList l) {
		String st="";
		int count=0;
		for(TodoItem myitem: l.getList()) {
			if(!st.contains(myitem.getCategory())) {
				if(count==0)
					st = myitem.getCategory();
				else 
					st = st + " / " +myitem.getCategory();
				count++;
			}
		}
		System.out.println(st + "\n" 
				+ "�� " + count + "���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.");
	}

	public static void listAll(TodoList l) {
		System.out.println("<��ü ���, �� " + l.getList().size() + "��>");
		int count=0;
		for (TodoItem item : l.getList()) {
			count++;
			System.out.println(count+ ". " +item.toString());
		}
	}
	
	public static void saveList(TodoList l, String fileName) {
		//try-catch for file open to write
		try {
			Writer fw = new FileWriter(fileName);
			for(TodoItem item: l.getList()) {
				fw.write(item.toSaveString());
			}
			fw.close();
			System.out.println("���Ͽ� ���������� ����Ǿ����ϴ�.");
		}	catch (FileNotFoundException e){
			e.printStackTrace();
		}	catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void loadList(TodoList l, String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String item="";
			while((item=br.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(item,"##");
				l.addItem(new TodoItem(st.nextToken(),st.nextToken(),st.nextToken(), st.nextToken(), st.nextToken()));
			}
			br.close();
			System.out.println("������ ���������� �������ϴ�.");
		} catch (FileNotFoundException e) {
			System.out.println("����� ������ �����ϴ�.");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
