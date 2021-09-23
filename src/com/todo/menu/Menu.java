package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println("_____________________________\n");
        System.out.println("<Command Help>");
        System.out.println("'add' -> 항목 추가");
        System.out.println("'del' -> 항목 삭제");
        System.out.println("'edit' -> 항목 수정|");
        System.out.println("'ls' -> 전체 목록");
        System.out.println("'ls_name_asc' -> 제목순 정렬");
        System.out.println("'ls_name_desc' -> 이름순 정렬");
        System.out.println("'ls_date' -> 날짜별 정렬");
        System.out.println("'exit' -> 프로그램 종료");
        System.out.println("_____________________________");
    }
    public static void prompt() {
    	System.out.print("\nCommand('help') -> ");
    }
}
