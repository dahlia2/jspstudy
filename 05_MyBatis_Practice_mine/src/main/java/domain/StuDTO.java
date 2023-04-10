package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuDTO {
	private int stuNo;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int ave;
	private int grade;
	
}
