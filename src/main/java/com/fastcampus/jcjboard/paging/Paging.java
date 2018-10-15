package com.fastcampus.jcjboard.paging;

public class Paging {
//    private PerPage perPage; // page, perPageNum 을 가지고 있음

    private int totalCount; // 전체 게시글 수
    private int startPage; // 게시글 번호에 따른 (보여지는)페이지의 시작 번호
    private int endPage; // 게시글 번호에 따른 (보여지는)페이지의 마지막 번호
    private boolean prev; // 이전 버튼을 누를 수 있는 경우/없는 경우 분류를 위함
    private boolean next;

    private int displayPageNum = 10; // 화면 하단에 보여지는 페이지의 개수
    private int tempEndPage;

    ///////////////////////////////////////////////////////////////////////////////////////////////

    // PerPage class
    final int PER_PAGE_NUM = 10;

    private int page; // 보여줄 페이지
    private int perPageNum; // 페이지당 보여줄  페이지수

    public Paging() {
        this.page = 1;
        this.perPageNum = PER_PAGE_NUM;
    }



    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;

        calcData(); // 전체 필드 변수들 세팅 : 전체 게시글 수의 setter가 호출될 때 전체 세팅되도록 함
    }

    private void calcData() { // 전체 필드 변수 값들을 계산하는 메서드

        endPage = (int) (Math.ceil(getPage() / (double) displayPageNum) * displayPageNum);

        startPage = (endPage - displayPageNum) + 1;

        int tempEndPage = (int) (Math.ceil(totalCount / (double) getPerPageNum()));
        this.tempEndPage = tempEndPage;

        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        prev = startPage == 1 ? false : true; // 1페이지면 이전 누를 수 없게 false
        next = endPage * getPerPageNum() >= totalCount ? false : true;

    }

    // getter setter

    public int getTempEndPage() {
        return tempEndPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getDisplayPageNum() {
        return displayPageNum;
    }

    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }


    //////////////////////////////////////////////////////////////////////
    // PerPage class

    // Sql 구문에서 limit 사용해야 하는데 시작부분에 필요한 값을 반환.
    public int getPageStart() {
        return (this.page -1) * this.perPageNum;
    }

    // 페이지당 10개!
//    public PerPage() {
//        this.page = 1;
//        this.perPageNum=PER_PAGE_NUM;
//    }
    public void basicPage() {
        this.page =1;
        this.perPageNum = PER_PAGE_NUM;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        if(page < 0) {
            this.page=1;
        }else {
            this.page = page;
        }
    }

    public int getPerPageNum() {
        return this.perPageNum;
    }

    // 왜 100보다 크다는 하는거지?
    // 1~10 페이지에 들어가는 게시글의 수가 100개가 최대라 그런가??
    public void setPerPageNum(int perPageNum) {
        if(perPageNum <= 0 || perPageNum > PER_PAGE_NUM*10) {
            this.perPageNum = PER_PAGE_NUM;
        }else {
            this.perPageNum = perPageNum;
        }
    }

    @Override
    public String toString() {
        return "Pagin 클래스 [page=" + page + ", perPageNum=" + perPageNum + "]";
    }


}
