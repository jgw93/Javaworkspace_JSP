package VO;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVO {
    private long tno;
    private String title;
    private String content;
    private long count;
    private LocalDate createDate;
}
