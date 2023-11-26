package org.example;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Task {
    private int id;
    private String title;
    private String content;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime finishedDate;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                ", finishedDate=" + finishedDate +
                '}';
    }
}
