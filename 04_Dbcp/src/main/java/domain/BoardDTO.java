package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BoardDTO {
	private int board_no;
	private String title;
	private String content;
	private Date modified_date;
	private Date created_date;  // 실무에서는 Date 클래스보단 String으로 처리함
}
