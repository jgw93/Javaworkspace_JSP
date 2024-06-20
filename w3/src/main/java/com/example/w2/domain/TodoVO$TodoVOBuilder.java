package com.example.w2.domain;


import java.time.LocalDate;

public class TodoVO$TodoVOBuilder {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;

    TodoVO$TodoVOBuilder() {
    }

    public TodoVO$TodoVOBuilder tno(Long tno) {
        this.tno = tno;
        return this;
    }

    public TodoVO$TodoVOBuilder title(String title) {
        this.title = title;
        return this;
    }

    public TodoVO$TodoVOBuilder dueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public TodoVO$TodoVOBuilder finished(boolean finished) {
        this.finished = finished;
        return this;
    }

    public TodoVO build() {
        return new TodoVO(this.tno, this.title, this.dueDate, this.finished);
    }

    public String toString() {
        return "TodoVO.TodoVOBuilder(tno=" + this.tno + ", title=" + this.title + ", dueDate=" + this.dueDate + ", finished=" + this.finished + ")";
    }
}
