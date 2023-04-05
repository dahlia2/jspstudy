package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BbsDTO {
	private int bbsNo;  // 매퍼 설정 mapUnderscoreToCamelCase 으로 카멜형식 적용
	private String title;
	private String content;
	private String modifiedDate;
	private String createdDate;

}
