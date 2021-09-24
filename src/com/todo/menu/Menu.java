package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println("__________________________________\n");
        System.out.println("         <Command Help>");
        System.out.println("  'add' -> 항목 추가");
        System.out.println("  'del' -> 항목 삭제");
        System.out.println("  'edit' -> 항목 수정|");
        System.out.println("  'ls' -> 전체 목록");
        System.out.println("  'find A' -> A 포함한 제목, 내용 찾기");
        System.out.println("  'find_cate A' -> A 포함한 카테고리 찾기");
        System.out.println("  'ls_name_asc' -> 제목순 정렬");
        System.out.println("  'ls_name_desc' -> 이름순 정렬");
        System.out.println("  'ls_date' -> 날짜별 정렬");
        System.out.println("  'ls_date_desc' -> 날짜별 역정렬");
        System.out.println("  'ls_cate' -> 카테고리별 정렬");
        System.out.println("  'exit' -> 프로그램 종료");
        System.out.println("__________________________________");
    }
    public static void prompt() {
    	System.out.print("\nCommand('help') -> ");
    }
}
