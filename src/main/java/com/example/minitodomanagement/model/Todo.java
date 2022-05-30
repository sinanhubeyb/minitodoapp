package com.example.minitodomanagement.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.AccessType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "todos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Todo {
    @Id
    @GeneratedValue
    Long id;

    String username;
    String description;
    Date targetDate;

    public Todo(String user, String desc, Date targetDate, boolean isDone) {
        super();
        this.username = user;
        this.description = desc;
        this.targetDate = targetDate;
    }
}
