package com.fastcampus.jcjboard.paging;

public class PerPage {
    private int page; // 보여줄 페이지
    private int perPageNum; // 페이지당 보여줄  페이지수

    // Sql 구문에서 limit 사용해야 하는데 시작부분에 필요한 값을 반환.
    public int getPageStart() {
        return (this.page -1) * this.perPageNum;
    }

    // 페이지당 10개!
    public PerPage() {
        this.page = 1;
        this.perPageNum=3;
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
        if(perPageNum <= 0 || perPageNum > 30) {
            this.perPageNum = 3;
        }else {
            this.perPageNum = perPageNum;
        }
    }

    @Override
    public String toString() {
        return "PerPage 클래스 [page=" + page + ", perPageNum=" + perPageNum + "]";
    }




}
