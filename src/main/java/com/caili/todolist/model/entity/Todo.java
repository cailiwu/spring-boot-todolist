package com.caili.todolist.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author cai-li
 */
@Entity
@Table
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String task;

    @Column
    Integer status;

    @Column
    String createTime;

    @Column
    String updateTime;
}
